package com.lewic.todoscore.service;

import com.lewic.todoscore.entity.jpa.primary.TaskRecord;

import java.util.List;

public interface TaskRecordService {

    List<TaskRecord> listByToday();

    void insertOne(TaskRecord taskRecord);

    void updateScore(TaskRecord taskRecord);

}
