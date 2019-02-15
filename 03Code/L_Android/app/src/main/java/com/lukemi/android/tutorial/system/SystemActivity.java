package com.lukemi.android.tutorial.system;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lukemi.android.tutorial.R;
import com.lukemi.android.tutorial.SystemMemoryActivity;
import com.lukemi.android.tutorial.widget.IntentJumpAdapter;
import com.lukemi.android.tutorial.widget.IntentJumpBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SystemActivity extends AppCompatActivity {


    @BindView(R.id.rv_intent)
    RecyclerView mRvIntent;
    private List<IntentJumpBean> intentJumpBeanList;
    private IntentJumpAdapter intentJumpAdapter;
    private BaseQuickAdapter.OnItemClickListener mOnItemClickListener = (BaseQuickAdapter adapter, View view, int position) -> {
        IntentJumpBean bean = (IntentJumpBean) adapter.getData().get(position);
        Class<?> c = bean.getC();
        if (c != null) {
            Intent intent = new Intent(this, c);
            if (bean.getFlag() != 0) {
                intent.setFlags(bean.getFlag());
            }
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system);
        ButterKnife.bind(this);
        initAdapter();
        initView();
    }

    private void initAdapter() {
        intentJumpBeanList = new ArrayList<>();
        intentJumpBeanList.add(new IntentJumpBean("调用系统App", SystemAppListActivity.class));
        intentJumpBeanList.add(new IntentJumpBean("调用系统 设置 相关 App", SettingAppActivity.class));
        intentJumpBeanList.add(new IntentJumpBean("设置桌面壁纸", WallPaperActivity.class));
        intentJumpBeanList.add(new IntentJumpBean("内存分析", SystemMemoryActivity.class));
        intentJumpBeanList.add(new IntentJumpBean("关闭应用", CloseAppActivity.class, Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
        intentJumpAdapter = new IntentJumpAdapter(R.layout.item_intent_jump, intentJumpBeanList);
        intentJumpAdapter.setOnItemClickListener(mOnItemClickListener);
    }

    private void initView() {
        mRvIntent.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRvIntent.setAdapter(intentJumpAdapter);
    }
}
