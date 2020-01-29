package com.lewic.todoscore.utils;

import com.lewic.todoscore.common.Constants;
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
    public static Boolean checkCronWithTime(String cron, Date date) throws ParseException {
        if (StringUtil.isBlank(cron)) {
            log.error("cron is blank, cron: {}", cron);
            throw new RuntimeException();
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
    public static Boolean checkCronWithTime(String cron, String simpleTime) throws ParseException {
        if (StringUtil.isBlank(simpleTime)) {
            log.error("time is blank, time: {}", simpleTime);
            throw new RuntimeException();
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.DATE_DEFAULT_FORMAT);
        return checkCronWithTime(cron, simpleDateFormat.parse(simpleTime));
    }


    /**
     * 校验当前时间是否满足cron表达式
     *
     * @param cron cron表达式
     * @param date 时间
     */
    public static Boolean checkCronWithToday(String cron) throws ParseException {
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

    public static Boolean todayIsWeekend() throws ParseException {
        Date today = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        return cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;
    }

    public static Boolean isWeekend(Date date) throws ParseException {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;
    }

    public static void main(String[] args) throws ParseException {
//        String cron = "0 0 10,11,15 * * ? ";
//        System.out.println(filterWithCronTime(cron, "HH"));//true，我当前时间为15:36，
//        System.out.println(filterWithCronTime(cron, "HHmm"));//false，我当前时间为15:36，


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        CronExpression cronExpression = new CronExpression("* 0/1 7-23 * * ?");

        boolean resCron = cronExpression.isSatisfiedBy(simpleDateFormat.parse("2018-04-27 16:00:00"));

        Assert.isTrue(resCron);
        System.out.println(resCron);

    }
}
