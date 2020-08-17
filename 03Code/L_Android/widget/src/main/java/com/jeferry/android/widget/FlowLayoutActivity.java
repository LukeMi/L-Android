package com.jeferry.android.widget;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.lukemi.android.common.view.FlowLayout;

import java.util.ArrayList;
import java.util.List;


public class FlowLayoutActivity extends AppCompatActivity {

    FlowLayout flowlayout;
    private List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow_layout);
        flowlayout = findViewById(R.id.flowlayout);
        initData();
        initView();
    }

    private void initView() {
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            TextView tv = new TextView(this);
            tv.setText(s);
//            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
//            tv.setLayoutParams(lp);
            tv.requestLayout();
            tv.invalidate();
            flowlayout.addView(tv);
        }
    }

    private void initData() {
        list.add("周一");
        list.add("图样图僧破");
        list.add("二十二");
        list.add("天");
        list.add("得得");
        list.add("恩恩恩");
        list.add("对对对对");
        list.add("哈哈哈哈哈");
        list.add("哈哈哈哈哈");
        list.add("天");
        list.add("得得");
        list.add("恩恩恩");
        list.add("对对对对");
        list.add("天");
        list.add("哈哈哈哈哈");

    }
}
