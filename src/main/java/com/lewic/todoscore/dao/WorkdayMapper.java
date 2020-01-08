package com.lewic.todoscore.dao;

import com.lewic.todoscore.entity.Workday;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;


/**
 * @author lewic
 * @apiNote 特殊工作日
 * @since 2019/6/14 16:37
 **/

@Mapper
public interface WorkdayMapper {

    List<Workday> list();

    Workday get(Date date);

    void insert(Workday workday);

    void delete(Integer id);
}

