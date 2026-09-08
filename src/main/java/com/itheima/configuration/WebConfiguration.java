package com.itheima.configuration;

import com.itheima.interceptor.JsonWebTokenInterceptor;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 自定义Web参数类 Spring当前都是基于默认的一些内容完成创建、使用。本质上提供了一个接口、可以基于实现接口来自定义一些Web相关的参数【拦截器】
 */
@Configuration // 声明注解表明当前类是一个参数类(当Spring扫描到的时候会默认按照类中声明的信息进行使用)
public class WebConfiguration implements WebMvcConfigurer {

    @Resource
    private JsonWebTokenInterceptor jsonWebTokenInterceptor;

    // 主动实现addInterceptors方法,将自定义的拦截器注册到Spring中
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 基于拦截器注册器完成自定义拦截器的注册并且注册的时候要声明拦截哪些路径
        registry.addInterceptor(jsonWebTokenInterceptor).addPathPatterns("/**").excludePathPatterns("/login");
    }
}
