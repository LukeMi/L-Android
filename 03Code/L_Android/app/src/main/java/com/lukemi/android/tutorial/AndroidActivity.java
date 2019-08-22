package com.lukemi.android.tutorial;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lukemi.android.tutorial.api.gps.GpsActivity;
import com.lukemi.android.tutorial.api.vibrator.VibratorActivity;
import com.lukemi.android.tutorial.killprocess.AppStaticsActivity;
import com.lukemi.android.tutorial.permission.PermissionActivity;
import com.lukemi.android.tutorial.sdf.SpActivity;
import com.lukemi.android.tutorial.system.FlashActivity;
import com.lukemi.android.tutorial.system.ScreenShotForbiddenActivity;
import com.lukemi.android.tutorial.widget.IntentJumpAdapter;
import com.lukemi.android.tutorial.widget.IntentJumpBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AndroidActivity extends AppCompatActivity {

    @BindView(R.id.rv_intent)
    RecyclerView mRvIntent;

    private List<IntentJumpBean> intentJumpBeanList;
    private IntentJumpAdapter intentJumpAdapter;
    private BaseQuickAdapter.OnItemClickListener mOnItemClickListener = (BaseQuickAdapter adapter, View view, int position) -> {
        Class<?> c = ((IntentJumpBean) adapter.getData().get(position)).getC();
        if (c != null) {
            startActivity(new Intent(this, c));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initData() {
        intentJumpBeanList = new ArrayList<>();
        intentJumpBeanList.add(new IntentJumpBean("IPC机制", IPCActivity.class));
        intentJumpBeanList.add(new IntentJumpBean("GPS", GpsActivity.class));
        intentJumpBeanList.add(new IntentJumpBean("Vibrator", VibratorActivity.class));
        intentJumpBeanList.add(new IntentJumpBean("Process", AppStaticsActivity.class));
        intentJumpBeanList.add(new IntentJumpBean("SharedPreference", SpActivity.class));
        intentJumpBeanList.add(new IntentJumpBean("后置灯", FlashActivity.class));
        intentJumpBeanList.add(new IntentJumpBean("禁止截屏", ScreenShotForbiddenActivity.class));
        intentJumpBeanList.add(new IntentJumpBean("Permission", PermissionActivity.class));
        intentJumpAdapter = new IntentJumpAdapter(R.layout.item_intent_jump, intentJumpBeanList);
        intentJumpAdapter.setOnItemClickListener(mOnItemClickListener);
    }

    private void initView() {
        mRvIntent.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRvIntent.setAdapter(intentJumpAdapter);
    }
}
