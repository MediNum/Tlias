package com.itheima.mapper;

import com.itheima.dto.operateLogQueryDTO;
import com.itheima.entity.OperateLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 日志 - 持久层
 */
@Mapper
public interface OperateLogMapper {

    /**
     * 分页查询日志消息
     * @param operateLogQueryDTO
     * @return
     */
    List<OperateLog> logList(operateLogQueryDTO operateLogQueryDTO);

    /**
     *
     */
    void insert(@Param("operateLogInfo") OperateLog operateLogInfo);
}
