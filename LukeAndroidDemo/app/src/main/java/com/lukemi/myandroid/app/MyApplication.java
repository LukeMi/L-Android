package com.lukemi.myandroid.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.multidex.MultiDex;
import android.text.TextUtils;
import android.util.Log;

import com.lukemi.myandroid.base.BaseApplication;
import com.lukemi.myandroid.dao.DaoMaster;
import com.lukemi.myandroid.dao.DaoSession;
import com.lukemi.myandroid.http.httpprocessor.VollyProcessor;
import com.lukemi.myandroid.service.ForegroundService;
import com.lukemi.myandroid.sessionlifecycle.MyActivityLifecycleCallbacks;
import com.lukemi.myandroid.util.Logcat;
import com.lukemi.myandroid.http.httpprocessor.HttpHelper;
import com.lzy.okgo.OkGo;
import com.squareup.leakcanary.LeakCanary;

import org.greenrobot.greendao.database.Database;

import java.util.logging.Level;

/**
 * Created by mzchen on 2016/10/23.
 */

public class MyApplication extends BaseApplication {

    private final int MSG_REPEAT_TIME = 0x00;
    private final int MSG_CURRENTTIME = 0x0001;
    private TimeChangedReceiver dateChangedReceiver;
    //当前时间
    private long currentTime = 0;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_CURRENTTIME:
                    currentTime = System.currentTimeMillis();
                    handler.sendEmptyMessageDelayed(MSG_CURRENTTIME, 1000);
                    break;
                case MSG_REPEAT_TIME:
                    Log.i("session", "定时任务");
                    handler.sendEmptyMessageDelayed(MSG_REPEAT_TIME, 60000);
                    break;
            }

        }
    };
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
        Logcat.log("----MyApplication---- is in");
        registerActivityLifecycleCallbacks(new MyActivityLifecycleCallbacks());
        handler.sendEmptyMessageDelayed(MSG_REPEAT_TIME, 60000);
        initReceiver();
//        ActiveAndroid.initialize(this);
        HttpHelper.init(new VollyProcessor(this));
        //启动服务
        Intent sevice = new Intent(this, ForegroundService.class);
//        this.startService(sevice);
        //OkGo 框架初始化
        OkGo.init(this);
        OkGo.getInstance()
                .debug("OkGo", Level.INFO, true);
        //内存泄漏
        if (!LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            LeakCanary.install(this);
        }
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, ENCRYPTED ? "notes-db-encrypted" : "notes-db");
        Database db = ENCRYPTED ? helper.getEncryptedWritableDb("super-secret") : helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    @Override
    public String getAppchannel() {
        SharedPreferences spf = getSharedPreferences("appConfigure", Context.MODE_PRIVATE);
        String appchannel = spf.getString("appchannel", "");
        if (TextUtils.isEmpty(appchannel)) {
            appchannel = "baidu";
            getSharedPreferences("appConfigure", Context.MODE_PRIVATE).edit().putString("appchannel", appchannel).apply();
            Logcat.log("----getAppchannel---- " + "is not from spf");
        }
        return appchannel;
    }

    /**
     * 初始化时间广播
     */
    private void initReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_TIME_CHANGED);   //为BroadcastReceiver指定action，使之用于接收同action的广播
        dateChangedReceiver = new TimeChangedReceiver();
        registerReceiver(dateChangedReceiver, intentFilter);
        handler.sendEmptyMessage(MSG_CURRENTTIME);//实时时间
    }


    /**
     * 时间改变的广播，加以处理时间改变的逻辑
     */
    public class TimeChangedReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(Intent.ACTION_TIME_CHANGED)) {
                if (handler.hasMessages(MSG_CURRENTTIME)) {
                    handler.removeMessages(MSG_CURRENTTIME);
                }
                //改变后的时间
                Long timestamp = System.currentTimeMillis();
                Logcat.log("---- duration = currentTime - timestamp ---- " + (currentTime - timestamp) + "=" + currentTime + "-" + timestamp);
                if (!handler.hasMessages(MSG_CURRENTTIME)) {
                    handler.sendEmptyMessage(MSG_CURRENTTIME);
                }
            }
        }
    }

}
