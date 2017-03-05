package com.tbug.android.collection.widget;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.tbug.android.collection.R;

public class TextViewActivity extends AppCompatActivity {

    private TextView vitercal_Scroll;
    private TextView horizontal_Scroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_text_view);
        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        vitercal_Scroll = ((TextView) findViewById(R.id.vitercal_Scroll));
        horizontal_Scroll = ((TextView) findViewById(R.id.horizontal_Scroll));
        setView();
    }

    /**
     * 设置View
     */
    private void setView() {
        vitercal_Scroll.setMovementMethod(ScrollingMovementMethod.getInstance());
        horizontal_Scroll.setMovementMethod(ScrollingMovementMethod.getInstance());
    }
}
