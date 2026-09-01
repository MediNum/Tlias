package com.itheima.controller;

import com.itheima.dto.StudentQueryDTO;
import com.itheima.service.IStuService;
import com.itheima.vo.PageR;
import com.itheima.vo.R;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 学生管理控制器
 */
@Slf4j
@RestController
@RequestMapping("/students")
public class StudentController {

    @Resource
    private IStuService stuService;


    /**
     * 学生列表查询
     * @param studentQueryDTO
     * @return
     */
    @GetMapping
    public R StudentList(StudentQueryDTO studentQueryDTO) {
        log.info("==>查询的学生信息 {} " , studentQueryDTO);
        PageR pageR = stuService.studentList(studentQueryDTO);
        return R.ok(pageR);
    }

}
