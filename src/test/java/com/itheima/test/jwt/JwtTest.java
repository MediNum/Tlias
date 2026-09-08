package com.itheima.test.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {

    @Test
    @DisplayName("JwtToken生成")
    public void createJwtToken() {
        // 指定密钥[生成部分3需要密钥]
        String secret = "itcast";
        // 指定共享数据(基于Map的方式)
        Map<String, Object> m =new HashMap<>();
        m.put("id",1);
        m.put("username","ergou");
        // 指定令牌的过期时间,当前时间 + 过期时间毫秒值 = 具体过期时间的毫秒值
        long exprTime = System.currentTimeMillis() + 1000 * 60;

        // 生成Jwt令牌
        String jwtToken = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, secret)  // 指定部分3生成的签名算法HS256并且密钥为secret
                .addClaims(m)  // 添加Jwt令牌保存数据(部分2)
                .setExpiration(new Date(exprTime))  // 声明令牌的过期时间
                .compact();

        // 打印令牌数据
        System.out.println(jwtToken);
    }

    @Test
    @DisplayName("JwtToken解析")
    public void parseJwtToken() {
        // 指定密钥
        String secret = "itcast";
        // 声明Jwt令牌

        String jwtToken = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJlcmdvdSIsImV4cCI6MTc4ODY4MzY1NX0.Aogmv-ZiL01wcOKCkQH-UhRBX1Y7CMgs6k8Y0tKtx7M";
        // 对令牌进行解析
        Claims claims = Jwts.parser()
                .setSigningKey(secret) // 指定密钥
                .parseClaimsJws(jwtToken)
                .getBody();
        System.out.println(claims);
    }
}
