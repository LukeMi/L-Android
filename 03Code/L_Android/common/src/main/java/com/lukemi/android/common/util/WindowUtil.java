package com.lukemi.android.common.util;

import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

public class WindowUtil {
    /**
     * 设置窗口透明度
     * @param context
     * @param alpha
     */
    public static void setWindowAlpha(AppCompatActivity context, float alpha) {
        WindowManager.LayoutParams attributes = context.getWindow().getAttributes();
        attributes.alpha = alpha;
        context.getWindow().setAttributes(attributes);
    }


}
