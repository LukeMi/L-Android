package com.luke.android.library.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

/**
 * 图片压缩处理的流程
 *
 * @author Jimmy
 */
public class BitmapUtils {

    /**
     * 对图片进行压缩的处理
     *
     * @param data      图片的数据源
     * @param setWidth  指定图片的宽度
     * @param setHeight 指定图片的高度
     * @return 压缩处理的图片
     * 碰到最多的OOM(Out of Memory) 20M,系统报错,通过该方法的处理可以有效的防止OOM的产生
     */
    public static Bitmap getBitmap(byte[] data, int setWidth, int setHeight) {
        //Bitmap bm = BitmapFactory.decodeByteArray(data, 0, data.length);
        //通过工具类Options对图片进行压缩处理
        BitmapFactory.Options opts = new BitmapFactory.Options();
        //注意:必须要设置的属性inJustDecodeBounds,设置参数true或者false
        /**
         * inJustDecodeBounds = true  只加载图片的边框信息,不加载实际内容
         * inJustDecodeBounds = false 加载图片所有的信息,包括图片内容
         */
        opts.inJustDecodeBounds = true; //不加载实际内容,只有图片的边框信息
        Bitmap bm = BitmapFactory.decodeByteArray(data, 0, data.length, opts);
        Log.i("123", "Bitmap使用opts是true的情况获取的信息-->>" + bm);
        //获取图片的边框信息
        int orginWidth = opts.outWidth; //原始的宽度
        int orginHeight = opts.outHeight; //原始的高度
        int scaleWidth = orginWidth / setWidth; // >1才有效果    5
        int scaleHeight = orginHeight / setHeight; // >1才有效果  4
        // 一般情况获取scale小的值作为压缩比
        int scale = scaleWidth < scaleHeight ? scaleWidth : scaleHeight;
        //注意:压缩图片的参数 (压缩比例)
        //该值>1才有压缩的效果,如果比<=1,就是原图的情况
        //实际情况是计算出来的,而不是直接写死的
        opts.inSampleSize = scale;
        //设置Bitmap的Config的设置 (也会影响到图片压缩的效果)
        /**
         * 总共有4组值可以设置 (1个像素点表示的位置)
         * 现在默认使用的图片都是1个像素点32位,4个字节
         * Bitmap.Config.ALPHA_8;  只有透明度没有红绿蓝 (1个像素1个字节)
         * Bitmap.Config.ARGB_4444 4*4(Alpha 4位  RED 4位  Green 4位 Blue 4位)(1个像素2个字节)
         * Bitmap.Config.RGB_565  5+6+5(RED 5位  Green 6位 Blue 5位)(1个像素2个字节) 如果用到透明度,他的图片清晰度高于ARGB_4444
         * Bitmap.Config.ARGB_8888 默认图片的像素 8*4(Alpha 8位  RED 8位  Green 8位 Blue 8位)
         * 图片的计算
         * 1. 100*100图片 = 100*100*4B (默认图片计算方式)
         * 2. 100*100图片 采用RGB_565 = 100*100*2B    比原来就小了一半
         *
         * android推荐使用 Bitmap.Config.RGB_565或者Bitmap.Config.ARGB_8888的模式
         *
         */
        //可以设置Bitmap.Config
        opts.inPreferredConfig = Bitmap.Config.RGB_565;
        //真是加载压缩后图片的内容了
        opts.inJustDecodeBounds = false; //要获取图片内容
        //BitmapFactory重新传入设置好的opts参数就可以了,这时候就可以返回按照要求压缩的图片信息了
        return BitmapFactory.decodeByteArray(data, 0, data.length, opts);
    }

    /**
     * 对图片进行压缩的处理
     *
     * @param setWidth  指定图片的宽度
     * @param setHeight 指定图片的高度
     * @return 压缩处理的图片
     * 碰到最多的OOM(Out of Memory) 20M,系统报错
     */
    public static Bitmap getBitmap(String pathName, int setWidth, int setHeight) {
        //Bitmap bm = BitmapFactory.decodeByteArray(data, 0, data.length);
        //通过工具类Options对图片进行压缩处理
        BitmapFactory.Options opts = new BitmapFactory.Options();
        //注意:必须要设置的属性inJustDecodeBounds,设置参数true或者false
        /**
         * inJustDecodeBounds = true  只加载图片的边框信息,不加载实际内容
         * inJustDecodeBounds = false 加载图片所有的信息,包括图片内容
         */
        opts.inJustDecodeBounds = true; //不加载实际内容,只有图片的边框信息
        Bitmap bm = BitmapFactory.decodeFile(pathName, opts);
        Log.i("123", "Bitmap使用opts是true的情况获取的信息-->>" + bm);
        //获取图片的边框信息
        int orginWidth = opts.outWidth; //原始的宽度
        int orginHeight = opts.outHeight; //原始的高度
        int scaleWidth = orginWidth / setWidth; // >1才有效果    5
        int scaleHeight = orginHeight / setHeight; // >1才有效果  4
        // 一般情况获取scale小的值作为压缩比
        int scale = scaleWidth < scaleHeight ? scaleWidth : scaleHeight;
        //注意:压缩图片的参数 (压缩比例)
        //该值>1才有压缩的效果,如果比<=1,就是原图的情况
        //实际情况是计算出来的,而不是直接写死的
        opts.inSampleSize = scale;
        //设置Bitmap的Config的设置 (也会影响到图片压缩的效果)
        /**
         * 总共有4组值可以设置 (1个像素点表示的位置)
         * 现在默认使用的图片都是1个像素点32位,4个字节
         * Bitmap.Config.ALPHA_8;  只有透明度没有红绿蓝 (1个像素1个字节)
         * Bitmap.Config.ARGB_4444 4*4(Alpha 4位  RED 4位  Green 4位 Blue 4位)(1个像素2个字节)
         * Bitmap.Config.RGB_565  5+6+5(RED 5位  Green 6位 Blue 5位)(1个像素2个字节) 如果用到透明度,他的图片清晰度高于ARGB_4444
         * Bitmap.Config.ARGB_8888 默认图片的像素 8*4(Alpha 8位  RED 8位  Green 8位 Blue 8位)
         * 图片的计算
         * 1. 100*100图片 = 100*100*4B (默认图片计算方式)
         * 2. 100*100图片 采用RGB_565 = 100*100*2B    比原来就小了一半
         *
         * android推荐使用 Bitmap.Config.RGB_565或者Bitmap.Config.ARGB_8888的模式
         *
         */
        //可以设置Bitmap.Config
        opts.inPreferredConfig = Bitmap.Config.RGB_565;
        //真是加载压缩后图片的内容了
        opts.inJustDecodeBounds = false; //要获取图片内容
        //BitmapFactory重新传入设置好的opts参数就可以了,这时候就可以返回按照要求压缩的图片信息了
        return BitmapFactory.decodeFile(pathName, opts);
    }

}
