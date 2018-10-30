package com.lukemi.android.tutorial.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * 包名查询，看是否有百度地图/高德地图app
 * Created by android on 2017/7/27.
 */

public class MapUtil {

    private static List<String> mPackageNames = new ArrayList<>();
    private static final String GAODE_PACKAGE_NAME = "com.autonavi.minimap";
    private static final String BAIDU_PACKAGE_NAME = "com.baidu.BaiduMap";

    private static void initPackageManager(@NonNull Context context) {
        PackageManager mPackageManager = context.getPackageManager();
        List<PackageInfo> pkgList = mPackageManager.getInstalledPackages(0);

        if (pkgList != null) {
            for (int i = 0; i < pkgList.size(); i++) {
                mPackageNames.add(pkgList.get(i).packageName);
            }
        }
    }

    public static boolean haveGaoDeMap(@NonNull Context context) {
        initPackageManager(context);
        return mPackageNames.contains(GAODE_PACKAGE_NAME);
    }

    public static boolean haveBaiDuMap(@NonNull Context context) {
        initPackageManager(context);
        return mPackageNames.contains(BAIDU_PACKAGE_NAME);
    }

    public static void openGaodeMapToGuide(@NonNull Activity activity, String address) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        String url = "androidamap://poi?sourceApplication=softname&keywords=" + address;
        Uri uri = Uri.parse(url);
        //将功能Scheme以URI的方式传入data
        intent.setData(uri);
        //启动该页面即可
        activity.startActivity(intent);
    }

    public static void openBaiduMapToGuide(@NonNull Activity activity, String address) {
        Intent intent = new Intent();
        String url = "baidumap://map/place/search?query=" + address;
        Uri uri = Uri.parse(url);
        //将功能Scheme以URI的方式传入data
        intent.setData(uri);
        //启动该页面即可
        activity.startActivity(intent);
    }

    public static void openBrowserToGuide(@NonNull Activity activity, String address) {
        String url = "http://api.map.baidu.com/geocoder?address=" + address + "&output=html&src=91360|91360病理网";
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        activity.startActivity(intent);
    }

}
