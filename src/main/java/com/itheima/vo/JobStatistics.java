package com.itheima.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 职位统计结果-实体类
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobStatistics {

    private List jobList; //职位名称列表
    private List dataList; // 职位人数列表
}
