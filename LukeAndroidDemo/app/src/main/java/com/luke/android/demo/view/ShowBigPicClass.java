package com.luke.android.demo.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by mzchen on 2016/12/23.
 */

public class ShowBigPicClass {
    private Context context;
    private String path;
    private Bitmap bitmap;
    private int imageId;
    private Drawable drawable;


    public ShowBigPicClass(Context context, String path) {
        this.context = context;
        this.path = path;
    }

    public ShowBigPicClass(Context context, Bitmap bitmap) {
        this.context = context;
        this.bitmap = bitmap;
    }

    public ShowBigPicClass(Context context, int imageId) {
        this.context = context;
        this.imageId = imageId;
    }

    public ShowBigPicClass(Context context, Drawable drawable) {
        this.context = context;
        this.drawable = drawable;
    }

    public void showDetailPhoto() {
//	全屏显示的方法
        final Dialog dialog = new Dialog(context, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        ImageView imgView = getView();
        dialog.setContentView(imgView);
        dialog.show();

// 大图显示之后，点击图片消失
        imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    //设置当前imgView的图片
    private ImageView getView() {
        ImageView imgView = null;
        try {
            imgView = new ImageView(context);
            imgView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            imgView.setAdjustViewBounds(true);
            Bitmap bitmap = decodeBigPhoto();
            Matrix matrix = new Matrix();
            matrix.setScale(0.5f, 0.5f);
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
                    bitmap.getHeight(), matrix, true);
            imgView.setImageBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imgView;
    }


    private Bitmap decodeBigPhoto() {
        if (bitmap != null) {
            return bitmap;
        } else if (path != null) {
            Bitmap bigBitmap;
            BitmapFactory.Options opt = new BitmapFactory.Options();
            opt.inSampleSize = 1;
            opt.inJustDecodeBounds = false;
            bigBitmap = BitmapFactory.decodeFile(path, opt);
            return bigBitmap;
        } else if (imageId != 0) {
            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), imageId);
            return bitmap;
        }
        return null;

    }
}
