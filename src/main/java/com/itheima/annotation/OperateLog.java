package com.itheima.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标记增强的注解,被该注解标记的方法(可以基于面向切面中的切点表达式被匹配到)
 */
@Target(ElementType.METHOD) // 该注解只能标记在方法上
@Retention(RetentionPolicy.RUNTIME) // 该注解可以一直存活
public @interface OperateLog {
}
