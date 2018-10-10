package com.lukemi.android.tutorial.killprocess;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Cache;
import com.activeandroid.query.Delete;
import com.lukemi.android.tutorial.R;
import com.lukemi.android.tutorial.util.Logcat;
import com.lukemi.android.tutorial.bean.AppInfo;

import java.util.ArrayList;
import java.util.List;

public class ProcessActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView lv;
    private ListView showDB;
    private ArrayList<AppInfo> appList;
    private String whiteOrder = "com.tencent.mobileqq";
    private int count;
    private final int MSG_CYCLE = 0x0001;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_CYCLE:
                    killBackProcess(ProcessActivity.this);
                    mHandler.sendEmptyMessageDelayed(MSG_CYCLE, 30000);
                    break;
            }
        }
    };
    private TextView showTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process);
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
                    if ((applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) > 0) {//安装的是系统应用，而不是第三方应用
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
        lv = ((ListView) findViewById(R.id.appList));
        showDB = ((ListView) findViewById(R.id.showDB));


        showTV = ((TextView) findViewById(R.id.showTV));
        findViewById(R.id.addDB).setOnClickListener(this);
        findViewById(R.id.delDB).setOnClickListener(this);
        findViewById(R.id.updateDB).setOnClickListener(this);
        findViewById(R.id.queryDB).setOnClickListener(this);


        loadInstallAppList();
    }

    /**
     * 加载列表
     */
    private void loadInstallAppList() {
        ArrayList<AppInfo> appInfoList = getAppInfoData(this, false);
        AppinfoAdapter adpater = new AppinfoAdapter(this, appInfoList);
        lv.setAdapter(adpater);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AppInfo appInfo = appList.get(position);
                Intent intent = new Intent(ProcessActivity.this,  ProcessDetailActivity.class);
                intent.putExtra("pName", appInfo.getPn());
                startActivity(intent);
//                Toast.makeText(ProcessActivity.this, "dianji", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        System.exit(0);
        super.onBackPressed();
    }

    /**
     * 获取已经手机已经安卓的软件（true非系统自带）
     */
    public ArrayList<AppInfo> getAppInfoData(Context context, boolean sysPackages) {
        appList = new ArrayList<>();
        List<PackageInfo> packList = context.getPackageManager().getInstalledPackages(0);
        for (int i = 0; i < packList.size(); i++) {
            PackageInfo packInfo = packList.get(i);
            ApplicationInfo applicationInfo = packInfo.applicationInfo;
            if ((applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) > 0) {//系统应用
                Logcat.log("----来自于方法二 ---->>过滤掉系统应用: " + packInfo.packageName);
                continue;
            }
            AppInfo appInfo = new AppInfo();
            appInfo.setAppName(packInfo.applicationInfo.loadLabel(context.getPackageManager()).toString());
            appInfo.setPn(packInfo.packageName);
            appInfo.setVc(packInfo.versionCode);
            appInfo.setVn(packInfo.versionName);
            appInfo.setLd(packInfo.lastUpdateTime);
            appInfo.setFd(packInfo.firstInstallTime);
            try {
//                appInfo.setIcon(context.getPackageManager().getApplicationIcon(appInfo.getPn()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            appList.add(appInfo);
            appInfo.save();
        }
        return appList;
    }

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
            String[] pkgNameList = appProcess.pkgList;//获取该进程中所有的app包名
            for (String pkgName : pkgNameList) {
                Log.e("name", pkgName);
                boolean isHave = (PackageManager.PERMISSION_GRANTED == packageManager.checkPermission("android.permission.INTERNET", pkgName));
                if (isHave) {
                    try {
                        ApplicationInfo info = packageManager.getApplicationInfo(pkgName, 0);
                        if ((info.flags & ApplicationInfo.FLAG_SYSTEM) <= 0) {//安装的第三方应用，而不是系统应用
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addDB:
                count++;
                AppInfo appInfo = new AppInfo();
                appInfo.setAppName(("setAppName" + count));
                appInfo.setPn(("setPn" + count));
                appInfo.setVc(count);
                appInfo.setVn(("setVn" + count));
                appInfo.setLd(System.currentTimeMillis());
                appInfo.setFd(System.currentTimeMillis());
                appInfo.save();
                break;
            case R.id.delDB:
                //Hero.delete(Hero.class,1);//删除id为1的记录
                new Delete().from(AppInfo.class).where("power>?", 60).execute();//删除power大于60的所有记录
                Toast.makeText(ProcessActivity.this, "删除数据ok", Toast.LENGTH_SHORT).show();
                break;
            case R.id.updateDB:
                AppInfo hero = AppInfo.load(AppInfo.class, 10);
                /**
                 * load方法其实执行的是：
                 * new Select()).from(type).where(tableInfo.getIdName() + "=?", new Object[]{Long.valueOf(id)}).executeSingle();
                 * 即是先单条查询；
                 * executeSingle（）相当于limit(1);
                 */
                hero.setPn("杜甫");
                hero.save();
                Toast.makeText(ProcessActivity.this, "修改数据ok", Toast.LENGTH_SHORT).show();
                break;
            case R.id.queryDB:

                showTV.setText(queryDB().toString());
                break;

        }

    }

    /**
     * 数据库查询数据
     */
    private StringBuilder queryDB() {
        StringBuilder content = new StringBuilder("");
        //获取数据库的游标，和SQLite相同
        Cursor cursor = ActiveAndroid.getDatabase().query(Cache.getTableName(AppInfo.class), null, null, null, null, null, null);
        int appName = cursor.getColumnIndex("appName");
        int pn = cursor.getColumnIndex("pn");
        int vn = cursor.getColumnIndex("vn");
        while (cursor.moveToNext()) {
            AppInfo appInfo = new AppInfo();
            appInfo.appName = cursor.getString(appName);
            appInfo.pn = cursor.getString(pn);
            appInfo.vn = cursor.getString(vn);
            content.append(appInfo.toString());
        }
        return content;
    }
}


/**
 * 适配器
 */
class AppinfoAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<AppInfo> appList;
    private LayoutInflater filter;

    public AppinfoAdapter(Context context, ArrayList<AppInfo> appList) {
        this.context = context;
        this.appList = appList;
        filter = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        if (appList != null) {
            return appList.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        return appList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = filter.inflate(R.layout.item_process, null);
            vh = new ViewHolder();
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.title = (TextView) convertView.findViewById(R.id.title);
        vh.pName = (TextView) convertView.findViewById(R.id.pName);
        vh.iconIV = (ImageView) convertView.findViewById(R.id.icon);

        vh.title.setText(appList.get(position).getAppName());
        vh.pName.setText(appList.get(position).getPn());
        Drawable icon = null;//appList.get(position).getIcon();
        if (icon != null) {
//            vh.iconIV.setImageDrawable(appList.get(position).getIcon());
        } else {
            vh.iconIV.setImageResource(R.mipmap.ic_launcher);
        }
        return convertView;
    }

    static class ViewHolder {
        public TextView title;
        public TextView pName;
        public ImageView iconIV;
    }
}
