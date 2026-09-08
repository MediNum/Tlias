package com.itheima.interceptor;

import cn.hutool.core.util.StrUtil;
import com.itheima.tool.JwtTokenTool;
import io.jsonwebtoken.Claims;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component // 注入到IOC容器中
public class JsonWebTokenInterceptor implements HandlerInterceptor {

    @Resource
    private JwtTokenTool jwtTokenTool;

    /**
     * 拦截器前方法逻辑
     *
     * @param httpServletRequest 本次请求的请求对象
     * @param httpServletResponse 本次响应的响应对象
     * @param handler true:放行  false:不放行
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        String requestURI = httpServletRequest.getRequestURI();

        // 进行令牌校验(没有令牌,直接响应401)
        String jsonWebToken = httpServletRequest.getHeader("token");
        if (StrUtil.isBlank(jsonWebToken)) {
            log.info("拦截到本次访问路径为{}的请求,执行操作(不放行 原因:没有Token令牌)", requestURI);
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 响应401状态码
            return false; // 不放行
        }

        // 如果if判断没有成功,说明token不是null值也不是"",则进行令牌的解析
        try {
            Claims claims = jwtTokenTool.parseJsonWebToken(jsonWebToken);
            log.info("拦截到本次访问路径为 {} 的请求,执行操作(放行,原因:Token 令牌存在,合法)", requestURI);
            // 如果parseJsonWebToken 没有抛出任何运行期异常,在说明令牌存在并且合法,放行
            return true;
        } catch (Exception e) {
            log.info("拦截到本次访问路径为 {}  的请求,执行操作(不放行, 原因:Token 非法,过期)" ,requestURI);
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 响应401状态码
            return false;
        }
    }
}
