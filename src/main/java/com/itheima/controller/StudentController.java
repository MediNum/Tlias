package com.itheima.controller;

import com.itheima.dto.StudentQueryDTO;
import com.itheima.entity.Stu;
import com.itheima.service.IStuService;
import com.itheima.vo.PageR;
import com.itheima.vo.R;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
     *
     * @param studentQueryDTO
     * @return
     */
    @GetMapping
    public R StudentList(StudentQueryDTO studentQueryDTO) {
        log.info("==>查询的学生信息 {} ", studentQueryDTO);
        PageR pageR = stuService.studentList(studentQueryDTO);
        return R.ok(pageR);
    }

    /**
     * 删除学员信息
     *
     * @param ids
     * @return
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable List<Integer> ids) {
        log.info("==> 删除学员信息 {}", ids);
        stuService.deleteByIds(ids);
        return R.ok();
    }

    /**
     * 新增学员信息
     *
     * @param stu
     * @return
     */
    @PostMapping
    public R addStudent(@RequestBody Stu stu) {
        log.info("==>新增的学员的信息为 {}", stu);
        stuService.addStudentInfo(stu);
        return R.ok();
    }

    /**
     * 根据ID查询学员信息
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R selectById(@PathVariable("id") Integer id) {
        log.info("==>需要查询的学生Id {} ", id);
        Stu stuInfo = stuService.selectById(id);
        return R.ok(stuInfo);
    }

    /**
     * 修改学员信息
     *
     * @param stu
     * @return
     */
    @PutMapping
    public R updateStu(@RequestBody Stu stu) {
        log.info("==>修改的学员的信息为 {} " , stu);
        stuService.updateStuInfo(stu);
        return R.ok();
    }

}
