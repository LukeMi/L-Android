package com.lukemi.android.tutorial.killprocess;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lukemi.android.tutorial.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author lukemi
 * @date 2018/12/25 20:30
 * @des 应用详情
 * @mail chenmingzhiji@163.com or mingzhichen1990@gmail.com
 */
public class ProcessDetailActivity extends AppCompatActivity {
    @BindView(R.id.rv_app_detail)
    RecyclerView rvAppDetail;
    @BindView(R.id.img_launcher)
    ImageView imgLauncher;

    private PackageManager mPackageManager;
    private String pkg;
    private List<KeyValueDetailBean> keyValueDetailBeanList = new ArrayList();
    private KeyValueDetailAdapter mKeyValueDetailAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process_detail);
        ButterKnife.bind(this);
        getIntentData();
        initData();
        initView();
    }

    private void getIntentData() {
        pkg = getIntent().getStringExtra(Intent.EXTRA_PACKAGE_NAME);
        mKeyValueDetailAdapter = new KeyValueDetailAdapter(R.layout.item_key_value_detail, keyValueDetailBeanList);
    }

    private void initData() {
        mPackageManager = getPackageManager();
        try {
            PackageInfo packageInfo = mPackageManager.getPackageInfo(pkg, PackageManager.MATCH_UNINSTALLED_PACKAGES);
            long longVersionCode = packageInfo.versionCode;
            String packageName = packageInfo.packageName;
            ApplicationInfo applicationInfo = packageInfo.applicationInfo;
//            int minSdkVersion = applicationInfo.minSdkVersion;
//            int targetSdkVersion = applicationInfo.targetSdkVersion;
            int uid = applicationInfo.uid;
            String name = applicationInfo.name;
//            String packageName = applicationInfo.packageName;
            String taskAffinity = applicationInfo.taskAffinity;
            String processName = applicationInfo.processName;
            String sourceDir = applicationInfo.sourceDir;
            Drawable drawable = applicationInfo.loadIcon(mPackageManager);
            String label = applicationInfo.loadLabel(mPackageManager).toString();

            keyValueDetailBeanList.add(new KeyValueDetailBean("name", name));
            keyValueDetailBeanList.add(new KeyValueDetailBean("longVersionCode", String.valueOf(longVersionCode)));
            keyValueDetailBeanList.add(new KeyValueDetailBean("label", label));
//            keyValueDetailBeanList.add(new KeyValueDetailBean("minSdkVersion", String.valueOf(minSdkVersion)));
//            keyValueDetailBeanList.add(new KeyValueDetailBean("targetSdkVersion", String.valueOf(targetSdkVersion)));
            keyValueDetailBeanList.add(new KeyValueDetailBean("uid", String.valueOf(uid)));
            keyValueDetailBeanList.add(new KeyValueDetailBean("packageName", packageName));
            keyValueDetailBeanList.add(new KeyValueDetailBean("taskAffinity", taskAffinity));
            keyValueDetailBeanList.add(new KeyValueDetailBean("processName", processName));
            keyValueDetailBeanList.add(new KeyValueDetailBean("sourceDir", sourceDir));

            imgLauncher.setImageDrawable(drawable);

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void initView() {
        rvAppDetail.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvAppDetail.setAdapter(mKeyValueDetailAdapter);
    }
}
