package com.lukemi.android.tutorial;

import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.lukemi.android.common.IntentJumpAdapter;
import com.lukemi.android.common.IntentJumpBean;
import com.lukemi.android.tutorial.baidu.BDActivity;
import com.lukemi.android.tutorial.base.AbsBaseActivity;
import com.lukemi.android.tutorial.brvah.BaseQuickActivity;
import com.lukemi.android.tutorial.db.GreenDaoActivity;
import com.lukemi.android.tutorial.evenbus.EventBusReceiveActivity;
import com.lukemi.android.tutorial.glide.GlideActivity;
import com.lukemi.android.tutorial.handler.HandlerTestActivity;
import com.lukemi.android.tutorial.room.RoomActivity;
import com.lukemi.android.tutorial.xg.XGPushActivity;
import com.tencent.bugly.crashreport.CrashReport;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ThirdAPIActivity extends AbsBaseActivity {

    @BindView(R.id.rv_intent)
    RecyclerView mRvIntent;

    private List<IntentJumpBean> intentJumpBeanList;
    private IntentJumpAdapter intentJumpAdapter;
    private OnItemClickListener mOnItemClickListener = (adapter, view, position) -> {
        Class<?> c = ((IntentJumpBean) adapter.getData().get(position)).getC();
        if (c != null) {
            startActivity(new Intent(this, c));
        } else if (((IntentJumpBean) adapter.getData().get(position)).getText() == "bugly") {
            CrashReport.testJavaCrash();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_third_api;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        intentJumpBeanList = new ArrayList<>();
        intentJumpBeanList.add(new IntentJumpBean("百度API", BDActivity.class));
        intentJumpBeanList.add(new IntentJumpBean("Handler机制", HandlerTestActivity.class));
        intentJumpBeanList.add(new IntentJumpBean("GreenDao", GreenDaoActivity.class));
        intentJumpBeanList.add(new IntentJumpBean("EventBus", EventBusReceiveActivity.class));
        intentJumpBeanList.add(new IntentJumpBean("Glide", GlideActivity.class));
        intentJumpBeanList.add(new IntentJumpBean("信鸽推送", XGPushActivity.class));
        intentJumpBeanList.add(new IntentJumpBean("BRVAH", BaseQuickActivity.class));
        intentJumpBeanList.add(new IntentJumpBean("room", RoomActivity.class));
        intentJumpBeanList.add(new IntentJumpBean("bugly", null));
        intentJumpAdapter = new IntentJumpAdapter(R.layout.item_intent_jump, intentJumpBeanList);
        intentJumpAdapter.setOnItemClickListener(mOnItemClickListener);
    }

    @Override
    protected void initView() {
        mRvIntent.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRvIntent.setAdapter(intentJumpAdapter);
    }
}
