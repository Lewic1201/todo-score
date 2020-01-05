package com.lewic.todoscore.dao;

import com.lewic.todoscore.dto.Page;
import com.lewic.todoscore.entity.Subject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author lewic
 * @apiNote 主题任务
 * @since 2019/6/14 16:37
 **/

@Mapper
public interface SubjectMapper {

    List<Subject> list(@Param("page") Page page);

    Subject get(@Param("id") Integer id);

    Integer getCount();

    Integer insert(Subject subject);

    void update(Subject subject);

    void delete(@Param("id") Integer id);
}

