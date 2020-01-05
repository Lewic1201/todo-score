package com.lewic.todoscore.dao;

import com.lewic.todoscore.dto.Page;
import com.lewic.todoscore.entity.Option;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author lewic
 * @apiNote 任务选项
 * @since 2019/6/14 16:37
 **/

@Mapper
public interface OptionMapper {

    List<Option> list(@Param("page") Page page);

    Option get(@Param("id") String id);

    Integer getCount();

    void insert(Option option);

    void update(Option option);

    void delete(@Param("subjectId") Integer subjectId);
}

