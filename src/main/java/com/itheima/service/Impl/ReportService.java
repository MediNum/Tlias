package com.itheima.service.Impl;

import com.itheima.mapper.ReportMapper;
import com.itheima.service.IReportService;
import com.itheima.vo.JobStatistics;
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
        List<Map<String,Object>> genderList = reportMapper.empGenderStatistics();
        return genderList;
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

}
