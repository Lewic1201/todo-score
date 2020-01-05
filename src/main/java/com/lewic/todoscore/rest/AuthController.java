package com.lewic.todoscore.rest;

import com.lewic.todoscore.entity.User;
import com.lewic.todoscore.service.impl.AuthServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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
    public String login(
            @ApiParam(value = "用户名") @RequestParam(value = "username") String username,
            @ApiParam(value = "密码") @RequestParam(value = "password") String password
    ) {
        return authServiceImpl.login(username, password).toString();
    }

    @ApiOperation("新建账号")
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public void insertUser(@ApiParam(value = "账户信息") @RequestBody User user) {
        authServiceImpl.register(user);
    }

}

