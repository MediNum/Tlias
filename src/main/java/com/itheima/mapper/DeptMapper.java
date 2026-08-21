package com.itheima.mapper;

import com.itheima.entity.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DeptMapper {

    /**
     * 查询部门信息
     * @return
     */
    List<Dept> list();

    /**
     * 删除部门信息
     * @param deptId
     */
    void deleteById(@Param("deptId") Integer deptId);

    /**
     * 添加部门信息
     * @param dept
     */
    void insert(@Param("dept") Dept dept);

    /**
     * 根据Id查询部门信息
     * @param deptId
     */
    Dept selectById(@Param("deptId") Integer deptId);

    /**
     * 修改部门信息
     */
    void modifyById(@Param("dept") Dept dept);
}
