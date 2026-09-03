package com.itheima.controller;

import com.itheima.service.IReportService;
import com.itheima.vo.R;
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
     * @return
     */
    @GetMapping("/empGenderData")
    public R EmpGenderStatistics() {
        List<Map<String,Object>> genderStatistics = reportService.empGenderStatistics();
        return R.ok(genderStatistics);
    }
}
