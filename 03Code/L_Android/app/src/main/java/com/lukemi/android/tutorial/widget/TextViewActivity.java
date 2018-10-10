package com.lukemi.android.tutorial.widget;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class TextViewActivity extends AppCompatActivity {

    private TextView vitercal_Scroll;
    private TextView horizontal_Scroll;
    private TextView marqueeTextView;//跑马灯textView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(com.lukemi.android.tutorial.R.layout.activity_text_view);
        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        vitercal_Scroll = ((TextView) findViewById(com.lukemi.android.tutorial.R.id.vitercal_Scroll));
        horizontal_Scroll = ((TextView) findViewById(com.lukemi.android.tutorial.R.id.horizontal_Scroll));
        marqueeTextView = ((TextView) findViewById(com.lukemi.android.tutorial.R.id.marqueeTextView));
//        marqueeTextView.init((WindowManager)this.getSystemService(WINDOW_SERVICE));
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
