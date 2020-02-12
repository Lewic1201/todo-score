package com.lewic.todoscore.service.impl;

import com.lewic.todoscore.dao.jpa.primary.TaskDao;
import com.lewic.todoscore.entity.jpa.primary.Task;
import com.lewic.todoscore.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TaskServiceImpl implements TaskService {

    private final TaskDao taskDao;

    @Autowired
    public TaskServiceImpl(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    @Override
    public void insertOne(Task task) {
        task.setDeleted(false);
        if(task.getId()!=null){
            task.setId(null);
        }
        taskDao.save(task);
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
    public void updateOne(Task task) throws Exception {
        if (task.getId() != null) {
            Optional<Task> taskOptional = taskDao.findById(task.getId());
            if (!taskOptional.isPresent()) {
                log.error("task not found");
                throw new Exception("task id is not found");
            }
        }else {
            log.error("task id is null");
            throw new Exception("task id is not found");
        }
        if (task.getDeleted()) {
            log.warn("deleted don't set");
            task.setDeleted(false);
        }
        taskDao.save(task);
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
        ;
    }
}
