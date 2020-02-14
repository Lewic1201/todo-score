package com.lewic.todoscore.dao.jpa.primary;

import com.lewic.todoscore.entity.jpa.primary.Task;
import com.lewic.todoscore.entity.jpa.primary.TaskRecord;
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
}
