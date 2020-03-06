package com.lewic.todoscore.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author lewic
 * @since 2020/1/6 22:16
 */

@Slf4j
@Configuration
public class Constants {

    /**
     * cron表达式
     */
    // TODO 可以指定凌晨以后执行，防止插入太早引起查漏

    // 每年1月1号凌晨执行
    public final static String CRON_EVERY_YEAR = "0 0 0 1 1 ? *";

    // 每月1号凌晨执行
    public final static String CRON_EVERY_MONTH = "0 0 0 1 * ? *";

    // 每周周日凌晨执行
    public final static String CRON_EVERY_WEEK = "0 0 0 ? * 1 *";

    // 每天凌晨执行
    public final static String CRON_EVERY_DAY = "0 0 0 * * ?";

    /**
     * 默认时间格式
     */
    public final static String DATE_DEFAULT_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

    /**
     * 默认日期格式
     */
    public final static String DAY_DEFAULT_FORMAT = "yyyy-MM-dd";

    /**
     * 无符号的日期格式
     */
    public final static String DAY_REST_FORMAT = "yyyyMMdd";

    /**
     * 登录开关
     */
    public static boolean LOGIN_SWITCH = true;

    @Value("${sys.switch.login}")
    void setLoginSwitch(boolean loginSwitch) {
        Constants.LOGIN_SWITCH = loginSwitch;
        log.debug("reading config param, LOGIN_SWITCH: " + loginSwitch);
    }
}
