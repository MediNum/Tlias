package com.itheima.service;

import com.itheima.dto.operateLogQueryDTO;
import com.itheima.vo.PageR;

/**
 * 日志 - 接口
 */
public interface IOperateLogService {

    /**
     * 日志分页查询
     * @param operateLogQueryDTO
     * @return
     */
    PageR logList(operateLogQueryDTO operateLogQueryDTO);
}
