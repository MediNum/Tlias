package com.itheima.service.Impl;

import com.itheima.entity.Dept;
import com.itheima.mapper.DeptMapper;
import com.itheima.service.IDeptService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 部门管理业务层实现类
 */
@Service
public class DeptService implements IDeptService {


    @Resource
    private DeptMapper deptMapper;

    /**
     * 查询部门信息
     *
     * @return
     */
    @Override
    public List<Dept> list() {
        List<Dept> deptList = deptMapper.list();
        return deptList;
    }

    /**
     * 删除部门信息
     *
     * @param deptId
     */
    @Override
    public void deleteById(Integer deptId) {
        deptMapper.deleteById(deptId);
    }

    /**
     * 添加部门信息
     *
     * @param dept
     */
    @Override
    public void insert(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.insert(dept);
    }

    /**
     * 根据Id查询部门信息
     *
     * @param deptId
     */
    @Override
    public Dept selectById(Integer deptId) {
        return deptMapper.selectById(deptId);
    }

    /**
     * 修改部门
     */
    @Override
    public void modifyById(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.modifyById(dept);
    }
}
