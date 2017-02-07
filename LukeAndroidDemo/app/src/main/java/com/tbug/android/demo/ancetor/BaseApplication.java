package com.tbug.android.demo.ancetor;

import android.app.Application;

/**
 * Created by mzchen on 2016/12/8.
 */

public abstract class BaseApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public abstract String getAppchannel();
}
