package com.lukemi.android.tutorial.system;

import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import com.lukemi.android.tutorial.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author lukemi
 * @date 2018/12/24 18:45
 * @des 闪光灯
 * @mail chenmingzhiji@163.com or mingzhichen1990@gmail.com
 */
public class FlashActivity extends AppCompatActivity {

    /**
     * 闪光灯不可用
     */
    private final int FLASH_UNABLE_STATUS = -1;

    /**
     * 闪光灯关闭
     */
    private final int FLASH_CLOSE_STATUS = 0;

    /**
     * 闪光灯打开
     */
    private final int FLASH_OPEN_STATUS = 1;


    @BindView(R.id.btn_switch)
    Button btnSwitch;
    private Camera camera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initData() {
        //初始化
        camera = Camera.open();
        camera.startPreview();

    }

    private void initView() {
        setBTN();
    }

    private void setBTN() {
        btnSwitch.setText(isFlashlightOn() ? "关" : "开");
    }

    @OnClick(R.id.btn_switch)
    public void onViewClicked() {
        flashlight();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    /**
     * 检查是否有相机权限
     *
     * @return
     */
    private boolean hasCameraPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return this.checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
        } else {
            return true;
        }
    }

    //判断
    public boolean isFlashlightOn() {
        try {
            Camera.Parameters parameters = camera.getParameters();
            String flashMode = parameters.getFlashMode();
            if (flashMode.equals(android.hardware.Camera.Parameters.FLASH_MODE_TORCH)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }


    public void Open() {
        //异常处理一定要加，否则Camera打开失败的话程序会崩溃
        try {
            camera = Camera.open();
        } catch (Exception e) {
            Toast.makeText(getBaseContext(), "Camera被占用，请先关闭", Toast.LENGTH_SHORT).show();
        }

        if (camera != null) {
            //打开闪光灯
            camera.startPreview();
            Camera.Parameters parameter = camera.getParameters();
            parameter.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            camera.setParameters(parameter);
        }
    }

    public void Close() {
        if (camera != null) {
            //关闭
            camera.getParameters().setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            camera.setParameters(camera.getParameters());
            camera.stopPreview();
            camera.release();
            camera = null;
        }
    }

    /**
     * 闪光灯开关
     */
    public void flashlight() {
        if (isFlashlightOn()) {
            Close();
            camera = null;
        } else {
            Open();
        }
        setBTN();
    }
}
