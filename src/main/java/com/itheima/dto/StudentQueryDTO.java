package com.itheima.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 学生查询条件DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentQueryDTO {

    private String name; // 姓名
    private Integer degree; // 学历
    private Integer clazzId; // 班级ID
    private Integer page; // 当前页
    private Integer pageSize; // 每页大小
}
