package com.lewic.todoscore.dao.jpa.primary;

import com.lewic.todoscore.entity.jpa.primary.CycleType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<CycleType, Integer> {
}
