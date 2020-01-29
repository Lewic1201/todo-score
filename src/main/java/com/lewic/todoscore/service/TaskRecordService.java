package com.lewic.todoscore.service;

import com.lewic.todoscore.dto.TaskRecordBean;
import com.lewic.todoscore.entity.jpa.primary.TaskRecord;

import java.util.List;

public interface TaskRecordService {

    List<TaskRecord> listByToday();

    void insertOne(TaskRecord taskRecord);

    void insertToday() throws Exception;

    void updateFinishStatus(Integer id, Boolean finish);

    Integer getTodayTotalScore();

    TaskRecordBean listByFinishNotNull(Integer pageNum, Integer pageSize);
}
