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

    /**
     * 根据id删除学生
     * @param ids
     */
    void deleteByIds(@Param("ids") List<Integer> ids);

    /**
     * 添加学生信息
     * @param stu
     */
    void addStudentInfo(@Param("stu") Stu stu);
}
