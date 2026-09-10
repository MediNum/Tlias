package com.itheima.filter;

import jakarta.servlet.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * 自定义Filter过滤器
 *
 */
@Slf4j
// @WebFilter(urlPatterns = "/*") //配置过滤器要拦截的请求路径（ /* 表示拦截浏览器的所有请求 ）
public class MyFilter implements Filter {

    /**
     * 过滤器的初始化方法 - 执行时机(项目启动时,MyFilter自动初始化)
     *
     * @param filterConfig 过滤器的参数信息
     *
     * @throws ServletException Servlet 异常
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("MyFilter 开始进行初始化...");
    }

    /**
     * 过滤器的核心和方法 - 执行时机(当拦截到了对应的请求的时候,默认执行该方法)
     *
     * @param servletRequest  表示本次请求的请求对象(可以基于该对象获取本次请求的相关参数)
     * @param servletResponse 表示本次响应的响应对象(可以基于该对象声明本次响应给客户端的相关信息)
     * @param filterChain    拦截器链(用于放行请求)
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("MyFilter拦截到了本次请求...");
        // 放行请求到实际资源或者下一个拦截器
        filterChain.doFilter(servletRequest , servletResponse);
    }

    /**
     * 过滤器的销毁方法 - 执行时机(当项目关闭的时候)
     */
    @Override
    public void destroy() {
        log.info("MyFilter 开始进行销毁...");
    }
}
