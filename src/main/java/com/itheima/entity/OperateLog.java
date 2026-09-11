package com.itheima.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 日志实体类
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OperateLog {
    private Integer id; // Id
    private Integer operateEmpId; // 操作人 Id
    private LocalDateTime operateTime; // 操作时间
    private String className; // 操作类名
    private String methodName; // 操作方法名
    private String methodParams; // 操作方法参数
    private String returnValue; // 操作方法返回值
    private Long costTime; // // 操作耗时

    private String operateEmpName; // 操作人姓名
}
