package com.itheima.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * 查询条件DTO
 */
@Data
@Builder
public class ClazzQueryDTO {

    private String name; // 班级名称
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate begin; // 课程开始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate end; // 课程结束时间
    private Integer page; // 页码
    private Integer pageSize; // 每页条数
}
