package com.itheima.tool;


import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.ObjectUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.itheima.configuration.AliYunOssProperties;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 阿里云OSS工具类
 */
@Component // 标记这是一个Spring组件
public class AliYunOssStorageTool {

    @Resource // 注入存储了阿里云Oss参数的对象
    private AliYunOssProperties aliyunOssProperties;

    /**
     * 上传文件到阿里云Oss对象存储到指定的Bucket
     */

    public String uploadFile2Oss(String fileName , byte[] bytes) throws Exception {
        // 基于读取当前系统的环境变量中的OSS访问凭证创建CredentialsProvider对象
        EnvironmentVariableCredentialsProvider environmentVariableCredentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();

        // 生成随机文件名称(避免Bucket桶中文件名称重复 UUID)
        String uploadFileDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM")); // 生成日期目录
        // 生成随机名称
        String uploadFileName = uploadFileDate.concat("/").concat(UUID.fastUUID().toString(true)).concat(fileName.substring(fileName.lastIndexOf(".")));

        // 创建OssClient后上传文件
        OSS ossClient = new OSSClientBuilder().build(aliyunOssProperties.getEndPoint(), environmentVariableCredentialsProvider);
        try {
            ossClient.putObject(aliyunOssProperties.getBucketName() , uploadFileName , new ByteArrayInputStream(bytes));
        } finally {
            if (ObjectUtil.isNotEmpty(ossClient))
                ossClient.shutdown();
        }
        // 封装上传文件的公网访问地址后返回
        String[] endPointArray = aliyunOssProperties.getEndPoint().split("//");
        return endPointArray[0] + "//" + aliyunOssProperties.getBucketName() + "." + endPointArray[1] + "/" + uploadFileName;
    }
}
