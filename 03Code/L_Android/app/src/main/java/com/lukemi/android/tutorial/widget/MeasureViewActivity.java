package com.lukemi.android.tutorial.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.lukemi.android.tutorial.R;
import com.lukemi.android.tutorial.util.Logcat;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by android on 2017/7/25.
 */

public class MeasureViewActivity extends AppCompatActivity {

    @BindView(R.id.tv_mearure)
    TextView tvMearure;
    @BindView(R.id.tv_result)
    TextView tvResult;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mearure_view);
        ButterKnife.bind(this);
        ViewTreeObserver viewTreeObserver = tvMearure.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mewsurView();
            }
        });
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();

    }

    private void mewsurView() {
        int left = tvMearure.getLeft();
        int top = tvMearure.getTop();
        int right = tvMearure.getRight();
        int bottom = tvMearure.getBottom();

        int measuredWidth = tvMearure.getMeasuredWidth();
        int measuredHeight = tvMearure.getMeasuredHeight();

        String result = "tvMearure: measure" +"\n" +
                                "left = " + left + "\n" +
                                "top = " + top + "\n" +
                                "right = " + right + "\n" +
                                "bottom = " + bottom + "\n" +
                                "measuredWidth = " + measuredWidth + "\n" +
                                "measuredHeight = " + measuredHeight + "\n";

        tvResult.setText(result);
    }
}
