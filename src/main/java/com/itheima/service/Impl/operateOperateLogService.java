package com.itheima.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dto.operateLogQueryDTO;
import com.itheima.entity.OperateLog;
import com.itheima.mapper.OperateLogMapper;
import com.itheima.service.IOperateLogService;
import com.itheima.vo.PageR;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 日志 - 服务层
 */
@Slf4j
@Service
public class operateOperateLogService implements IOperateLogService {

    @Resource
    private OperateLogMapper operateLogMapper;

    /**
     * 日志分页查询
     *
     * @param operateLogQueryDTO
     * @return
     */
    @Override
    public PageR logList(operateLogQueryDTO operateLogQueryDTO) {
        // 开启分页
        PageHelper.startPage(operateLogQueryDTO.getPage() , operateLogQueryDTO.getPageSize());
        // 查询数据
        List<OperateLog> list = operateLogMapper.logList(operateLogQueryDTO);
        // 用PageInfo包装 List
        PageInfo<OperateLog> pageInfo = new PageInfo<>(list);
        // 将总记录数和分页查询结果封装到PageR对象中并返回
        return PageR.builder()
                .total(pageInfo.getTotal())
                .rows(pageInfo.getList())
                .build();
    }
}
