package com.lewic.todoscore.intercepter;

import com.lewic.todoscore.common.Constants;
import com.lewic.todoscore.common.ResponseCode;
import com.lewic.todoscore.dao.AccessTraceDao;
import com.lewic.todoscore.entity.AccessTrace;
import com.lewic.todoscore.utils.HttpUtil;
import com.lewic.todoscore.utils.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 记录请求日志
 * @author lewic
 * @since 2020/3/7 16:32
 */
@Component
@Slf4j
public class TraceInterceptor implements HandlerInterceptor {

    private AccessTraceDao accessTraceDao;

    @Autowired
    public TraceInterceptor(AccessTraceDao accessTraceDao) {
        this.accessTraceDao = accessTraceDao;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        try {
            AccessTrace accessTrace = new AccessTrace();
            accessTrace.setReqMethod(request.getMethod());
            accessTrace.setReqPath(request.getServletPath());
            String token = request.getHeader(Constants.TOKEN);
            if (token != null) {
                String username = TokenUtil.getJwtUsername(token);
                if (username != null) {
                    accessTrace.setUsername(username);
                }
            }
            accessTrace.setReqPath(request.getServletPath());
            String reqIp = HttpUtil.getReqIp(request);
            if (reqIp != null) {
                accessTrace.setReqIp(reqIp);
            }
            accessTraceDao.save(accessTrace);

        } catch (Exception e) {
            log.error(ResponseCode.NO_REQ_TRACE.getMessage());
        }
        return true;
    }

}