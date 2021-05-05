package com.jeferry.android.widget;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class ConstraintLayoutActivity extends AppCompatActivity {

    ImageView imageView;
    TextView tvTitle;
    TextView tvProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraint_layout);
        initView();
    }

    private void initView() {
        imageView = findViewById(R.id.imageView);
        tvTitle = findViewById(R.id.tv_title);
        tvProfile = findViewById(R.id.tv_profile);
        ConstraintLayout.LayoutParams lp = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int widthPixels = metrics.widthPixels;
        int heightPx = widthPixels * 300 / 818;
        lp.width = widthPixels;
        lp.height = heightPx;
        imageView.setLayoutParams(lp);
    }
}
