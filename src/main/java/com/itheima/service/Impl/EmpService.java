package com.itheima.service.Impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dto.EmpQueryDTO;
import com.itheima.dto.PasswordQueryDTO;
import com.itheima.entity.Emp;
import com.itheima.entity.EmpExpr;
import com.itheima.exception.BusinessException;
import com.itheima.mapper.EmpMapper;
import com.itheima.service.IEmpExprService;
import com.itheima.service.IEmpService;
import com.itheima.tool.AliYunOssStorageTool;
import com.itheima.tool.CurrentHolder;
import com.itheima.vo.PageR;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 员工管理业务类
 */
@Slf4j
@Service
public class EmpService implements IEmpService {

    // 注入员工Mapper
    @Resource
    private EmpMapper empMapper;

    // 注入员工工作经历业务类
    @Resource
    private IEmpExprService empExprService;

    // 注入Oss工具类
    @Resource
    private AliYunOssStorageTool aliYunOssStorageTool;


    /**
     * 分页查询员工信息
     *
     * @param empQueryDTO
     * @return
     */
    @Override
    public PageR pageSelect(EmpQueryDTO empQueryDTO) {
        // 开启分页
        PageHelper.startPage(empQueryDTO.getPage(), empQueryDTO.getPageSize());
        // 2. 正常查询，返回值就用最普通的 List 接收（解耦）
        List<Emp> list = empMapper.pageSelect(empQueryDTO);
        // 3. 用 PageInfo 包装 List（PageInfo 会自动去读取 ThreadLocal 里的总数等信息）
        PageInfo<Emp> empPageInfo = new PageInfo<>(list);
        // 将总记录数和分页查询结果封装到PageR对象中并返回
        return PageR.builder()
                .total(empPageInfo.getTotal())
                .rows(empPageInfo.getList())
                .build();
    }

    /**
     * 批量删除员工信息
     *
     * @param ids
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByIds(List<Integer> ids) {

        if (ids != null && !ids.isEmpty()) {
            // 获取删除员工头像信息
            List<String> ListImage = empMapper.selectImgById(ids);
            ListImage.stream()
                    .filter(StrUtil::isNotBlank)
                    .filter(aliYunOssStorageTool::isOssFile)
                    .forEach(image -> {
                        try {
                            aliYunOssStorageTool.deleteFile(image);
                            log.info("头像删除成功!");
                        } catch (Exception e) {
                            log.error("删除头像失败,image:{}", image, e);
                            throw new BusinessException("头像信息删除失败");
                        }
                    });
            ;
            // 批量删除员工信息
            empMapper.deleteByIds(ids);
            // 同时批量删除员工工作经历信息
            empExprService.deleteByIds(ids);
        }
    }

    /**
     * 新增员工信息
     *
     * @param emp
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addEmp(Emp emp) {
        // 设置默认密码
        emp.setPassword("123456");
        // 设置创建时间
        emp.setCreateTime(LocalDateTime.now());
        // 设置更新时间
        emp.setUpdateTime(LocalDateTime.now());

        // 如果用户没有上传头像则设置默认头像
        if (ObjectUtil.isEmpty(emp.getImage())) {
            emp.setImage("https://storage.360buyimg.com/i.imageUpload/beadb5e434303031353234393235353235373736_big.jpg");
        }

        log.info("===>用户信息{},用户的手机号{}", emp.getUsername(), emp.getPhone());
        // 判断员工用户名和手机号是否重复
        Emp empInfo = empMapper.selectByUsernameOrPhone(emp.getUsername(), emp.getPhone());
        log.info("===>查询到重复的用户信息数{}", empInfo);
        if (empInfo != null) {
            // 命中行用户名与本次输入相同 -> 用户名冲入,否则是手机号冲突
            if (emp.getUsername().equals(empInfo.getUsername())) {
                throw new BusinessException("用户名重复,请重新输入!");
            }
            throw new BusinessException("手机号重复,请重新输入!");
        }
        // 添加员工信息
        empMapper.addEmp(emp);

        log.info("添加的员工ID为：{}", emp.getId());
        // 获取员工工作经历
        List<EmpExpr> exprList = emp.getExprList();
        // 遍历员工工作经历，设置员工Id
        if (CollUtil.isNotEmpty(exprList)) {
            exprList.forEach(expr -> expr.setEmpId(emp.getId()));
            empExprService.addEmpExpr(exprList);
        }
    }

    /**
     * 根据员工Id查询员工信息
     *
     * @param id
     * @return
     */
    @Override
    public Emp selectListById(Integer id) {
        if (ObjectUtil.isEmpty(id))
            return null;
        return empMapper.selectById(id);
    }

    /**
     * 更新员工信息
     *
     * @param emp
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateEmp(Emp emp) {
        // 用户更新头像时删除Bucket中原始头像
        Integer empId = emp.getId();
        List<Integer> ids = List.of(empId);
        List<String> ImageInfo = empMapper.selectImgById(ids);
        ImageInfo.stream()
                .filter(StrUtil::isNotBlank)
                .filter(aliYunOssStorageTool::isOssFile)
                .forEach(image -> {
                    try {
                        aliYunOssStorageTool.deleteFile(image);
                    } catch (Exception e) {
                        log.info("删除头像失败,image:{} ", image, e);
                        throw new BusinessException("原始头像删除失败!");
                    }
                });
        // 更新修改时间
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);
        // 更新员工工作经历
        List<EmpExpr> exprList = emp.getExprList();

        if (CollUtil.isNotEmpty(exprList)) {
            exprList.forEach(empExpr -> empExpr.setEmpId(emp.getId()));
        }
        log.info("更新的员工工作经历为：{}", exprList);
        empExprService.updateById(emp.getId(), exprList);
    }

    /**
     * 查询所有员工信息
     *
     * @return
     */
    @Override
    public List<Emp> selectAllList() {
        return empMapper.selectAllList();
    }

    /**
     * 修改当前登录用户的密码
     *
     * @param passwordQueryDTO
     */
    @Override
    public void updatePassword(PasswordQueryDTO passwordQueryDTO) {
        String oldPassword = passwordQueryDTO.getOldPassword();
        String newPassword = passwordQueryDTO.getNewPassword();

        // 校验原密码和新密码是否为空
        if (StrUtil.isBlank(oldPassword) || StrUtil.isBlank(newPassword)) {
            throw new BusinessException("请输入原密码和新密码!");
        }
        // 校验新密码长度是否满足6-20位要求
        if (newPassword.length() < 6 || newPassword.length() > 20) {
            throw new BusinessException("新密码长度必须为6-20位!");
        }
        // 从ThreadLocal中获取当前登录用户的Id(由拦截器解析Token后存入)
        Integer empId = CurrentHolder.getCurrentId();
        // 查询当前登录用户的原始密码
        String dbPassword = empMapper.selectPasswordById(empId);
        // 校验原密码是否正确
        if (!StrUtil.equals(oldPassword, dbPassword)) {
            throw new BusinessException("原密码错误");
        }
        // 修改密码,同时刷新修改时间
        empMapper.updatePassword(empId, newPassword, LocalDateTime.now());
    }
}
