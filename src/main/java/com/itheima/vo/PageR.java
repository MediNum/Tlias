package com.itheima.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 封装分页查询 - 实体类
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageR {

    private Long total; // 总记录数
    private List<?> rows; // 当前页数据列表
}
