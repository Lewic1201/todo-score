package com.lewic.todoscore.vo;


import com.lewic.todoscore.entity.TaskRecord;
import com.lewic.todoscore.vo.base.BaseBeanList;

import java.util.List;


public class TaskRecordBean extends BaseBeanList<TaskRecord> {

    public TaskRecordBean() {
    }

    public TaskRecordBean(long count, List<TaskRecord> data) {
        super((int) count, data);
    }

}
