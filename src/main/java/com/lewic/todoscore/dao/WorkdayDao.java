package com.lewic.todoscore.dao;

import com.lewic.todoscore.entity.Workday;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author lewic
 * @since 2020/1/16 22:13
 */

public interface WorkdayDao extends JpaRepository<Workday, Integer> {
}
