package com.ruiaa.toolpck.util;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by ruiaa on 2016/10/21.
 */

public class DateUtil {

    /*
     *      通用  单位 毫秒
     *
     *      2016/10/10
     *
     *      2016-10-10
     *
     *      20:30
     */
    public static String toDateString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        return dateFormat.format(date);
    }

    public static String toDateString(long time) {
        return toDateString(new Date(time));
    }

    public static String toDateStringUse_(Date date) {
        return toDateString(date).replaceAll("/", "-");
    }

    public static String toDateStringUse_(long time){
        return toDateString(time).replaceAll("/", "-");
    }

    public static String toHourMinString(Date date){
        DateFormat dateFormat=new SimpleDateFormat("HH:mm");
        return dateFormat.format(date);
    }

    public static String toHourMinString(long time){
        return toHourMinString(new Date(time));
    }

    public static String dateNoYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return "" + (month > 9 ? month : "0" + month) + "-" + (day > 9 ? day : "0" + day);
    }


    public static boolean isTheSameDay(Date one, Date another) {
        Calendar _one = Calendar.getInstance();
        _one.setTime(one);
        Calendar _another = Calendar.getInstance();
        _another.setTime(another);
        int oneDay = _one.get(Calendar.DAY_OF_YEAR);
        int anotherDay = _another.get(Calendar.DAY_OF_YEAR);
        int oneYear = _one.get(Calendar.YEAR);
        int anotherYear = _another.get(Calendar.YEAR);
        return oneDay == anotherDay && oneYear == anotherYear;
    }

    public static boolean isTheSameDay(long oneTime,long anotherTime){
        return isTheSameDay((new Date(oneTime)),(new Date(anotherTime)));
    }
}
