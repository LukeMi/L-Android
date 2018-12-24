package com.lukemi.android.tutorial;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lukemi.android.tutorial.animation.AnimationActivity;
import com.lukemi.android.tutorial.base.BaseActivity;
import com.lukemi.android.tutorial.category.ComponentActivity;
import com.lukemi.android.tutorial.gps.GpsActivity;
import com.lukemi.android.tutorial.killprocess.ProcessActivity;
import com.lukemi.android.tutorial.notification.NotificationActivity;
import com.lukemi.android.tutorial.sdf.SpActivity;
import com.lukemi.android.tutorial.setting.FlashActivity;
import com.lukemi.android.tutorial.setting.ScreenShotForbiddenActivity;
import com.lukemi.android.tutorial.setting.SettingActivity;
import com.lukemi.android.tutorial.utiltest.UtilMainActivity;
import com.lukemi.android.tutorial.widget.IntentJumpAdapter;
import com.lukemi.android.tutorial.widget.IntentJumpBean;
import com.lukemi.android.tutorial.widget.WidgetActivity;
import com.lukemi.android.tutorial.widget.wechat.activity.WeChatMainActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
        intentJumpBeanList.add(new IntentJumpBean("Process", ProcessActivity.class));
        intentJumpBeanList.add(new IntentJumpBean("SharedPreference", SpActivity.class));
        intentJumpBeanList.add(new IntentJumpBean("后置灯", FlashActivity.class));
        intentJumpBeanList.add(new IntentJumpBean("禁止截屏", ScreenShotForbiddenActivity.class));
        intentJumpAdapter = new IntentJumpAdapter(R.layout.item_intent_jump, intentJumpBeanList);
        intentJumpAdapter.setOnItemClickListener(mOnItemClickListener);
    }

    private void initView() {
        mRvIntent.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRvIntent.setAdapter(intentJumpAdapter);
    }
}
