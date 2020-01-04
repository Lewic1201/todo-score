package com.lewic.todoscore.service;

import com.lewic.todoscore.entity.User;

import java.util.List;

public interface UserService {

    User get(String id);

    List<User> list();

    void insert(User user);
}
