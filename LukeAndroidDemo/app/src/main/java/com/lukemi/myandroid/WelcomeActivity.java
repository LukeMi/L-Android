package com.lukemi.myandroid;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.lukemi.myandroid.util.Logcat;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.Permission;

/**
 * 欢迎页面设计<br/>
 * 功能点：延时跳转/点击跳转/TextView显示不同颜色文字
 * 坑点：解决启动时候白屏黑屏的问题；
 * <p>
 * <p>
 * created bt: tubg
 * created at: 2017/3/31 14:04
 * e_mail: tbug.chen@gmail.com / tbugchen@163.com
 */
public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView welcIV;
    private TextView countDownTV;
    private ProgressBar showProcessbar;
    private AsyncTask<String, Integer, Bitmap> asyncTask;
    private String picURL = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1491035800721&di=e447ef2886c8c4fe81225ac2d6c357bd&imgtype=0&src=http%3A%2F%2Fimg.warting.com%2Fallimg%2F2016%2F0711%2Fvim241exxwa-763.jpg";

    private final int MSG_DELAY = 0x0001;
    private int countTime = 3;
    private Handler mhandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_DELAY:
                    String textStr = countTime + " 跳转";
                    SpannableStringBuilder ssb = new SpannableStringBuilder(textStr);
                    ssb.setSpan(new ForegroundColorSpan(Color.WHITE), 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    ssb.setSpan(new ForegroundColorSpan(Color.RED), 1, textStr.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
                    countDownTV.setText(ssb);
                    if (countTime == 0) {
                        mhandler.removeMessages(MSG_DELAY);
                        goToMain();

                    } else {
                        countTime -= 1;
                        mhandler.sendEmptyMessageDelayed(MSG_DELAY, 1000);
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        initView();
        mhandler.sendEmptyMessage(MSG_DELAY);
    }

    @Override
    protected void onDestroy() {
        if (mhandler.hasMessages(MSG_DELAY)) {
            mhandler.removeMessages(MSG_DELAY);
        }
        if (asyncTask != null && !asyncTask.isCancelled()) {
            asyncTask.cancel(true);
        }
        super.onDestroy();
    }

    /**
     * 初始化控件
     * <p>
     * created by: tbug
     * created at: 2017/3/31 13:42
     */
    private void initView() {
        welcIV = ((ImageView) findViewById(R.id.welcIV));
        countDownTV = ((TextView) findViewById(R.id.countDownTV));
        showProcessbar = ((ProgressBar) findViewById(R.id.showProcessbar));
        showProcessbar.setMax(100);
        showProcessbar.setProgress(0);

        countDownTV.setOnClickListener(this);
        asyncTask = new PicAsncTask().execute(picURL);
    }

    /**
     * 跳转到主界面
     * <p>
     * created by: tbug
     * created at: 2017/3/31 14:44
     */
    private void goToMain() {
        startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
        finish();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.countDownTV:
                goToMain();
                break;
        }
    }

    class PicAsncTask extends AsyncTask<String, Integer, Bitmap> {
        @Override
        protected void onPreExecute() {
            showProcessbar.setVisibility(View.VISIBLE);
            super.onPreExecute();
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            HttpURLConnection conn = null;
            InputStream is = null;
            ByteArrayOutputStream bos = null;
            try {

                URL url = new URL(params[0]);
//获取URL相关参数
                String authority = url.getAuthority();
                String file = url.getFile();
                String host = url.getHost();
                String path = url.getPath();
                String protocol = url.getProtocol();
                String query = url.getQuery();
                String userInfo = url.getUserInfo();
                String ref = url.getRef();
                int defaultPort = url.getDefaultPort();
                int port = url.getPort();
//打印URl相关参数
                Logcat.log("url get方法参数： " + "authority: " + authority + ";file: " + file +
                        ";host " + host + ";path: " + path + ";protocol: " + protocol + "; query: " + query +
                        ";userInfo: " + userInfo + ";ref: " + ref + ";defaultPort: " + defaultPort + ";port: " + port);

                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setConnectTimeout(5000);
                conn.setReadTimeout(5000);
                conn.setDoInput(true);
                conn.setDoOutput(false);
                conn.connect();

                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
//获取httpURLConnection相关参数
                    String contentEncoding = conn.getContentEncoding();
                    String requestMethod = conn.getRequestMethod();
                    String responseMessage = conn.getResponseMessage();
                    String contentType = conn.getContentType();
                    int connectTimeout = conn.getConnectTimeout();
                    int readTimeout = conn.getReadTimeout();
                    int contentLength = conn.getContentLength();
                    long lastModified = conn.getLastModified();
                    long date = conn.getDate();
                    boolean doInput = conn.getDoInput();
                    boolean doOutput = conn.getDoOutput();
                    boolean useCaches = conn.getUseCaches();
                    Permission permission = conn.getPermission();
                    URL url1 = conn.getURL();

                    Logcat.log("HttpURLConnection 相关参数：" + ";contentEncoding: " + contentEncoding +
                            ";requestMethod: " + requestMethod +
                            ";responseMessage: " + responseMessage +
                            ";contentType: " + contentType +
                            ";connectTimeout: " + connectTimeout +
                            ";readTimeout: " + readTimeout +
                            ";contentLength: " + contentLength +
                            ";lastModified: " + lastModified +
                            ";date: " + date +
                            ";doInput: " + doInput +
                            ";doOutput: " + doOutput +
                            ";useCaches: " + useCaches +
                            ";permission: " + permission.getName() + "; " + permission.getActions() +
                            ";url1: " + url1.getHost());

                    //为了显示进度条这里使用 字节数组输出流
                    is = conn.getInputStream();
                    bos = new ByteArrayOutputStream();
                    int length = -1;
                    int progress = 0; //进度
                    int count = conn.getContentLength(); //获取内容产固定
                    byte[] bs = new byte[5];
                    while ((length = is.read(bs)) != -1) {
                        progress += length; //进度累加
                        if (count == 0) {
                            publishProgress(-1);
                        } else {
                            bos.write(bs, 0, length);
                            publishProgress((int) ((float) progress / count * 100));
                        }
                    }
                    return BitmapFactory.decodeByteArray(bos.toByteArray(), 0, bos.size());
                } else {
                    //UI线程更新UI
                    mhandler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(WelcomeActivity.this, "网络请求错误", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (is != null) {
                        is.close();
                    }
                    if (bos != null) {
                        bos.close();
                    }
                    if (conn != null) {
                        conn.disconnect();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            showProcessbar.setProgress(values[0]);
            if (values[0] == 100) {
                showProcessbar.setVisibility(View.GONE);
            }
            Logcat.log("onProgressUpdate  -->>: " + values[0]);
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(final Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (bitmap != null) {
                Logcat.log("onPostExecute -->> " + bitmap.getByteCount() + " 字节");
                welcIV.setImageBitmap(bitmap);
                //创建异步任务保存图片
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            saveBitmap2File(bitmap, new File(WelcomeActivity.this.getFilesDir(), System.currentTimeMillis() + ".png"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        }

    }

    /**
     * 保存图片
     *
     * @param bitmap 将要保存的图片Bitmap
     * @param file   保存文件
     * @return File
     * created by: tbug
     * created at: 2017/4/1 16:51
     */
    public File saveBitmap2File(Bitmap bitmap, File file) throws IOException {
        if (!file.exists() && file.isFile()) {
            file.createNewFile();
        }
        //将Bitmap转化成二进制数组
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] bytes = baos.toByteArray();
        //创建文件字节输出流
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(bytes, 0, bytes.length);
        if (file != null) {
            return file;
        }
        return null;
    }
}
