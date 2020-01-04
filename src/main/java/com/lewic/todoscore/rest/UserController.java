//package com.lewic.todoscore.rest;
//
//import com.lewic.todoscore.dto.Message;
//import com.lewic.todoscore.service.impl.UserServiceImpl;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiParam;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//
///**
// * @author lewic
// * @apiNote 账号
// * @since 2019/6/13 15:22
// **/
//@RestController
//@RequestMapping("/api/v1.0")
//public class UserController {
//    @Autowired
//    UserServiceImpl userService;
//
//
//    @ApiOperation("获取账号列表")
//    @RequestMapping(value = "/user", method = RequestMethod.GET)
//    public Message listUser(
//            @ApiParam(value = "第几页") @RequestParam(required = false, value = "pageNum", defaultValue = "1") Integer pageNum,
//            @ApiParam(value = "每页条数") @RequestParam(required = false, value = "pageSize", defaultValue = "10") Integer pageSize
//    ) {
//        return userService.listUser(pageNum, pageSize);
//    }
//
//    @ApiOperation("根据id查询账号")
//    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
//    public Message getUser(
//            @PathVariable(value = "id") String id
//    ) {
//        return userService.getUser(id);
//    }
//
//    @ApiOperation("新建账号")
//    @RequestMapping(value = "/user", method = RequestMethod.POST)
//    public Message insertUser(
//            @ApiParam(value = "节点名称") @RequestParam(value = "nodeName") String nodeName,
//            @ApiParam(value = "状态") @RequestParam(value = "status") Integer status,
//            @ApiParam(value = "ip地址") @RequestParam(value = "ipAddress") String ipAddress,
//            @ApiParam(value = "应用名称") @RequestParam(value = "appName") String appName,
//            @ApiParam(value = "告警数") @RequestParam(value = "alarmNum") Integer alarmNum,
//            @ApiParam(value = "服务器日志") @RequestParam(value = "serverLog") String serverLog,
//            @ApiParam(value = "更新时间") @RequestParam(value = "updateTime") String updateTime,
//            @ApiParam(value = "创建时间") @RequestParam(value = "createTime") String createTime
//    ) {
//        return userService.insertUser(nodeName, status, ipAddress, appName, alarmNum, serverLog, updateTime, createTime);
//    }
//
//
//    @ApiOperation("更新账号")
//    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
//    public Message updateUser(
//            @PathVariable(value = "id") String id,
//            @ApiParam(value = "节点名称") @RequestParam(value = "nodeName") String nodeName,
//            @ApiParam(value = "状态") @RequestParam(value = "status") Integer status,
//            @ApiParam(value = "ip地址") @RequestParam(value = "ipAddress") String ipAddress,
//            @ApiParam(value = "应用名称") @RequestParam(value = "appName") String appName,
//            @ApiParam(value = "告警数") @RequestParam(value = "alarmNum") Integer alarmNum,
//            @ApiParam(value = "服务器日志") @RequestParam(value = "serverLog") String serverLog,
//            @ApiParam(value = "更新时间") @RequestParam(value = "updateTime") String updateTime,
//            @ApiParam(value = "创建时间") @RequestParam(value = "createTime") String createTime
//    ) {
//        return userService.updateUser(id, nodeName, status, ipAddress, appName, alarmNum, serverLog, updateTime, createTime);
//    }
//
//    @ApiOperation("删除账号")
//    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
//    public Message deleteUser(@PathVariable(value = "id") String id) {
//        return userService.deleteUser(id);
//    }
//
//}
//
