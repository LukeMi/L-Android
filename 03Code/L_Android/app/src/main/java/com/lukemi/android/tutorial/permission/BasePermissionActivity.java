package com.lukemi.android.tutorial.permission;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;


/**
 * 权限ase封装 RxJava2 java8  ::操作符使用
 */
public class BasePermissionActivity extends AppCompatActivity {

    private final int PERMISSSION_REQUEST_CODE = 0x000001;
    private final int SETTING_REQUEST_CODE = 0x000002;
    private Consumer<Boolean> mConsumer;
    private String mPermissionHint;
    private AlertDialog mAlertDialog;
    private List<String> mDeniedPermissionList;

    protected void requestPermission(String[] permissions, Consumer<Boolean> grantedConsumer, String permissionStr) throws Exception {
        this.mConsumer = grantedConsumer;
        this.mPermissionHint = permissionStr;

        List<String> deniedPermissionList = new ArrayList<>();
        for (String per : permissions) {
            if (ContextCompat.checkSelfPermission(this, per) == PackageManager.PERMISSION_DENIED) {
                deniedPermissionList.add(per);
            }
        }
        if (deniedPermissionList.isEmpty()) {
            mConsumer.accept(true);
            return;
        }

        boolean showPermissionRational = false;
        for (String denyPer : deniedPermissionList) {
            if (showPermissionRational = ActivityCompat.shouldShowRequestPermissionRationale(this, denyPer)) {
                break;
            }
        }

        if (showPermissionRational) {
            mAlertDialog = new AlertDialog.Builder(this)
                    .setTitle("权限申请")
                    .setMessage("为了功能的正常使用，请给权限")
                    .setPositiveButton("确定", (dialog, witch) -> {
                        ActivityCompat.requestPermissions(this,
                                deniedPermissionList.toArray(new String[0]),
                                PERMISSSION_REQUEST_CODE);
                        mAlertDialog.dismiss();
                    })
                    .setNegativeButton("取消", (dialog, witch) -> {
                        mAlertDialog.dismiss();
                    }).create();
            mAlertDialog.show();
        } else {
            ActivityCompat.requestPermissions(this,
                    deniedPermissionList.toArray(new String[0]),
                    PERMISSSION_REQUEST_CODE);
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSSION_REQUEST_CODE) {
            mDeniedPermissionList = new ArrayList<>();
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                    mDeniedPermissionList.add(permissions[i]);
                }
            }
            if (mDeniedPermissionList.isEmpty()) {
                try {
                    mConsumer.accept(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                mAlertDialog = new AlertDialog.Builder(this)
                        .setTitle("权限申请")
                        .setMessage(mPermissionHint + "\n" + "是否前往设置")
                        .setPositiveButton("确定", (dialog, witch) -> {
                            go2Setting();
                            mAlertDialog.dismiss();
                        })
                        .setNegativeButton("取消", (dialog, witch) -> {
                            try {
                                mConsumer.accept(false);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            mAlertDialog.dismiss();
                        }).create();
                mAlertDialog.show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SETTING_REQUEST_CODE) {
            boolean allGranted = true;
            for (String permission : mDeniedPermissionList) {
                if (!(allGranted = ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_DENIED)) {
                    break;
                }
            }
            if (allGranted) {
                try {
                    mConsumer.accept(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    mConsumer.accept(false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private void go2Setting() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivityForResult(intent, SETTING_REQUEST_CODE);
    }
}
