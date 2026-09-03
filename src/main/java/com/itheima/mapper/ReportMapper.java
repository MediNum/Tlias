package com.itheima.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 员工信息统计 - 持久层
 */
@Mapper
public interface ReportMapper {

    /**
     * 员工性别统计
     * 
     * @return
     */
    List<Map<String, Object>> empGenderStatistics();

    /**
     * 员工职位人数统计(部门自动跟随dept表变化)
     *
     * @return
     */
    List<Map<String, Object>> empJobStatistics();
}
