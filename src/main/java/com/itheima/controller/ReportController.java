package com.itheima.controller;

import com.itheima.service.IReportService;
import com.itheima.vo.JobStatistics;
import com.itheima.vo.R;
import com.itheima.vo.StudentStatistics;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


/**
 * 员工信息统计 - 控制层
 */
@Slf4j
@RestController
@RequestMapping("/report")
public class ReportController {

    @Resource
    private IReportService reportService;

    /**
     * 员工性别统计
     *
     * @return
     */
    @GetMapping("/empGenderData")
    public R EmpGenderStatistics() {
        List<Map<String, Object>> genderStatistics = reportService.empGenderStatistics();
        return R.ok(genderStatistics);
    }

    /**
     *
     * 员工职位人数统计
     *
     * @return
     */
    @GetMapping("/empJobData")
    public R EmpJpbStatistics() {
        JobStatistics jobStatistics = reportService.empJobStatistics();
        return R.ok(jobStatistics);
    }

    /**
     * 学员学历统计
     *
     * @return
     */
    @GetMapping("/studentDegreeData")
    public R stuDegreeDataStatistics() {
        List<Map<String, Object>> DegreeStatistics = reportService.stuDegreeDataStatistics();
        return R.ok(DegreeStatistics);
    }

    /**
     * 学生人数统计
     *
     * @return
     */
    @GetMapping("/studentCountData")
    public R StuCountStatistics() {
        StudentStatistics studentStatistics = reportService.stuCountStatistics();
        return R.ok(studentStatistics);
    }
}
