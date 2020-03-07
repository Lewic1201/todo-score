package com.lewic.todoscore.service;


import com.lewic.todoscore.dto.LoginReq;
import com.lewic.todoscore.dto.RegisterReq;
import com.lewic.todoscore.vo.LoginVo;

public interface AuthService {

    LoginVo login(LoginReq loginReq) throws Exception;

    void register(RegisterReq registerReq) throws Exception;

}
