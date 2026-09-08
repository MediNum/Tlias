package com.itheima.mapper;

import com.itheima.dto.LoginQueryDTO;
import com.itheima.entity.Emp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 登录 持久层
 */
@Mapper
public interface LoginMapper {

    /**
     * 用户登录
     *
     * @param loginQueryDTO
     */
    Emp login(@Param("loginQueryDTO") LoginQueryDTO loginQueryDTO);
}
