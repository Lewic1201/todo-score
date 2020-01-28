package com.lewic.todoscore.dao.jpa.primary;

import com.lewic.todoscore.entity.jpa.primary.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author lewic
 * @since 2020/1/16 22:13
 */

public interface TaskDao extends JpaRepository<Task, Integer>, JpaSpecificationExecutor<Task> {

//    @Query(value = "SELECT * FROM task WHERE cycle_type_id IN (:ids)",nativeQuery = true)
//    List<Task> listTaskByCycleTypeIds(@Param("ids") List<String> ids);

    List<Task> findByCycleTypeIdIn(Integer[] ids);

//    List<Integer> findIdByCycleTypeIdIn(Integer[] ids);
}
