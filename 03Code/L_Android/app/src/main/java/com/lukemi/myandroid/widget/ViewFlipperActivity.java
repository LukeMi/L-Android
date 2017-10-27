package com.lukemi.myandroid.widget;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ViewFlipper;

import com.lukemi.myandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewFlipperActivity extends AppCompatActivity {

    @BindView(R.id.marquee_view)
    ViewFlipper marqueeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_flipper);
        ButterKnife.bind(this);
    }
}
