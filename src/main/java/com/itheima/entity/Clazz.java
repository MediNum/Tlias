package com.itheima.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 班级信息 - 实体类
 * @author HC
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Clazz {
    private Integer id; // id
    private String name; // 班级名称
    private String room; // 班级教师
    private LocalDate beginDate; // 开课时间
    private LocalDate endDate; // 结课时间
    private Integer masterId; // 班主任Id
    private Integer subject; // 学科
    private LocalDateTime createTime; // 创建时间
    private LocalDateTime updateTime; // 修改时间

    private String masterName; // 班主任名称
    private String status; //班级状态 - 未开班 , 在读 , 已结课
}
