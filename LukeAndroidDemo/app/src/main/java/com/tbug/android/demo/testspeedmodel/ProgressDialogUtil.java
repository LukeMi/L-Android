package com.tbug.android.demo.testspeedmodel;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.Gravity;
import android.view.KeyEvent;

/**
 * Created by Administrator on 2016/11/4.
 */

public class ProgressDialogUtil extends ProgressDialog {
    private Context context = null;
    /**
     * 等待框返回按键响应
     */
    public final static int Message_waitdialog_return = 0x44ff;
    private static ProgressDialogUtil customProgressDialog = null;
    private static ProgressDialogBackButtonListener progressClickBackKeyListener = null;


    public ProgressDialogUtil(Context context) {
        super(context);
        this.context = context;
    }


    public static void createProgressDialog(Context context, String Message, ProgressDialogBackButtonListener clickBackListener) {
        if (customProgressDialog == null) {
            progressClickBackKeyListener = clickBackListener;
            customProgressDialog = new ProgressDialogUtil(context);
            customProgressDialog.getWindow().getAttributes().gravity = Gravity.CENTER;
            customProgressDialog.setCanceledOnTouchOutside(false);
            customProgressDialog.setCancelable(true);
            customProgressDialog.setMessage(Message);

        }else{
            changeMessage(Message);
        }
        customProgressDialog.show();

    }
    public static void createProgressDialog(Context context, String Message) {
        if (customProgressDialog == null) {
            customProgressDialog = new ProgressDialogUtil(context);
            customProgressDialog.getWindow().getAttributes().gravity = Gravity.CENTER;
            customProgressDialog.setCanceledOnTouchOutside(false);
            customProgressDialog.setCancelable(true);
            customProgressDialog.setMessage(Message);

        }else{
            changeMessage(Message);
        }
        customProgressDialog.show();

    }


    public static void stopProgressDialog() {
        if (customProgressDialog != null) {
            customProgressDialog.dismiss();
            customProgressDialog = null;
        }
    }

    /**
     * [Summary]
     * setMessage 提示内容
     *
     * @param strMessage
     * @return
     */
    public static void changeMessage(String strMessage) {
        if (customProgressDialog != null) {
            customProgressDialog.setMessage(strMessage);
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
               if(UnitTool.doubleClickControl( KeyEvent.KEYCODE_BACK))  {
                progressClickBackKeyListener.backButtonClick();
            }

                break;
            default:
                break;
        }

        return false;

    }

    /**
     * 回调类
     *
     * @author zhangxiaoyu
     */
    public abstract static class ProgressDialogBackButtonListener {
        public abstract void backButtonClick();
    }



    }
