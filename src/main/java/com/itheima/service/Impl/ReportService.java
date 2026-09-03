package com.itheima.service.Impl;

import com.itheima.mapper.ReportMapper;
import com.itheima.service.IReportService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
}
