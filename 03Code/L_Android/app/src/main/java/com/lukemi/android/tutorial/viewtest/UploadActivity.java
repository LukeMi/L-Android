package com.lukemi.android.tutorial.viewtest;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.lukemi.android.tutorial.R;
import com.lukemi.android.tutorial.util.BitmapUtils;
import com.lukemi.android.common.util.Logcat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UploadActivity extends AppCompatActivity {

    @BindView(R.id.img_show)
    ImageView imgShow;
    private int RESULT_LOAD_IMAGE = 0x0011;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_choice, R.id.btn_upload})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_choice:
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
                break;
            case R.id.btn_upload:
                upLoadAvatar();
                break;
        }
    }

    /**
     * 上传头像
     */
    private void upLoadAvatar() {
        final String url = "http://bbs.91360.com/pathosec-app.html?jscbk=direct&action=setavatar&uid=16359&token=wpfClEg2VmbCkW7DnMOPFMOHbBPDijnDrFJ+w6DCiADCksK4";
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Logcat.log("----CameraActivity----onActivityResult--->" + "requestCode = " + requestCode + ";resultCode = " + resultCode);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
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
//            bitmap = BitmapFactory.decodeFile(picturePath);
            bitmap = BitmapUtils.drawable2Bitmap(this.getResources().getDrawable(R.drawable.ic_launcher));
            imgShow.setImageBitmap(bitmap);
        }
    }



}
