package com.lewic.todoscore.utils;

import com.lewic.todoscore.common.Constants;
import com.lewic.todoscore.common.ResponseCode;
import com.lewic.todoscore.exception.ClientException;
import lombok.extern.slf4j.Slf4j;
import org.quartz.CronExpression;
import org.springframework.util.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author lewic
 * @since 2020/1/18 21:03
 */

@Slf4j
public class CronUtil {

    /**
     * 校验所给时间是否满足cron时间规则表达式
     *
     * @param cron cron表达式
     * @param date 时间
     */
    @SuppressWarnings("rawtypes")
    public static Boolean checkCronWithTime(String cron, Date date) throws ParseException, ClientException {
        if (StringUtil.isBlank(cron)) {
            throw new ClientException(ResponseCode.PARAM_CHECK_ERROR, "cron is blank, cron: " + cron);
        }
        CronExpression exp = new CronExpression(cron);
        Boolean result = exp.isSatisfiedBy(date);
        log.debug("cron is satisfied {}: {}", date.toString(), result);
        return result;
    }


    /**
     * 校验所给时间是否满足cron表达式
     *
     * @param cron       表达式: 秒 分 小时 月份中的日期 月份 星期中的日期 年份(可选)
     * @param simpleTime 时间
     */
    public static Boolean checkCronWithTime(String cron, String simpleTime) throws ParseException, ClientException {
        if (StringUtil.isBlank(simpleTime)) {
            throw new ClientException(ResponseCode.PARAM_CHECK_ERROR, "time is blank, time: " + simpleTime);
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.DATE_DEFAULT_FORMAT);
        return checkCronWithTime(cron, simpleDateFormat.parse(simpleTime));
    }


    /**
     * 校验当前时间是否满足cron表达式
     *
     * @param cron cron表达式
     */
    public static Boolean checkCronWithToday(String cron) throws ParseException, ClientException {
        return checkCronWithTime(cron, new Date());
    }

//    public static String isWeekend(String bDate) throws ParseException {
//        DateFormat format1 = new SimpleDateFormat("yyyy/MM/dd");
//        Date bdate = format1.parse(bDate);
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(bdate);
//        if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
//            return "OK";
//        } else{
//            return "NO";
//        }
//
//    }

    public static Boolean todayIsWeekend() {
        Date today = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        return cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;
    }

    public static Boolean isWeekend(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;
    }

    public static void main(String[] args) throws ParseException {
//        String cron = "0 0 10,11,15 * * ? ";
//        System.out.println(filterWithCronTime(cron, "HH"));//true，我当前时间为15:36，
//        System.out.println(filterWithCronTime(cron, "HHmm"));//false，我当前时间为15:36，

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // 每天
        String cron1 = "* * * * * ? *";
        // 每周周日
        String cron2 = "* * * ? * 1 *";
        // 每月1号
        String cron3 = "* * * 1 * ? *";
        // 每年1月1号
        String cron4 = "* * * 1 1 ? *";

        CronExpression cronExpression1 = new CronExpression(cron1);
        boolean resCron1 = cronExpression1.isSatisfiedBy(simpleDateFormat.parse("2020-01-03 23:59:59"));
        Assert.isTrue(resCron1, "每天校验失败");

        CronExpression cronExpression2 = new CronExpression(cron2);
        boolean resCron2 = cronExpression2.isSatisfiedBy(simpleDateFormat.parse("2020-02-16 00:00:00"));
        Assert.isTrue(resCron2, "每周校验失败");
        boolean resCron21 = cronExpression2.isSatisfiedBy(simpleDateFormat.parse("2020-02-15 23:59:59"));
        Assert.isTrue(!resCron21, "每周校验失败");

        CronExpression cronExpression3 = new CronExpression(cron3);
        boolean resCron3 = cronExpression3.isSatisfiedBy(simpleDateFormat.parse("2020-02-01 23:59:59"));
        Assert.isTrue(resCron3, "每月校验失败");
        boolean resCron31 = cronExpression3.isSatisfiedBy(simpleDateFormat.parse("2020-02-02 23:59:59"));
        Assert.isTrue(!resCron31, "每月校验失败");

        CronExpression cronExpression4 = new CronExpression(cron4);
        boolean resCron4 = cronExpression4.isSatisfiedBy(simpleDateFormat.parse("2020-01-01 23:59:59"));
        Assert.isTrue(resCron4, "每年校验失败");
        boolean resCron41 = cronExpression4.isSatisfiedBy(simpleDateFormat.parse("2019-12-02 23:59:59"));
        Assert.isTrue(!resCron41, "每年校验失败");
    }
}
