package com.itheima.controller;

import com.itheima.dto.ClazzQueryDTO;
import com.itheima.entity.Clazz;
import com.itheima.service.IClazzService;
import com.itheima.vo.PageR;
import com.itheima.vo.R;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 班级 - 控制器
 */
@Slf4j
@RestController
@RequestMapping("/clazzs")
public class ClazzController {

    @Resource
    private IClazzService clazzService;

    /**
     * 分页查询班级
     *
     * @param clazzQueryDTO
     * @return
     */
    @GetMapping
    public R listAllClazz(ClazzQueryDTO clazzQueryDTO) {
        log.info("分页查询班级,查询条件：{}", clazzQueryDTO);
        PageR pageR = clazzService.clazzList(clazzQueryDTO);
        return R.ok(pageR);
    }

    /**
     * 删除班级信息
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public R deleteById(@PathVariable Integer id) {
        log.info("删除班级信息,id:{}", id);
        clazzService.deleteById(id);
        return R.ok();
    }

    /**
     * 添加班级信息
     *
     * @param clazz
     * @return
     */
    @PostMapping
    public R saveClazzInfo(@RequestBody Clazz clazz) {
        log.info("添加班级信息,clazz:{}", clazz);
        clazzService.insertInfo(clazz);
        return R.ok();
    }

    /**
     * 根据Id查询班级信息
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R selectById(@PathVariable Integer id) {
        log.info("查询班级信息,id:{}", id);
        Clazz clazzInfo = clazzService.selectById(id);
        return R.ok(clazzInfo);
    }

    /**
     * 修改班级信息
     *
     * @param clazz
     * @return
     */
    @PutMapping
    public R updateByClazz(@RequestBody Clazz clazz) {
        log.info("修改班级信息,clazz:{}", clazz);
        clazzService.modifyById(clazz);
        return R.ok();
    }

    /**
     * 查询所有班级信息
     *
     * @return
     */
    @GetMapping("/list")
    public R listAllClazzInfo() {
        List<Clazz> clazzListInfo = clazzService.clazzListAll();
        log.info("查询所有班级信息,clazzListInfo:{}", clazzListInfo);
         return R.ok(clazzListInfo);
        // return R.ok();
    }
}

