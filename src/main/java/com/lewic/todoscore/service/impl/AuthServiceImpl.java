package com.lewic.todoscore.service.impl;

import com.lewic.todoscore.common.ApiResult;
import com.lewic.todoscore.common.Enums.RoleType;
import com.lewic.todoscore.common.Enums.AccountStatus;
import com.lewic.todoscore.common.ResponseCode;
import com.lewic.todoscore.dao.UserDao;
import com.lewic.todoscore.dao.UserInfoDao;
import com.lewic.todoscore.dto.LoginReq;
import com.lewic.todoscore.dto.RegisterReq;
import com.lewic.todoscore.entity.Task;
import com.lewic.todoscore.entity.User;
import com.lewic.todoscore.entity.UserInfo;
import com.lewic.todoscore.exception.ClientException;
import com.lewic.todoscore.service.AuthService;
import com.lewic.todoscore.utils.StringUtil;
import com.lewic.todoscore.utils.TokenUtil;
import com.lewic.todoscore.vo.LoginVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author lewic
 * @since 2020/1/4 21:46
 */
@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserDao userDao;

    private final UserInfoDao userInfoDao;

    @Autowired
    public AuthServiceImpl(UserDao userDao, UserInfoDao userInfoDao) {
        this.userDao = userDao;
        this.userInfoDao = userInfoDao;
    }

    @Override
    public LoginVo login(LoginReq loginReq) throws ClientException {
        // todo 密码需要加密
        User loginUser = userDao.findByUsername(loginReq.getUsername());
        if (loginUser == null) {
            throw new ClientException(ResponseCode.USER_NO_EXISTS);
        }
        if (!loginUser.getPassword().equals(loginReq.getPassword())) {
            throw new ClientException(ResponseCode.USER_INFO_ERROR);
        } else {
            String token = TokenUtil.sign(loginUser);
            return new LoginVo(token);
        }
    }

    @Override
    @Transactional
    public void register(RegisterReq registerReq) throws ClientException {
        this.check(registerReq);
        User user = new User();
        user.setUsername(registerReq.getUsername());
        user.setPassword(registerReq.getPassword());
        user.setRole(RoleType.COMMON.getCode());
        user.setStatus(AccountStatus.NORMAL.getCode());
        userDao.save(user);
        userDao.flush();
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(user.getId());
        userInfo.setAddress(registerReq.getAddress());
        userInfo.setBirthday(registerReq.getBirthday());
        userInfo.setMobile(registerReq.getMobile());
        userInfo.setEmail(registerReq.getEmail());
        userInfoDao.save(userInfo);
    }

    private void check(RegisterReq registerReq) throws ClientException {
        // todo 每种错误单独设置对应的错误码
        if (StringUtil.isEmpty(registerReq.getUsername())) {
            throw new ClientException(ResponseCode.PARAMETER_ERROR, "用户名不能为空");
        }
        if (StringUtil.isEmpty(registerReq.getPassword())) {
            throw new ClientException(ResponseCode.PARAMETER_ERROR, "密码不能为空");
        }
        if (StringUtil.isEmpty(registerReq.getMobile())) {
            throw new ClientException(ResponseCode.PARAMETER_ERROR, "手机号不能为空");
        }
        // todo 用户名;密码;手机号;邮箱;校验正则
        // todo 生日必须早于现在
        // todo sex校验
        try {
            // 用户名不能重复
            User userCheck = userDao.findByUsername(registerReq.getUsername());
            if (userCheck != null && userCheck.getUsername().equals(registerReq.getUsername())) {
                throw new ClientException(ResponseCode.USER_EXISTS);
            }
        } catch (ClientException e) {
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ClientException(ResponseCode.SELECT_ERROR);
        }
    }
}
