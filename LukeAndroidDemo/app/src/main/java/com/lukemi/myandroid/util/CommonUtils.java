package com.lukemi.myandroid.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.lukemi.myandroid.R;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

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
//                .asBitmap()//（可选）只允许加载静态图片，不需要Glide去帮我们自动进行图片格式的判断了（gif图片就只能先是一帧）
                .animate(R.anim.alpha)//设置动画
//                        .asGif()//（可选）只允许加载动态图片，和上面的只能选一个
                .placeholder(R.drawable.ic_launcher)//（可选）设置默认占位图
                .error(R.drawable.ic_launcher)//（可选）设置异常占位图
                .diskCacheStrategy(DiskCacheStrategy.NONE)//（可选）禁用缓存all:缓存源资源和转换后的资源；none:不作任何磁盘缓存 ；source:缓存源资源 result：缓存转换后的资源
                .thumbnail(0.1f)//这样会先加载缩略图 然后在加载全图
                .override(800, 800)//设置加载尺寸
                .centerCrop()//设置动态转换:centerCrop()、fitCenter()等函数也可以通过自定义Transformation
                .transform(new GlideRoundTransform(context))
                .into(imageView);//传入要设置的ImageView
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
                .placeholder(com.lukemi.myandroid.R.drawable.ic_launcher)//（可选）设置默认占位图
                .error(com.lukemi.myandroid.R.drawable.ic_launcher)//（可选）设置出错占位图
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
     * Volley框架网络请求
     *
     * @param context
     * @param url     <br/>
     *                created by: tbug
     *                created at: 2017/4/10 19:00
     */
    private static void volleyRequest(Context context, String url) {
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //主线程操作(可结合Handler使用)

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //主线程操作
                    }
                });
        queue.add(stringRequest);
    }

    /**
     * okHttp3 GET 请求
     *
     * @param url
     * @return
     */
    public static String okHttp3GETRequest(String url) {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            okhttp3.Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * okhttp3 POST 网络请求（暂时未验证）
     *
     * @param url
     * @param data
     * @return
     * @throws Exception
     */
    public static String okHttp3POSTRequest(String url, String data) throws Exception {
        MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, data.getBytes());
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        okhttp3.Response response = client.newCall(request).execute();
        return response.body().string();

    }


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

            // Load up an instance of the specific script that we want to use.
            ScriptIntrinsicBlur script = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
            script.setInput(input);

            // Set the blur radius
            script.setRadius(25);

            // Start the ScriptIntrinisicBlur
            script.forEach(output);

            // Copy the output to the blurred bitmap
            output.copyTo(blurredBitmap);

            bitmap.recycle();

            return blurredBitmap;
        }

        @Override
        public String key() {
            return "blur";
        }
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
     * 圆角转化器
     */
    public static class GlideRoundTransform extends BitmapTransformation {
        private float radius = 0f;

        public  GlideRoundTransform(Context context) {
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
    }
}


