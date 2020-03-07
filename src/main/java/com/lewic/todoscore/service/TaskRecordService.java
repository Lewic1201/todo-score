package com.lewic.todoscore.service;

import com.lewic.todoscore.exception.ClientException;
import com.lewic.todoscore.vo.ScoreInfoVo;
import com.lewic.todoscore.vo.TaskRecordBean;
import com.lewic.todoscore.entity.TaskRecord;

import java.util.Date;
import java.util.List;

public interface TaskRecordService {

    List<TaskRecord> listByToday();

    List<TaskRecord> listByDay(Date day);

    void insertOne(TaskRecord taskRecord);

    void insertToday() throws Exception;

    void updateFinishStatus(Integer id, Boolean finish) throws ClientException;

    ScoreInfoVo getTodayTotalScore() throws Exception;

    ScoreInfoVo getDayTotalScore(Date date) throws Exception;

    TaskRecordBean listByFinishNotNull(Integer pageNum, Integer pageSize);
}
