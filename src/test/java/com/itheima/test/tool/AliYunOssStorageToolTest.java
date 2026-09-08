package com.itheima.test.tool;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.itheima.configuration.AliYunOssProperties;
import com.itheima.tool.AliYunOssStorageTool;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 阿里云OSS工具类 - 本地集成测试
 *
 * 不做任何Mock，直接使用本机环境变量中的OSS访问凭证，真实上传到Bucket。
 * 需要先在系统中配置环境变量：
 *   ALIBABA_CLOUD_ACCESS_KEY_ID
 *   ALIBABA_CLOUD_ACCESS_KEY_SECRET
 * 未配置环境变量时（如CI环境）测试自动跳过，不会误报失败。
 */
@SpringBootTest
@EnabledIfEnvironmentVariables({
        @EnabledIfEnvironmentVariable(named = "ALIBABA_CLOUD_ACCESS_KEY_ID", matches = ".+"),
        @EnabledIfEnvironmentVariable(named = "ALIBABA_CLOUD_ACCESS_KEY_SECRET", matches = ".+")
})
class AliYunOssStorageToolTest {

    @Autowired
    private AliYunOssStorageTool tool;

    @Autowired
    private AliYunOssProperties aliyunOssProperties;

    @Test
    @DisplayName("真实上传到本地凭证对应的Bucket，返回公网访问地址，并清理测试对象")
    void uploadFile2Oss_success_returnPublicUrl() throws Exception {
        byte[] imageBytes = "integration-test-content".getBytes(StandardCharsets.UTF_8);

        String url = tool.uploadFile2Oss("test-avatar.png", imageBytes);

        // 1. 返回地址前缀 = 协议 + bucket + endpoint域名
        String expectPrefix = aliyunOssProperties.getEndPoint().split("//")[0] + "//"
                + aliyunOssProperties.getBucketName() + "." + aliyunOssProperties.getEndPoint().split("//")[1] + "/";
        assertTrue(url.startsWith(expectPrefix), "URL前缀不正确: " + url);
        assertTrue(url.endsWith(".png"), "URL应保留.png后缀: " + url);

        // 2. 对象名格式正确（yyyy/MM/32位十六进制随机串.png）
        String objectName = url.substring(expectPrefix.length());
        assertTrue(objectName.matches("\\d{4}/\\d{2}/[0-9a-f]{32}\\.png"), "对象名格式不正确: " + objectName);

        // 3. 用真实客户端校验对象已存在，随后删除该测试对象，避免Bucket残留
        EnvironmentVariableCredentialsProvider provider =
                CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();
        OSS ossClient = new OSSClientBuilder().build(aliyunOssProperties.getEndPoint(), provider);
        try {
            assertTrue(ossClient.doesObjectExist(aliyunOssProperties.getBucketName(), objectName),
                    "上传后对象不存在: " + objectName);
            ossClient.deleteObject(aliyunOssProperties.getBucketName(), objectName);
        } finally {
            ossClient.shutdown();
        }
    }
}
