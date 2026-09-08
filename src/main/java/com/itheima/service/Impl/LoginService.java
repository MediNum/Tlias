package com.itheima.service.Impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.itheima.dto.LoginQueryDTO;
import com.itheima.entity.Emp;
import com.itheima.exception.BusinessException;
import com.itheima.mapper.LoginMapper;
import com.itheima.service.ILoginService;
import com.itheima.tool.JwtTokenTool;
import com.itheima.vo.Login;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * 登录服务层
 */
@Slf4j
@Service
public class LoginService implements ILoginService {

    // 注入员工服务层接口实现类
    @Resource
    private LoginMapper loginMapper;

    // 注入Jwt令牌生成工具
    @Resource
    private JwtTokenTool jwtTokenTool;
    /**
     * 用户登录
     *
     * @param loginQueryDTO
     */
    @Override
    public Login login(LoginQueryDTO loginQueryDTO) {
        // 判断输入用户名或密码是否规范
        if (StrUtil.isBlank(loginQueryDTO.getUsername())||StrUtil.isBlank(loginQueryDTO.getPassword())) {
            throw new BusinessException("请输入用户名或密码!");
        }
        Emp emp = loginMapper.login(loginQueryDTO);
        // 判断是否查询到用户登录信息
        if (ObjectUtil.isNull(emp)) {
            throw new BusinessException("用户名或密码错误!");
        }
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("id" , emp.getId());
        claims.put("username" , emp.getUsername());
        String JwtToken = jwtTokenTool.createJsonWebToken(claims);
        return Login.builder().id(emp.getId()).username(emp.getUsername()).name(emp.getName()).token(JwtToken).build();
    }
}
