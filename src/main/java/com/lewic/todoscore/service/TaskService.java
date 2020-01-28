package com.lewic.todoscore.service;

import com.lewic.todoscore.entity.jpa.primary.Task;

import java.util.List;

public interface TaskService {

    void insertOne(Task task);

    List<Task> listAll();

    Task showOne(Integer id);

    void updateOne(Task task);

    void deleteOne(Integer id);
}
