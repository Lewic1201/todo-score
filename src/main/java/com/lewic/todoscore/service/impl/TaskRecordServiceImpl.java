package com.lewic.todoscore.service.impl;

import com.lewic.todoscore.dao.jpa.primary.TaskRecordDao;
import com.lewic.todoscore.entity.jpa.primary.TaskRecord;
import com.lewic.todoscore.service.TaskRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * @author lewic
 * @since 2020/1/19 0:58
 */
@Slf4j
@Service
public class TaskRecordServiceImpl implements TaskRecordService {

    private final TaskRecordDao taskRecordDao;

    @Autowired
    public TaskRecordServiceImpl(TaskRecordDao taskRecordDao) {
        this.taskRecordDao = taskRecordDao;
    }


    @Override
    public List<TaskRecord> listByToday() {
        Date today = new Date();

        // 获取当天起始时间和结束时间
        Calendar calendarStart = new GregorianCalendar();
        calendarStart.setTime(today);
        calendarStart.set(Calendar.HOUR, 0);
        calendarStart.set(Calendar.MINUTE, 0);
        calendarStart.set(Calendar.SECOND, 0);
        Date dateStart = calendarStart.getTime();
        Calendar calendarEnd = new GregorianCalendar();
        calendarEnd.setTime(dateStart);
        calendarEnd.add(Calendar.HOUR, 1);
        Date dateEnd = calendarEnd.getTime();

        List<TaskRecord> taskRecords = taskRecordDao.
                findAllByCreateTimeGreaterThanEqualAndCreateTimeLessThan(dateStart, dateEnd);
        return taskRecords;
    }

    @Override
    @Transactional
    public void insertOne(TaskRecord taskRecord) {

    }

    @Override
    @Transactional
    public void updateScore(TaskRecord taskRecord) {

    }
}
