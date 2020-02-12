package com.lewic.todoscore.service.impl;

import com.lewic.todoscore.dao.jpa.primary.TaskDao;
import com.lewic.todoscore.dao.jpa.primary.TaskRecordDao;
import com.lewic.todoscore.dao.mybatis.master.TaskRecordMapper;
import com.lewic.todoscore.common.Page;
import com.lewic.todoscore.dto.TaskRecordBean;
import com.lewic.todoscore.entity.jpa.primary.Task;
import com.lewic.todoscore.entity.jpa.primary.TaskRecord;
import com.lewic.todoscore.entity.mybatis.vo.TaskRecordI;
import com.lewic.todoscore.service.TaskRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

/**
 * @author lewic
 * @since 2020/1/19 0:58
 */
@Slf4j
@Service
public class TaskRecordServiceImpl implements TaskRecordService {

    private final TaskRecordDao taskRecordDao;

    private final TaskDao taskDao;

    private final CycleTypeServiceImpl cycleTypeService;

    private final TaskRecordMapper taskRecordMapper;

    // todo taskRecordMapper报错
    @Autowired
    public TaskRecordServiceImpl(TaskRecordDao taskRecordDao, TaskDao taskDao, CycleTypeServiceImpl cycleTypeService, TaskRecordMapper taskRecordMapper) {
        this.taskRecordDao = taskRecordDao;
        this.taskDao = taskDao;
        this.cycleTypeService = cycleTypeService;
        this.taskRecordMapper = taskRecordMapper;
    }


    @Override
    public List<TaskRecord> listByToday() {
        Date today = new Date();

        // 获取当天起始时间和结束时间
        Calendar calendarStart = new GregorianCalendar();
        calendarStart.setTime(today);
        calendarStart.set(Calendar.HOUR, -12);
        calendarStart.set(Calendar.MINUTE, 0);
        calendarStart.set(Calendar.SECOND, 0);
        Date dateStart = calendarStart.getTime();
        Calendar calendarEnd = new GregorianCalendar();
        calendarEnd.setTime(dateStart);
        calendarEnd.add(Calendar.HOUR, 24);
        Date dateEnd = calendarEnd.getTime();

        return taskRecordDao.findAllByCreateTimeGreaterThanEqualAndCreateTimeLessThan(dateStart, dateEnd);
    }

    @Override
    @Transactional
    public void insertOne(TaskRecord taskRecord) {
        taskRecordDao.save(taskRecord);
    }

    @Override
    @Transactional
    public void insertToday() throws Exception {
        Integer[] todayCycleTypeIds = cycleTypeService.listCycleTypeIdByToday();
        // todo 可以更改为只查询出id
        // todo 批量更新需要优化
        List<Task> todayTasks = taskDao.findByCycleTypeIdIn(todayCycleTypeIds);
        for (Task task : todayTasks) {
            // todo 可以修改查找方法，不需要continue过滤
//            if(task.getStatus().equals(0)){
//                continue;
//            }
            if(task.getDeleted()){
                continue;
            }
            TaskRecord taskRecord = new TaskRecord();
            taskRecord.setTask(task);
            taskRecord.setScore(0);
            taskRecordDao.save(taskRecord);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Integer getTodayTotalScore() {
        List<TaskRecord> taskRecords = listByToday();
        Integer totalScore = 0;
        for (TaskRecord taskRecord : taskRecords) {
            if (taskRecord.getScore() != null) {
                totalScore += taskRecord.getScore();
            }
        }
        return totalScore;
    }

    @Override
    public TaskRecordBean listByFinishNotNull(Integer pageNum, Integer pageSize) {
        Integer total = taskRecordMapper.getCountByFinishNotNull();
        Page page = new Page(pageSize, pageNum, total);
        List<TaskRecordI> taskRecordIS = taskRecordMapper.listByFinishNotNull(page);
        return new TaskRecordBean(page, taskRecordIS);
    }

    @Override
    @Transactional
    public void updateFinishStatus(Integer id, Boolean finish) {
        Optional<TaskRecord> optionalTaskRecord = taskRecordDao.findById(id);
        TaskRecord taskRecord;
        if (optionalTaskRecord.isPresent()) {
            taskRecord = optionalTaskRecord.get();
            taskRecord.setFinish(finish);
            if (finish) {
                taskRecord.setScore(taskRecord.getTask().getScore());
            } else {
                taskRecord.setScore(0);
            }
        } else {
            throw new RuntimeException("get this taskRecord info failed!!");
        }
        taskRecordDao.save(taskRecord);
    }
}
