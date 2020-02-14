package com.lewic.todoscore.service;

import com.lewic.todoscore.vo.TaskRecordBean;
import com.lewic.todoscore.entity.jpa.primary.TaskRecord;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface TaskRecordService {

    List<TaskRecord> listByToday();

    List<TaskRecord> listByDay(Date day);

    void insertOne(TaskRecord taskRecord);

    void insertToday() throws Exception;

    void updateFinishStatus(Integer id, Boolean finish);

    Map<String, Object> getTodayTotalScore() throws Exception;

    Map<String, Object> getDayTotalScore(Date date) throws Exception;

    TaskRecordBean listByFinishNotNull(Integer pageNum, Integer pageSize);
}
