package com.itheima.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 登录 响应类
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Login {
    private Integer id; // 用户id
    private String username; // 用户名
    private String name; // 用户姓名
    private String token; // token令牌
}
