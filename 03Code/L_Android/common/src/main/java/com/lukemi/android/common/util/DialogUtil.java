package com.lukemi.android.common.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.text.TextUtils;

import androidx.appcompat.app.AlertDialog;

/**
 * 对话框帮助类
 * Created by mzchen on 2017/2/27.
 */

public class DialogUtil {

    /**
     * 进度对话框
     *
     * @param context                上下文
     * @param style                  风格
     * @param title                  标题
     * @param message                消息
     * @param cancelable             按返回键是否可取消
     * @param canceledOnTouchOutside 点击对话框外是否可取消
     * @return 进度条对话框示例
     */
    public static ProgressDialog show_progressDialog(Context context, int style, String title, String message, boolean cancelable, boolean canceledOnTouchOutside) {
        try {
            ProgressDialog progressDialog = new ProgressDialog(context);
            if (style != -1) {
                progressDialog.setProgressStyle(style);
            }
            if (!TextUtils.isEmpty(title)) {
                progressDialog.setTitle(title);
            }
            progressDialog.setMessage(message);
            progressDialog.setCancelable(cancelable);
            progressDialog.setCanceledOnTouchOutside(canceledOnTouchOutside);
            progressDialog.show();
            return progressDialog;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param context
     * @param title
     * @param message
     * @param cancelable
     * @return
     */
    public static AlertDialog.Builder createAlertDialog_Builder(Context context, int style, String title, String message, boolean cancelable) {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            if (style ==0){

            }
            builder.setTitle(title);
            builder.setMessage(message);
            builder.setCancelable(cancelable);
            return builder;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
