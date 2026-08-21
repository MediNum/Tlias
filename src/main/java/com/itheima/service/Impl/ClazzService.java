package com.itheima.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dto.ClazzQueryDTO;
import com.itheima.entity.Clazz;
import com.itheima.mapper.ClazzMapper;
import com.itheima.service.IClazzService;
import com.itheima.vo.PageR;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzService implements IClazzService {

    @Resource
    private ClazzMapper clazzMapper;

    /**
     * 班级列表查询
     *
     * @param clazzQueryDTO
     * @return
     */
    @Override
    public PageR clazzList(ClazzQueryDTO clazzQueryDTO) {
        // 开启分页
        PageHelper.startPage(clazzQueryDTO.getPage(), clazzQueryDTO.getPageSize());
        // 2. 正常查询，返回值就用最普通的 List 接收（解耦）
        List<Clazz> list = clazzMapper.clazzList(clazzQueryDTO);

        // 3. 用 PageInfo 包装 List（PageInfo 会自动去读取 ThreadLocal 里的总数等信息）
        PageInfo<Clazz> pageInfo = new PageInfo<>(list);

        // 将总记录数和分页查询结果封装到PageR对象中并返回
        return PageR.builder()
                .total(pageInfo.getTotal())
                .rows(pageInfo.getList())
                .build();
    }

    /**
     * 删除班级
     *
     * @param id
     * @return
     */
    @Override
    public void deleteById(Integer id) {
        if (id != null && id > 0)
            clazzMapper.deleteById(id);
    }

    /**
     * 添加班级信息
     *
     * @param clazz
     */
    @Override
    public void insertInfo(Clazz clazz) {
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.insertInfo(clazz);
    }

    /**
     * 根据Id查询班级信息
     *
     * @param id
     * @return
     */
    @Override
    public Clazz selectById(Integer id) {
        return clazzMapper.selectById(id);
    }

    /**
     * 修改班级信息
     *
     * @param clazz
     */
    @Override
    public void modifyById(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.modifyById(clazz);
    }

    /**
     * 查询所有班级信息
     *
     * @return
     */
    @Override
    public List<Clazz> clazzListAll() {
        return clazzMapper.clazzListAll();
    }
}
