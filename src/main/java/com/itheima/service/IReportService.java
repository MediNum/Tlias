package com.itheima.service;

import com.itheima.vo.JobStatistics;

import java.util.List;
import java.util.Map;

/**
 * 员工信息统计 - 接口
 */
public interface IReportService {

    /**
     * 员工性别统计
     * @return
     */
    List<Map<String,Object>> empGenderStatistics();

    /**
     * 员工职位人数统计
     * @return
     */
    JobStatistics empJobStatistics();
}
