package com.lukemi.android.collection.util;

import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;

public class SettingUtils {
    /**
     * 设置壁纸
     * <p>
     * add Permission{@link android.Manifest.permission#SET_WALLPAPER}
     * created by: tbug
     * created at: 2017/4/9 13:49
     */
    public static void setWallPaper(Context context, Bitmap bitmap) {
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(context);
        try {
            wallpaperManager.setBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
