package com.itheima.service;

import com.itheima.dto.LoginQueryDTO;
import com.itheima.vo.Login;

/**
 * 登录 - 接口
 */
public interface ILoginService {

    /**
     * 用户登录
     *
     * @param loginQueryDTO
     */
    Login login(LoginQueryDTO loginQueryDTO);
}
