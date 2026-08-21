package com.itheima.service;

import com.itheima.dto.ClazzQueryDTO;
import com.itheima.entity.Clazz;
import com.itheima.vo.PageR;

import java.util.List;


public interface IClazzService {

    /**
     * 班级列表查询
     *
     * @param clazzQueryDTO
     * @return
     */
    PageR clazzList(ClazzQueryDTO clazzQueryDTO);

    /**
     * 删除班级
     *
     * @param id
     * @return
     */
    void deleteById(Integer id);

    /**
     * 添加班级信息
     *
     * @param clazz
     */
    void insertInfo(Clazz clazz);

    /**
     * 根据Id查询班级信息
     *
     * @param id
     * @return
     */
    Clazz selectById(Integer id);

    /**
     * 修改班级信息
     *
     * @param clazz
     */
    void modifyById(Clazz clazz);

    /**
     * 查询所有班级信息
     *
     * @return
     */
    List<Clazz> clazzListAll();
}
