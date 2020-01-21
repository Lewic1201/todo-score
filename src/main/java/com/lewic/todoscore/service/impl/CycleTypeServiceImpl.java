package com.lewic.todoscore.service.impl;

import com.lewic.todoscore.dao.jpa.primary.CycleTypeDao;
import com.lewic.todoscore.dao.jpa.primary.TaskRecordDao;
import com.lewic.todoscore.entity.jpa.primary.CycleType;
import com.lewic.todoscore.entity.jpa.primary.TaskRecord;
import com.lewic.todoscore.service.CycleTypeService;
import com.lewic.todoscore.service.TaskRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * @author lewic
 * @since 2020/1/19 0:58
 */
@Slf4j
@Service
public class CycleTypeServiceImpl implements CycleTypeService {

    private final CycleTypeDao cycleTypeDao;

    @Autowired
    public CycleTypeServiceImpl(CycleTypeDao cycleTypeDao) {
        this.cycleTypeDao = cycleTypeDao;
    }

    @Override
    public List<CycleType> listByToday() {
        return null;
    }

    @Override
    public void insertOne(CycleType cycleType) {

    }
}
