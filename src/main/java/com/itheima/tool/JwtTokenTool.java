package com.itheima.tool;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/**
 * Jwt 令牌生成,解析
 */
@Component
public class JwtTokenTool {
    // 加密密钥 基于application.yml 文件中的内容注入
    @Value("${jwt.secret}")
    private String secret;

    // 过期时间 基于application.yml 文件中的内容注入
    @Value("${jwt.exprired}")
    private Long expired;

    /**
     * 生成Jwt令牌
     *
     * @param map 共享数据
     * @return Jwt令牌
     */
    public String createJsonWebToken(Map<String, Object> map) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, secret)
                .addClaims(map)
                .setExpiration(new Date(System.currentTimeMillis() + expired))
                .compact();
    }

    /**
     * 解析Jwt令牌
     *
     * @param jwtToken 要解析的Jwt令牌数据
     * @return 解析成功后的部分2数据组成的Claims对象(Map集合)
     */
    public Claims parseJsonWebToken(String jwtToken) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(jwtToken)
                .getBody();
    }
}
