package com.lewic.todoscore.dao;

import com.lewic.todoscore.entity.Task;
import com.lewic.todoscore.entity.TaskRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * @author lewic
 * @since 2020/1/19 0:41
 */

public interface TaskRecordDao extends JpaRepository<TaskRecord, Integer> {

    List<TaskRecord> findAllByCreateTimeGreaterThanEqualAndCreateTimeLessThan(Date dateStart, Date dateEnd);

    List<TaskRecord> findByTaskOrderByCreateTimeDesc(Task task);

    Integer countByFinishNotNull();

    Page<TaskRecord> findByFinishNotNull(Pageable pageable);

}
