package com.itheima.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 日志查询条件 - DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class operateLogQueryDTO {

    private Integer page; // 页码
    private Integer pageSize; // 每页记录数

}
