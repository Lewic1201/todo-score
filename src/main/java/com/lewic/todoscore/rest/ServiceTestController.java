package com.lewic.todoscore.rest;


import com.alibaba.fastjson.JSON;
import com.lewic.todoscore.common.ApiResult;
import com.lewic.todoscore.common.ResponseCode;
import com.lewic.todoscore.exception.ClientException;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author lewic
 * @apiNote 任务
 * @since 2019/6/13 15:22
 **/
//@RestController为@Controller+@ResponstBody,会自动转换返回值为json
@RestController
@RequestMapping("/v1/service/test")
public class ServiceTestController {

    @ApiOperation("获取任务详情")
    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public boolean testClientException() throws ClientException {
        throw new ClientException(ResponseCode.INTERNAL_FAIL);
    }

    @ApiOperation("获取任务详情")
    @RequestMapping(value = "/error/json", method = RequestMethod.GET)
    public String testApiResult(){
        return JSON.toJSONString(ApiResult.error(ResponseCode.INTERNAL_FAIL));
    }


}

