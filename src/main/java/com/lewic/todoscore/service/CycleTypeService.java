package com.lewic.todoscore.service;

import com.lewic.todoscore.entity.jpa.primary.CycleType;

import java.util.List;

public interface CycleTypeService {

    List<CycleType> listByToday();

    void insertOne(CycleType cycleType);

}
