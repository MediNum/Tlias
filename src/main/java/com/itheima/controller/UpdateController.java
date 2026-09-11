package com.itheima.controller;

import com.itheima.annotation.OperateLog;
import com.itheima.tool.AliYunOssStorageTool;
import com.itheima.vo.R;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 图片上传 - 控制层
 *
 * @author HC
 * @date 2023/8/10 16:40
 */
@Slf4j
@RestController
public class UpdateController {

    @Resource
    private AliYunOssStorageTool aliYunOssStorageTool;

    /**
     * 文件上传
     */
    @OperateLog
    @PostMapping("/upload")
    public R uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
        // 可以基于MultipartFile获取文件的原始名称,文件数据的字节数据,[上传到阿里云Oss]
        log.info("文件原始名称: {}", file.getOriginalFilename());
        String originalFilename = file.getOriginalFilename();
        byte[] bytes = file.getBytes();
        
        // 基于阿里云的文件上传工具进行文件上传并且接收文件在桶中的URL访问地址
        String fileBuketURL = aliYunOssStorageTool.uploadFile2Oss(originalFilename, bytes);
        return R.ok(fileBuketURL);

    }
}
