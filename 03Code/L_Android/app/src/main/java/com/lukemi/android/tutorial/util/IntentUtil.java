package com.lukemi.android.tutorial.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import androidx.annotation.NonNull;


public class IntentUtil {

    /**
     * 判定Intent是否有效
     *
     * @param intent
     * @param packageManager
     * @return
     */
    public static boolean isIntentExist(Intent intent, PackageManager packageManager) {
        if (intent == null) {
            return false;
        } else {
            return intent.resolveActivity(packageManager) != null;
        }
    }

    /**
     * 判定Intent是否有效
     *
     * @param intent
     * @param context
     * @return
     */
    public static boolean isIntentExist(Intent intent, @NonNull Context context) {
        if (intent == null) {
            return false;
        } else {
            return intent.resolveActivity(context.getPackageManager()) != null;
        }
    }
}
