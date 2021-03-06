package com.lewic.todoscore.rest;


import com.alibaba.fastjson.JSON;
import com.lewic.todoscore.common.ResponseCode;
import com.lewic.todoscore.dto.TaskDto;
import com.lewic.todoscore.entity.jpa.primary.Task;
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
    public String list() {
        return JSON.toJSON(taskService.listAllNormal()).toString();
    }

    @Override
    @ApiOperation("获取任务详情")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String show(@PathVariable(value = "id") Integer id) throws Exception {
        Task task = taskService.showOne(id);
        if (task != null) {

            return JSON.toJSON(taskService.showOne(id)).toString();
        } else {
            return "no found";
        }
    }

    @Override
    @ApiOperation("新增任务")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String create(@ApiParam(value = "任务") @RequestBody TaskDto taskDto) throws Exception {
        // todo 新增任务如果满足今天的条件，需要在taskRecord中新增记录
        taskService.insertOne(taskDto);
        return ResponseCode.SUCCESS.getValue();
    }

    @Override
    @ApiOperation("修改任务")
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public String edit(TaskDto obj) throws Exception {

        taskService.updateOne(obj);
        return ResponseCode.SUCCESS.getValue();
    }

    @Override
    @ApiOperation("删除任务")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable(value = "id") Integer id) throws Exception {
        //todo 任务关联不能删除
        taskService.deleteOne(id);
        return ResponseCode.SUCCESS.getValue();
    }

}

