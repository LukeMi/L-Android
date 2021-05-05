package com.jeferry.android.widget;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SpanActivity extends AppCompatActivity {

    TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_span);
        mTv = findViewById(R.id.tv);
        String text = "需要\n大小";
        String index = "大小";
        int start = text.indexOf(index);
        int end = text.length();
        System.out.println("start : " + start + " ;end : " + end);
        SpannableString ss = new SpannableString(text);
        RelativeSizeSpan rs = new RelativeSizeSpan(0.6f);
        ss.setSpan(rs, start, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        mTv.setText(ss);

    }
}
