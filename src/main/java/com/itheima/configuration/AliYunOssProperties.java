package com.itheima.configuration;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 阿里云OSS 配置类
 */
@Data
@Component

public class AliYunOssProperties {
    private String bucketName; // 桶名称
    private String endPoint; // 节点URL路径
}
