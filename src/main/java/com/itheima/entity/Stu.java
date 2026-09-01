package com.itheima.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * 学生实体类
 * @author HC
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stu {

    private Integer id; //
    private String name; // 姓名
    private String no; // 学号
    private Integer gender; // 性别 , 1: 男 , 2 : 女
    private String phone; // 电话
    private String idCard; // 身份证
    private Integer isCollege; // 是否来自于院校, 1: 是, 0: 否
    private String address; // 地址
    private Integer degree; // 最高学历, 1: 初中, 2: 高中 , 3: 大专 , 4: 本科 , 5: 硕士 , 6: 博士
    private LocalDate graduationDate; // 毕业日期
    private Integer clazzId; // 班级ID
    private Short violationCount; // 违纪次数
    private Short violationScore; // 违纪积分
    private LocalDate createTime; // 创建时间
    private LocalDate updateTime;   // 更新时间

    private String clazzName; // 班级名称
}
