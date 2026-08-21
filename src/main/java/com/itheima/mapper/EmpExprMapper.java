package com.itheima.mapper;

import com.itheima.entity.EmpExpr;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 员工工作经历Mapper
 */
@Mapper
public interface EmpExprMapper {

    /**
     * 批量新增员工工作经历
     *
     * @param exprList
     */
    void addEmpExpr(@Param("exprList") List<EmpExpr> exprList);

    /**
     * 批量删除员工工作经历
     *
     * @param ids
     */
    void deleteByIds(@Param("ids") List<Integer> ids);

    /**
     * 根据ID更新员工工作经历
     *
     * @param expr
     */
    void updateById(@Param("expr") EmpExpr expr);

}
