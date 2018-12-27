package com.lukemi.android.tutorial.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.renderscript.Allocation;
import android.renderscript.RenderScript;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lukemi.android.tutorial.R;
import com.lukemi.android.tutorial.glide.GlideUtil;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

public class CommonUtils {
    /**
     * Glide加载网络图片
     *
     * @param context
     * @param url
     * @param imageView <br/>
     *                  created by: tbug
     *                  created at: 2017/4/10 19:00
     */
    public static void glideLoadPic(Context context, String url, ImageView imageView) {

        Glide
                .with(context)//传入上下文
                .load(url)//图片url
                .into(imageView);//传入要设置的ImageView
    }

    public static void glideLoadPicGround(Context context, String url, ImageView imageView) {
        GlideUtil.loadImgByUrl(context, imageView, url);
    }

    /**
     * Picasso加载网络图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void picassoLoadPic(Context context, String url, ImageView imageView) {

        Picasso
                .with(context)//传入上下文
//                .setIndicatorsEnabled(true)//显示指示器
                .load(url)//（可选）传入图片网址
                .placeholder(R.drawable.ic_launcher)//（可选）设置默认占位图
                .error(R.drawable.ic_launcher)//（可选）设置出错占位图
//                .noFade()//（可选）不设置渐入的效果
                .resize(400, 200)//（可选）设置图片尺寸
                .onlyScaleDown()//（可选）和 resize连用，切resize在前   只有当原始图片的尺寸大于我们指定的尺寸时
//                .fit()//（可选）fit  只对ImageView 有效,&&不能和reSize()连用   它会自动测量我们的View的大小，然后内部调用reszie方法把图片裁剪到View的大小
                .centerCrop()//（可选）处理图片拉伸或者扭曲了
//                .centerInside()//（可选）和上面的设置2选一图片展示完全
                .rotate(180)//（可选）图片旋转
//                .rotate(180, 200, 100)//（可选）
                .transform(new BlurTransformation(context))//（可选）高斯模糊
                .priority(Picasso.Priority.HIGH)//（可选）请求优先级LOW, NORMAL,HIGH
                .tag("PhotoTag")//（可选）设置TAG
//        NO_CACHE：表示处理请求的时候跳过检查内存缓存
//        NO_STORE: 表示请求成功之后，不将最终的结果存到内存。
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE) //（可选）静止内存缓存
//        NO_CACHE: 表示处理请求的时候跳过处理磁盘缓存
//        NO_STORE: 表示请求成功后，不将结果缓存到Disk,但是这个只对OkHttp有效。
//        OFFLINE: 这个就跟 上面两个不一样了，如果networkPolicy方法用的是这个参数，那么Picasso会强制这次请求从缓存中获取结果，不会发起网络请求，不管缓存中能否获取到结果。
                .networkPolicy(NetworkPolicy.NO_CACHE)//（可选）跳过磁盘缓存
                .into(imageView);//设置到的ImageViw
    }

    /**
     * 将View 转化成 BitMap
     *
     * @param view
     * @return
     */
    private Bitmap getViewBitmap(View view) {

        view.setDrawingCacheEnabled(true);

        view.measure(
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0,
                view.getMeasuredWidth(),
                view.getMeasuredHeight());

        view.buildDrawingCache();
        Bitmap cacheBitmap = view.getDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(cacheBitmap);

        return bitmap;
    }

    /**
     * 密码EditText内容是否可见
     *
     * @param editText 密码输入框
     * @param flag     true显示，false不显示
     */
    public void showPasswordETcontent(EditText editText, boolean flag) {
        if (flag) {
            //注释掉的是第一种方法(numberPassword有效)
            editText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
//            editText.setInputType(InputType.TYPE_TEXT_VARIATION_NORMAL);

        } else {
            editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
//            editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
        editText.requestFocus();
        //设置光标可见
        editText.setCursorVisible(true);
        //设置光标位置---防止点击之后游标回到0位置
        int location = editText.length();
        editText.setSelection(location);
    }


    /* */

    /**
     * 圆角转化器
     *//*
    public static class GlideRoundTransform extends BitmapTransformation {
        private float radius = 0f;

        public GlideRoundTransform(Context context) {
            this(context, 4);
        }

        public GlideRoundTransform(Context context, int dp) {
            super(context);
            this.radius = Resources.getSystem().getDisplayMetrics().density * dp;
        }

        @Override
        protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
            return roundCrop(pool, toTransform);
        }

        private Bitmap roundCrop(BitmapPool pool, Bitmap source) {
            if (source == null) return null;
            Bitmap result = pool.get(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
            if (result == null) {
                result = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
            }
            Canvas canvas = new Canvas(result);
            Paint paint = new Paint();
            paint.setShader(new BitmapShader(source, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
            paint.setAntiAlias(true);
            RectF rectF = new RectF(0f, 0f, source.getWidth(), source.getHeight());
            canvas.drawRoundRect(rectF, radius, radius, paint);
            return result;
        }

        @Override
        public String getId() {
            return getClass().getName() + Math.round(radius);
        }

        @Override
        public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {

        }
    }*/

    public static class BlurTransformation implements Transformation {

        RenderScript rs;

        public BlurTransformation(Context context) {
            super();
            rs = RenderScript.create(context);
        }

        @Override
        public Bitmap transform(Bitmap bitmap) {
            // Create another bitmap that will hold the results of the filter.
            Bitmap blurredBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);

            // Allocate memory for Renderscript to work with
            Allocation input = Allocation.createFromBitmap(rs, blurredBitmap, Allocation.MipmapControl.MIPMAP_FULL, Allocation.USAGE_SHARED);
            Allocation output = Allocation.createTyped(rs, input.getType());
            output.copyTo(blurredBitmap);
            bitmap.recycle();
            return blurredBitmap;
        }

        @Override
        public String key() {
            return "blur";
        }
    }
}


