package com.lukemi.android.tutorial.permission;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
public class PermissionActivity extends BasePermissionActivity {

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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @OnClick(R.id.btn_camera)
    public void onViewClicked() {
        try {
            requestPermission(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, this::granted, "sss");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void granted(boolean granted) {
        Logcat.log("granted : " + granted);
        tvResult.setText(granted ? "有权限" : "没权限");
        if (granted) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            startActivity(intent);
        }
    }
}
