package com.itheima.tool;


import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
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

    public String uploadFile2Oss(String fileName, byte[] bytes) throws Exception {
        // 基于读取当前系统的环境变量中的OSS访问凭证创建CredentialsProvider对象
        EnvironmentVariableCredentialsProvider environmentVariableCredentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();

        // 生成随机文件名称(避免Bucket桶中文件名称重复 UUID)
        String uploadFileDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM")); // 生成日期目录
        // 生成随机名称
        String uploadFileName = uploadFileDate.concat("/").concat(UUID.fastUUID().toString(true)).concat(fileName.substring(fileName.lastIndexOf(".")));

        // 创建OssClient后上传文件
        OSS ossClient = new OSSClientBuilder().build(aliyunOssProperties.getEndPoint(), environmentVariableCredentialsProvider);
        try {
            ossClient.putObject(aliyunOssProperties.getBucketName(), uploadFileName, new ByteArrayInputStream(bytes));
        } finally {
            if (ObjectUtil.isNotEmpty(ossClient))
                ossClient.shutdown();
        }
        // 封装上传文件的公网访问地址后返回
        String[] endPointArray = aliyunOssProperties.getEndPoint().split("//");
        return endPointArray[0] + "//" + aliyunOssProperties.getBucketName() + "." + endPointArray[1] + "/" + uploadFileName;
    }

    /**
     * 删除阿里云Oss对象存储中的头像文件
     */
    public void deleteFile(String objectName) throws Exception {
        if (StrUtil.isBlank(objectName))
            return;
        String endpoint = aliyunOssProperties.getEndPoint();
        String bucketName = aliyunOssProperties.getBucketName();

        // 如果传入的是完整的访问地址,截取出objectName名称(Key),deleteObject的第二个参数不能带域名前缀,否则删不掉文件
        String urlPrefix = getUrlPrefix();
        if (objectName.startsWith(urlPrefix)) {
            objectName = objectName.substring(urlPrefix.length());
        }

        // 从环境变量中获取访问凭证。运行本代码示例之前，请确保已设置环境变量OSS_ACCESS_KEY_ID和OSS_ACCESS_KEY_SECRET。
        EnvironmentVariableCredentialsProvider credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, credentialsProvider);

        try {
            // 删除文件或目录,如果要删除目录,目录必须为空
            ossClient.deleteObject(bucketName, objectName);
            // 删除后校验文件是否还存在OSS删除是幂等的，传入不存在的key也不会报错)
            boolean exists = ossClient.doesObjectExist(bucketName, objectName);
            if (exists) {
                System.out.println("删除失败，文件仍然存在: " + objectName);
            } else {
                System.out.println("删除成功: " + objectName);
            }
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }

    // 获取当前桶文件的公网访问地址前缀
    public String getUrlPrefix() {
        String[] endPointArray = aliyunOssProperties.getEndPoint().split("//");
        return endPointArray[0] + "//" + aliyunOssProperties.getBucketName() + "." + endPointArray[1] + "/";
    }

    // 判断地址是否为当前OSS桶中的文件地址
    public boolean isOssFile(String url) {
        return StrUtil.isNotBlank(url) && url.startsWith(getUrlPrefix());
    }
}
