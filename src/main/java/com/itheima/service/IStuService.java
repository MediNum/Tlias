package com.itheima.service;

import com.itheima.dto.StudentQueryDTO;
import com.itheima.vo.PageR;

public interface IStuService {

    /**
     * 学生列表查询
     * @param studentQueryDTO
     * @return
     */
    PageR studentList(StudentQueryDTO studentQueryDTO);
}
