package com.lewic.todoscore.intercepter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * 拦截器配置
 *
 * @author lewic
 * @since 2020/3/7 16:27
 */

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    private TraceInterceptor traceInterceptor;

    private LoginInterceptor loginInterceptor;

    //构造方法
    public InterceptorConfig(TraceInterceptor traceInterceptor, LoginInterceptor loginInterceptor) {
        this.traceInterceptor = traceInterceptor;
        this.loginInterceptor = loginInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 请求记录
        registry.addInterceptor(traceInterceptor)
                .addPathPatterns("/**");

        // 放行接口:注册;登录;登出;静态资源
        List<String> excludePath = new ArrayList<>();
        excludePath.add("/v1/register");
        excludePath.add("/v1/login");
        excludePath.add("/v1/logout");
        excludePath.add("/static/**");
        excludePath.add("/assets/**");

        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/v1/**")
                .excludePathPatterns(excludePath);

        WebMvcConfigurer.super.addInterceptors(registry);
    }

}