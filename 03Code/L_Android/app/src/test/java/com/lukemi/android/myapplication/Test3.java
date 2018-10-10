package com.lukemi.android.myapplication;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test3 {

    @Test
    public void ge() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        String format = sdf.format(new Date());
        Log.i("TAF", format);
    }

}
