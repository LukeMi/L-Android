package com.lukemi.android.tutorial;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.jeferry.android.widget.JSAndroidActivity;
import com.lukemi.android.common.IntentJumpAdapter;
import com.lukemi.android.common.IntentJumpBean;
import com.lukemi.android.tutorial.api.gps.GpsActivity;
import com.lukemi.android.tutorial.api.sensor.SensorActivity;
import com.lukemi.android.tutorial.api.vibrator.VibratorActivity;
import com.lukemi.android.tutorial.killprocess.AppStaticsActivity;
import com.lukemi.android.tutorial.permission.PermissionActivity;
import com.lukemi.android.tutorial.sp.SpActivity;
import com.lukemi.android.tutorial.system.FlashActivity;
import com.lukemi.android.tutorial.system.ScreenShotForbiddenActivity;
import com.lukemi.android.tutorial.touch.TouchEventActivity;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AndroidActivity extends AppCompatActivity {

    private static final String TAG = AndroidActivity.class.getSimpleName();

    @BindView(R.id.rv_intent)
    RecyclerView mRvIntent;

    private List<IntentJumpBean> intentJumpBeanList;
    private IntentJumpAdapter intentJumpAdapter;
    private OnItemClickListener mOnItemClickListener = (adapter, view, position) -> {
        IntentJumpBean bean = (IntentJumpBean) adapter.getData().get(position);
        Class<?> c = bean.getC();
        if (c != null) {
            startActivity(new Intent(this, c));
        } else if (TextUtils.equals(bean.getText(), "Scheme")) {
            scheme();
        }
    };

    private void scheme() {
        Intent intent = new Intent();
        String scheme = "aidl://host:3000/path?id=2&type=1";
        intent.setData(Uri.parse(scheme));
        intent.putExtra("aidl", "aidl");
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        KLog.d(TAG, "onActivityResult");
        if (data != null) {
            String get = data.getStringExtra("aidl");
            KLog.d(TAG, get);
        }
    }

    private void initData() {
        intentJumpBeanList = new ArrayList<>();
        intentJumpBeanList.add(new IntentJumpBean("IPC机制", IPCActivity.class));
        intentJumpBeanList.add(new IntentJumpBean("GPS", GpsActivity.class));
        intentJumpBeanList.add(new IntentJumpBean("Vibrator", VibratorActivity.class));
        intentJumpBeanList.add(new IntentJumpBean("Sensor", SensorActivity.class));
        intentJumpBeanList.add(new IntentJumpBean("Process", AppStaticsActivity.class));
        intentJumpBeanList.add(new IntentJumpBean("SharedPreference", SpActivity.class));
        intentJumpBeanList.add(new IntentJumpBean("后置灯", FlashActivity.class));
        intentJumpBeanList.add(new IntentJumpBean("禁止截屏", ScreenShotForbiddenActivity.class));
        intentJumpBeanList.add(new IntentJumpBean("Permission", PermissionActivity.class));
        intentJumpBeanList.add(new IntentJumpBean("事件分发", TouchEventActivity.class));
        intentJumpBeanList.add(new IntentJumpBean("JS", JSAndroidActivity.class));
        intentJumpBeanList.add(new IntentJumpBean("Scheme", null));
        intentJumpAdapter = new IntentJumpAdapter(R.layout.item_intent_jump, intentJumpBeanList);
        intentJumpAdapter.setOnItemClickListener(mOnItemClickListener);
    }

    private void initView() {
        mRvIntent.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRvIntent.setAdapter(intentJumpAdapter);
    }
}
