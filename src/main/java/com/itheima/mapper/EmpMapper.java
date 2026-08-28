package com.itheima.mapper;

import com.itheima.dto.EmpQueryDTO;
import com.itheima.entity.Emp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 员工管理Mapper
 */
@Mapper
public interface EmpMapper {

    /**
     * 分页查询员工信息
     *
     * @param empQueryDTO
     * @return
     */
    List<Emp> pageSelect(@Param("empQueryDTO") EmpQueryDTO empQueryDTO);

    /**
     * 批量删除员工信息
     *
     * @param ids
     */
    void deleteByIds(@Param("ids") List<Integer> ids);

    /**
     * 新增员工信息
     *
     * @param emp
     */
    void addEmp(@Param("emp") Emp emp);

    /**
     * 根据员工Id查询员工信息
     *
     * @param id
     * @return
     */
    Emp selectById(@Param("id") Integer id);

    /**
     * 更新员工信息
     *
     * @param emp
     */
    void updateById(@Param("emp") Emp emp);

    /**
     * 查询所有员工信息
     *
     * @return
     */
    List<Emp> selectAllList();

    /**
     * 根据用户名查询员工信息是否重复
     * @param username
     * @return
     */
    Integer selectByUsername(@Param("username") String username);
}
