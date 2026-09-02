package com.itheima.service.Impl;

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
}
