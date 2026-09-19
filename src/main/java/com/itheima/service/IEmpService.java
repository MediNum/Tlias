package com.itheima.service;

import com.itheima.dto.EmpQueryDTO;
import com.itheima.dto.PasswordQueryDTO;
import com.itheima.entity.Emp;
import com.itheima.vo.PageR;

import java.util.List;

/**
 * 员工管理业务接口
 */
public interface IEmpService {

    /**
     * 分页查询员工信息
     *
     * @param empQueryDTO
     * @return
     */
    PageR pageSelect(EmpQueryDTO empQueryDTO);

    /**
     * 批量删除员工信息
     *
     * @param ids
     */
    void deleteByIds(List<Integer> ids);

    /**
     * 新增员工信息
     *
     * @param emp
     */
    void addEmp(Emp emp);

    /**
     * 根据员工Id查询员工信息
     *
     * @param id
     * @return
     */
    Emp selectListById(Integer id);

    /**
     * 修改员工信息
     *
     * @param emp
     */
    void updateEmp(Emp emp);

    /**
     * 查询所有员工信息
     *
     * @return
     */
    List<Emp> selectAllList();

    /**
     * 修改当前登录用户的密码
     *
     * @param passwordQueryDTO
     */
    void updatePassword(PasswordQueryDTO passwordQueryDTO);
}
