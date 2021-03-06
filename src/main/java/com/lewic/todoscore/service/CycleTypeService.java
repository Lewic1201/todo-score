package com.lewic.todoscore.service;

import com.lewic.todoscore.entity.jpa.primary.CycleType;

import java.util.Date;
import java.util.List;

public interface CycleTypeService {

    Integer[] listCycleTypeIdByToday() throws Exception;

    List<CycleType> listCycleType();

    void insertOne(CycleType cycleType);

    void deleteOne(Integer id);

    Boolean filterByCronAndWorkday(CycleType cycleType, Date date) throws Exception;
}
