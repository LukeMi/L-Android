package com.lukemi.android.tutorial.viewtest;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.lukemi.android.tutorial.util.HttpUtils;
import com.lukemi.android.common.util.Logcat;
import com.lukemi.android.tutorial.util.SDCardUtils;
import com.lukemi.android.tutorial.view.LinePathView;
import com.lukemi.android.common.ShowBigPicClass;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class EleSignActivity extends AppCompatActivity implements View.OnClickListener {

    private LinePathView eleSignView;
    private Button isSigned_ESA_BTN;
    private Button showPicBig_ESA_BTN;
    private Button clearSigned_ESA_BTN;
    private int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 0x0001;
    private short SDK_PERMISSION_REQUEST = 0x0002;
    File f = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.lukemi.android.tutorial.R.layout.activity_ele_sign);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        getPermissions();
//        getPersimmions();
    }

    /**
     * 请求权限
     */
    private void getPermissions() {
        if (SDCardUtils.isSDCardMounted()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (this.checkSelfPermission(Manifest.permission_group.STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    /*  requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            WRITE_EXTERNAL_STORAGE_REQUEST_CODE);*/
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void initView() {
        eleSignView = findViewById(com.lukemi.android.tutorial.R.id.elecSign_ESA);
        eleSignView.setPenColor(com.lukemi.android.tutorial.R.color.black);
        isSigned_ESA_BTN = findViewById(com.lukemi.android.tutorial.R.id.isSigned_ESA);
        clearSigned_ESA_BTN = findViewById(com.lukemi.android.tutorial.R.id.clearSigned_ESA);
        showPicBig_ESA_BTN = findViewById(com.lukemi.android.tutorial.R.id.showPicBig_ESA);
        isSigned_ESA_BTN.setOnClickListener(this);
        clearSigned_ESA_BTN.setOnClickListener(this);
        showPicBig_ESA_BTN.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case com.lukemi.android.tutorial.R.id.isSigned_ESA:
                String msg = eleSignView.getTouched() ? "已经签名" : "未签名";
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                break;
            case com.lukemi.android.tutorial.R.id.clearSigned_ESA:
                eleSignView.clear();
                break;
            case com.lukemi.android.tutorial.R.id.showPicBig_ESA:
                Bitmap bm = eleSignView.getBitMap();
                boolean b = savePicToLocal();
                if (eleSignView.getTouched()) {
                    Logcat.log("----bmisrecycled---->>" + bm.isRecycled() + ";是否保存成功 " + b + ";" +
                            "压缩前----图片的大小" + (bm.getByteCount() / 1024 / 1024)
                            + "M宽度为" + bm.getWidth() + "高度为" + bm.getHeight());
//                    ShowBigPicClass showBigPicClass = new ShowBigPicClass(this, BitmapFactory.decodeResource(getResources(), R.drawable.abc));

                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    bm.compress(Bitmap.CompressFormat.JPEG, 100, out);
                    BitmapFactory.Options newOpts = new BitmapFactory.Options();
                    int be = 2;
                    newOpts.inSampleSize = be;
                    ByteArrayInputStream isBm = new ByteArrayInputStream(out.toByteArray());
                    Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);
                    Logcat.log("----bmisrecycled---->>" + bitmap.isRecycled() + ";是否保存成功 " + b + ";" +
                            "压缩后----图片的大小" + (bitmap.getByteCount() / 1024 / 1024)
                            + "M宽度为" + bitmap.getWidth() + "高度为" + bitmap.getHeight());

                    ShowBigPicClass showBigPicClass = new ShowBigPicClass(this, bitmap);
                    showBigPicClass.showDetailPhoto();
                } else {
                    Toast.makeText(this, "未签名", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    /**
     * @return true 保存成功；false 不成功
     */
    private boolean savePicToLocal() {

        if (SDCardUtils.isSDCardMounted()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (this.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    f = new File(Environment.getDataDirectory(), File.separator+new Date().getTime() + ".jpg");
                } else {
                    Toast.makeText(this, "API 23 无此权限", Toast.LENGTH_SHORT).show();
                }
            } else {
                f = new File(Environment.getDataDirectory(), new Date().getTime() + ".jpg");
            }

        } else {
            f = new File(this.getFilesDir(), new Date().getTime() + ".jpg");
        }

        if (f == null) {
            try {
                f.createNewFile();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            eleSignView.save(f.getAbsolutePath());
                            File file = new File(Environment.getDownloadCacheDirectory(),File.separator+"11.jpg");
                            FileOutputStream fos = new FileOutputStream(file);
                            byte[] bytes = HttpUtils.httpGetRequest_ByteArray("https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png");
                            fos.write(bytes);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                return f.exists();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    @TargetApi(23)
    private void getPersimmions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ArrayList<String> permissions = new ArrayList<String>();
     /*       *//***
             * 定位权限为必须权限，用户如果禁止，则每次进入都会申请
             *//*
            // 定位精确位置
            if(checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
            }
            if(checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);
            }
			*//*
             * 读写权限和电话状态权限非必要权限(建议授予)只会申请一次，用户同意或者禁止，只会弹一次
			 *//*
            // 读写权限
           if (addPermission(permissions, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                permissionInfo += "Manifest.permission.WRITE_EXTERNAL_STORAGE Deny \n";
            }
            // 读取电话状态权限
            if (addPermission(permissions, Manifest.permission.READ_PHONE_STATE)) {
                permissionInfo += "Manifest.permission.READ_PHONE_STATE Deny \n";
            }*/

            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }
            if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.READ_PHONE_STATE);
            }

            if (permissions.size() > 0) {
                requestPermissions(permissions.toArray(new String[permissions.size()]), SDK_PERMISSION_REQUEST);
            }
        }
    }
}
