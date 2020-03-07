package com.lewic.todoscore.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lewic
 * @since 2020/2/21 19:23
 */

public class DateUtil {

    // 获取所给日期当天起始时间和第二天起始时间
    public static Map<String, Date> getDayStartAndEnd(Date date) {
        Date dateStart = DateUtil.getDayStart(date);
        Calendar calendarEnd = new GregorianCalendar();
        calendarEnd.setTime(dateStart);
        calendarEnd.add(Calendar.HOUR, 24);
        Date dateEnd = calendarEnd.getTime();
        Map<String, Date> day = new HashMap<>();
        day.put("dateStart", dateStart);
        day.put("dateEnd", dateEnd);
        return day;
    }

    // 获取所给日期当天起始时间和结束时间
    public static Date getDayStart(Date date) {
        Calendar calendarStart = new GregorianCalendar();
        calendarStart.setTime(date);
        calendarStart.set(Calendar.HOUR_OF_DAY, 0);
        calendarStart.set(Calendar.MINUTE, 0);
        calendarStart.set(Calendar.SECOND, 0);
        calendarStart.set(Calendar.MILLISECOND, 0);
        return calendarStart.getTime();
    }

    // 获取所给日期当天结束时间
    public static Date getDayEnd(Date date) {
        Calendar calendarEnd = new GregorianCalendar();
        calendarEnd.setTime(date);
        calendarEnd.set(Calendar.HOUR_OF_DAY, 23);
        calendarEnd.set(Calendar.MINUTE, 59);
        calendarEnd.set(Calendar.SECOND, 59);
        calendarEnd.set(Calendar.MILLISECOND, 999);
        return calendarEnd.getTime();
    }

    public static void main(String[] args) {
        Date today = new Date();

        Map<String, Date> map = getDayStartAndEnd(today);
        System.out.println(map.get("dateStart").getTime());
        System.out.println(map.get("dateEnd").getTime());
    }
}
