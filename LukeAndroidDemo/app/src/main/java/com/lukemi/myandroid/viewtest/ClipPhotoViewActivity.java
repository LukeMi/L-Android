package com.lukemi.myandroid.viewtest;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.lukemi.myandroid.R;
import com.lukemi.myandroid.util.BitmapUtils;
import com.lukemi.myandroid.util.Logcat;
import com.lukemi.myandroid.view.ClipImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ClipPhotoViewActivity extends AppCompatActivity {

    @BindView(R.id.civ)
    ClipImageView civ;
    @BindView(R.id.img_show)
    ImageView imgShow;
    private int REQUESTCODE_CAMERA = 0x0001;
    private int REQUESTCODE_ALBUM = 0x0002;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clip_photo_view);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.image_back, R.id.text_save, R.id.btn_camera, R.id.btn_loc})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_back:
                finish();
                break;
            case R.id.text_save:
                clip();
                break;
            case R.id.btn_camera:
//                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                picName1 = "ZSGD" + System.currentTimeMillis() + ".jpg";
//                Uri imageUri = Uri.fromFile(getPhotoFile(picName1));
//                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
//                startActivityForResult(intent, REQUESTCODE_CAMERA);
                break;
            case R.id.btn_loc:
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, REQUESTCODE_ALBUM);
                break;
        }
    }

    /**
     * 裁剪
     */
    private void clip() {
        Bitmap bitmap = civ.clipBitmap();
        imgShow.setImageBitmap(bitmap);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUESTCODE_CAMERA && data != null){

        }else if (requestCode == REQUESTCODE_ALBUM && data != null){
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
            civ.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        }
    }
}
