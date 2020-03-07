package com.lewic.todoscore.dao;

import com.lewic.todoscore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Integer> {

    User findByUsernameAndPassword(String username, String password);

}
