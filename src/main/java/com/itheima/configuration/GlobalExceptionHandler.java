package com.itheima.configuration;

import com.itheima.exception.BusinessException;
import com.itheima.vo.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 全局通用异常处理方法,用于处理项目中所有的异常
     */
    @ExceptionHandler(Exception.class)
    public R exceptionHandlerMethod(Exception e) {
        log.error("出现异常了: {}", e.toString());
        return R.error("系统维护中,请稍后再试");
    }

    /**
     * 业务异常处理方法,将业务提示信息返回给前端
     */
    @ExceptionHandler(value = BusinessException.class)
    public R businessExceptionHandlerMethod(BusinessException e) {
        log.error("出现业务异常: {}", e.getMessage());
        return R.error(e.getMessage());
    }
}
