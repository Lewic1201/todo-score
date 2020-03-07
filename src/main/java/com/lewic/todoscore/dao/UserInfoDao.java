package com.lewic.todoscore.dao;

import com.lewic.todoscore.entity.CycleType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoDao extends JpaRepository<CycleType, Integer> {
}
