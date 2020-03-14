package com.lewic.todoscore.dao;

import com.lewic.todoscore.entity.AccessTrace;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessTraceDao extends JpaRepository<AccessTrace, Integer> {
}
