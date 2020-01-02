package com.jeferry.android.widget;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.TextView;

public class SeekBarActivity extends AppCompatActivity {

    private static final String TAG = SeekBarActivity.class.getSimpleName();

    private SeekBar mSeekBar;

    private TextView mTvValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seekbar);
        initView();
    }

    private void initView() {
        mSeekBar = findViewById(R.id.sb1);
        mTvValue = findViewById(R.id.tv_value);
        setValue(mSeekBar.getProgress());
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.d(TAG, "onProgressChanged : " + progress);
                setValue(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.d(TAG, "onStartTrackingTouch : " + seekBar.getId());
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.d(TAG, "onStopTrackingTouch : " + seekBar.getId());
            }
        });
    }

    private void setValue(int progress) {
        mTvValue.setText(String.valueOf(progress));
    }
}
