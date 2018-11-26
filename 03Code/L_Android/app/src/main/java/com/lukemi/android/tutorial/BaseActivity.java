package com.lukemi.android.tutorial;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import com.lukemi.android.common.util.Logcat;


public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Logcat.log_life("----lifeTime---->" + this.getClass().getSimpleName() + " : " + "onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onRestart() {
        Logcat.log_life("----lifeTime---->" + this.getClass().getSimpleName() + " : " + "onRestart");
        super.onRestart();
    }

    @Override
    protected void onStart() {
        Logcat.log_life("----lifeTime---->" + this.getClass().getSimpleName() + " : " + "onStart");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Logcat.log_life("----lifeTime---->" + this.getClass().getSimpleName() + " : " + "onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Logcat.log_life("----lifeTime---->" + this.getClass().getSimpleName() + " : " + "onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Logcat.log_life("----lifeTime---->" + this.getClass().getSimpleName() + " : " + "onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Logcat.log_life("----lifeTime---->" + this.getClass().getSimpleName() + " : " + "onDestroy");
        super.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        Logcat.log_life("----lifeTime---->" + this.getClass().getSimpleName() + " : " + "onSaveInstanceState");
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        Logcat.log_life("----lifeTime---->" + this.getClass().getSimpleName() + " : " + "onConfigurationChanged");
        super.onConfigurationChanged(newConfig);
    }
}
