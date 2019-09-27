package com.jeferry.android.widget;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import static android.widget.ImageView.ScaleType;

public class ImageViewCenterInsideActivity extends AppCompatActivity {

    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view_center_inside);
        initView();
    }

    private void initView() {
        img = findViewById(R.id.img);
        findViewById(R.id.FIT_END).setOnClickListener(this::onViewClicked);
        findViewById(R.id.CENTER).setOnClickListener(this::onViewClicked);
        findViewById(R.id.CENTER_CROP).setOnClickListener(this::onViewClicked);
        findViewById(R.id.CENTER_INSIDE).setOnClickListener(this::onViewClicked);
        findViewById(R.id.MATRIX).setOnClickListener(this::onViewClicked);
        findViewById(R.id.FIT_XY).setOnClickListener(this::onViewClicked);
        findViewById(R.id.FIT_START).setOnClickListener(this::onViewClicked);
        findViewById(R.id.FIT_CENTER).setOnClickListener(this::onViewClicked);
    }

    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.FIT_END) {
            img.setScaleType(ScaleType.FIT_END);
        } else if (id == R.id.CENTER) {
            img.setScaleType(ScaleType.CENTER);
        } else if (id == R.id.CENTER_CROP) {
            img.setScaleType(ScaleType.CENTER_CROP);
        } else if (id == R.id.CENTER_INSIDE) {
            img.setScaleType(ScaleType.CENTER_INSIDE);
        } else if (id == R.id.MATRIX) {
            img.setScaleType(ScaleType.MATRIX);
        } else if (id == R.id.FIT_XY) {
            img.setScaleType(ScaleType.FIT_XY);
        } else if (id == R.id.FIT_START) {
            img.setScaleType(ScaleType.FIT_START);
        } else if (id == R.id.FIT_CENTER) {
            img.setScaleType(ScaleType.FIT_CENTER);
        }
    }
}
