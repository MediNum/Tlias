package com.itheima.filter;

import cn.hutool.core.util.StrUtil;
import com.itheima.tool.JwtTokenTool;
import io.jsonwebtoken.Claims;
import jakarta.annotation.Resource;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * JsonWebToken令牌校验过滤器
 */
@Slf4j
@WebFilter(urlPatterns = "/*")
public class JsonWebTokenFilter implements Filter {

    @Resource
    private JwtTokenTool jwtTokenTool;

    /**
     * 过滤器的核心和方法-执行时机(当拦截到了对应的请求的时候,默认执行该方法)
     *
     * @param servletRequest  表示本次请求的请求对象(可以基于该对象获取本次请求的相关参数)
     * @param servletResponse 表示本次响应的响应对象(可以基于该对象声明本次响应给客户端的相关信息)
     * @param filterChain     拦截器链(用于放行请求)
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        // 将方法原始的的servletRequest,servletResponse向下转型为HttpServletRequest,HttpServletResponse 默认要使用Filter
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        // 获取本次拦截到的请求的实际访问资源路径 判断是否为登录请求
        String requestURI = httpServletRequest.getRequestURI();
        if (requestURI.equals("/login")) {
            log.info("拦截到本次访问路径为{}的登录请求,执行操作(放行)", requestURI);
            filterChain.doFilter(httpServletRequest,httpServletResponse);
            return; // 拦截器的doFilter放行之后,目标方法执行完成后,会重新回到doFilter方法继续向下执行(return 会停止继续向下执行)
        }

        // 如果if判断没有成功说明本次不是登录请求,则表示要进行令牌校验(没有令牌,直接响应401)
        String jsonWebToken = httpServletRequest.getHeader("token");
        if (StrUtil.isBlank(jsonWebToken)) {
            log.info("拦截到本次访问路径为 {} 的请求,执行操作(不放行 原因: 没有Token令牌)", requestURI);
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 响应401状态码
            return; // 直接响应401即可,不需要向下执行其他逻辑
        }

        // 如果if判断没有成功说明token不是null值也不是"",则进行令牌的解析
        try {
            Claims claims = jwtTokenTool.parseJsonWebToken(jsonWebToken);
            log.info("拦截到本次访问路径为{}的请求,执行操作(放行 原因:Token令牌存在、合法)", requestURI);
            // 如果parseJsonWebToken没有抛出任何运行异常,则说明令牌存在并且合法,放行
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return; // 目标资源执行完成后直接结束doFilter方法运行,不再向下继续执行
        } catch (Exception e) {
            log.info("拦截到本次访问路径为{}的请求,执行操作(不放行 原因:Token非法、过期)", requestURI);
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 响应401状态码
        }
    }
}
