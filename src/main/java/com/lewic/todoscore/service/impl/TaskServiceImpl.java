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
        taskDao.save(task);
    }

    @Override
    public List<Task> listAll() {
        return taskDao.findAll();
    }

    @Override
    public Task showOne(Integer id) {
        Optional<Task> optionalTask = taskDao.findById(id);
        return optionalTask.orElse(null);
    }

    @Override
    public void updateOne(Task task) {
        taskDao.save(task);
    }

    @Override
    public void deleteOne(Integer id) {
        taskDao.deleteById(id);
    }
}
