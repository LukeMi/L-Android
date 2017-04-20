package com.lukemi.android.collection.sessionlifecycle;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;


/**
 * Created by mzchen on 2016/10/23.
 */

public class MyActivityLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {
    private long startTime;
    private long endTime;
    private boolean isFirstShow = true;

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        Log.i("session", activity.getClass().getSimpleName() + "-->onActivityCreated");
    }

    @Override
    public void onActivityStarted(Activity activity) {
        Log.i("session", activity.getClass().getSimpleName() + "-->onActivityStarted");
    }

    @Override
    public void onActivityResumed(Activity activity) {
        RunTimeDuration runTimeDuration = RunTimeDuration.getInstance();
        Log.i("session", activity.getClass().getSimpleName() + "-->onActivityResumed");
        startTime = System.currentTimeMillis();
        if (isFirstShow) {
            isFirstShow = false;
            runTimeDuration.setStartTime(startTime);
            Log.i("session", isFirstShow + RunTimeDuration.getInstance().toString());
        }
        if (!isFirstShow && startTime - endTime > 30000 && startTime - endTime != startTime) {
            runTimeDuration.setEndTime(endTime);
            runTimeDuration.setDuration(runTimeDuration.getEndTime() - runTimeDuration.getStartTime());
            //处理逻辑的时候
            Log.i("session", isFirstShow + RunTimeDuration.getInstance().toString());
            Log.i("session", isFirstShow +""+ startTime);

            runTimeDuration.setStartTime(startTime);
        }
    }

    @Override
    public void onActivityPaused(Activity activity) {
        Log.i("session", activity.getClass().getSimpleName() + "-->onActivityPaused");
        endTime = System.currentTimeMillis();
        Log.i("session", activity.getClass().getSimpleName() + "-->onActivityPaused" + endTime);
    }

    @Override
    public void onActivityStopped(Activity activity) {
        Log.i("session", activity.getClass().getSimpleName() + "-->onActivityStopped");
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        Log.i("session", activity.getClass().getSimpleName() + "-->onActivitySaveInstanceState");
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        Log.i("session", activity.getClass().getSimpleName() + "-->onActivityDestroyed");
    }
}
