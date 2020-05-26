package com.lukemi.android.myapplication;


import com.lukemi.android.common.util.Logcat;

import org.junit.Test;

public class Test1 {
    public static final String TAG = Test1.class.getSimpleName();

    @Test
    public void hh() {
        Log.i(TAG, "2+3 = " + (2 + 3) + "\n");
    }

    @Test
    public void hh1() {
        Log.i(TAG, "2+31 = " + (2 + 31) + "\n");
    }

    @Test
    public void hh2() {
        Log.i(TAG, "24+31 = " + (24 + 31) + "\n");
    }

    @Test
    public void print() {
        Logcat.log("");
        Logcat.log("1234567890123456789012345678901234567890");
    }
}
