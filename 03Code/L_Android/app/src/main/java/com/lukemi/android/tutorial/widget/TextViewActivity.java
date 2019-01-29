package com.lukemi.android.tutorial.widget;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.lukemi.android.tutorial.R;

public class TextViewActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView vitercal_Scroll;
    private TextView horizontal_Scroll;
    //跑马灯textView
    private TextView marqueeTextView;
    private TextView tvDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_text_view);
        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        vitercal_Scroll = findViewById(R.id.vitercal_Scroll);
        horizontal_Scroll = findViewById(R.id.horizontal_Scroll);
        marqueeTextView = findViewById(R.id.marqueeTextView);
        tvDrawable = findViewById(R.id.tv_drawable);
        tvDrawable.setOnClickListener(this);
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


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_drawable:
                tvDrawable.setSelected(!tvDrawable.isSelected());
            break;
            default:
                break;
        }

    }
}
