package com.lewic.todoscore.service;

import com.lewic.todoscore.entity.jpa.primary.Task;

import java.util.List;

public interface TaskService {

    void insertOne(Task task);

    List<Task> listAllNormal();

    Task showOne(Integer id) throws Exception;

    void updateOne(Task task) throws Exception;

    void deleteOne(Integer id) throws Exception;
}
