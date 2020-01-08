package com.lewic.todoscore.rest;

import com.alibaba.fastjson.JSON;
import com.lewic.todoscore.common.ResponseCode;
import com.lewic.todoscore.entity.Subject;
import com.lewic.todoscore.service.SubjectService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author lewic
 * @apiNote 主题任务
 * @since 2019/6/13 15:22
 **/
@RestController
@RequestMapping("/v1/todayTask")
public class TodayTaskController {
    private final SubjectService subjectService;

    @Autowired
    public TodayTaskController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @ApiOperation("获取主题任务列表")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String listSubject(
            @ApiParam(value = "第几页") @RequestParam(required = false, value = "pageNum", defaultValue = "1") Integer pageNum,
            @ApiParam(value = "每页条数") @RequestParam(required = false, value = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        return JSON.toJSON(subjectService.list(pageNum, pageSize)).toString();
    }

    @ApiOperation("更新主题任务")
    @RequestMapping(value = "", method = RequestMethod.PATCH)
    public String updateSubject(@ApiParam(value = "主题任务") @RequestBody Subject subject) {
        if (subject != null && subject.getId() != null) {
            subjectService.update(subject);
        } else {
            return ResponseCode.ILLEGAL_ARGUMENT.getValue();
        }
        return ResponseCode.SUCCESS.getValue();
    }

}

