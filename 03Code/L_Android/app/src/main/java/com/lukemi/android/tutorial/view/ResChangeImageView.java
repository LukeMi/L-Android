package com.lukemi.android.tutorial.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

/**
 * Created by android on 2017/8/24.
 */

public class ResChangeImageView extends AppCompatImageView {
    private OnImgeChangeListener onImgeChangeListener;

    public ResChangeImageView(Context context) {
        super(context);
    }

    public ResChangeImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ResChangeImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        super.setImageBitmap(bm);
        imgChanged();

    }

    @Override
    public void setImageDrawable(@Nullable Drawable drawable) {
        super.setImageDrawable(drawable);
        imgChanged();
    }

    @Override
    public void setImageResource(@DrawableRes int resId) {
        super.setImageResource(resId);
        imgChanged();
    }

    private void imgChanged() {
        if (onImgeChangeListener != null) {
            onImgeChangeListener.onImgeChangeListener(true);
        }
    }

    public void setOnImgeChangeListener(OnImgeChangeListener onImgeChangeListener) {
        this.onImgeChangeListener = onImgeChangeListener;
    }

    public interface OnImgeChangeListener {
        void onImgeChangeListener(boolean changed);
    }


}
