package com.itheima.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 学生人数统计结果 - 实体类
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentStatistics {
    private List clazzList; // 班级列表
    private List dataList; // 每个班级的人数列表
}
