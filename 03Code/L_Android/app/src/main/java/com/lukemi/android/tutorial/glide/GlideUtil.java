package com.lukemi.android.tutorial.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.lukemi.android.common.util.Logcat;
import com.lukemi.android.tutorial.R;

import java.security.MessageDigest;

/**
 * @author lukemi
 * @date 2018/12/24 15:45
 * @des Glide Util
 * @mail chenmingzhiji@163.com or mingzhichen1990@gmail.com
 */
public class GlideUtil {

    /**
     * @param context
     * @param imageView
     * @param url
     */
    public static void loadImgByUrl(Context context, ImageView imageView, String url) {
        Glide.with(context)
                .load(url)
                .into(imageView);

    }

    public static void loadImgRoot(Context context,
                                   ImageView imageView,
                                   String url,
                                   boolean dontAnimate,
                                   boolean isGif) {
        RequestOptions transform = new RequestOptions()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .fallback(R.mipmap.ic_launcher);
        if (isGif || dontAnimate) {
            // gif 不能播放
            transform.dontAnimate();
        }
        transform.priority(Priority.IMMEDIATE)
                .centerInside()
                .transform(new Transformation<Bitmap>() {
                    @NonNull
                    @Override
                    public Resource<Bitmap> transform(@NonNull Context context, @NonNull Resource<Bitmap> resource, int outWidth, int outHeight) {
                        Logcat.log("outWidth : " + outWidth + " ;outHeight : " + outHeight);
                        return resource;
                    }

                    @Override
                    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {

                    }
                });
        Glide
                .with(context)
                .load(url)
                .apply(transform)
                .transition(DrawableTransitionOptions.withCrossFade(100))
                .addListener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        Logcat.log("onLoadFailed");
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        Logcat.log("onResourceReady");
                        return false;
                    }
                })
                .into(imageView);
    }


}

