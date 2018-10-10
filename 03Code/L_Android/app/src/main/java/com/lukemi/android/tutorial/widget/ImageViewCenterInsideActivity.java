package com.lukemi.android.tutorial.widget;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.lukemi.android.tutorial.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.widget.ImageView.ScaleType;

public class ImageViewCenterInsideActivity extends AppCompatActivity {

    @BindView(R.id.img)
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view_center_inside);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.FIT_END, R.id.CENTER, R.id.CENTER_CROP, R.id.CENTER_INSIDE, R.id.MATRIX, R.id.FIT_XY, R.id.FIT_START, R.id.FIT_CENTER})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.FIT_END:
                img.setScaleType(ScaleType.FIT_END);
                break;
            case R.id.CENTER:
                img.setScaleType(ScaleType.CENTER);
                break;
            case R.id.CENTER_CROP:
                img.setScaleType(ScaleType.CENTER_CROP);
                break;
            case R.id.CENTER_INSIDE:
                img.setScaleType(ScaleType.CENTER_INSIDE);
                break;
            case R.id.MATRIX:
                img.setScaleType(ScaleType.MATRIX);
                break;
            case R.id.FIT_XY:
                img.setScaleType(ScaleType.FIT_XY);
                break;
            case R.id.FIT_START:
                img.setScaleType(ScaleType.FIT_START);
                break;
            case R.id.FIT_CENTER:
                img.setScaleType(ScaleType.FIT_CENTER);
                break;
        }
    }
}
