package com.itheima.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dto.StudentQueryDTO;
import com.itheima.entity.Stu;
import com.itheima.mapper.StuMapper;
import com.itheima.service.IStuService;
import com.itheima.vo.PageR;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 学生业务逻辑实现类
 */
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
}
