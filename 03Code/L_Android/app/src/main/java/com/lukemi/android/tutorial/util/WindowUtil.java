package com.lukemi.android.tutorial.util;

import android.app.Activity;
import android.content.Context;
import android.view.WindowManager;

public class WindowUtil {
    /**
     * 设置窗口透明度
     * @param context
     * @param alpha
     */
    public static void setWindowAlpha(Activity context, float alpha) {
        WindowManager.LayoutParams attributes = context.getWindow().getAttributes();
        attributes.alpha = alpha;
        context.getWindow().setAttributes(attributes);
    }


}
