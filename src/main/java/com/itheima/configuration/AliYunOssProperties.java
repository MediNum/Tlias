package com.itheima.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 阿里云OSS 配置类
 */
@Data
@Component // 标记这是一个Spring组件
@ConfigurationProperties(prefix = "aliyun.oss") // 会自动读取application.yml中aliyun.oss的配置
public class AliYunOssProperties {
    private String bucketName; // 桶名称
    private String endPoint; // 节点URL路径
}
