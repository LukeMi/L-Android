package com.lukemi.android.tutorial.xg;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.jeferry.android.xgpush.XGPushUtil;
import com.lukemi.android.tutorial.R;
import com.socks.library.KLog;

public class XGPushActivity extends AppCompatActivity {

    private static final String TAG = XGPushActivity.class.getSimpleName();

    // 注册
    private TextView mTvXgRegister;

    // 反注册
    private TextView mTvXgUnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_x_g_push);
        initView();
    }

    private void initView() {
        mTvXgRegister = findViewById(R.id.tv_xg_register);
        mTvXgUnRegister = findViewById(R.id.tv_xg_un_register);
        setRegisterEnable(true);
        findViewById(R.id.tv_xg_register).setOnClickListener(this::onClick);
        findViewById(R.id.tv_xg_un_register).setOnClickListener(this::onClick);

    }

    private void onClick(View view) {
        int id = view.getId();
        if (id == R.id.tv_xg_register) {
            XGPushUtil.register(getApplicationContext(), new com.tencent.android.tpush.XGIOperateCallback() {
                @Override
                public void onSuccess(Object data, int flag) {
                    //token在设备卸载重装的时候有可能会变
                    KLog.d(TAG, "注册成功，设备token为：" + data + " ；" + Thread.currentThread().getName());
                    new Handler(Looper.getMainLooper()).post(() -> {
                        setRegisterEnable(false);
                    });
                }

                @Override
                public void onFail(Object data, int errCode, String msg) {
                    KLog.d(TAG, "注册失败，错误码：" + errCode + ",错误信息：" + msg);
                }
            });
        } else if (id == R.id.tv_xg_un_register) {
            setRegisterEnable(true);
            XGPushUtil.unregister(getApplicationContext());
        }
    }

    private void setRegisterEnable(boolean enable) {
        mTvXgRegister.setEnabled(enable);
        mTvXgUnRegister.setEnabled(!enable);
    }

}