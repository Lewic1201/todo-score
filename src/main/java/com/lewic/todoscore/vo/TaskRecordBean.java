package com.lewic.todoscore.vo;

import com.lewic.todoscore.common.Page;
import com.lewic.todoscore.vo.base.BaseBeanList;
import com.lewic.todoscore.entity.mybatis.master.TaskRecordI;

import java.util.List;

public class TaskRecordBean extends BaseBeanList<TaskRecordI> {

    public TaskRecordBean() {
    }

    public TaskRecordBean(Page page, List<TaskRecordI> data) {
        super(page, data);
    }

}
