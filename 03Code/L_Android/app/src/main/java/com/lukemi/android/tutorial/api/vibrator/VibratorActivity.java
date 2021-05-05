package com.lukemi.android.tutorial.api.vibrator;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.lukemi.android.tutorial.R;

/**
 * 震动api Vibrator
 */
public class VibratorActivity extends AppCompatActivity {

    private Vibrator mVibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vibrator);
        mVibrator = (android.os.Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        findViewById(R.id.btn_vibrator).setOnClickListener(this::onClick);
        findViewById(R.id.btn_vibrator_cancel).setOnClickListener(this::onClick);
    }

    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_vibrator:
                vibrator();
                break;
            case R.id.btn_vibrator_cancel:
                vibratorCancel();
                break;
            default:
                break;
        }
    }

    private void vibrator() {
        if (mVibrator.hasVibrator()) {
            mVibrator.vibrate(15000);
        }
    }

    private void vibratorCancel() {
        mVibrator.cancel();
    }


}
