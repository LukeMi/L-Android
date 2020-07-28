package com.jeferry.android.xgpush;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.socks.library.KLog;
import com.tencent.android.tpush.XGPushBaseReceiver;
import com.tencent.android.tpush.XGPushClickedResult;
import com.tencent.android.tpush.XGPushRegisterResult;
import com.tencent.android.tpush.XGPushShowedResult;
import com.tencent.android.tpush.XGPushTextMessage;

public class XGPushReceiver extends XGPushBaseReceiver {

    private static final String TAG = XGPushReceiver.class.getSimpleName();

    @Override
    public void onRegisterResult(Context context, int i, XGPushRegisterResult xgPushRegisterResult) {

    }

    @Override
    public void onUnregisterResult(Context context, int i) {

    }

    @Override
    public void onSetTagResult(Context context, int i, String s) {

    }

    @Override
    public void onDeleteTagResult(Context context, int i, String s) {

    }

    @Override
    public void onSetAccountResult(Context context, int i, String s) {

    }

    @Override
    public void onDeleteAccountResult(Context context, int i, String s) {

    }

    // 透传消息接受者
    @Override
    public void onTextMessage(Context context, XGPushTextMessage xgPushTextMessage) {
        KLog.d(TAG, JSON.toJSON(xgPushTextMessage));
        if (xgPushTextMessage != null) {
            String customContent = xgPushTextMessage.getCustomContent();
            JSONObject object = JSON.parseObject(customContent);
            String url = object.getString("url");
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            if (intent.resolveActivity(context.getPackageManager()) != null) {
                context.startActivity(intent);
            }
        }
    }

    // 通知栏点击事件
    @Override
    public void onNotificationClickedResult(Context context, XGPushClickedResult xgPushClickedResult) {
        KLog.d(TAG, context.getClass().getName());
        KLog.d(TAG, JSON.toJSON(xgPushClickedResult));
        if (xgPushClickedResult != null) {
            String customContent = xgPushClickedResult.getCustomContent();
            JSONObject object = JSON.parseObject(customContent);
            String url = object.getString("url");
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            if (intent.resolveActivity(context.getPackageManager()) != null) {
                context.startActivity(intent);
            }
        }
    }

    @Override
    public void onNotificationShowedResult(Context context, XGPushShowedResult xgPushShowedResult) {

    }
}
