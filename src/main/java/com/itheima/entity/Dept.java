package com.itheima.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 *  部门信息- 实体类
 *  @author
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Dept {

    private Integer id; // id
    private String name; // 部门名称
    private LocalDateTime createTime; // 创建时间
    private LocalDateTime updateTime; // 更新时间
}
