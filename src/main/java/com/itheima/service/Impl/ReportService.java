package com.itheima.service.Impl;

import com.itheima.mapper.ReportMapper;
import com.itheima.service.IReportService;
import com.itheima.vo.JobStatistics;
import com.itheima.vo.StudentStatistics;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 员工信息统计 - 服务层
 */
@Slf4j
@Service
public class ReportService implements IReportService {

    @Resource
    private ReportMapper reportMapper;

    /**
     * 员工性别统计
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> empGenderStatistics() {
        return reportMapper.empGenderStatistics();
    }

    /**
     * 员工职位人数统计
     *
     * @return
     */
    @Override
    public JobStatistics empJobStatistics() {
        List<Map<String,Object>> jobStatisticsList = reportMapper.empJobStatistics();
        List<String> jobList = jobStatisticsList.stream().map(map -> map.get("name").toString()).collect(Collectors.toList());
        List<Integer> dataList = jobStatisticsList.stream().map(map -> Integer.valueOf(map.get("count").toString())).collect(Collectors.toList());
        log.info("==>jobList列表 {}" , jobList);
        log.info("==>dataList列表 {}" , dataList);
        return JobStatistics.builder().jobList(jobList).dataList(dataList).build();
    }

    /**
     * 学员学历统计
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> stuDegreeDataStatistics() {
        return reportMapper.stuDegreeDataStatistics();
    }

    /**
     * 学生人数统计
     *
     * @return
     */
    @Override
    public StudentStatistics stuCountStatistics() {
        List<Map<String,Object>> stuStatisticsList = reportMapper.stuCountStatistics();
        List<String> clazzList = stuStatisticsList.stream().map(map -> map.get("name").toString()).collect(Collectors.toList());
        List<Integer> dataList = stuStatisticsList.stream().map(map -> Integer.valueOf(map.get("count").toString())).collect(Collectors.toList());
        return StudentStatistics.builder().clazzList(clazzList).dataList(dataList).build();
    }

}
