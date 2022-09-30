package com.seewo.binlogsql.tool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @Author tonglele
 * @Date 2022/8/1
 * @Description Util for date
 */
public class DateUtil {
    // 正数，日期往后增加,负数往前移动
    public static Date addDate(Date date, int field, int amount) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(field, amount);
        date = calendar.getTime();
        return date;
    }

    // 得到整的时间，例如整天，整小时
    public static Date getTruncatedDate(Date date, SimpleDateFormat sdf) {
        String startDateStr = sdf.format(date);
        try {
            return sdf.parse(startDateStr);
        } catch (ParseException e) {
            throw new RuntimeException("日期转换错误, 无法得到整的时间，Date：" + date + "SimpleDateFormat: " + sdf);
        }
    }

    public static String getMonthStr(Date date, int amount){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
        Date truncatedDate = getTruncatedDate(date, formatter);
        Date newDate = addDate(truncatedDate, Calendar.MONTH, amount);
        return formatter.format(newDate);
    }

    public static Date getFirstDayOfMonth(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
        return getTruncatedDate(date, formatter);
    }

    public static Date getFirstDayOfNextMonth(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
        Date thisMonth = getTruncatedDate(date, formatter);
        return addDate(thisMonth, Calendar.MONTH, 1);
    }

    public static Date getLastDayOfMonth(Date date){
        return addDate(getFirstDayOfNextMonth(date), Calendar.DATE, -1);
    }
}
