package com.itheima.controller;

import com.itheima.dto.LoginQueryDTO;
import com.itheima.service.ILoginService;
import com.itheima.vo.Login;
import com.itheima.vo.R;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 登录控制层
 */
@Slf4j
@RestController
@RequestMapping("/login")
public class LoginController {

    @Resource
    private ILoginService loginService;

    /**
     * 员工登录
     *
     * @param loginQueryDTO
     * @return
     */
    @PostMapping
    public R login(@RequestBody LoginQueryDTO loginQueryDTO) {
        log.info("获取到的用户信息 {}" ,loginQueryDTO);
        Login loginInfo = loginService.login(loginQueryDTO);
        log.info("==>查询到的用户信息 {}" ,loginInfo);
        return R.ok(loginInfo);
    }
}

