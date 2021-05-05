package com.jeferry.android.widget;

import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by android on 2017/7/25.
 */

public class MeasureViewActivity extends AppCompatActivity {


    TextView tvMearure;

    TextView tvResult;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mearure_view);
        initView();
    }

    private void initView() {
        tvMearure = findViewById(R.id.tv_mearure);
        tvResult = findViewById(R.id.tv_result);
        ViewTreeObserver viewTreeObserver = tvMearure.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(() -> measureView());
    }

    private void measureView() {
        int left = tvMearure.getLeft();
        int top = tvMearure.getTop();
        int right = tvMearure.getRight();
        int bottom = tvMearure.getBottom();

        int measuredWidth = tvMearure.getMeasuredWidth();
        int measuredHeight = tvMearure.getMeasuredHeight();

        String result = "tvMearure: measure" + "\n" +
                "left = " + left + "\n" +
                "top = " + top + "\n" +
                "right = " + right + "\n" +
                "bottom = " + bottom + "\n" +
                "measuredWidth = " + measuredWidth + "\n" +
                "measuredHeight = " + measuredHeight + "\n";

        tvResult.setText(result);
    }
}
