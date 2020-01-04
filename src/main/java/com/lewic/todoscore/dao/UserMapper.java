package com.lewic.todoscore.dao;

import com.lewic.todoscore.dto.Page;
import com.lewic.todoscore.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author lewic
 * @apiNote 账号
 * @since 2019/6/14 16:37
 **/

@Mapper
public interface UserMapper {

    List<User> list(@Param("page") Page page);

    User get(@Param("id") Integer id);

    Integer getCount();

    void insert(User user);

    void update(User user);

    void delete(@Param("id") Integer id);

    User login(@Param("username") String username,
               @Param("password") String password);
}

