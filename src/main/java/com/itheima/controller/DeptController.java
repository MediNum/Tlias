package com.itheima.controller;

import com.itheima.entity.Dept;
import com.itheima.service.IDeptService;
import com.itheima.vo.R;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门管理控制类
 */
@Slf4j
@RestController
@RequestMapping("/depts")
public class DeptController {

    @Resource
    private IDeptService deptService;


    /**
     * 查询所有部门
     */
    @GetMapping
    public R listAllDept() {
        List<Dept> deptList = deptService.list();
        return R.ok(deptList);
    }

    /**
     * 删除部门
     */
    @DeleteMapping
    public R deleteById(@RequestParam("id") Integer deptId) {
        deptService.deleteById(deptId);
        return R.ok();
    }

    /**
     * 添加部门信息
     */
    @PostMapping
    public R saveDeptInfo(@RequestBody Dept dept) {
        deptService.insert(dept);
        return R.ok();
    }

    /**
     *根据Id查询部门信息
     */
    @GetMapping("/{deptId}")
    public R selectById(@PathVariable Integer deptId) {
        log.info("查询到的部门Id为 {} ",deptId);
        Dept deptList = deptService.selectById(deptId);
        return R.ok(deptList);
    }

    /**
     * 修改部门
     */
    @PutMapping
    public R updateById(@RequestBody Dept dept) {
        deptService.modifyById(dept);
        return R.ok();
    }
}


