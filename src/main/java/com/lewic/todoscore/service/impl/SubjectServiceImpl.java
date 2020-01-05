package com.lewic.todoscore.service.impl;

import com.lewic.todoscore.dao.OptionMapper;
import com.lewic.todoscore.dao.SubjectMapper;
import com.lewic.todoscore.dto.Page;
import com.lewic.todoscore.dto.SubjectBean;
import com.lewic.todoscore.entity.Option;
import com.lewic.todoscore.entity.Subject;
import com.lewic.todoscore.service.SubjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @author lewic
 * @apiNote 主题任务
 * @since 2019/6/13 15:10
 **/

@Service
@Slf4j
public class SubjectServiceImpl implements SubjectService {
    private final SubjectMapper subjectMapper;
    private final OptionMapper optionMapper;

    @Autowired
    public SubjectServiceImpl(SubjectMapper subjectMapper, OptionMapper optionMapper) {
        this.subjectMapper = subjectMapper;
        this.optionMapper = optionMapper;
    }

    public SubjectBean list(Integer pageNum, Integer pageSize) {
        Integer total = subjectMapper.getCount();
        Page page = new Page(pageSize, pageNum, total);

        List<Subject> subjects = subjectMapper.list(page);

        SubjectBean subjectBean = new SubjectBean();
        subjectBean.setData(subjects);
        subjectBean.setCount(total);
        subjectBean.setPageNum(pageNum);
        return subjectBean;
    }


    public Subject get(Integer id) {
        Subject subject = subjectMapper.get(id);
        if (subject == null) {
            throw new NullPointerException();
        }
        return subject;
    }

    @Transactional
    public void create(Subject subject) {
        if (subject != null) {
            subjectMapper.insert(subject);
            if (subject.getOptions() != null) {
                List<Option> options = subject.getOptions();
                for (Option option : options) {
                    option.setSubjectId(subject.getId());
                    optionMapper.insert(option);
                }
            }
        }
    }

    @Transactional
    public void update(Subject subject) {
        if (subject != null) {
            subjectMapper.update(subject);
            if (subject.getOptions() != null) {
                List<Option> options = subject.getOptions();
                for (Option option : options) {
                    optionMapper.update(option);
                }
            }
        }
    }

    @Transactional
    public void delete(Integer id) {
        subjectMapper.delete(id);
        optionMapper.delete(id);
    }

    public void getCount() {
        subjectMapper.getCount();
    }


}
