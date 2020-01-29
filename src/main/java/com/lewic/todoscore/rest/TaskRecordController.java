package com.lewic.todoscore.rest;


import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lewic.todoscore.common.ResponseCode;
import com.lewic.todoscore.common.View;
import com.lewic.todoscore.service.TaskRecordService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author lewic
 * @apiNote 任务
 * @since 2019/6/13 15:22
 **/
@RestController
@RequestMapping("/v1/record/task")
public class TaskRecordController {

    private final TaskRecordService taskRecordService;

    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public TaskRecordController(TaskRecordService taskRecordService) {
        this.taskRecordService = taskRecordService;
    }

    @ApiOperation("获取今天所有的任务")
    @RequestMapping(value = "/today", method = RequestMethod.GET)
    @JsonView(View.Summary.class)
    public String today() throws Exception {
        return mapper.writerWithView(View.Summary.class).writeValueAsString(taskRecordService.listByToday());
    }

    @ApiOperation("获取今天的得分")
    @RequestMapping(value = "/today/score", method = RequestMethod.GET)
    public String todayScore() {
        return taskRecordService.getTodayTotalScore().toString();
    }

    @ApiOperation("获取历史得分记录（最近的分数在前）")
    @RequestMapping(value = "/history/score", method = RequestMethod.GET)
    public String historyScore() {
        // todo 获取历史得分；排序
        return ResponseCode.SUCCESS.getValue();
    }

    @ApiOperation("获取历史记录")
    @RequestMapping(value = "/history", method = RequestMethod.GET)
    public String history(
            @ApiParam(value = "第几页")
            @RequestParam(required = false, value = "pageNum", defaultValue = "1")
                    Integer pageNum,
            @ApiParam(value = "每页条数")
            @RequestParam(required = false, value = "pageSize", defaultValue = "10")
                    Integer pageSize) {
        return JSON.toJSON(taskRecordService.listByFinishNotNull(pageNum, pageSize)).toString();
    }

    @ApiOperation("完成后标记")
    @RequestMapping(value = "/{id}/{finish}", method = RequestMethod.PATCH)
    public String edit(@PathVariable(value = "id") Integer id, @PathVariable(value = "finish") Boolean finish) {
        taskRecordService.updateFinishStatus(id, finish);
        return ResponseCode.SUCCESS.getValue();
    }

}

