package com.lewic.todoscore.rest;


import com.lewic.todoscore.common.ApiResult;
import com.lewic.todoscore.entity.jpa.primary.CycleType;
import com.lewic.todoscore.service.impl.CycleTypeServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author lewic
 * @apiNote 循环方式
 * @since 2019/6/13 15:22
 **/
@RestController
@RequestMapping("/v1/cycleType")
public class CycleTypeController {
    private final CycleTypeServiceImpl cycleTypeService;

    @Autowired
    public CycleTypeController(CycleTypeServiceImpl cycleTypeService) {
        this.cycleTypeService = cycleTypeService;
    }

    @ApiOperation("获取循环方式列表")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ApiResult listCycleType() {
        return ApiResult.success(cycleTypeService.listCycleType());
    }

    @ApiOperation("新增循环方式")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ApiResult insertCycleType(
            @ApiParam(value = "主题任务") @RequestBody CycleType cycleType
    ) {
        cycleTypeService.insertOne(cycleType);
        return ApiResult.success();
    }

    @ApiOperation("删除循环方式")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ApiResult deleteCycleType(@PathVariable(value = "id") Integer id) {
        cycleTypeService.deleteOne(id);
        return ApiResult.success();
    }

}

