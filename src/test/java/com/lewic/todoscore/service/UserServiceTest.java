package com.lewic.todoscore.service;

import com.lewic.todoscore.dao.mybatis.master.UserMapper;
import com.lewic.todoscore.dao.jpa.primary.TaskDao;
import com.lewic.todoscore.common.Page;
import com.lewic.todoscore.entity.mybatis.vo.User;
import com.lewic.todoscore.entity.jpa.primary.Task;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author lewic
 * @since 2020/1/16 22:09
 */
@Slf4j
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TaskDao taskDao;

    @BeforeAll
    static void beforeClass(){
    }

    @AfterAll
    static void afterClass(){
    }


    @BeforeEach
    void beforeTest(){
        System.out.println("@Before");
    }

    @AfterEach
    void afterTest(){
        System.out.println("@After");
    }

    @Test
    void baseTest() throws Exception {
        User user = new User();
        user.setId(10000);
        user.setUsername("zhang3");
        user.setPassword("123456");
//        userDao.save(user);
        userMapper.insert(user);
        List<User> users = userMapper.list(new Page(10,10));
        log.info(users.toString());
    }

    @Test
    void taskTest() throws Exception {
        Task task = new Task();
        task.setContent("早睡");
        task.setScore(10);
        task.setStatus(1);
        task.setDescription("好好休息");
        taskDao.save(task);
        List<Task> tasks = taskDao.findAll();
        log.info(tasks.toString());
    }
}
