package com.lukemi.android.common.util;

import android.util.Log;

import com.lukemi.android.common.BuildConfig;


/**
 * 日志打印帮助类
 * Created by mzchen on 2016/11/15.
 */

public class Logcat {

    public static void log(String log) {
        log(Log.DEBUG, BuildConfig.LOG_TAG, log);
    }

    /**
     * Activity生命周期打印
     *
     * @param log
     */
    public static void log_life(String log) {
        log(Log.INFO, "activity_life ", log);
    }

    public static void log_view(String log) {
        log(Log.INFO, "view_life ", log);
    }

    /**
     * 日志打印 (Root)
     *
     * @param logLevel {@link Log#VERBOSE} {@link Log#DEBUG} {@link Log#INFO}
     *                 {@link Log#WARN} {@link Log#ERROR}{@link Log#ASSERT}
     * @param tag      Log tag TAG标签
     * @param log      log 日志
     */
    public static void log(int logLevel, String tag, String log) {
        if (BuildConfig.DEBUG) {
            log = log.trim();
            int startIndex = 0;
            //设置日志单次打印长度
            int MAX_LENGTH = 4000;
            while (startIndex < log.length()) {
                int endIndex = startIndex + MAX_LENGTH < log.length() ? startIndex + MAX_LENGTH : log.length();
                String s = log.substring(startIndex, endIndex);
                switch (logLevel) {
                    case Log.VERBOSE:
                        Log.v(tag, "--->" + s.trim());
                        break;
                    case Log.DEBUG:
                        Log.d(tag, "--->" + s.trim());
                        break;
                    case Log.INFO:
                        Log.i(tag, "--->" + s.trim());
                        break;
                    case Log.WARN:
                        Log.w(tag, "--->" + s.trim());
                        break;
                    case Log.ERROR:
                        Log.e(tag, "--->" + s.trim());
                        break;
                    case Log.ASSERT:
                    default:
                        Log.i(tag, "--->" + s.trim());
                        break;
                }
                startIndex += MAX_LENGTH;
            }
        }
    }
}
