package com.jeferry.android.widget.flexbox;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.flexbox.FlexboxLayout;
import com.jeferry.android.widget.R;

import java.util.ArrayList;
import java.util.List;


public class FlexBoxLayoutActivity extends AppCompatActivity {

    private List<FlexBoxEntity> list = new ArrayList<>();

    private FlexboxLayout mFl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flexbox_layout);
        initView();
        initData();
    }

    protected void initView() {
        mFl = findViewById(R.id.fl);
    }

    private void initData() {
        FlexBoxEntity f1 = new FlexBoxEntity("android_android", false);
        FlexBoxEntity f2 = new FlexBoxEntity("java_java", false);
        FlexBoxEntity f3 = new FlexBoxEntity("html_html", false);
        FlexBoxEntity f4 = new FlexBoxEntity("angrialjs_angrialjs", false);
        list.add(f1);
        list.add(f2);
        list.add(f3);
        list.add(f4);

        LayoutInflater inflater = LayoutInflater.from(this);
        for (FlexBoxEntity s : list) {
            String name = s.name;
            boolean selected = s.selected;
            View view = inflater.inflate(R.layout.item_flex_box, null);
            TextView tv = view.findViewById(R.id.tv);
            view.setOnClickListener(v -> {
                v.setSelected(s.selected = !s.selected);
                String text = tv.getText().toString() + (s.selected ? "选中" : "未选中");
                Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
            });
            tv.setText(name);
            tv.setSelected(selected);
            mFl.addView(view);
        }
    }
}
