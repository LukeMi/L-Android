package com.lukemi.android.tutorial.permission;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.lukemi.android.common.util.Logcat;
import com.lukemi.android.tutorial.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author lukemi
 * @date 2019/1/2 10:47
 * @des 权限测试类
 * @mail chenmingzhiji@163.com or mingzhichen1990@gmail.com
 */
public class PermissionActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_CAMERA = 0x0000001;

    @BindView(R.id.btn_camera)
    Button btnCamera;
    @BindView(R.id.tv_result)
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        ButterKnife.bind(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_CAMERA) {
            int permission = grantResults[0];
            tvResult.setText((permission == PackageManager.PERMISSION_GRANTED) ? "有权限" : "没权限");
        }
    }

    @OnClick(R.id.btn_camera)
    public void onViewClicked() {
        Logcat.log(" hasCameraPermission() = " + hasCameraPermission());
        if (hasCameraPermission()) {
            tvResult.setText("有相机权限");
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                this.requestPermissions(new String[]{Manifest.permission.CAMERA}, REQUEST_CODE_CAMERA);
            }
        }
    }

    private boolean hasCameraPermission() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        } else {
            return checkCallingOrSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
        }
    }
}
