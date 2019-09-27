package com.lukemi.android.common.util;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.lukemi.android.common.R;

import static android.widget.Toast.makeText;

/**
 * Toast帮助类
 * Created by mzchen on 2017/2/27.
 */

public class ToastUtil {

    private static Context mContext;

    private static Toast mToast;

    private static TextView mTvContent;

    private ToastUtil(Context context) {
        mContext = context;
    }

    /**
     * u‘d better
     *
     * @param application
     */
    public static void init(@NonNull Application application) {
        mContext = application;
    }

    /**
     * u need init first
     *
     * @param stringId 字符串id
     */
    public static void toast(@StringRes int stringId) {
        if (mToast == null) {
            mToast = new Toast(mContext);
            LayoutInflater inflater = LayoutInflater.from(mContext);
            mTvContent = (TextView) inflater.inflate(R.layout.view_c_toast, null);
            mToast.setView(mTvContent);
            mToast.setGravity(Gravity.BOTTOM, 0, 0);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mTvContent.setText(mContext.getResources().getString(stringId));
        mToast.show();
    }

    /**
     * u need init first
     *
     * @param content 字符串id
     */
    public static void toast(String content) {
        if (mToast == null) {
            mToast = new Toast(mContext);
            LayoutInflater inflater = LayoutInflater.from(mContext);
            mTvContent = (TextView) inflater.inflate(R.layout.view_c_toast, null);
            mToast.setView(mTvContent);
            mToast.setGravity(Gravity.CENTER, 0, 0);
            mToast.setDuration(Toast.LENGTH_LONG);
        }
        mTvContent.setText(content);
        mToast.show();
    }

    public ToastUtil() {

    }

    /**
     * 一般性Toast显示+String
     *
     * @param context  上下文
     * @param text     显示的信息
     * @param duration 显示时长
     */
    public static Toast show_makeText(Context context, CharSequence text, int duration) {
        if (context != null) {
            Toast toast = makeText(context.getApplicationContext(), text, duration);
            toast.show();
            return toast;
        }
        return null;

    }

    /**
     * 一般性Toast显示+StringID
     *
     * @param context   上下文
     * @param string_id 显示的信息id
     * @param duration  显示时长
     */
    public static Toast show_makeText(Context context, @StringRes int string_id, int duration) {
        if (context != null) {
            Toast toast = Toast.makeText(context.getApplicationContext(), context.getResources().getString(string_id), duration);
            toast.show();
            return toast;
        }
        return null;

    }

    /**
     * 一般性Toast显示+位置设置
     *
     * @param context  上下文
     * @param text     显示的信息
     * @param gravity  位置
     * @param xOffset  x轴偏移量
     * @param yOffset  y轴偏移量
     * @param duration 时长   {@link Toast#LENGTH_SHORT} {@link Toast#LENGTH_LONG}
     * @author cmz
     * @version v1.0
     * @see <a href="https:developer.android.google.cn">参考</a>
     */
    public static Toast show_makeText_position(Context context, CharSequence text, int gravity, int xOffset, int yOffset, int duration) {
        if (context != null) {
            Toast toast = makeText(context.getApplicationContext(), text, duration);
            toast.setGravity(gravity, xOffset, yOffset);
            toast.show();
            return toast;
        }
        return null;
    }


    /**
     * 一般性Toast显示+位置设置+StringID
     *
     * @param context   上下文
     * @param string_id 显示的信息id
     * @param gravity   位置
     * @param xOffset   x轴偏移量
     * @param yOffset   y轴偏移量
     * @param duration  时长
     */
    public static Toast show_makeText_position(Context context, int string_id, int gravity, int xOffset, int yOffset, int duration) {
        if (context != null) {
            Toast toast = show_makeText_position(context.getApplicationContext(), context.getString(string_id), gravity, xOffset, yOffset, duration);
            return toast;
        }
        return null;
    }


    /**
     * 自定义Toast显示
     *
     * @param view             要显示的View
     * @param horizontalMargin 水平外边距
     * @param verticalMargin   垂直外边距
     */
    public Toast show_view(Context context, View view, float horizontalMargin, float verticalMargin, int duration) {
        if (context != null) {
            Toast toast = new Toast(getApplicationContext(context));
            toast.setView(view);
            toast.setDuration(duration);
            toast.setMargin(horizontalMargin, verticalMargin);
            toast.show();
            return toast;
        }
        return null;
    }

    /**
     * 自定义Toast显示+显示位置
     *
     * @param context          上下文
     * @param view             自定义View
     * @param horizontalMargin 水平外边距
     * @param verticalMargin   垂直外边距
     * @param gravity          位置
     * @param xOffset          x轴偏移量
     * @param yOffset          y轴偏移量
     * @param duration         时长
     */
    public Toast show_view_position(Context context, View view, float horizontalMargin, float verticalMargin, int gravity, int xOffset, int yOffset, int duration) {
        if (context != null) {
            Toast toast = new Toast(getApplicationContext(context));
            toast.setView(view);
            toast.setDuration(duration);
            toast.setMargin(horizontalMargin, verticalMargin);
            toast.setGravity(gravity, xOffset, yOffset);
            toast.show();
            return toast;
        }
        return null;
    }

    public static Context getApplicationContext(Context context) {
        return context.getApplicationContext();
    }

}
