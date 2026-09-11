package com.itheima.aspect;

import com.itheima.entity.OperateLog;
import com.itheima.mapper.OperateLogMapper;
import com.itheima.tool.CurrentHolder;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * 操作日志 - 切面类
 */
@Slf4j
@Aspect
@Component
public class OperateLogAspect {

    // 注入操作层日志到的持久层
    @Resource
    private OperateLogMapper operateLogMapper;

    // 切入点是Controller中左右标记了@OperateLog注解的方法
    @Pointcut("@annotation(com.itheima.annotation.OperateLog)")
    private void operateLogPointCut() {}


    @Around("operateLogPointCut()")
    public Object operateLogHandler(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // 记录原始方法执行前的时间点
        long start = System.currentTimeMillis();
        // 调用原始方法
        Object r = proceedingJoinPoint.proceed();
        // 记录原始方法执行后的时间点
        long end = System.currentTimeMillis();
        // 原始方法执行消耗时间
        long costTime = end - start;
        // 获取当前方法执行的时间点
        LocalDateTime operateTime = LocalDateTime.now();
        // 获取原始方法的参数信息,将原始方法中的信息转换为String 类型,方便后续使用
        Object[] args = proceedingJoinPoint.getArgs();
        String methodParams = Arrays.toString(args);
        // 获取原始方法的方法名
        String methodName = proceedingJoinPoint.getSignature().getName();
        // 获取原始方法所在类的类名
        String className = proceedingJoinPoint.getTarget().getClass().getName();
        // 从ThreadLocal中获取Interceptor拦截器中存入的员工Id
        Integer empId = CurrentHolder.getCurrentId();
        // 封装操作日志对象后基于持久层添加到数据库中
        OperateLog operateLogInfo = OperateLog.builder()
                .operateEmpId(empId)
                .operateTime(operateTime)
                .className(className)
                .methodName(methodName)
                .methodParams(methodParams)
                .returnValue(r.toString())
                .costTime(costTime).build();
        operateLogMapper.insert(operateLogInfo);
        return r;
    }
}
