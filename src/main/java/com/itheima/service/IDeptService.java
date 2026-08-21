package com.itheima.service;

import com.itheima.entity.Dept;

import java.util.List;

/**
 * 部门管理业务层接口
 */
public interface IDeptService {

    /**
     * 查询部门信息
     */
    List<Dept> list();

    /**
     * 删除部门信息
     * @param deptId
     */
    void deleteById(Integer deptId);

    /**
     * 添加部门信息
     * @param dept
     */
    void insert(Dept dept);

    /**
     * 根据Id查询部门信息
     * @param deptId
     */
    Dept selectById(Integer deptId);

    /**
     * 修改部门
     */
    void modifyById(Dept dept);
}
