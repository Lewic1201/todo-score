package com.lewic.todoscore.service;

import com.lewic.todoscore.dto.SubjectBean;
import com.lewic.todoscore.entity.Option;
import com.lewic.todoscore.entity.Subject;

import java.util.List;

public interface OptionService {

    Option get(Integer subjectId);

    List<Option> list(Integer pageNum, Integer pageSize);

    void create(Option option);

    void update(Option option);

    void delete(Integer id);

    void getCount();
}
