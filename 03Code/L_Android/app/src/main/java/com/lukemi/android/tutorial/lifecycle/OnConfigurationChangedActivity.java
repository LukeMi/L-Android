package com.lukemi.android.tutorial.lifecycle;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.lukemi.android.tutorial.R;
import com.socks.library.KLog;

import butterknife.ButterKnife;

/**
 * 1. 不设置清单文件  android:configChanges ；生命周期重走
 * 2. 设置清单文件  - android:configChanges ；而不会重走生命周期 ；对应activity - onConfigurationChanged 会回调，
 */
public class OnConfigurationChangedActivity extends AppCompatActivity {

    private final String TAG = OnConfigurationChangedActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session3);
        ButterKnife.bind(this);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        int orientation = newConfig.orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            KLog.d(TAG, "横屏操作");
        } else if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            KLog.d(TAG, "竖屏操作");
        }
    }
}
