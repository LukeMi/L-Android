package com.lukemi.android.tutorial.aidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.lukemi.android.toturial.aidl.ServerAidlInterface;
import com.lukemi.android.tutorial.R;
import com.socks.library.KLog;

import java.util.List;

import butterknife.OnClick;

public class AidlClientActivity extends AppCompatActivity {

    private final String TAG = AidlClientActivity.class.getSimpleName();

    TextView tvResult;

    private ServerAidlInterface serverAidlInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl_client);
        initView();
    }

    private void initView() {
        tvResult = findViewById(R.id.tv_result);
        findViewById(R.id.btn_calc).setOnClickListener(this::onViewClicked);
        findViewById(R.id.btn_c).setOnClickListener(this::onViewClicked);
    }

    @Override
    protected void onDestroy() {
        unbindService(serviceConnection);
        super.onDestroy();
    }

    @OnClick({R.id.btn_calc, R.id.btn_c})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_calc:
                connect();
                break;
            case R.id.btn_c:
                try {
                    int multi = serverAidlInterface.multi(3, 5);
                    KLog.d(TAG, "multi : " + multi);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    private void connect() {
        String pName = "com.lukemi.android.toturial.aidl";
        boolean appInstalled = isAppInstalled(this, pName);
        if (appInstalled) {
            Intent intent = new Intent();
            intent.setClassName("com.lukemi.android.toturial.aidl", "com.lukemi.android.toturial.aidl.service.AidlServerService");
            bindService(intent, serviceConnection, BIND_AUTO_CREATE);
        } else {
            tvResult.setText("未安装 aidl server App");
        }
    }

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            KLog.d(TAG, "onServiceConnected : " + name);
            serverAidlInterface = ServerAidlInterface.Stub.asInterface(service);
            String result;
            try {
                int add = serverAidlInterface.add(31, 40);
                result = String.format("31 + 40 = %d", add);
                tvResult.setText(result);
            } catch (RemoteException e) {
                e.printStackTrace();
                result = "RemoteException";
            }
            tvResult.setText(result);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            KLog.d(TAG, "onServiceDisconnected : " + name);
            tvResult.setText("RemoteException");
        }
    };

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
