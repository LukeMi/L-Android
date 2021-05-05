package com.lukemi.android.tutorial.api.sensor;

import android.content.Context;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.lukemi.android.tutorial.R;

public class SensorActivity extends AppCompatActivity {

    private SensorManager mSensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
        mSensorManager = ((SensorManager) getSystemService(Context.SENSOR_SERVICE));
//        mSensorManager.registerListener()
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
