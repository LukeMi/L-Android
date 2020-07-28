package com.jeferry.android.xgpush;

import android.content.Context;

import com.tencent.android.tpush.XGIOperateCallback;
import com.tencent.android.tpush.XGPushManager;

public class XGPushUtil {

    /**
     * 注册信鸽
     *
     * @param context
     * @param xgiOperateCallback
     */
    public static void register(Context context, XGIOperateCallback xgiOperateCallback) {
        XGPushManager.registerPush(context, xgiOperateCallback);
    }

    /**
     * 取消注册信鸽
     *
     * @param context
     */
    public static void unregister(Context context) {
        XGPushManager.unregisterPush(context);
    }

}
