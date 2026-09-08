package com.itheima.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 登录查询 DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginQueryDTO {

    private String username; // 用户名
    private String password; // 密码
}
