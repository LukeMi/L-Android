package com.lukemi.android.tutorial.manager;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.lukemi.android.common.IntentJumpAdapter;
import com.lukemi.android.common.IntentJumpBean;
import com.lukemi.android.tutorial.R;
import com.lukemi.android.tutorial.killprocess.AppStaticsActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author lukemi
 * @date 2018/12/27 9:46
 * @des Android 管理类
 * @mail chenmingzhiji@163.com or mingzhichen1990@gmail.com
 */
public class ManagerStatisticsActivity extends AppCompatActivity {

    @BindView(R.id.rv_intent)
    RecyclerView mRvIntent;

    private List<IntentJumpBean> intentJumpBeanList;
    private IntentJumpAdapter intentJumpAdapter;
    private OnItemClickListener mOnItemClickListener = (  adapter,   view,   position) -> {
        Class<?> c = ((IntentJumpBean) adapter.getData().get(position)).getC();
        if (c != null) {
            startActivity(new Intent(this, c));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_statistics);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initData() {
        intentJumpBeanList = new ArrayList<>();
        intentJumpBeanList.add(new IntentJumpBean("ProcessManager", AppStaticsActivity.class));
        intentJumpBeanList.add(new IntentJumpBean("ActivityManager", ActivityManagerActivity.class));
        intentJumpAdapter = new IntentJumpAdapter(R.layout.item_intent_jump, intentJumpBeanList);
        intentJumpAdapter.setOnItemClickListener(mOnItemClickListener);
    }

    private void initView() {
        mRvIntent.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRvIntent.setAdapter(intentJumpAdapter);
    }
}
