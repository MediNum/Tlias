package com.itheima.service.Impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dto.StudentQueryDTO;
import com.itheima.entity.Stu;
import com.itheima.exception.BusinessException;
import com.itheima.mapper.StuMapper;
import com.itheima.service.IStuService;
import com.itheima.vo.PageR;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

/**
 * 学生业务逻辑实现类
 */
@Slf4j
@Service
public class StuService implements IStuService {

    @Resource
    private StuMapper stuMapper;

    /**
     * 学生列表查询
     *
     * @param studentQueryDTO
     * @return
     */
    @Override
    public PageR studentList(StudentQueryDTO studentQueryDTO) {
        // 开启分页
        PageHelper.startPage(studentQueryDTO.getPage() , studentQueryDTO.getPageSize());
        // 正常查询,返回值就用List<Student>接收
        List<Stu> list = stuMapper.studentList(studentQueryDTO);
        // 3.用PageInfo包装list
        PageInfo<Stu> PageInfo = new PageInfo<>(list);
        // 将总记录数和分页查询结果封装到PageR对象中并返回
        return PageR.builder()
                .total(PageInfo.getTotal())
                .rows(PageInfo.getList())
                .build();
    }

    /**
     * 根据id删除学生
     *
     * @param ids
     */
    @Override
    public void deleteByIds(List<Integer> ids) {
        stuMapper.deleteByIds(ids);
    }

    /**
     * 新增学员信息
     *
     * @param stu
     */
    @Override
    public void addStudentInfo(Stu stu) {
        // 设置创建时间
        stu.setCreateTime(LocalDate.now());
        // 设置更新时间
        stu.setUpdateTime(LocalDate.now());
        // 设置默认班级ID为0
        stu.setClazzId(0);

        // 违纪次数.违纪扣分设置默认值
        if (stu.getViolationCount() == null) {
            stu.setViolationCount((short)0);
        }
        if (stu.getViolationScore() == null) {
            stu.setViolationScore((short)0);
        }

        log.info("==>获取到的用户名 {} " , stu.getNo());
        // 判断学号是否重复,重复则抛出业务异常
        Integer StuNum = stuMapper.selectStuNo(stu.getNo());
        if (StuNum > 0) {
            throw new BusinessException("学号已存在,请勿重复添加");
        }
        stuMapper.addStudentInfo(stu);
    }

    /**
     * 根据ID查询学员信息
     *
     * @param id
     * @return
     */
    @Override
    public Stu selectById(Integer id) {
        if (ObjectUtil.isEmpty(id))
            return null;
        return stuMapper.selectById(id);
    }

    /**
     * 修改学员信息
     *
     * @param stu
     */
    @Override
    public void updateStuInfo(Stu stu) {
        // 更新修改时间
        stu.setUpdateTime(LocalDate.now());
        stuMapper.updateStuInfo(stu);
    }

    /**
     * 学员违纪扣分
     *
     * @param id
     * @param scoreStr
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateStuScore(Integer id, String scoreStr) {
        if (ObjectUtil.isEmpty(id)) {
            throw new BusinessException("无法获取学员信息!");
        }
        // 判断1:是否为空/纯空格
        if (StrUtil.isBlank(scoreStr)) {
            throw new BusinessException("扣分不能为空");
        }
        // 判断2:是否为非数字(正则:只能是纯数字,不能含有字母/符号/小数点)
        if (!scoreStr.matches("\\d+")) {
            throw new BusinessException("扣分必须为正整数,不能包含字母或符号");
        }
        // 通过校验后在转换为数字,并作范围判断
        int score = Integer.parseInt(scoreStr);
        if (score > 0 && score <= 100) {
            stuMapper.updateStuCount(id);
            stuMapper.updateStuScore(id,score);
        } else {
            throw new BusinessException("请输入正确的违纪扣分");
        }
    }
}
