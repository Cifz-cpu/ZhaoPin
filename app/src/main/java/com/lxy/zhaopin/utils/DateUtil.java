package com.lxy.zhaopin.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**时间转化util
 * Created by cifz on 2016/11/28.
 */

public class DateUtil {

    private static final int seconds_of_1minute = 60;

    private static final int seconds_of_30minutes = 30 * 60;

    private static final int seconds_of_1hour = 60 * 60;

    private static final int seconds_of_1day = 24 * 60 * 60;

    private static final int seconds_of_15days = seconds_of_1day * 15;

    private static final int seconds_of_30days = seconds_of_1day * 30;

    private static final int seconds_of_6months = seconds_of_30days * 6;

    private static final int seconds_of_1year = seconds_of_30days * 12;


    //将时间转化为string格式
    public static String dateToString (Date s) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String result = sdf.format(s);
        return result;
    }

    public static String dateToStringNoyear (Date s) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");
        String result = sdf.format(s);
        return result;
    }
    public static String dateToStringNoyearHaveS (Date s) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm:ss");
        String result = sdf.format(s);
        return result;
    }

    public static String dateToStringNowTime (Date s) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String result = sdf.format(s);
        return result;
    }

    //将时间转化为string格式(不要时分秒)
    public static String dateToStringShort (Date s) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String result = sdf.format(s);
        return result;
    }
    //将时间转化为string格式(不要时分秒与年)
    public static String dateToStringShortNoYear (Date s) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
        String result = sdf.format(s);
        return result;
    }
    //将string格式时间转化为Date
    public static Date stringToDate(String s) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.parse(s);
    }
    //将时间转化为string格式(没有秒)
    public static String dateToStringNoS (Date s) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String result = sdf.format(s);
        return result;
    }

    /**
     * 格式化时间(传入时间为2011-11-11 22：15：15类型)
     * @param mTime
     * @return
     */
    public static String getTimeRange(String mTime)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        /**获取当前时间*/
        Date  curDate = new  Date(System.currentTimeMillis());
        String dataStrNew= sdf.format(curDate);
        Date startTime=null;
        try {
            /**将时间转化成Date*/
            curDate=sdf.parse(dataStrNew);
            startTime = sdf.parse(mTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        /**除以1000是为了转换成秒*/
        long   between=(curDate.getTime()- startTime.getTime())/1000;
        int   elapsedTime= (int) (between);
        if (elapsedTime < seconds_of_1minute) {

            return "刚刚";
        }
        if (elapsedTime < seconds_of_30minutes) {

            return elapsedTime / seconds_of_1minute + "分钟前";
        }
        if (elapsedTime < seconds_of_1hour) {

            return "半小时前";
        }
        if (elapsedTime < seconds_of_1day) {

            return elapsedTime / seconds_of_1hour + "小时前";
        }
//        if (elapsedTime < seconds_of_15days) {
//
//            return elapsedTime / seconds_of_1day + "天前";
//        }
//        if (elapsedTime < seconds_of_30days) {
//
//            return "半个月前";
//        }
//        if (elapsedTime < seconds_of_6months) {
//
//            return elapsedTime / seconds_of_30days + "月前";
//        }
        if (elapsedTime < seconds_of_1year) {

//            return "半年前";
            return DateUtil.dateToStringNoyear(startTime);
        }
        if (elapsedTime >= seconds_of_1year) {

//            return elapsedTime / seconds_of_1year + "年前";
            return DateUtil.dateToStringShort(startTime);
        }
        return "遥远的远方";
    }

    /**
     * 两个时间相差的分钟数
     * @param start
     * @param end
     * @return
     */
    public static long differMinuter(Date start,Date end){
        if (null!=start&&null!=end){
            return (end.getTime()-start.getTime())/60000;
        }else {
            return 0;
        }
    }

}
