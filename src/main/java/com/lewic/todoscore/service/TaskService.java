package com.lewic.todoscore.service;

import com.lewic.todoscore.dto.TaskDto;
import com.lewic.todoscore.entity.jpa.primary.Task;

import java.util.List;

public interface TaskService {

    void insertOne(TaskDto task) throws Exception;

    List<Task> listAllNormal();

    Task showOne(Integer id) throws Exception;

    void updateOne(TaskDto task) throws Exception;

    void deleteOne(Integer id) throws Exception;
}
