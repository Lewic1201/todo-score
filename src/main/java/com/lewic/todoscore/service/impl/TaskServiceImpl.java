package com.lewic.todoscore.service.impl;

import com.lewic.todoscore.dao.jpa.primary.CycleTypeDao;
import com.lewic.todoscore.dao.jpa.primary.TaskDao;
import com.lewic.todoscore.dao.jpa.primary.TaskRecordDao;
import com.lewic.todoscore.dto.TaskDto;
import com.lewic.todoscore.entity.jpa.primary.CycleType;
import com.lewic.todoscore.entity.jpa.primary.Task;
import com.lewic.todoscore.entity.jpa.primary.TaskRecord;
import com.lewic.todoscore.service.TaskService;
import com.lewic.todoscore.utils.ToUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TaskServiceImpl implements TaskService {

    private final TaskDao taskDao;

    private final CycleTypeDao cycleTypeDao;

    private final TaskRecordDao taskRecordDao;

    private final CycleTypeServiceImpl cycleTypeService;

    @Autowired
    public TaskServiceImpl(TaskDao taskDao, CycleTypeDao cycleTypeDao, TaskRecordDao taskRecordDao, CycleTypeServiceImpl cycleTypeService) {
        this.taskDao = taskDao;
        this.cycleTypeDao = cycleTypeDao;
        this.taskRecordDao = taskRecordDao;
        this.cycleTypeService = cycleTypeService;
    }

    @Override
    public void insertOne(TaskDto taskDto) throws Exception {
        Task task = new Task();
        taskDtoToTask(taskDto, task);
        task.setDeleted(false);
        if (task.getId() != null) {
            task.setId(null);
        }

        taskDao.save(task);

        // 校验今天是否可用
        boolean recordUpdateFlag = cycleTypeService.filterByCronAndWorkday(task.getCycleType(), new Date());
        if (recordUpdateFlag) {
            TaskRecord taskRecord = ToUtil.taskToTaskRecord(task);
            taskRecordDao.save(taskRecord);
        }

    }

    @Override
    public List<Task> listAllNormal() {
        return taskDao.findByDeletedFalseOrderByIdDesc();
    }

    @Override
    public Task showOne(Integer id) throws Exception {
        Task task = taskDao.findByIdAndDeletedFalse(id);
        if (task == null || task.getId() == null) {
            log.error("task not found");
            throw new Exception("task id is not found");
        }
        return task;
    }

    @Override
    public void updateOne(TaskDto taskDto) throws Exception {

        if (taskDto.getId() == null) {
            log.error("task id is null");
            throw new Exception("task id is not found");
        }

        Optional<Task> taskOptional = taskDao.findById(taskDto.getId());
        if (!taskOptional.isPresent()) {
            log.error("task not found");
            throw new Exception("task id is not found");
        }
        Task task = taskOptional.get();
        if (task.getDeleted()) {
            log.warn("deleted don't set");
            throw new Exception("deleted don't set");
        }

        // 如果循环方式改变需要更新当天的taskRecord记录
        boolean recordUpdateFlag = false;
        if (!taskDto.getCycleTypeId().equals(task.getCycleType().getId())) {
            Date today = new Date();
            CycleType cycleTypeOld = task.getCycleType();
            CycleType cycleTypeNew = cycleTypeDao.getOne(taskDto.getCycleTypeId());
            if (cycleTypeNew == null) {
                throw new Exception("cycleType find is null");
            }
            Boolean todayEnableOld = cycleTypeService.filterByCronAndWorkday(cycleTypeOld, today);
            Boolean todayEnableNew = cycleTypeService.filterByCronAndWorkday(cycleTypeNew, today);

            if (todayEnableOld != todayEnableNew) {
                // 如果更改前的task有record，更改后没有，则删掉；
                // 如果更改前task无record，更改后有，则新增；
                if (todayEnableOld) {
                    List<TaskRecord> taskRecords = taskRecordDao.findByTaskOrderByCreateTimeDesc(task);
                    if (taskRecords != null) {
                        taskRecordDao.deleteById(taskRecords.get(0).getId());
                    }
                } else {
                    recordUpdateFlag = true;
                }
            }

        }

        taskDtoToTask(taskDto, task);
        taskDao.save(task);

        if (recordUpdateFlag) {
            TaskRecord taskRecordNew = ToUtil.taskToTaskRecord(task);
            taskRecordDao.save(taskRecordNew);
        }
    }

    @Override
    public void deleteOne(Integer id) throws Exception {
        Optional<Task> taskOptional = taskDao.findById(id);
        if (taskOptional.isPresent()) {
            Task task = taskOptional.get();
            task.setDeleted(true);
            taskDao.save(task);
        } else {
            throw new Exception("task id is not found");
        }
    }

    private void taskDtoToTask(TaskDto taskDto, Task task) throws Exception {
        task.setContent(taskDto.getContent());
        task.setDescription(taskDto.getDescription());
        task.setStatus(taskDto.getStatus());
        task.setScore(taskDto.getScore());
        CycleType cycleType = cycleTypeDao.getOne(taskDto.getCycleTypeId());
        if (cycleType == null) {
            throw new Exception("cycleType find is null");
        }
        task.setCycleType(cycleType);
    }

}
