package com.itheima.mapper;

import com.itheima.dto.ClazzQueryDTO;
import com.itheima.entity.Clazz;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ClazzMapper {
    /**
     * 查询班级列表
     *
     * @param clazzQueryDTO
     * @return
     */
    List<Clazz> clazzList(@Param("clazzQueryDTO") ClazzQueryDTO clazzQueryDTO);

    /**
     * 删除班级信息
     *
     * @param id
     */
    void deleteById(@Param("id") Integer id);

    /**
     * 添加班级信息
     *
     * @param clazz
     */
    void insertInfo(@Param("clazz") Clazz clazz);

    /**
     * 根据Id查询部门信息
     *
     * @param id
     * @return
     */
    Clazz selectById(@Param("id") Integer id);

    /**
     * 修改班级信息
     *
     * @param clazz
     */
    void modifyById(@Param("clazz") Clazz clazz);

    /**
     * 查询所有班级信息
     *
     * @return
     */
    List<Clazz> clazzListAll();

    /**
     * 查询班级内是否还有学生
     * @param id
     * @return
     */
    Integer countStu(@Param("id") Integer id);
}
