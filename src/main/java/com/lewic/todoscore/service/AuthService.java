package com.lewic.todoscore.service;

import com.lewic.todoscore.entity.mybatis.vo.User;

public interface AuthService {

    Boolean login(String username, String password);

    void register(User user);
}
