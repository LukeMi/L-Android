package com.tbug.android.library.util;

import android.util.Log;

/**
 * Created by mzchen on 2016/11/15.
 */

public class Logcat {

    public static void log(String log) {
        if (ConfigUtil.debug) {
            log = log.trim();
            int startIndex = 0;
            int maxLength = 4000;
            while (startIndex < log.length()) {
                int endIndex = startIndex + maxLength < log.length() ? startIndex + maxLength : log.length();
                String s = log.substring(startIndex, endIndex);
                Log.i(ConfigUtil.TAG, "--->" + s.trim());
                startIndex += maxLength;
            }
        }
    }
}
