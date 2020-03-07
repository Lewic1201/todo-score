package com.lewic.todoscore.utils;

import com.lewic.todoscore.entity.Task;
import com.lewic.todoscore.entity.TaskRecord;

/**
 * @author lewic
 * 转换不同的对象,不相干类之中提取的公共转换
 * @since 2020/2/26 12:31
 */

public class ToUtil {

    /**
     * 生成清单与之对应的清单记录
     * @param task 清单
     * @return 清单记录
     */
    public static TaskRecord taskToTaskRecord(Task task){
        TaskRecord taskRecord = new TaskRecord();
        taskRecord.setTask(task);
        taskRecord.setContent(task.getContent());
        taskRecord.setDescription(task.getDescription());
        taskRecord.setScoreValue(task.getScore());
        taskRecord.setScore(0);
        // todo finish需修改设置为null
        taskRecord.setFinish(false);
        return taskRecord;
    }

}
