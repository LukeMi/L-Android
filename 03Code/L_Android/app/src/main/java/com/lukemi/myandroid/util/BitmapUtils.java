package com.lukemi.myandroid.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;

import java.io.ByteArrayOutputStream;

/**
 * 图片压缩处理的流程
 *
 * @author Jimmy
 */
public class BitmapUtils {

    /**
     * Bitmap图片缩放
     *
     * @param bitmap
     * @param width
     * @param height <br/>
     *               created by: tbug
     *               created at: 2017/4/1 14:55
     */
    public Bitmap zoomImg(Bitmap bitmap, int width, int height) {
        // 获得图片的宽高
        int width_ = bitmap.getWidth();
        int height_ = bitmap.getHeight();
        // 计算缩放比例
        float scaleWidth = ((float) width) / width_;
        float scaleHeight = ((float) height) / height_;
        // 取得想要缩放的matrix参数
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // 得到新的图片   www.2cto.com
        Bitmap newbm = Bitmap.createBitmap(bitmap, 0, 0, width_, height_, matrix, true);
        return newbm;
    }

    /**
     * 对图片进行压缩的处理
     *
     * @param bitmap    图片的数据源
     * @param setWidth  指定图片的宽度
     * @param setHeight 指定图片的高度
     * @return 压缩处理的图片
     * 碰到最多的OOM(Out of Memory) 20M,系统报错,通过该方法的处理可以有效的防止OOM的产生
     */
    public static Bitmap compressBitmap(Bitmap bitmap, int setWidth, int setHeight) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
        byte[] data = bos.toByteArray();
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
     * @param data      图片的数据源
     * @param setWidth  指定图片的宽度
     * @param setHeight 指定图片的高度
     * @return 压缩处理的图片
     * 碰到最多的OOM(Out of Memory) 20M,系统报错,通过该方法的处理可以有效的防止OOM的产生
     */
    public static Bitmap compressBitmap(byte[] data, int setWidth, int setHeight) {
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
    public static Bitmap compressBitmap(String pathName, int setWidth, int setHeight) {
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


    /**
     * 将View /布局转化成 BitMap
     *
     * @param addViewContent 需要转化的View/Layout
     * @return bitMap
     */
    public static Bitmap view2Bitmap(View addViewContent) {
        try {
            addViewContent.setDrawingCacheEnabled(true);

            addViewContent.measure(
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            addViewContent.layout(0, 0,
                    addViewContent.getMeasuredWidth(),
                    addViewContent.getMeasuredHeight());

            addViewContent.buildDrawingCache();
            Bitmap cacheBitmap = addViewContent.getDrawingCache();
            Bitmap bitmap = Bitmap.createBitmap(cacheBitmap);
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 从资源中获取Bitmap
     *
     * @param context 上下文
     * @param resID   资源ID
     * @return Bitmap
     */
    public static Bitmap getBitmapFromRES(Context context, int resID) {
        try {
            Resources res = context.getResources();
            Bitmap bm = BitmapFactory.decodeResource(res, resID);
            return bm;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * Bitmap → byte[]
     *
     * @param bitmap
     * @return byte[]
     */
    public static byte[] bitmap2ByteArray(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }


    /**
     * byte[] → Bitmap
     *
     * @param bytes 字节数组
     * @return Bitmap
     */
    public static Bitmap byteArray2Bimap(byte[] bytes) {
        if (bytes.length != 0) {
            return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        } else {
            return null;
        }
    }


    /**
     * Bitmap → Drawable
     *
     * @param context 上下文
     * @param bitmap  源Bitmap
     * @return Bitmap
     */
    public static Drawable Bitmap2Drawable(Context context, Bitmap bitmap) {
        BitmapDrawable bd = new BitmapDrawable(context.getResources(), bitmap);
//        因为BtimapDrawable是Drawable的子类，最终直接使用bd对象即可。
        return bd;
    }


    /**
     * Bitmap缩放
     *
     * @param bitmap 将要缩放的Bitmap
     * @param width  宽度
     * @param height 高度
     * @return Bitmap缩放比
     */
    public static Bitmap zoomBitmap(Bitmap bitmap, int width, int height) {
        try {
            int w = bitmap.getWidth();
            int h = bitmap.getHeight();
            Matrix matrix = new Matrix();
            float scaleWidth = ((float) width / w);
            float scaleHeight = ((float) height / h);
            matrix.postScale(scaleWidth, scaleHeight);
            if (w < 0 || h < 0) {
                return bitmap;
            }
            Bitmap newbmp = Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);
            return newbmp;
        } catch (Exception e) {
            e.printStackTrace();
            return bitmap;
        }
    }


    /**
     * Drawable缩放
     *
     * @param drawable 源Drawable
     * @param w        宽
     * @param h        高度
     * @return Drawable
     */
    public static Drawable zoomDrawable(Context context, Drawable drawable, int w, int h) {
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        // drawable转换成bitmap
        Bitmap oldbmp = drawable2Bitmap(drawable);
        // 创建操作图片用的Matrix对象
        Matrix matrix = new Matrix();
        // 计算缩放比例
        float sx = ((float) w / width);
        float sy = ((float) h / height);
        // 设置缩放比例
        matrix.postScale(sx, sy);
        // 建立新的bitmap，其内容是对原bitmap的缩放后的图
        Bitmap newbmp = Bitmap.createBitmap(oldbmp, 0, 0, width, height,
                matrix, true);
        return new BitmapDrawable(context.getResources(), newbmp);
    }


    /**
     * Drawable转化为Bitmap
     *
     * @param drawable drawable文件
     * @return Bitmap
     */
    public static Bitmap drawable2Bitmap(Drawable drawable) {
        // 取 drawable 的长宽
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();

        // 取 drawable 的颜色格式
        Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                                       : Bitmap.Config.RGB_565;
        // 建立对应 bitmap
        Bitmap bitmap = Bitmap.createBitmap(w, h, config);
        // 建立对应 bitmap 的画布
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        // 把 drawable 内容画到画布中
        drawable.draw(canvas);
        return bitmap;
    }


    /**
     * 获得圆角图片
     *
     * @param bitmap  源bitmap
     * @param roundPx 圆角
     * @return Bitmap
     */
    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, float roundPx) {

        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        Bitmap output = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, w, h);
        final RectF rectF = new RectF(rect);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }


    /**
     * 获得带倒影的图片
     *
     * @param bitmap 源bitmap
     * @return Bitmap
     */
    public static Bitmap createReflectionImageWithOrigin(Bitmap bitmap) {
        final int reflectionGap = 4;
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();

        Matrix matrix = new Matrix();
        matrix.preScale(1, -1);

        Bitmap reflectionImage = Bitmap.createBitmap(bitmap, 0, h / 2, w,
                h / 2, matrix, false);

        Bitmap bitmapWithReflection = Bitmap.createBitmap(w, (h + h / 2),
                Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmapWithReflection);
        canvas.drawBitmap(bitmap, 0, 0, null);
        Paint deafalutPaint = new Paint();
        canvas.drawRect(0, h, w, h + reflectionGap, deafalutPaint);

        canvas.drawBitmap(reflectionImage, 0, h + reflectionGap, null);

        Paint paint = new Paint();
        LinearGradient shader = new LinearGradient(0, bitmap.getHeight(), 0,
                                                          bitmapWithReflection.getHeight() + reflectionGap, 0x70ffffff,
                                                          0x00ffffff, Shader.TileMode.CLAMP);
        paint.setShader(shader);
        // Set the Transfer mode to be porter duff and destination in
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        // Draw a rectangle using the paint with our linear gradient
        canvas.drawRect(0, h, w, bitmapWithReflection.getHeight()
                                         + reflectionGap, paint);
        return bitmapWithReflection;
    }

    /**
     * 进行截取屏幕{只能用一次}
     *
     * @param activity
     * @return bitmap
     */
    public static Bitmap takeActivityShot(Activity activity) {
        Log.i("TAG", "tackScreenShot");
        // View是你须要截图的View
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap b1 = view.getDrawingCache();

        // 获取状况栏高度
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;
        Logcat.log("frame.top: " + frame.top);
        // 获取屏幕长和高
        int width = activity.getWindowManager().getDefaultDisplay().getWidth();
        int height = activity.getWindowManager().getDefaultDisplay()
                             .getHeight();

        // 去掉题目栏
        Bitmap b = Bitmap.createBitmap(b1, 0, statusBarHeight, width, height
                                                                              - statusBarHeight);
        view.destroyDrawingCache();
        return b;
    }

    /**
     * 获取某个控件的截图
     *
     * @param v
     * @return
     */
    public static Bitmap takeViewShot(View v) {
        View view = v;
        view.setDrawingCacheEnabled(true);
//        view.buildDrawingCache();
        Bitmap tempBit = view.getDrawingCache();
        Rect frame = new Rect();
        view.getWindowVisibleDisplayFrame(frame);
        int width = view.getWidth();
        int height = view.getHeight();
        Bitmap bitmap = Bitmap.createBitmap(tempBit, 0, 0, width, height);
        view.destroyDrawingCache();
        return bitmap;
    }

}
