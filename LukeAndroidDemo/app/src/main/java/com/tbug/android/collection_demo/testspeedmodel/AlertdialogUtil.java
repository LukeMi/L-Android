package com.tbug.android.collection_demo.testspeedmodel;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;

/**
 * Created by Administrator on 2016/11/4.
 */

public class AlertdialogUtil {
    public static String TAG = "AlertdialogUtil";
    public static AlertDialog mPrompdlg = null;
    public static Context context;
    public static int MessageSort;
    public static long CLICK_FUNCION_KEY_PER_TIME = 1000;//按功能按键的间隔不得小于此时间，防止发出两条指令
    public static long mClickTimeout = 0;//上次按下功能建的时间


    /**
     * 消息提示框，或用户信息交互框
     */
    public static void createPrompDialog(Context ct, String Message, String title,
                                         String cancleBtString, String okBtString,String neutralBtString ,
                                         final ButtonClickCallback btCallback,
                                         int sortType) {
        destroyPrompDialog();
        if(ct == null){
            Log.e(TAG, "activity可能已经不存在了");
            return;
        }
        context = ct;
        MessageSort = sortType;
        AlertDialog.Builder mAlerdlg = new AlertDialog.Builder(context, AlertDialog.THEME_HOLO_LIGHT);
        mAlerdlg.setMessage(Message);
        mAlerdlg.setTitle(title);

        mAlerdlg.setNegativeButton(cancleBtString, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                if ((System.currentTimeMillis() - mClickTimeout) < CLICK_FUNCION_KEY_PER_TIME) {
                    Log.e(TAG, "点击太快不产生动作");
                    return;
                }
                mClickTimeout = System.currentTimeMillis();
                btCallback.negativeButtonClick();
            }
        });


        mAlerdlg.setPositiveButton(okBtString, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                if ((System.currentTimeMillis() - mClickTimeout) < CLICK_FUNCION_KEY_PER_TIME) {
                    Log.e(TAG, "点击太快不产生动作");
                    return;
                }
                mClickTimeout = System.currentTimeMillis();
                btCallback.positiveButtonClick();
            }
        });

        mAlerdlg.setNeutralButton(neutralBtString, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if ((System.currentTimeMillis() - mClickTimeout) < CLICK_FUNCION_KEY_PER_TIME) {
                    Log.e(TAG, "点击太快不产生动作");
                    return;
                }
                mClickTimeout = System.currentTimeMillis();
                btCallback.neutralButtonClick();
            }
        });

        mAlerdlg.setCancelable(false);// 设置点击屏幕Dialog不消失

        mPrompdlg = mAlerdlg.create();
        mPrompdlg.show();

    }

    public static void destroyPrompDialog() {
        if (mPrompdlg != null) {
            mPrompdlg.dismiss();
            mPrompdlg = null;
        }
    }

    /**
     * 回调类
     *
     * @author zhangxiaoyu
     */
    public static class ButtonClickCallback {
        public void negativeButtonClick() {
        }
        public void positiveButtonClick() {
        }
        public void neutralButtonClick() {
        }


    }

}
