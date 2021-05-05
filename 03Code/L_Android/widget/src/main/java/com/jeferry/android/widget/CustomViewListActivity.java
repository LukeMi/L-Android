package com.jeferry.android.widget;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.lukemi.android.common.IntentJumpAdapter;
import com.lukemi.android.common.IntentJumpBean;

import java.util.ArrayList;
import java.util.List;

public class CustomViewListActivity extends AppCompatActivity {
    private RecyclerView mRvIntent;
    private List<IntentJumpBean> intentJumpBeanList;
    private IntentJumpAdapter intentJumpAdapter;
    private OnItemClickListener mOnItemClickListener = (adapter, view, position) -> {
        Class<?> c = ((IntentJumpBean) adapter.getData().get(position)).getC();
        if (c != null) {
            startActivity(new Intent(this, c));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view_list);

        initData();
        initView();
    }

    private void initData() {
        intentJumpBeanList = new ArrayList<>();
        intentJumpBeanList.add(new IntentJumpBean("笑脸图片", EmotionalFaceActivity.class));
        intentJumpBeanList.add(new IntentJumpBean("签名", SignActivity.class));

        intentJumpAdapter = new IntentJumpAdapter(R.layout.item_intent_jump, intentJumpBeanList);
        intentJumpAdapter.setOnItemClickListener(mOnItemClickListener);
    }

    private void initView() {
        mRvIntent = findViewById(R.id.rv_intent);
        mRvIntent.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRvIntent.setAdapter(intentJumpAdapter);
    }

}
