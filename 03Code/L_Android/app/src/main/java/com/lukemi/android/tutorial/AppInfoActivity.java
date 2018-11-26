package com.lukemi.android.tutorial;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lukemi.android.common.util.Logcat;
import com.lukemi.android.tutorial.bean.AppInfos;
import com.lukemi.android.tutorial.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AppInfoActivity extends AppCompatActivity implements BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.rv_appinfo)
    RecyclerView rvAppinfo;
    private List<AppInfos> appInfoList = new ArrayList<>();
    private PackageManager pm;
    private AppinfoAdapter appinfoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_info);
        ButterKnife.bind(this);
        initView();
        refresh();
    }

    private void refresh() {
        appInfoList.clear();
        new Thread(new Runnable() {
            @Override
            public void run() {
                getInstallAppInfo();
            }
        }).start();
    }


    private void initView() {
        pm = getPackageManager();
        appinfoAdapter = new AppinfoAdapter(R.layout.item_appinfo, appInfoList);
        appinfoAdapter.setOnItemClickListener(this);
        rvAppinfo.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvAppinfo.setAdapter(appinfoAdapter);
    }

    private void getInstallAppInfo() {

        List<PackageInfo> installedPackages = pm.getInstalledPackages(0);

        for (PackageInfo packageInfo : installedPackages) {

            try {
                String packageName = packageInfo.packageName;
                String appName = packageInfo.applicationInfo.loadLabel(pm).toString();
                int versionCode = packageInfo.versionCode;
                String versionName = packageInfo.versionName;
                Drawable applicationIcon = pm.getApplicationIcon(packageName);
//                Drawable drawable = packageInfo.applicationInfo.loadIcon(getPackageManager());
                AppInfos appInfo = new AppInfos();
                appInfo.setAppName(appName);
                appInfo.setIcon(applicationIcon);
                appInfo.setPn(packageName);
                appInfo.setVc(versionCode);
                appInfo.setVn(versionName);
//                if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {//判定是否不是系统应用
                appInfoList.add(appInfo);
//                }
                Logcat.log("initView: " + packageInfo.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                appinfoAdapter.notifyDataSetChanged();
            }
        });
    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        AppInfos appInfo = appInfoList.get(position);
        ToastUtil.show_makeText(this, appInfo.toString(), Toast.LENGTH_SHORT);
    }


    class AppinfoAdapter extends BaseQuickAdapter<AppInfos, BaseViewHolder> {

        public AppinfoAdapter(@LayoutRes int layoutResId, @Nullable List<AppInfos> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, AppInfos item) {
            Logcat.log("AppInfo: " + item.toString());
            Drawable icon = item.getIcon();
            String appName = item.getAppName();
            String pn = item.getPn();
            helper
                    .setImageDrawable(R.id.iv_appIcon, icon)
                    .setText(R.id.tv_appname, appName)
                    .setText(R.id.tv_app_pkg, pn);
        }
    }

}
