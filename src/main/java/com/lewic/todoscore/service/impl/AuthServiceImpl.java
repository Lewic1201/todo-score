package com.lewic.todoscore.service.impl;

import com.lewic.todoscore.common.Enums.RoleType;
import com.lewic.todoscore.common.Enums.AccountStatus;
import com.lewic.todoscore.dao.mybatis.master.UserMapper;
import com.lewic.todoscore.entity.mybatis.vo.User;
import com.lewic.todoscore.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lewic
 * @since 2020/1/4 21:46
 */
@Service
@Slf4j
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public Boolean login(String username, String password) {
        User loginUser = userMapper.login(username, password);
        return loginUser != null && loginUser.getUsername() != null;
    }

    @Override
    public void register(User user) {
        user.setRole(RoleType.ADMIN.getCode());
        user.setStatus(AccountStatus.NORMAL.getCode());
        userMapper.insert(user);
    }
}
