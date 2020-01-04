//package com.lewic.todoscore.service.impl;
//
//import com.lewic.todoscore.dao.UserMapper;
//import com.lewic.todoscore.dto.Message;
//import com.lewic.todoscore.common.Page;
//import com.lewic.todoscore.dto.Page;
//import com.lewic.todoscore.entity.User;
//import com.lewic.todoscore.mapper.UserMapper;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//
///**
// * @author lewic
// * @apiNote 账号
// * @since 2019/6/13 15:10
// **/
//
//@Service
//@Slf4j
//public class UserServiceImpl {
//    @Autowired
//    UserMapper userMapper;
//
//
//    public Message listUser(Integer pageNum, Integer pageSize) {
//        Integer total = userMapper.getCount();
//        Page page = new Page(pageSize, pageNum, total);
//        Map<String, Object> resultMap = new HashMap<>();
//
//        List<User> users = userMapper.listUser(page);
//        resultMap.put("userList", users);
//        resultMap.put("page", page);
//
//        Message<Map> msg = new Message<>();
//        msg.setData(resultMap);
//        return msg;
//    }
//
//
//    public Message getUser(String id) {
//        User user = userMapper.get(id);
//
//        Users<User> msg = new Message();
//        msg.setData(user);
//        return msg;
//    }
//
//
//    public Message insertUser(String nodeName, Integer status, String ipAddress, String appName, Integer alarmNum, String serverLog, String updateTime, String createTime) {
//        Message<String> msg = new Message<>();
//        try {
//            User user = new User(nodeName, status, ipAddress, appName, alarmNum, serverLog, updateTime, createTime);
//            userMapper.insert(user);
//            msg.setData("insert success");
//        } catch (Exception e) {
//            msg.setMessage("insert failed, because: " + e.getMessage());
//        }
//        return msg;
//    }
//
//    public Message updateUser(String id, String nodeName, Integer status, String ipAddress, String appName, Integer alarmNum, String serverLog, String updateTime, String createTime) {
//        Message<String> msg = new Message<>();
//        try {
//            User user = new User(id, nodeName, status, ipAddress, appName, alarmNum, serverLog, updateTime, createTime);
//            userMapper.update(user);
//            msg.setData("update success");
//        } catch (Exception e) {
//            msg.setMessage("update failed, because: " + e.getMessage());
//        }
//        return msg;
//    }
//
//    public Message deleteUser(String tpId) {
//        Message<String> msg = new Message<>();
//        try {
//            userMapper.delete(tpId);
//            msg.setData("delete success");
//        } catch (Exception e) {
//            msg.setMessage("delete failed, because:" + e.getMessage());
//        }
//        return msg;
//    }
//
//}
