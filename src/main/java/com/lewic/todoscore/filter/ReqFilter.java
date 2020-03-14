package com.lewic.todoscore.filter;

import com.lewic.todoscore.common.Constants;
import com.lewic.todoscore.common.ResponseCode;
import com.lewic.todoscore.dao.AccessTraceDao;
import com.lewic.todoscore.dao.UserDao;
import com.lewic.todoscore.entity.AccessTrace;
import com.lewic.todoscore.utils.HttpUtil;
import com.lewic.todoscore.utils.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;


/**
 * @author lewic
 * @since 2020/3/14 15:36
 */

@Slf4j
@WebFilter(urlPatterns = "/v2*", filterName = "reqFilter")
public class ReqFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {

    }

    /**
     * 1. 过滤器是在web应用启动的时候初始化一次, 在web应用停止的时候销毁.
     * 2. 可以对请求的URL进行过滤, 对敏感词过滤,
     * 3. 挡在拦截器的外层
     * 4. Filter 是 Servlet 规范的一部分
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}