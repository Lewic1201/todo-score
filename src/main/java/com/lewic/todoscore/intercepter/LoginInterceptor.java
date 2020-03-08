package com.lewic.todoscore.intercepter;

import com.alibaba.fastjson.JSON;
import com.lewic.todoscore.common.ApiResult;
import com.lewic.todoscore.common.Constants;
import com.lewic.todoscore.common.ResponseCode;
import com.lewic.todoscore.dao.UserDao;
import com.lewic.todoscore.entity.User;
import com.lewic.todoscore.utils.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lewic
 * @since 2020/3/7 16:32
 */
@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    private final UserDao userDao;

    @Autowired
    public LoginInterceptor(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // todo 可以和下面的options合并
        // 登录验证开关
        if (!Constants.LOGIN_SWITCH) {
            return true;
        }

        // options请求,用于获取目的资源所支持的通信选项
        if (request.getMethod().equals("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }

        String token = request.getHeader("token");
        if (token != null) {
            // todo 处理删除用户
            User user = userDao.findByUsername(TokenUtil.getJwtUsername(token));
            if (user != null) {
                boolean result = TokenUtil.verify(token, user);
                if (result) {
                    log.debug("token verify success.");
                    return true;
                }
            }
        }

        // 验证失败处理
        response.setCharacterEncoding(Constants.DEFAULT_ENCODING);
        response.setContentType(Constants.DEFAULT_CONTENT_TYPE);
        try {
            ApiResult res = ApiResult.error(ResponseCode.AUTHENTICATION_ERROR);
            response.getWriter().append(JSON.toJSONString(res));
            log.warn("token verify failed!!!!!!!!!!!!!");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(ResponseCode.INTERNAL_FAIL.getCode());
            return false;
        }
        return false;
    }

}