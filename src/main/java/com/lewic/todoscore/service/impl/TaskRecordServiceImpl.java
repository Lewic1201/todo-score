package com.lewic.todoscore.service.impl;

import com.lewic.todoscore.common.ResponseCode;
import com.lewic.todoscore.dao.jpa.primary.TaskDao;
import com.lewic.todoscore.dao.jpa.primary.TaskRecordDao;
import com.lewic.todoscore.dao.mybatis.master.TaskRecordMapper;
import com.lewic.todoscore.common.Page;
import com.lewic.todoscore.exception.ClientException;
import com.lewic.todoscore.utils.DateUtil;
import com.lewic.todoscore.utils.ToUtil;
import com.lewic.todoscore.vo.ScoreInfoVo;
import com.lewic.todoscore.vo.TaskRecordBean;
import com.lewic.todoscore.entity.jpa.primary.Task;
import com.lewic.todoscore.entity.jpa.primary.TaskRecord;
import com.lewic.todoscore.entity.mybatis.vo.TaskRecordI;
import com.lewic.todoscore.service.TaskRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;
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
        return listByDay(new Date());
    }

    @Override
    public List<TaskRecord> listByDay(Date day) {
        try {
            Map<String, Date> map = DateUtil.getDayStartAndEnd(day);
            log.debug("time stamp range:{} -> {}", map.get("dateStart").getTime(),
                    map.get("dateEnd").getTime());
            return taskRecordDao.findAllByCreateTimeGreaterThanEqualAndCreateTimeLessThan(
                    map.get("dateStart"), map.get("dateEnd"));
        } catch (Exception e) {
            log.error("get task record failed!!");
            return null;
        }
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
        List<Task> todayTasks = taskDao.findByCycleTypeIdIn(todayCycleTypeIds);
        for (Task task : todayTasks) {
            // todo 可以修改查找方法，不需要continue过滤
//            if(task.getStatus().equals(0)){
//                continue;
//            }
            if (task.getDeleted()) {
                continue;
            }
            TaskRecord taskRecord = ToUtil.taskToTaskRecord(task);
            taskRecordDao.save(taskRecord);
        }
    }

    @Override
    public ScoreInfoVo getTodayTotalScore() throws Exception {
        return getDayTotalScore(new Date());
    }

    @Override
    @Transactional(readOnly = true)
    public ScoreInfoVo getDayTotalScore(Date date) throws Exception {
        List<TaskRecord> taskRecords = listByDay(date);
        Integer score = 0;
        Integer totalScore = 0;
        for (TaskRecord taskRecord : taskRecords) {
            if (taskRecord.getScore() != null) {
                score += taskRecord.getScore();
                totalScore += taskRecord.getScoreValue();
            }
        }
        return new ScoreInfoVo(score, totalScore);
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
    public void updateFinishStatus(Integer id, Boolean finish) throws ClientException {
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
            throw new ClientException(ResponseCode.UPDATE_ERROR, "get this taskRecord info failed!!");
        }
        taskRecordDao.save(taskRecord);
    }

}
