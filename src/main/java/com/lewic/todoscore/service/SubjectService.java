package com.lewic.todoscore.service;

import com.lewic.todoscore.dto.SubjectBean;
import com.lewic.todoscore.entity.Subject;
import com.lewic.todoscore.entity.User;

import java.util.List;

public interface SubjectService {

    Subject get(Integer id);

    SubjectBean list(Integer pageNum, Integer pageSize);

    void create(Subject subject);

    void update(Subject subject);

    void delete(Integer id);

    void getCount();
}
