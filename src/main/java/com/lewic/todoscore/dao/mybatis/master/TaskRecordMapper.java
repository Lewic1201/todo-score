package com.lewic.todoscore.dao.mybatis.master;

import com.lewic.todoscore.common.Page;
import com.lewic.todoscore.entity.mybatis.vo.TaskRecordI;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author lewic
 * @apiNote 任务记录
 * @since 2019/6/14 16:37
 **/

@Mapper
public interface TaskRecordMapper {

    List<TaskRecordI> listByFinishNotNull(@Param("page") Page page);

    Integer getCountByFinishNotNull();

}

