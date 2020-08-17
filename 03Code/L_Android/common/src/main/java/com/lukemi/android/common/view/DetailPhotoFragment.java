package com.lukemi.android.common.view;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.lukemi.android.common.R;
import com.lukemi.android.common.util.Logcat;


public class DetailPhotoFragment extends DialogFragment implements View.OnClickListener {


    private static Bitmap bitmap;
    private ImageView imageView;
    private View view;

    public DetailPhotoFragment() {
    }

    public static DetailPhotoFragment getInstance(Bitmap bm) {
        bitmap = bm;
        return new DetailPhotoFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setStyle(DialogFragment.STYLE_NORMAL, R.style.Dialog_FullScreen);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        imageView = (ImageView) inflater.inflate(R.layout.photodetail_view, container, true);
        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        if (layoutParams == null) {
            Logcat.log("----layoutParams----" + null);
        }
//        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
//        layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
//        imageView.setLayoutParams(layoutParams);
        imageView.setImageBitmap(bitmap);
        imageView.setOnClickListener(this);
        return imageView;
    }

/*    @Override
    public void onResume() {
        // Get existing layout params for the window
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        // Assign window properties to fill the parent
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.MATCH_PARENT;
        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
        // Call super onResume after sizing
        super.onResume();
    }*/

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.photoDetail) {
            ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 1.4f, 1.0f, 1.4f,
                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            scaleAnimation.setDuration(3000);
            scaleAnimation.setFillAfter(true);
            imageView.startAnimation(scaleAnimation);
        }
    }
}

