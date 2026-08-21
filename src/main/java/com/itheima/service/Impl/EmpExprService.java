package com.itheima.service.Impl;

import cn.hutool.core.collection.CollUtil;
import com.itheima.entity.EmpExpr;
import com.itheima.mapper.EmpExprMapper;
import com.itheima.service.IEmpExprService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 员工工作经历业务类
 */
@Slf4j
@Service
public class EmpExprService implements IEmpExprService {

    @Resource
    private EmpExprMapper empExprMapper;

    /**
     * 批量新增员工工作经历
     *
     * @param exprList
     */
    @Override
    public void addEmpExpr(List<EmpExpr> exprList) {
        empExprMapper.addEmpExpr(exprList);
    }

    /**
     * 批量删除员工工作经历
     *
     * @param ids
     */
    @Override
    public void deleteByIds(List<Integer> ids) {
        empExprMapper.deleteByIds(ids);
    }

    /**
     * 更新员工工作经历
     *
     * @param empExprList
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateById(Integer empId, List<EmpExpr> empExprList) {
        log.info("更新的员工工作经历的信息为 {} ",  empExprList);
            // 删除empId的员工工作经历
            empExprMapper.deleteByIds(List.of(empId));
            if (CollUtil.isNotEmpty(empExprList)) {
                // 添加员工工作经历
                empExprMapper.addEmpExpr(empExprList);
            }
    }
}
