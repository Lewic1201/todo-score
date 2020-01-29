package com.lewic.todoscore.dto;

import com.lewic.todoscore.common.Page;
import com.lewic.todoscore.dto.base.BaseBeanList;
import com.lewic.todoscore.entity.mybatis.vo.TaskRecordI;

import java.util.List;

public class TaskRecordBean extends BaseBeanList<TaskRecordI> {

    public TaskRecordBean() {
    }

    public TaskRecordBean(Page page, List<TaskRecordI> data) {
        super(page, data);
    }

}
