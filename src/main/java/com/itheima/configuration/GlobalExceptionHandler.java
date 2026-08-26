package com.itheima.configuration;

import com.itheima.vo.R;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GlobalExceptionHandler {

    /**
     * 全局通用异常处理方法,用于处理项目中所有的异常
     */
    public R exceptionHandlerMethod(Exception e) {
        log.error("出现异常了: {}", e.toString());
        return R.error("系统维护中,请稍后再试");
    }
}
