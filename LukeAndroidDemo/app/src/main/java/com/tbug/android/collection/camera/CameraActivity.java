package com.tbug.android.collection.camera;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.text.TextPaint;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.tbug.android.collection.R;
import com.tbug.android.collection.util.BitmapUtils;
import com.tbug.android.collection.util.ConfigUtil;
import com.tbug.android.collection.util.HttpUtils;
import com.tbug.android.collection.util.Logcat;
import com.tbug.android.collection.view.ShowBigPicClass;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.tbug.android.collection.util.ConfigUtil.CA_REQUESTCODE;

public class CameraActivity extends AppCompatActivity implements View.OnClickListener {


    //    private File pic1File;
    private String picName1;
    private Button takePhoneBTN;
    private Button uploadPhotosBTN;
    private Button nativiePicBTN;
    private ImageView preViewPicIV;
    Bitmap waterBm;
    private int RESULT_LOAD_IMAGE = 0x0011;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        initView();
    }

    private void initView() {
        takePhoneBTN = ((Button) findViewById(R.id.takePhotos_CA));
        uploadPhotosBTN = ((Button) findViewById(R.id.uploadPhotos_CA));
        nativiePicBTN = ((Button) findViewById(R.id.nativiePic_CA));
        preViewPicIV = ((ImageView) findViewById(R.id.preViewPic_CA));
        uploadPhotosBTN.setOnClickListener(this);
        takePhoneBTN.setOnClickListener(this);
        nativiePicBTN.setOnClickListener(this);
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
                startActivityForResult(intent, ConfigUtil.CA_REQUESTCODE);
                break;
            case R.id.uploadPhotos_CA:
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                waterBm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] bytes = baos.toByteArray();
                String url = "http://192.168.33.52:8090";
                HttpUtils.httpPostRequest(url, bytes);
                break;
            case R.id.preViewPic_CA:

                ShowBigPicClass showBigPicClass = new ShowBigPicClass(this, waterBm);
                showBigPicClass.showDetailPhoto();

               /* DetailPhotoFragment detailPhotoFragment = DetailPhotoFragment.getInstance(waterBm);
//                detailPhotoFragment.setCancelable(false);
                detailPhotoFragment.show(getSupportFragmentManager(), null);*/
                break;
            case R.id.nativiePic_CA:
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);

                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Logcat.log("----CameraActivity----onActivityResult--->" + "requestCode = " + requestCode + ";resultCode = " + resultCode);
        if (requestCode == CA_REQUESTCODE) {
            Bitmap bmp1 = BitmapUtils.compressBitmap(getPhotoFile(picName1).getAbsolutePath(), 1000, 1000);
            if (null != bmp1) {
                preViewPicIV.setDrawingCacheEnabled(true);
                waterBm = setWatermark(bmp1, "ZSGD");
                preViewPicIV.setDrawingCacheEnabled(true);
                preViewPicIV.setImageBitmap(waterBm);
            }
        } else if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            Logcat.log("----CameraActivity----onActivityResult----file---->" + filePathColumn[0] + " ;picturePath= " + picturePath);
            cursor.close();
            Bitmap bmp1 = BitmapUtils.compressBitmap(picturePath, 100, 100);
            preViewPicIV.setImageBitmap(BitmapFactory.decodeFile(picturePath));

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
    private Bitmap setWatermark(Bitmap src, String customStr) {
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
        paint.setTextAlign(Paint.Align.LEFT);


        TextPaint tpaint = new TextPaint();
        float width = Layout.getDesiredWidth(customStr, 0, customStr.length(), tpaint);

        canvas.drawBitmap(src, 0, 0, null);
        float x = w - width;
        float y = h - paint.getTextSize();

        canvas.drawText(customStr, x, y, paint);

        return bmpTemp;
    }
}
