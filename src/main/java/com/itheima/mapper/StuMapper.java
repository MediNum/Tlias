package com.itheima.mapper;

import com.itheima.dto.StudentQueryDTO;
import com.itheima.entity.Stu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 学生Mapper接口
 */
@Mapper
public interface StuMapper {

    /**
     * 学生列表查询
     * @param studentQueryDTO
     * @return
     */
    List<Stu> studentList(@Param("studentQueryDTO") StudentQueryDTO studentQueryDTO);
}
