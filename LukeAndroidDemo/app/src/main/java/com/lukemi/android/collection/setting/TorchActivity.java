package com.lukemi.android.collection.setting;

import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class TorchActivity extends AppCompatActivity implements View.OnClickListener {
    private Button torchSwitch;
    private Button adjustBright;
    public static Camera cam;//静态很重要

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.lukemi.android.collection.R.layout.activity_torch);

        initView();
    }

    private void initView() {

        torchSwitch = ((Button) findViewById(com.lukemi.android.collection.R.id.torchSwitch));
        adjustBright = ((Button) findViewById(com.lukemi.android.collection.R.id.torchSwitch));

        torchSwitch.setText("开");
        torchSwitch.setOnClickListener(this);
        adjustBright.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int smoothValue = 100;
        try {
            switch (v.getId()) {
                case com.lukemi.android.collection.R.id.torchSwitch:
                    if (((Button) v).getText().equals("开")) {
                        ((Button) v).setText("关");
                        flashLightOn(v);
                    } else {
                        ((Button) v).setText("开");
                        flashLightOff(v);
                    }
                    break;
                case com.lukemi.android.collection.R.id.adjustBright:
                    if (smoothValue >= 0) {
                        cam.startSmoothZoom(smoothValue);
                        cam.startPreview();
                        if (smoothValue == 0) {
                            smoothValue = 100;
                        }
                    }else{
                        smoothValue -= 10;
                    }
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(TorchActivity.this, "该设备不支持led", Toast.LENGTH_SHORT).show();
        }

    }

    public void flashLightOn(View view) {

        try {
            //先判断是否 devices 支持 LED
            if (getPackageManager().hasSystemFeature(
                    PackageManager.FEATURE_CAMERA_FLASH)) {
                cam = Camera.open();
                Camera.Parameters p = cam.getParameters();
                p.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                cam.setParameters(p);
                cam.startPreview();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getBaseContext(), "Exception flashLightOn()",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void flashLightOff(View view) {
        try {
            if (getPackageManager().hasSystemFeature(
                    PackageManager.FEATURE_CAMERA_FLASH)) {
                cam.stopSmoothZoom();
                cam.stopPreview();
                cam.release();
                cam = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getBaseContext(), "Exception flashLightOff",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
