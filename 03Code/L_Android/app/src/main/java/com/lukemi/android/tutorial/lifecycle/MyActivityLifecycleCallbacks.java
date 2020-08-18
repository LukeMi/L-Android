package com.lukemi.android.tutorial.lifecycle;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;

import com.socks.library.KLog;


/**
 * Created by mzchen on 2016/10/23.
 */
@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
public class MyActivityLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {

    private static final String TAG = MyActivityLifecycleCallbacks.class.getSimpleName();

    private long startTime;
    private long endTime;
    private boolean isFirstShow = true;

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        KLog.d(TAG, "<-- " + activity.getClass().getSimpleName());
    }

    @Override
    public void onActivityStarted(Activity activity) {
        KLog.d(TAG, "<-- " + activity.getClass().getSimpleName());
    }

    @Override
    public void onActivityResumed(Activity activity) {
        RunTimeDuration runTimeDuration = RunTimeDuration.getInstance();
        KLog.d(TAG, "<-- " + activity.getClass().getSimpleName());
        startTime = System.currentTimeMillis();
        if (isFirstShow) {
            isFirstShow = false;
            runTimeDuration.setStartTime(startTime);
            KLog.d(TAG, isFirstShow + RunTimeDuration.getInstance().toString());
        }
        if (!isFirstShow && startTime - endTime > 30000 && startTime - endTime != startTime) {
            runTimeDuration.setEndTime(endTime);
            runTimeDuration.setDuration(runTimeDuration.getEndTime() - runTimeDuration.getStartTime());
            //处理逻辑的时候
            KLog.d(TAG, isFirstShow + RunTimeDuration.getInstance().toString());
            KLog.d(TAG, isFirstShow + "" + startTime);

            runTimeDuration.setStartTime(startTime);
        }
    }

    @Override
    public void onActivityPaused(Activity activity) {
        KLog.d(TAG, "<-- " + activity.getClass().getSimpleName());
        endTime = System.currentTimeMillis();
        KLog.d(TAG, "<-- " + activity.getClass().getSimpleName() + " end time: " + endTime);
    }

    @Override
    public void onActivityStopped(Activity activity) {
        KLog.d(TAG, "<-- " + activity.getClass().getSimpleName());
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        KLog.d(TAG, "<-- " + activity.getClass().getSimpleName());
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        KLog.d(TAG, "<-- " + activity.getClass().getSimpleName());
    }
}
