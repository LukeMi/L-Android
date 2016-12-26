package com.luke.android.demo.camera;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.luke.android.demo.R;
import com.luke.android.demo.util.BitmapUtils;
import com.luke.android.demo.util.ConfigUtil;
import com.luke.android.demo.util.HttpUtils;
import com.luke.android.demo.util.Logcat;
import com.luke.android.demo.view.ShowBigPicClass;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.luke.android.demo.R.id.preViewPic_CA;
import static com.luke.android.demo.util.ConfigUtil.CA_REQUESTCODE;

public class CameraActivity extends AppCompatActivity implements View.OnClickListener {


    //    private File pic1File;
    private String picName1;
    private Button takePhoneBTN;
    private Button uploadPhotosBTN;
    private ImageView preViewPicIV;
    Bitmap waterBm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        initView();
    }

    private void initView() {
        takePhoneBTN = ((Button) findViewById(R.id.takePhotos_CA));
        uploadPhotosBTN = ((Button) findViewById(R.id.uploadPhotos_CA));
        preViewPicIV = ((ImageView) findViewById(preViewPic_CA));
        uploadPhotosBTN.setOnClickListener(this);
        takePhoneBTN.setOnClickListener(this);
        preViewPicIV.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.takePhotos_CA:
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                picName1 = "ZSGD" + System.currentTimeMillis() + ".jpg";
                Uri imageUri = Uri.fromFile(getPhotoFile(picName1));
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, CA_REQUESTCODE);
                break;
            case R.id.uploadPhotos_CA:
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                waterBm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] bytes = baos.toByteArray();
                String url = "http://192.168.33.52:8090";
                HttpUtils.httpDoPost(bytes, url);
                break;
            case R.id.preViewPic_CA:

                ShowBigPicClass showBigPicClass = new ShowBigPicClass(this, waterBm);
                showBigPicClass.showDetailPhoto();

               /* DetailPhotoFragment detailPhotoFragment = DetailPhotoFragment.getInstance(waterBm);
//                detailPhotoFragment.setCancelable(false);
                detailPhotoFragment.show(getSupportFragmentManager(), null);*/
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Logcat.log("----CameraActivity----onActivityResult--->" + "requestCode = " + requestCode + ";resultCode = " + resultCode);
        if (requestCode == ConfigUtil.CA_REQUESTCODE) {
            Bitmap bmp1 = BitmapUtils.getBitmap(getPhotoFile(picName1).getAbsolutePath(), 1000, 1000);
            if (null != bmp1) {
                preViewPicIV.setDrawingCacheEnabled(true);
                waterBm = addWatermark(bmp1, "ZSGD");
                preViewPicIV.setDrawingCacheEnabled(true);
                preViewPicIV.setImageBitmap(waterBm);
            }
        }
    }

    /**
     * 创建文件创（需要权限）
     *
     * @return File
     */
    public static File getPhotoFile(String picName) {
        if (android.os.Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED)) {

            File tempFile = new File(Environment.getExternalStorageDirectory(), picName);
            Logcat.log("----getTempImage---->>" + picName);
            try {
                tempFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return tempFile;
        }
        return null;
    }

    //为图片添加水印     
    private Bitmap addWatermark(Bitmap src, String customStr) {
        int w = src.getWidth();
        int h = src.getHeight();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String msg = customStr + sdf.format(new Date());
        String msg = sdf.format(new Date());
        Bitmap bmpTemp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmpTemp);
        Paint paint = new Paint();
        String familyName = "宋体";
        Typeface font = Typeface.create(familyName, Typeface.BOLD);
        paint.setColor(Color.YELLOW);
        paint.setTypeface(font);
        paint.setAntiAlias(true);
        paint.setTextSize(40);
        canvas.drawBitmap(src, 0, 0, null);
        canvas.drawText(msg, 0 /*w / 2*/, paint.getTextSize()/*h / 2*/, paint);
        return bmpTemp;
    }
}
