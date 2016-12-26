package com.luke.android.demo.testview;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.luke.android.demo.R;
import com.luke.android.demo.util.Logcat;
import com.luke.android.demo.util.SDCardUtils;
import com.luke.android.demo.view.LinePathView;
import com.luke.android.demo.view.ShowBigPicClass;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Date;

public class EleSignActivity extends AppCompatActivity implements View.OnClickListener {

    private LinePathView eleSignView;
    private Button isSigned_ESA_BTN;
    private Button showPicBig_ESA_BTN;
    private Button clearSigned_ESA_BTN;
    private int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 0x0001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ele_sign);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPermissions();
    }

    /**
     * 请求权限
     */
    private void getPermissions() {
        if (SDCardUtils.isSDCardMounted()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (this.checkSelfPermission(Manifest.permission_group.STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
                }
            }
        }
    }

    private void initView() {
        eleSignView = ((LinePathView) findViewById(R.id.elecSign_ESA));
        eleSignView.setPenColor(R.color.black);
        isSigned_ESA_BTN = ((Button) findViewById(R.id.isSigned_ESA));
        clearSigned_ESA_BTN = ((Button) findViewById(R.id.clearSigned_ESA));
        showPicBig_ESA_BTN = ((Button) findViewById(R.id.showPicBig_ESA));
        isSigned_ESA_BTN.setOnClickListener(this);
        clearSigned_ESA_BTN.setOnClickListener(this);
        showPicBig_ESA_BTN.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.isSigned_ESA:
                String msg = eleSignView.getTouched() ? "已经签名" : "未签名";
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                break;
            case R.id.clearSigned_ESA:
                eleSignView.clear();
                break;
            case R.id.showPicBig_ESA:
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
        File f = null;
        if (SDCardUtils.isSDCardMounted()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (this.checkSelfPermission(Manifest.permission_group.STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    f = new File(Environment.getDataDirectory(), new Date().getTime() + ".jpg");
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
                eleSignView.save(f.getAbsolutePath());
                if (f.exists()) {
                    return true;
                } else {
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
