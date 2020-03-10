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
        LOGIN_SWITCH = loginSwitch;
        log.debug("reading config param, LOGIN_SWITCH: " + loginSwitch);
    }

    /**
     * 每天导入记录定时任务开关
     */
    public static boolean IMPORT_RECORD_SWITCH = true;

    @Value("${sys.switch.scheduler.importRecord}")
    void setImportRecordSwitch(boolean importRecordSwitch) {
        IMPORT_RECORD_SWITCH = importRecordSwitch;
        log.debug("reading config param, IMPORT_RECORD_SWITCH: " + importRecordSwitch);
    }


    /**
     * 每天导入记录定时任务开关
     */
    public static boolean TEST_SWITCH = true;

    @Value("${sys.switch.scheduler.test}")
    void setTestSwitch(boolean testSwitch) {
        TEST_SWITCH = testSwitch;
        log.debug("reading config param, TEST_SWITCH: " + testSwitch);
    }


    /**
     * 默认ContentType;HTTP请求头
     */
    public static final String DEFAULT_CONTENT_TYPE = "application/json;charset=utf-8";

    /**
     * 默认字符编码;HTTP请求头
     */
    public static final String DEFAULT_ENCODING = "UTF-8";

    /**
     * token过期时间
     */
    public static final long TOKEN_EXPIRE_TIME = 15 * 60 * 1000;

    /**
     * jwt签发者
     */
    public static final String JWT_ISSUER = "auth0";

    /**
     * jwt携带参数key
     */
    public static final String JWT_USERNAME = "username";

}
