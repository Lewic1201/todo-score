package com.lewic.todoscore.rest;


import com.lewic.todoscore.common.ApiResult;
import com.lewic.todoscore.common.ResponseCode;
import com.lewic.todoscore.dto.TaskDto;
import com.lewic.todoscore.entity.Task;
import com.lewic.todoscore.exception.ClientException;
import com.lewic.todoscore.rest.base.BaseRestFul;
import com.lewic.todoscore.service.TaskService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @author lewic
 * @apiNote 任务
 * @since 2019/6/13 15:22
 **/
@RestController
@RequestMapping("/v1/task")
public class TaskController implements BaseRestFul<TaskDto> {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    @ApiOperation("获取所有的任务列表")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ApiResult<List<Task>> list() {
        return ApiResult.success(taskService.listAllNormal());
    }

    @Override
    @ApiOperation("获取任务详情")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ApiResult<Task> show(@PathVariable(value = "id") Integer id) throws Exception {
        Task task = taskService.showOne(id);
        if (task != null) {
            return ApiResult.success(taskService.showOne(id));
        } else {
            throw new ClientException(ResponseCode.PARAMETER_ERROR, "task id is null");
        }
    }

    @Override
    @ApiOperation("新增任务")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ApiResult create(@ApiParam(value = "任务") @RequestBody TaskDto taskDto) throws Exception {
        taskService.insertOne(taskDto);
        return ApiResult.success();
    }

    @Override
    @ApiOperation("修改任务")
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public ApiResult edit(TaskDto obj) throws Exception {
        taskService.updateOne(obj);
        return ApiResult.success();
    }

    @Override
    @ApiOperation("删除任务")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ApiResult delete(@PathVariable(value = "id") Integer id) throws Exception {
        taskService.deleteOne(id);
        return ApiResult.success();
    }

}

