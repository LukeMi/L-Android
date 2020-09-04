package com.lukemi.android.tutorial.lib;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lukemi.android.common.IntentJumpAdapter;
import com.lukemi.android.common.IntentJumpBean;
import com.lukemi.android.tutorial.R;
import com.lukemi.android.tutorial.bitmap.BitmapActivity;

import java.util.ArrayList;
import java.util.List;

public class FrameworkLibActivity extends AppCompatActivity {

    private RecyclerView mRvIntent;

    private List<IntentJumpBean> intentJumpBeanList;

    private IntentJumpAdapter intentJumpAdapter;

    private BaseQuickAdapter.OnItemClickListener mOnItemClickListener = (BaseQuickAdapter adapter, View view, int position) -> {
        IntentJumpBean bean = (IntentJumpBean) adapter.getData().get(position);
        Class<?> c = bean.getC();
        if (c != null) {
            startActivity(new Intent(this, c));
        } else if (null != bean.getPostcard()) {
            bean.getPostcard().navigation();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_framework_lib);
        initData();
        initView();
    }

    private void initData() {
        intentJumpBeanList = new ArrayList<>();
        intentJumpBeanList.add(new IntentJumpBean("Bitmap", BitmapActivity.class));

        intentJumpAdapter = new IntentJumpAdapter();
        intentJumpAdapter.addData(intentJumpBeanList);
        intentJumpAdapter.setOnItemClickListener(mOnItemClickListener);
    }

    private void initView() {
        mRvIntent = findViewById(R.id.rv_intent);
        mRvIntent.setAdapter(intentJumpAdapter);
    }
}