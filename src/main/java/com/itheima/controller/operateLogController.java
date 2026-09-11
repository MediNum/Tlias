package com.itheima.controller;

import com.itheima.dto.operateLogQueryDTO;
import com.itheima.service.IOperateLogService;
import com.itheima.vo.PageR;
import com.itheima.vo.R;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 日志 - 控制层
 */
@Slf4j
@RestController
@RequestMapping("/log")
public class operateLogController {

    @Resource
    private IOperateLogService logService;

    @GetMapping("/page")
    public R listLog(operateLogQueryDTO operateLogQueryDTO) {
        log.info("查询条件 {} ", operateLogQueryDTO);
        PageR pageR = logService.logList(operateLogQueryDTO);
        return R.ok(pageR);
    }
}
