package com.itheima.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * 员工工作经历
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmpExpr {
    private Integer id; // 主键 id
    private Integer empId; // 员工Id
    private LocalDate begin; // 入职时间
    private LocalDate end; // 离职时间
    private String company; // 公司名称
    private String job; // 职位
}
