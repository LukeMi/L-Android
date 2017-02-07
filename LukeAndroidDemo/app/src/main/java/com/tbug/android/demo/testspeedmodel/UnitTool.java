package com.tbug.android.demo.testspeedmodel;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;


public class UnitTool {
    public static String TAG = "UnitTool";
    public static final int FLAG_ONU = 0x01;//onu标志
    public static final int FLAG_ONU_BR = 0x0177;//onu桥模式
    public static final int FLAG_ONU_ROUTE = 0x0166;//onu测速模块模式

    public static final int iPLAMA = 327;

    public static long clickTimeout = 0;

    public static long CLICK_KEY_PER_TIME = 1000;
    public static int preKeyId = -998;

    public static boolean doubleClickControl(int keyId) {
        if ((System.currentTimeMillis() - clickTimeout) < CLICK_KEY_PER_TIME && keyId == preKeyId)
        {
            Log.e(TAG, "点击太快不产生动作");
            return false;
        }
        clickTimeout = System.currentTimeMillis();
        preKeyId = keyId;

        return true;
    }

    /**
     * 保存配置
     *
     * @param ct
     * @param strKey
     * @param strValue
     */
    public static void saveConfig(Context ct, String strKey, String strValue) {
        SharedPreferences setting = ct.getSharedPreferences("ST327Seedtest", Context.MODE_PRIVATE);
        Editor editor = setting.edit();
        editor.putString(strKey, strValue);
        editor.commit();
        return;
    }

    public static void saveConfig(Context ct, String strKey, int iValue) {
        SharedPreferences setting = ct.getSharedPreferences("ST327Seedtest", Context.MODE_PRIVATE);
        Editor editor = setting.edit();
        editor.putInt(strKey, iValue);
        editor.commit();
        return;
    }

    /**
     * 获取保存的配置信息
     *
     * @param ct
     * @param strKey
     * @return
     */
    public static String getConfig(Context ct, String strKey, String defaultValue) {
        SharedPreferences setting = ct.getSharedPreferences("ST327Seedtest", Context.MODE_PRIVATE);
        return setting.getString(strKey, defaultValue);
    }

    public static int getConfig(Context ct, String strKey, int defaultValue) {
        SharedPreferences setting = ct.getSharedPreferences("ST327Seedtest", Context.MODE_PRIVATE);
        return setting.getInt(strKey, defaultValue);
    }

    public static String getVersion(Context ct) {
        try {
            PackageManager manager = ct.getPackageManager();
            PackageInfo info = manager.getPackageInfo(ct.getPackageName(), 0);
            String versionName = info.versionName;
//			//下面的代码是获取版本Code
            int versionCode = info.versionCode;
            return versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return "没找到version name";
        }
    }



    public static String FileSavePath = "";

    /**
     * 删除几天前的日志的天数
     */
    public static int DELETE_LOG_FILE_DAY = 3;

    /**
     * 设置日志存放路径
     */
    public static void setLocalPath() {

        String logicalPath = "/storage/sdcard0";
        UnitTool.FileSavePath = logicalPath + "/BigSpeed_Log/";
        LogUtil.setLogFilePath(FileSavePath);


    }

    /**
     * 应用名，用于日志文件名上
     */
    public static String appName = "";
    /**
     * 其它信息，用于日志名上，可以是帐号、MEID等信息
     */
    public static String OtherWantUseString = "_Log";
}
