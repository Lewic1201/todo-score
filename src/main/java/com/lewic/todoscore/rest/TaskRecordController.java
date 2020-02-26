package com.lewic.todoscore.rest;


import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lewic.todoscore.common.ApiResult;
import com.lewic.todoscore.common.Constants;
import com.lewic.todoscore.common.View;
import com.lewic.todoscore.exception.ClientException;
import com.lewic.todoscore.service.TaskRecordService;
import com.lewic.todoscore.vo.ScoreInfoVo;
import com.lewic.todoscore.vo.TaskRecordBean;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


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

    @ApiOperation("获取某天所有的任务")
    @RequestMapping(value = "/day/{day}", method = RequestMethod.GET)
    @JsonView(View.Summary.class)
    public String day(@PathVariable(value = "day") @DateTimeFormat(pattern = Constants.DAY_REST_FORMAT) Date day)
            throws Exception {
        ApiResult apiResult = ApiResult.success(taskRecordService.listByDay(day));
        // 使用View过滤
        return mapper.writerWithView(View.Summary.class).writeValueAsString(apiResult);
    }

    @ApiOperation("获取某天的得分")
    @RequestMapping(value = "/day/{day}/score", method = RequestMethod.GET)
    public ApiResult<ScoreInfoVo> dayScore(@PathVariable(value = "day") @DateTimeFormat(pattern = Constants.DAY_REST_FORMAT) Date day)
            throws Exception {
        ScoreInfoVo scoreInfoVo = taskRecordService.getDayTotalScore(day);
        return ApiResult.success(scoreInfoVo);
    }

    @ApiOperation("获取今天所有的任务")
    @RequestMapping(value = "/today", method = RequestMethod.GET)
    @JsonView(View.Summary.class)
    public String today() throws Exception {
        ApiResult apiResult = ApiResult.success(taskRecordService.listByToday());
        return mapper.writerWithView(View.Summary.class).writeValueAsString(apiResult);
    }

    @ApiOperation("获取今天的得分")
    @RequestMapping(value = "/today/score", method = RequestMethod.GET)
    public ApiResult todayScore() throws Exception {
        ScoreInfoVo scoreInfoVo = taskRecordService.getTodayTotalScore();
        return ApiResult.success(scoreInfoVo);
    }

    @ApiOperation("获取历史得分记录（最近的分数在前）")
    @RequestMapping(value = "/history/score", method = RequestMethod.GET)
    public ApiResult historyScore() {
        // todo 获取历史得分；排序
        return ApiResult.success();
    }

    @ApiOperation("获取历史记录")
    @RequestMapping(value = "/history", method = RequestMethod.GET)
    public ApiResult<TaskRecordBean> history(
            @ApiParam(value = "第几页")
            @RequestParam(required = false, value = "pageNum", defaultValue = "1")
                    Integer pageNum,
            @ApiParam(value = "每页条数")
            @RequestParam(required = false, value = "pageSize", defaultValue = "10")
                    Integer pageSize) {
        // todo finish要置空，为未完成的状态，这块查不出来
        return ApiResult.success(taskRecordService.listByFinishNotNull(pageNum, pageSize));
    }

    @ApiOperation("完成后标记")
    @RequestMapping(value = "/finish/{id}/{finish}", method = RequestMethod.PATCH)
    public ApiResult edit(@PathVariable(value = "id") Integer id, @PathVariable(value = "finish") Boolean finish)
            throws ClientException {
        taskRecordService.updateFinishStatus(id, finish);
        return ApiResult.success();
    }

}

