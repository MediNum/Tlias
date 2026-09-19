package com.itheima.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 修改密码 DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PasswordQueryDTO {

    private String oldPassword; // 原密码
    private String newPassword; // 新密码
}
