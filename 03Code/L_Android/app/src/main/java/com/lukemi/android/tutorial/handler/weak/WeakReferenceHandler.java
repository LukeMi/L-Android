package com.lukemi.android.tutorial.handler.weak;

import android.app.Activity;
import android.os.Handler;

import java.lang.ref.WeakReference;

public class WeakReferenceHandler extends Handler {

    private final WeakReference<Activity> activityWeakReference;

    public WeakReferenceHandler(Activity activity) {
        activityWeakReference = new WeakReference<>(activity);
    }

    public Activity getReference() {
        return activityWeakReference.get();
    }
}
