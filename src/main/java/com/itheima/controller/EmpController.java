package com.itheima.controller;

import com.itheima.dto.EmpQueryDTO;
import com.itheima.entity.Emp;
import com.itheima.service.IEmpService;
import com.itheima.vo.PageR;
import com.itheima.vo.R;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 员工管理控制类
 */
@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {

    @Resource
    private IEmpService empService;

    /**
     * 分页查询员工信息
     *
     * @return
     */
    @GetMapping
    public R listAllEmp(EmpQueryDTO empQueryDTO) {
        log.info("分页查询员工信息，参数：{}", empQueryDTO);
        PageR empListInfo = empService.pageSelect(empQueryDTO);
        return R.ok(empListInfo);
    }

    /**
     * 批量删除员工信息
     *
     * @param ids
     * @return
     */
    @DeleteMapping
    public R deleteByIds(@RequestParam List<Integer> ids) {
        log.info("批量删除员工信息，参数：{}", ids);
        empService.deleteByIds(ids);
        return R.ok();
    }

    /**
     * 新增员工信息
     *
     * @param emp
     * @return
     */
    @PostMapping
    public R saveEmpInfo(@RequestBody Emp emp) {
        log.info("添加员工信息，参数：{}", emp);
        empService.addEmp(emp);
        return R.ok();
    }

    /**
     * 根据员工Id查询员工信息
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R selectListById(@PathVariable Integer id) {
        log.info("查询员工信息，参数：{}", id);
        Emp empList = empService.selectListById(id);
        return R.ok(empList);
    }

    /**
     * 修改员工信息
     *
     * @param emp
     * @return
     */
    @PutMapping
    public R updateEmp(@RequestBody Emp emp) {
        log.info("更新员工信息，参数：{}", emp);
        empService.updateEmp(emp);
        return R.ok();
    }

    /**
     * 查询所有员工信息
     *
     * @return
     */
    @GetMapping("/list")
    public R selectAllList() {
        log.info("查询所有员工信息");
        List<Emp> empList = empService.selectAllList();
        return R.ok(empList);
    }
}
