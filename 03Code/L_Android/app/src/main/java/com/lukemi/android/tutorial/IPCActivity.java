package com.lukemi.android.tutorial;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.lukemi.android.tutorial.aidl.AidlClientActivity;
import com.lukemi.android.tutorial.util.ToastUtil;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 测试前
 */
public class IPCActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ipc);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_aidl, R.id.btn_scheme})
    public void onViewClicked(View view) {
        Class<?> target = null;
        switch (view.getId()) {
            case R.id.btn_aidl:
                target = AidlClientActivity.class;
                break;
            case R.id.btn_scheme:
                String path = "aidl://hostq:8010/path1?id=0";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(path));
                boolean b = getPackageManager().resolveActivity(intent, 0) != null;
                if (b){
                    startActivity(intent);
                }else{
                    ToastUtil.show_makeText(this,"未安装对应app",Toast.LENGTH_LONG);
                }

                break;
        }
        if (target != null) {
            startActivity(new Intent(this, target));
        }
    }


    //判断应用是否安装
    public boolean isAppInstalled(Context context, String packageName) {

        final PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> pInfo = packageManager.getInstalledPackages(0);
        if (pInfo != null) {
            for (int i = 0; i < pInfo.size(); i++) {
                if (pInfo.get(i).packageName.contains(packageName)) {
                    return true;
                }
            }
        }
        return false;
    }
}
