package com.jeferry.android.widget;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;


public class FlexBoxLayoutActivity extends AppCompatActivity {

    private static final String[] CONTENT = {"android_android", "ios_ios", "java_java", "html_html", "angrialjs_angrialjs"};

    private FlexboxLayout mFl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flexbox_layout);
        initView();
    }

    protected void initView() {
        mFl = findViewById(R.id.fl);
        LayoutInflater inflater = LayoutInflater.from(this);
        for (String s : CONTENT) {
            View view = inflater.inflate(R.layout.item_flex_box, null);
            TextView tv = view.findViewById(R.id.tv);
            tv.setText(s);
            mFl.addView(view);
        }
    }

}
