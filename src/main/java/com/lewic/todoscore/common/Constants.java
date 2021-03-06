package com.lewic.todoscore.common;

/**
 * @author lewic
 * @since 2020/1/6 22:16
 */

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
     * 时间格式
     */
    public final static String DATE_DEFAULT_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

    public final static String DAY_DEFAULT_FORMAT = "yyyy-MM-dd";

    public final static String DAY_REST_FORMAT = "yyyyMMdd";

}
