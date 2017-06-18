package com.shantoo.develop.library.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**日期和时间处理工具类*/
public class DateTimeUtil {

    private static Locale LOCAL = Locale.CANADA;
    private static String mYear;  // 系统当前年
    private static String mMonth; // 系统当前月
    private static String mDay;   // 系统当前日
    private static String mWay;   // 系统当前周

    private static final int FIVE_OF_WEEK = 5;
    private static final int SEVEN_OF_WEEK = 7;

    private static SimpleDateFormat TYPE_MONTH_DAY = new SimpleDateFormat("MM-dd", Locale.CHINA);

    private static SimpleDateFormat TYPE_YEAR_MOUNTH_DAY =
                                                   new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);

    private static SimpleDateFormat TYPE_YEAR_MOUNTH_DAY_HOUR_MINUTE =
                                             new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);

    private static SimpleDateFormat TYPE_YEAR_MOUNTH_DAY_HOUR_MINUTE_SECOND =
                                          new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);

    private static SimpleDateFormat TYPE_SHOPPING_CART =
                                                   new SimpleDateFormat("yyyy-MM.dd", Locale.CHINA);

    public static void with(Locale locale){
        LOCAL = locale;
    }

    /**
     * 获取系统当前时间，格式为: 06-09
     */
    public static String getCurrentDate() {
        return TYPE_MONTH_DAY.format(new Date());
    }

    /**
     * 格式化日期，格式为: 2013-11-11
     *
     * @return String
     */
    public static String getFormatDate(Date date) {
        return TYPE_YEAR_MOUNTH_DAY.format(date);
    }

    /**
     * 获取当前时间，格式为: 2013-11-11 14:40:59
     *
     * @return String
     */
    public static String getFormatTime() {
        return TYPE_YEAR_MOUNTH_DAY_HOUR_MINUTE_SECOND.format(new Date());
    }

    /**
     * 格式化时间，格式为: 2013-11-11 14:40:59
     *
     * @return String
     */
    public static String getFormatTime(Date datetime) {
        return TYPE_YEAR_MOUNTH_DAY_HOUR_MINUTE_SECOND.format(datetime);
    }

    /**
     * 获取当前时间，格式为2013-11-11 14:40
     *
     * @return String
     */
    public static String getFormatTimeShort() {
        return TYPE_YEAR_MOUNTH_DAY_HOUR_MINUTE.format(new Date());
    }

    /**
     * 格式化时间，格式为2013-11-11 14:40
     *
     * @return String
     */
    public static String getFormatTimeShort(Date datetime) {
        return TYPE_YEAR_MOUNTH_DAY_HOUR_MINUTE.format(datetime);
    }

    /**
     * 获取当前时间，格式为2013-11-11 14:40
     *
     * @return String
     */
    public static String getFormatTimeShort(String datetime) {
        return TYPE_YEAR_MOUNTH_DAY_HOUR_MINUTE.format(new Date(getTimeMillis(datetime)));
    }

    /**
     * 将格式符合2013-11-11 14:40:59的时间字符串解析为日期
     *
     * @return String
     * @throws ParseException
     */
    public static Date parseTimeFromString(String timeStr) throws ParseException {
        return TYPE_YEAR_MOUNTH_DAY_HOUR_MINUTE_SECOND.parse(timeStr);
    }

    /**
     * 将格式符合2013-11-11的时间字符串解析为日期
     *
     * @return String
     * @throws ParseException
     */
    public static Date parseDateFromString(String dateStr) throws ParseException {
        return TYPE_YEAR_MOUNTH_DAY.parse(dateStr);
    }

    /**
     * 将时间转化为"2013-11.11"的格式
     *
     * @return String
     */
    public static String parseShoppingCartTime(String timeStr) {
        System.out.println("timeStr = " + timeStr);
        return TYPE_SHOPPING_CART.format(Timestamp.valueOf(timeStr));
    }
    @SuppressWarnings("WeakerAccess")
    public static long getTimeMillis(String time) {
        if (!time.contains(":")) {
            time += " 00";
        }
        while (time.split(":").length < 3) {
            time += ":00";
        }
        return Timestamp.valueOf(time + ".00").getTime();
    }

    /**
     * 计算提醒剩余时间
     *
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 剩余时间
     */
    public static String calculateRemindDiffTime(String startTime, String endTime) {
        long startTimeLong = getTimeMillis(startTime);
        long endTimeLong = getTimeMillis(endTime);
        return calculateRemindDiffDay(startTimeLong, endTimeLong);
    }

    /**
     * 计算提醒剩余时间
     *
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 剩余时间
     */
    @SuppressWarnings("WeakerAccess")
    public static String calculateRemindDiffDay(long startTime, long endTime) {
        int diff;
        long nd = 1000 * 60 * 60 * 24;// 一天的毫秒数
        diff = (int) ((endTime - startTime) / nd);
        if (diff < 0) {
            diff = 0;
        }
        return diff + "天";
    }

    /**
     * 获取系统当前日期
     * @return 系统当前日期 格式: x月x日
     */
    public static String getCurrentDateString() {
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);// 获取当前月份
        mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH));// 获取当前月份的日期号码
        if (Integer.parseInt(mDay) > MaxDayFromDayOFMONTH(Integer.parseInt(mYear), (Integer.parseInt(mMonth)))) {
            mDay = String.valueOf(MaxDayFromDayOFMONTH(Integer.parseInt(mYear), (Integer.parseInt(mMonth))));
        }
        return mMonth + "月" + mDay + "日";
    }

    /**
     * 获取系统当前日期
     *
     * @return 系统当前日期 格式: x年x月x日
     */
    @SuppressWarnings("WeakerAccess")
    public static String StringData() {
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        mYear = String.valueOf(c.get(Calendar.YEAR));// 获取当前年份
        mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);// 获取当前月份
        mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH));// 获取当前月份的日期号码
        if (Integer.parseInt(mDay) > MaxDayFromDayOFMONTH(Integer.parseInt(mYear), (Integer.parseInt(mMonth)))) {
            mDay = String.valueOf(MaxDayFromDayOFMONTH(Integer.parseInt(mYear), (Integer.parseInt(mMonth))));
        }
        return mYear + "-" + (mMonth.length() == 1 ? "0" + mMonth : mMonth) + "-" + (mDay.length() == 1 ? "0" + mDay : mDay);
    }

    /**
     * 根据当前日期获取星期
     *
     * @return 系统当前星期数
     */
    @SuppressWarnings("WeakerAccess")
    public static String getWeek(String time) throws ParseException {
        String Week = "";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(format.parse(time));
        if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            Week = "星期日";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
            Week = "星期一";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {
            Week = "星期二";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
            Week = "星期三";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
            Week = "星期四";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
            Week = "星期五";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
            Week = "星期六";
        }
        return Week;
    }

    public static void main(String args[]) {
        List<String> list;
        list = get7Date();
        for (String string:list) {
            System.out.println(string);
        }
      /*  try {
        } catch (ParseException e) {
            e.printStackTrace();
        }*/
    }

    /**
     * 获取系统当前日期往后一周的日期(包含星期日和星期天)
     *
     * @return 系统当前日期往后一周的日期 格式:x月x日
     */
    @SuppressWarnings("WeakerAccess")
    public static List<String> get7Date() {
        return getDate(SEVEN_OF_WEEK);
    }

    /**
     * 获取系统当前日期往后一周的日期(不包含星期日和星期天)
     *
     * @return 系统当前日期往后一周的日期 格式:x月x日
     */
    public static List<String> get5Date() {
        return getDate(FIVE_OF_WEEK);
    }

    private static List<String> getDate(int paramWeekCount){
        List<String> dates = new ArrayList<>();
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        for (int i = 0; i < paramWeekCount; i++) {
            mYear = String.valueOf(c.get(Calendar.YEAR));// 获取当前年份
            mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);// 获取当前月份
            mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH) + i);// 获取当前日份的日期号码
            if (Integer.parseInt(mDay) > MaxDayFromDayOFMONTH(Integer.parseInt(mYear), (i + 1))) {
                mDay = String.valueOf(MaxDayFromDayOFMONTH(Integer.parseInt(mYear), (i + 1)));
            }
            String date = "今天";
            if(i!=0){
                date = mMonth + "月" + mDay + "日";
            }
            dates.add(date);
        }
        return dates;
    }


    /**
     * 获取系统当前日期往后一周的集合
     */
    public static List<String> get7Week() throws ParseException  {
        String week;
        List<String> weeksList = new ArrayList<>();
        List<String> dateList = get7date();
        for (String s : dateList) {
            if (s.equals(StringData())) {
                week = "今天";
            } else {
                week = getWeek(s);
            }
            weeksList.add(week);
        }
        return weeksList;
    }

    /**
     * 获取系统当前日期往后一周的集合
     * @return 系统当前日期往后一周的集合 格式:2017-02-15
     */
    @SuppressWarnings("WeakerAccess")
    public static List<String> get7date() {
        List<String> dates = new ArrayList<>();
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(c.getTime());
        dates.add(date);
        for (int i = 0; i < 6; i++) {
            c.add(Calendar.DAY_OF_MONTH, 1);
            date = sdf.format(c.getTime());
            dates.add(date);
        }
        return dates;
    }

    /**
     * 获取系统当年当月的最大日期
     **/
    @SuppressWarnings("WeakerAccess")
    public static int MaxDayFromDayOFMONTH(int year, int month) {
        Calendar time = Calendar.getInstance();
        time.clear();
        time.set(Calendar.YEAR, year);
        time.set(Calendar.MONTH, month - 1);//注意,Calendar对象默认一月为0
        return time.getActualMaximum(Calendar.DAY_OF_MONTH);//本月份的天数;
    }
}
