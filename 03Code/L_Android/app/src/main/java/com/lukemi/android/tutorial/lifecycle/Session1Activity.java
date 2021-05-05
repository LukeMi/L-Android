package com.lukemi.android.tutorial.lifecycle;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import com.lukemi.android.common.util.Logcat;
import com.lukemi.android.tutorial.R;
import com.lukemi.android.tutorial.lifecycle.flag.FlagActivity;
import com.socks.library.KLog;


public class Session1Activity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = Session1Activity.class.getSimpleName();

    private Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session1);
        findViewById(R.id.intentBTN_SA1).setOnClickListener(this);
        findViewById(R.id.tv_onConfigChanged).setOnClickListener(this::onClick);
        findViewById(R.id.tv_activity_option).setOnClickListener(this::onClick);
        findViewById(R.id.tv_single_instance).setOnClickListener(this::onClick);
        findViewById(R.id.tv_flag).setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_onConfigChanged:
                startActivity(new Intent(Session1Activity.this, OnConfigurationChangedActivity.class));
                break;
            case R.id.intentBTN_SA1:
                Intent intent = new Intent(this, SaveInstanceStateActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_activity_option:
                ActivityOptionsCompat optionsCompat = ActivityOptionsCompat
                        .makeCustomAnimation(this, R.anim.anim_bottom_in, R.anim.anim_bottom_out);
                ActivityOptionActivity.start(this, optionsCompat);
                break;
            case R.id.tv_single_instance:
                KLog.d(TAG, getTaskId());
                mHandler.postDelayed(() -> Session1Activity.this.startActivity(new Intent(this, SingleInstanceActivity.class)), 3000);
                break;
            case R.id.tv_flag:
                startActivity(new Intent(this, FlagActivity.class));
                break;
            default:
                break;
        }
    }

    @Override
    protected void onResume() {
        Logcat.log("Session1Activity onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Logcat.log("Session1Activity onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Logcat.log("Session1Activity onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        mHandler.removeCallbacksAndMessages(null);
        Logcat.log("Session1Activity onDestroy");
        super.onDestroy();
    }
}
