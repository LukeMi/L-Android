package com.lukemi.android.tutorial.category;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lukemi.android.tutorial.R;
import com.lukemi.android.tutorial.base.AbsBaseActivity;
import com.lukemi.android.tutorial.receiver.ReceiverActivity;
import com.lukemi.android.tutorial.sessionlifecycle.Session1Activity;
import com.lukemi.android.tutorial.volum.VolumeActivity;
import com.lukemi.android.tutorial.widget.IntentJumpAdapter;
import com.lukemi.android.tutorial.widget.IntentJumpBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ComponentActivity extends AbsBaseActivity {

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
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_component;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        intentJumpBeanList = new ArrayList<>();
        intentJumpBeanList = new ArrayList<>();
        intentJumpBeanList.add(new IntentJumpBean("Activity", Session1Activity.class));
        intentJumpBeanList.add(new IntentJumpBean("Service", VolumeActivity.class));
        intentJumpBeanList.add(new IntentJumpBean("BroadcastReceiver", ReceiverActivity.class));
        intentJumpBeanList.add(new IntentJumpBean("ContentProvider", null));
        intentJumpAdapter = new IntentJumpAdapter(R.layout.item_intent_jump, intentJumpBeanList);
        intentJumpAdapter.setOnItemClickListener(mOnItemClickListener);
    }

    @Override
    protected void initView() {
        mRvIntent.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRvIntent.setAdapter(intentJumpAdapter);
    }


}