package com.lukemi.android.tutorial.killprocess;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lukemi.android.common.util.Logcat;
import com.lukemi.android.tutorial.R;
import com.lukemi.android.tutorial.bean.AppInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AppStaticsActivity extends AppCompatActivity {

    private final int MSG_CYCLE = 0x0001;
    @BindView(R.id.appList)
    ListView lv;
    private ArrayList<AppInfo> appList;
    private String whiteOrder = "com.tencent.mobileqq";
    private int count;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_CYCLE:
                    killBackProcess(AppStaticsActivity.this);
                    mHandler.sendEmptyMessageDelayed(MSG_CYCLE, 30000);
                    break;
            }
        }
    };
    private PackageManager mPackageManager;

    /**
     * 获得手机中正在运行的有访问网络权限的第三方应用的名字
     *
     * @param context
     * @return
     */
    public static void getRunningAPP(Context context) {
        PackageManager packageManager = context.getPackageManager();
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> list = manager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo appProcess : list) {
            //获取该进程中所有的app包名
            String[] pkgNameList = appProcess.pkgList;
            for (String pkgName : pkgNameList) {
                Log.e("name", pkgName);
                boolean isHave = (PackageManager.PERMISSION_GRANTED == packageManager.checkPermission("android.permission.INTERNET", pkgName));
                if (isHave) {
                    try {
                        ApplicationInfo info = packageManager.getApplicationInfo(pkgName, 0);
                        //安装的第三方应用，而不是系统应用
                        if ((info.flags & ApplicationInfo.FLAG_SYSTEM) <= 0) {
                            Logcat.log("runningName" + info.loadLabel(packageManager).toString());
                        }
                    } catch (PackageManager.NameNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_statics);
        ButterKnife.bind(this);
        initViews();
        mHandler.sendEmptyMessageDelayed(MSG_CYCLE, 0);
    }

    /**
     * 杀死后台进程
     */
    private void killBackProcess(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = am.getRunningAppProcesses();
        if (null == runningAppProcesses || runningAppProcesses.size() == 0) {
            Logcat.log("无可运行程序---->>");
        }
        String currentPKG = getApplication().getPackageName();
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            String[] pkgList = runningAppProcessInfo.pkgList;
            for (int i = 0; i < pkgList.length; i++) {
                String packName = pkgList[i];
                try {
                    ApplicationInfo applicationInfo = getPackageManager().getApplicationInfo(packName, 0);
                    //安装的是系统应用，而不是第三方应用
                    if ((applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) > 0) {
                        continue;
                    }
                    if (!packName.contains(currentPKG)) {
                        am.killBackgroundProcesses(packName);
                        Logcat.log("后台--pid = " + runningAppProcessInfo.pid + "; pkg = " + pkgList[i]);
                    } else {
                        Logcat.log("前台--pid = " + runningAppProcessInfo.pid + "; pkg = " + pkgList[i]);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void initViews() {
        mPackageManager = this.getPackageManager();
        loadInstallAppList();
    }

    /**
     * 加载列表
     */
    private void loadInstallAppList() {
        ArrayList<AppInfo> appInfoList = getAppInfoData(this, false);
        AppinfoAdapter adpater = new AppinfoAdapter(this, appInfoList);
        lv.setAdapter(adpater);
        lv.setOnItemClickListener((AdapterView<?> parent, View view, int position, long id) -> {
            AppInfo appInfo = appList.get(position);
            Intent intent = new Intent(AppStaticsActivity.this, ProcessDetailActivity.class);
            intent.putExtra(Intent.EXTRA_PACKAGE_NAME, appInfo.pn);
            startActivity(intent);
        });
    }

    @Override
    public void onBackPressed() {
        System.exit(0);
        Process.killProcess(Process.myPid());
        super.onBackPressed();
    }

    /**
     * 获取已经手机已经安卓的软件（true非系统自带）
     */
    public ArrayList<AppInfo> getAppInfoData(Context context, boolean sysPackages) {
        appList = new ArrayList<>();
        List<PackageInfo> packList = mPackageManager.getInstalledPackages(0);
        for (int i = 0; i < packList.size(); i++) {
            PackageInfo packInfo = packList.get(i);
            ApplicationInfo applicationInfo = packInfo.applicationInfo;
            //系统应用
            if (!sysPackages && (applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) > 0) {
                Logcat.log("----来自于方法二 ---->>过滤掉系统应用: " + packInfo.packageName);
                continue;
            }
            AppInfo appInfo = new AppInfo();
            appInfo.setAppName(packInfo.applicationInfo.loadLabel(mPackageManager).toString());
            appInfo.setPn(packInfo.packageName);
            appInfo.setVc(packInfo.versionCode);
            appInfo.setVn(packInfo.versionName);
            appInfo.setLd(packInfo.lastUpdateTime);
            appInfo.setFd(packInfo.firstInstallTime);
            appInfo.setIcon(packInfo.applicationInfo.loadIcon(getPackageManager()));
            try {
                appInfo.setIcon(context.getPackageManager().getApplicationIcon(appInfo.getPn()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            appList.add(appInfo);
        }
        return appList;
    }

}


