package com.itheima.service;

import com.itheima.entity.EmpExpr;

import java.util.List;

/**
 * 员工工作经历业务类接口
 */
public interface IEmpExprService{

    /**
     * 新增员工工作经历
     *
     * @param exprList
     */
    void addEmpExpr(List<EmpExpr> exprList);

    /**
     * 批量删除员工工作经历
     *
     * @param ids
     */
    void deleteByIds(List<Integer> ids);

    /**
     * 更新员工工作经历
     *
     * @param empExpr
     */
    void updateById(Integer empId, List<EmpExpr> empExpr);
}
