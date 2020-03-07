package com.lewic.todoscore.rest;

import com.lewic.todoscore.common.ApiResult;
import com.lewic.todoscore.dto.LoginReq;
import com.lewic.todoscore.dto.RegisterReq;
import com.lewic.todoscore.exception.ClientException;
import com.lewic.todoscore.service.impl.AuthServiceImpl;
import com.lewic.todoscore.vo.LoginVo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author lewic
 * @apiNote 账号
 * @since 2019/6/13 15:22
 **/
@RestController
@RequestMapping("/v1")
public class AuthController {
    private final AuthServiceImpl authServiceImpl;

    @Autowired
    public AuthController(AuthServiceImpl authServiceImpl) {
        this.authServiceImpl = authServiceImpl;
    }

    @ApiOperation("登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ApiResult<LoginVo> login(@RequestBody LoginReq loginReq
    ) throws ClientException {
        return ApiResult.success(authServiceImpl.login(loginReq));
    }

    @ApiOperation("注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ApiResult register(@ApiParam(value = "用户信息") @RequestBody RegisterReq registerReq
    ) throws ClientException {
        authServiceImpl.register(registerReq);
        return ApiResult.success();
    }

}

