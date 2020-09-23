package com.lukemi.android.tutorial.app;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.multidex.MultiDex;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.lukemi.android.common.util.Logcat;
import com.lukemi.android.common.util.ToastUtil;
import com.lukemi.android.tutorial.BuildConfig;
import com.lukemi.android.tutorial.base.BaseApplication;
import com.lukemi.android.tutorial.db.dao.DaoMaster;
import com.lukemi.android.tutorial.db.dao.DaoSession;
import com.lukemi.android.tutorial.receiver.TimeChangedReceiver;
import com.lukemi.android.tutorial.service.ForegroundService;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.bugly.crashreport.CrashReport;

import org.greenrobot.greendao.database.Database;

/**
 * Created by mzchen on 2016/10/23.
 *
 * @author chenmz
 * @date on 2016/10/23
 */

public class Application extends BaseApplication {

    public static final int MSG_REPEAT_TIME = 0x0000;
    public static final int MSG_CURRENT_TIME = 0x0001;

    /**
     * 当前时间
     */
    public static long currentTime = 0;
    public static Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_CURRENT_TIME:
                    currentTime = System.currentTimeMillis();
                    handler.sendEmptyMessageDelayed(MSG_CURRENT_TIME, 1000);
                    break;
                case MSG_REPEAT_TIME:
                    Log.i("session", "定时任务");
                    handler.sendEmptyMessageDelayed(MSG_REPEAT_TIME, 60000);
                    break;
                default:
                    break;
            }

        }
    };

    private TimeChangedReceiver dateChangedReceiver;
    private boolean ENCRYPTED = false;
    private DaoSession daoSession;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Logcat.log("----Application---- is in");
//        registerActivityLifecycleCallbacks(new MyActivityLifecycleCallbacks());
        handler.sendEmptyMessageDelayed(MSG_REPEAT_TIME, 60000);
        initReceiver();
//        ActiveAndroid.initialize(this);
        //启动服务
        Intent sevice = new Intent(this, ForegroundService.class);
//        this.startService(sevice);
        initLeakCanary();
        initDao();
        initToast();
        initARouter();
        initBugly();
    }

    private void initARouter() {
        // 这两行必须写在init之前，否则这些配置在init过程中将无效
        if (BuildConfig.DEBUG) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        // 尽可能早，推荐在Application中初始化
        ARouter.init(this);
    }

    /**
     * 初始化全局Toast
     */
    private void initToast() {
        ToastUtil.init(this);
    }

    /**
     * leakCanary 内存泄漏监听
     */
    private void initLeakCanary() {
        if (BuildConfig.DEBUG) {
            if (!LeakCanary.isInAnalyzerProcess(this)) {
                LeakCanary.install(this);
            }
        }
    }

    private void initDao() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, ENCRYPTED ? "notes-db-encrypted" : "notes-db");
        Database db = ENCRYPTED ? helper.getEncryptedWritableDb("super-secret") : helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    @Override
    public String getAppChannel() {
        SharedPreferences spf = getSharedPreferences("appConfigure", Context.MODE_PRIVATE);
        String appchannel = spf.getString("appchannel", "");
        if (TextUtils.isEmpty(appchannel)) {
            appchannel = "baidu";
            getSharedPreferences("appConfigure", Context.MODE_PRIVATE).edit().putString("appchannel", appchannel).apply();
            Logcat.log("----getAppChannel---- " + "Mis not from spf");
        }
        return appchannel;
    }

    /**
     * 初始化时间广播
     */
    private void initReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        // 为BroadcastReceiver指定action，使之用于接收同action的广播
        intentFilter.addAction(Intent.ACTION_TIME_CHANGED);
        dateChangedReceiver = new TimeChangedReceiver();
        registerReceiver(dateChangedReceiver, intentFilter);
        // 实时时间
        handler.sendEmptyMessage(MSG_CURRENT_TIME);
    }

    private void initBugly() {
        CrashReport.initCrashReport(getApplicationContext(), "ecec18200b", true);
    }

}
