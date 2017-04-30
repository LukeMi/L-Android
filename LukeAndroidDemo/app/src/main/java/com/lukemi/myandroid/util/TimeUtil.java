package com.lukemi.myandroid.util;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.SystemClock;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class TimeUtil {

    public static void setSysTime(Context ctx, long timeMillies) {
        try {
            SystemClock.setCurrentTimeMillis(timeMillies);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final static String FORMAT_DATE = "yyyy-MM-dd";
    public final static String FORMAT_TIME = "hh:mm";
    public final static String FORMAT_DATE_TIME = "yyyy-MM-dd hh:mm";
    public final static String FORMAT_MONTH_DAY_TIME = "MM月dd日 hh:mm";
    private static SimpleDateFormat sdf = new SimpleDateFormat();
    private static final int YEAR = 365 * 24 * 60 * 60;// 年
    private static final int MONTH = 30 * 24 * 60 * 60;// 月
    private static final int DAY = 24 * 60 * 60;// 天
    private static final int HOUR = 60 * 60;// 小时
    private static final int MINUTE = 60;// 分钟

    /**
     * 根据时间戳获取描述性时间，如3分钟前，1天前
     *
     * @param timestamp 时间戳 单位为毫秒
     * @return 时间字符串
     */
    public static String getDescriptionTimeFromTimestamp(long timestamp) {
        long currentTime = System.currentTimeMillis();
        long timeGap = (currentTime - timestamp) / 1000;// 与现在时间相差秒数
        System.out.println("timeGap: " + timeGap);
        String timeStr = null;
        if (timeGap > YEAR) {
            timeStr = timeGap / YEAR + "年前";
        } else if (timeGap > MONTH) {
            timeStr = timeGap / MONTH + "个月前";
        } else if (timeGap > DAY) {// 1天以上
            timeStr = timeGap / DAY + "天前";
        } else if (timeGap > HOUR) {// 1小时-24小时
            timeStr = timeGap / HOUR + "小时前";
        } else if (timeGap > MINUTE) {// 1分钟-59分钟
            timeStr = timeGap / MINUTE + "分钟前";
        } else {// 1秒钟-59秒钟
            timeStr = "刚刚";
        }
        return timeStr;
    }

    /**
     * 根据时间戳获取指定格式的时间，如2011-11-30 08:40
     *
     * @param timestamp 时间戳 单位为毫秒
     * @param format    指定格式 如果为null或空串则使用默认格式"yyyy-MM-dd HH:MM"
     * @return
     */
    public static String getFormatTimeFromTimestamp(long timestamp,
                                                    String format) {
        if (format == null || format.trim().equals("")) {
            sdf.applyPattern(FORMAT_DATE);
            int currentYear = Calendar.getInstance().get(Calendar.YEAR);
            int year = Integer.valueOf(sdf.format(new Date(timestamp))
                    .substring(0, 4));
            System.out.println("currentYear: " + currentYear);
            System.out.println("year: " + year);
            if (currentYear == year) {//如果为今年则不显示年份
                sdf.applyPattern(FORMAT_MONTH_DAY_TIME);
            } else {
                sdf.applyPattern(FORMAT_DATE_TIME);
            }
        } else {
            sdf.applyPattern(format);
        }
        Date date = new Date(timestamp);
        return sdf.format(date);
    }

    /**
     * 根据时间戳获取时间字符串，并根据指定的时间分割数partionSeconds来自动判断返回描述性时间还是指定格式的时间
     *
     * @param timestamp      时间戳 单位是毫秒
     * @param partionSeconds 时间分割线，当现在时间与指定的时间戳的秒数差大于这个分割线时则返回指定格式时间，否则返回描述性时间
     * @param format
     * @return
     */
    public static String getMixTimeFromTimestamp(long timestamp,
                                                 long partionSeconds, String format) {
        long currentTime = System.currentTimeMillis();
        long timeGap = (currentTime - timestamp) / 1000;// 与现在时间相差秒数
        if (timeGap <= partionSeconds) {
            return getDescriptionTimeFromTimestamp(timestamp);
        } else {
            return getFormatTimeFromTimestamp(timestamp, format);
        }
    }

    /**
     * 获取当前日期的指定格式的字符串
     *
     * @param format 指定的日期时间格式，若为null或""则使用指定的格式"yyyy-MM-dd HH:MM"
     * @return
     */
    public static String getCurrentTime(String format) {
        if (format == null || format.trim().equals("")) {
            sdf.applyPattern(FORMAT_DATE_TIME);
        } else {
            sdf.applyPattern(format);
        }
        return sdf.format(new Date());
    }

    /**
     * 将日期字符串以指定格式转换为Date
     *
     * @param timeStr 日期字符串
     * @param format  指定的日期格式，若为null或""则使用指定的格式"yyyy-MM-dd HH:MM"
     * @return
     */
    public static Date getTimeFromString(String timeStr, String format) {
        if (format == null || format.trim().equals("")) {
            sdf.applyPattern(FORMAT_DATE_TIME);
        } else {
            sdf.applyPattern(format);
        }
        try {
            return sdf.parse(timeStr);
        } catch (ParseException e) {
            return new Date();
        }
    }

    /**
     * 将Date以指定格式转换为日期时间字符串
     *
     * @param time   日期
     * @param format 指定的日期时间格式，若为null或""则使用指定的格式"yyyy-MM-dd HH:MM"
     * @return
     */
    public static String getStringFromTime(Date time, String format) {
        if (format == null || format.trim().equals("")) {
            sdf.applyPattern(FORMAT_DATE_TIME);
        } else {
            sdf.applyPattern(format);
        }
        return sdf.format(time);
    }

    public static void setSysTimeZone(Context ctx, String timeZoneID) {
        if (timeZoneID != null) {
            AlarmManager a = (AlarmManager) ctx.getSystemService(Context.ALARM_SERVICE);
            try {
                a.setTimeZone(timeZoneID);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @return yyyyMMddHHmmss
     */
    @SuppressLint("SimpleDateFormat")
    public static String getCurrentTime() {
        String strdate = null;
        Date date = new Date();
        if (date != null) {
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
            strdate = format.format(date);
        }
        return strdate;
    }

    /**
     * get build time of app
     */
    public static long getBuildDate(Context context) {
        try {
            ApplicationInfo ai = context.getPackageManager().getApplicationInfo(context.getPackageName(), 0);
            ZipFile zf = new ZipFile(ai.sourceDir);
            ZipEntry ze = zf.getEntry("classes.dex");
            long time = ze.getTime();

            return time;

        } catch (Exception e) {
        }

        return 0l;
    }

    @SuppressLint("SimpleDateFormat")
    public static boolean checkTimeValid(String time) {
        if (time == null)
            return false;

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = null;
        try {
            date = format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }

        if (date == null)
            return false;

        Date current = new Date();
        if (date.compareTo(current) > 0)
            return true;
        else
            return false;
    }

    @SuppressLint("SimpleDateFormat")
    public static long getTimeMillisFromString(String time) {
        if (time == null)
            return 0;

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = null;
        try {
            date = format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }

        if (date == null)
            return 0;

        return date.getTime();
    }

    @SuppressLint("SimpleDateFormat")
    public static long getDurationTime(String startTime, String endTime) {
        if (startTime == null || endTime == null)
            return 0;

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = format.parse(startTime);
            endDate = format.parse(endTime);
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }

        if (startDate == null || endDate == null)
            return 0;

        return (endDate.getTime() - startDate.getTime()) < 0 ? 0 : (endDate.getTime() - startDate.getTime());
    }

    @SuppressLint("SimpleDateFormat")
    public static String getDefDate() {
        Date dt = new Date();
        // 最后的aa表示“上午”或“下午” HH表示24小时制 如果换成hh表示12小时制
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(dt);
    }

    @SuppressLint("SimpleDateFormat")
    public static String getDetailTime() {
        Date dt = new Date();
        // 最后的aa表示“上午”或“下午” HH表示24小时制 如果换成hh表示12小时制
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(dt);
    }

    @SuppressLint("SimpleDateFormat")
    public static String getDetailTimeS() {
        Date dt = new Date();
        // 最后的aa表示“上午”或“下午” HH表示24小时制 如果换成hh表示12小时制
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        return sdf.format(dt);
    }

    /**
     * generate 20121224142321 yyyyMMddHHmm
     *
     * @param d
     * @return string
     */
    public static String toStringYyyyMMddHHmm(Date d) {
        String strdate = null;
        if (d != null) {
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmm");
            strdate = format.format(d);
        }
        return strdate;
    }

    @SuppressLint("SimpleDateFormat")
    public static String getYD() {
        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd");
        return sdf.format(dt);
    }
}
