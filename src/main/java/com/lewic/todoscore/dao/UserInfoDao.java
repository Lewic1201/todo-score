package com.lewic.todoscore.dao;

import com.lewic.todoscore.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoDao extends JpaRepository<UserInfo, Integer> {
}
