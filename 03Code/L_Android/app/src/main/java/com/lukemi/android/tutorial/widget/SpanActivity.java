package com.lukemi.android.tutorial.widget;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;
import android.widget.TextView;

import com.lukemi.android.tutorial.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SpanActivity extends AppCompatActivity {

    @BindView(R.id.tv)
    TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_span);
        ButterKnife.bind(this);


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
