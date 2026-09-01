package com.itheima.service;

import com.itheima.dto.StudentQueryDTO;
import com.itheima.entity.Stu;
import com.itheima.vo.PageR;

import java.util.List;

public interface IStuService {

    /**
     * 学生列表查询
     * @param studentQueryDTO
     * @return
     */
    PageR studentList(StudentQueryDTO studentQueryDTO);

    /**
     * 根据id删除学生
     * @param ids
     */
    void deleteByIds(List<Integer> ids);

    /**
     *新增员工信息
     * @param stu
     */
    void addStudentInfo(Stu stu);
}
