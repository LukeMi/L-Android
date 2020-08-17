package com.jeferry.android.widget;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class ViewFlipperActivity extends AppCompatActivity {

    ViewFlipper marqueeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_flipper);
        marqueeView = findViewById(R.id.marquee_view);
        initView();
    }

    private void initView() {
        ViewFlipper viewFlipper = findViewById(R.id.marquee);
        TextView t1 = new TextView(this);
        t1.setBackgroundColor(ContextCompat.getColor(this, R.color.color_black));
        t1.setTextColor(ContextCompat.getColor(this, R.color.white));
        t1.setGravity(Gravity.CENTER);
        t1.setText("t1");

        TextView t2 = new TextView(this);
        t2.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
        t2.setTextColor(ContextCompat.getColor(this, R.color.color_black));
        t2.setGravity(Gravity.CENTER);
        t2.setText("t2");

        viewFlipper.addView(t1, 380, 80);
        viewFlipper.addView(t2, 800, 180);
        viewFlipper.setFlipInterval(1000);
        viewFlipper.setAutoStart(true);
        viewFlipper.startFlipping();
    }
}
