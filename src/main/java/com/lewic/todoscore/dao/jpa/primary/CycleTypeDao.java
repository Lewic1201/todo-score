package com.lewic.todoscore.dao.jpa.primary;

import com.lewic.todoscore.entity.jpa.primary.CycleType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author lewic
 * @since 2020/1/16 22:13
 */

public interface CycleTypeDao extends JpaRepository<CycleType, Integer> {
}
