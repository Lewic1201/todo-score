package com.lewic.todoscore.rest;

import com.lewic.todoscore.entity.User;
import com.lewic.todoscore.service.impl.AuthServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    public String login(
            @ApiParam(value = "用户名") @RequestParam(value = "username") String username,
            @ApiParam(value = "密码") @RequestParam(value = "password") String password
    ) {
        return authServiceImpl.login(username, password).toString();
    }

    @ApiOperation("注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void register(@ApiParam(value = "用户信息") @RequestBody User user) {
        authServiceImpl.register(user);
    }

}

