package com.lukemi.android.myapplication;

public class Log {

    public static void i(String tag, String log) {
        System.out.println("INFO: " + tag + ": " + log);
    }

    public static void d(String tag, String log) {
        System.out.println("DEBUG: " + tag + ": " + log);
    }
}
