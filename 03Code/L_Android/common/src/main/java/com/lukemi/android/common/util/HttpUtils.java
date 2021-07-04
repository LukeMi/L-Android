package com.lukemi.android.common.util;

import android.util.Log;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 网络请求工具类
 *
 * @author Jimmy
 */
public class HttpUtils {
    public static final String TAG = HttpUtils.class.getSimpleName();
    public static final String IMAGE_PATH = "https://www.baidu.com/img/540x258_2179d1243e6c5320a8dcbecd834a025d.png";
    public static final String TOU_TIAO_URL = "https://s9.pstatp.com/package/apk/news_article/1001_8310/news_article_tt_wtt_qrcod_v1001_8310_ca49_1624598627.apk?v=1624598630";


    /**
     * 通过GET请求方式获取Json数据
     *
     * @param webSite 网址
     * @return JSON 字符串
     */
    public static String getString(String webSite) {
        InputStream is = null;
        // 读取String内容
        BufferedReader br = null;
        try {
            URL url = new URL(webSite);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true); // 可以读取
            conn.setDoOutput(false); // 没有写入
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(8000);
            conn.setReadTimeout(5000);
            conn.connect();
            if (conn.getResponseCode() == 200) {
                // 获取数据
                is = conn.getInputStream();
                // 在转换流里定义编码格式(网上下载的数据有中文，一定要考虑到编码格式)
                br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
                // 存放每一行的StringBuilder
                StringBuilder builder = new StringBuilder();
                String line = null;
                while ((line = br.readLine()) != null) {
                    // 每一行的信息加入StringBuilder
                    builder.append(line);
                }
                return builder.toString();
            }
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
                if (is != null)
                    is.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 通过GET方式下载二进制数据
     *
     * @param webSite 网址
     * @return byte[] 字节数组
     */
    public static byte[] getByteArray(String webSite) {
        InputStream is = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            URL url = new URL(webSite);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(false);
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(8000);
            conn.setReadTimeout(5000);
            conn.connect();
            if (conn.getResponseCode() == 200) {
                is = conn.getInputStream();
                //缓存大小
                byte[] buffer = new byte[1024 * 4];
                int len = 0;
                while ((len = is.read(buffer)) != -1) {
                    baos.write(buffer, 0, len);
                }
            }
            return baos.toByteArray();
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (baos != null)
                    baos.close();
                if (is != null)
                    is.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void httpDoPost(byte[] data, String urlSite) {
        try {
            URL url = new URL(urlSite);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            OutputStream outputStream = httpURLConnection.getOutputStream();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestProperty("Content-type", "application/octet-stream");
            DataOutputStream dos = new DataOutputStream(outputStream);
            dos.write(data, 0, data.length);
            dos.flush();
            dos.close();
            outputStream.close();

            if (httpURLConnection.getResponseCode() == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                StringBuffer sb = new StringBuffer();
                String line = "";
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
                reader.close();
                httpURLConnection.disconnect();
                //如果上传不成功就保存为文件
                String result = sb.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过GET请求方式获取Json数据
     *
     * @param url 网址/环境
     * @return JSON 字符串
     */
    public static String httpGetRequest_String(String url) {
        // TODO Auto-generated method stub
        InputStream is = null;
        // 读取String内容
        BufferedReader br = null;
        try {
            URL _url = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) _url.openConnection();
            conn.setDoInput(true); // 可以读取
            conn.setDoOutput(false); // 没有写入
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(8000);
            conn.setReadTimeout(5000);
            conn.connect();
            if (conn.getResponseCode() == 200) {
                // 获取数据
                is = conn.getInputStream();
                // 在转换流里定义编码格式(网上下载的数据有中文，一定要考虑到编码格式)
                br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
                // 存放每一行的StringBuilder
                StringBuilder builder = new StringBuilder();
                String line = null;
                while ((line = br.readLine()) != null) {
                    // 每一行的信息加入StringBuilder
                    builder.append(line);
                }
                return builder.toString();
            }
            if (br != null)
                br.close();
            if (is != null)
                is.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过GET方式下载二进制数据
     *
     * @param url 网址
     * @return byte[] 字节数组
     */
    public static byte[] httpGetRequest_ByteArray(String url) {
        InputStream is = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            URL _url = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) _url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(false);
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(8000);
            conn.setReadTimeout(5000);
            conn.connect();
            if (conn.getResponseCode() == 200) {
                is = conn.getInputStream();
                //缓存大小
                byte[] buffer = new byte[1024 * 4];
                int len = 0;
                while ((len = is.read(buffer)) != -1) {
                    baos.write(buffer, 0, len);
                }
            }
            return baos.toByteArray();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (baos != null)
                    baos.close();
                if (is != null)
                    is.close();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * http POST请求（方法一）
     *
     * @param url  请求url
     * @param data 字节数组
     */
    public static void httpPostRequest(String url, byte[] data) {
        try {
            URL _url = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) _url.openConnection();
            OutputStream outputStream = httpURLConnection.getOutputStream();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestProperty("Content-type", "application/octet-stream");
            DataOutputStream dos = new DataOutputStream(outputStream);
            dos.write(data, 0, data.length);
            dos.flush();
            dos.close();
            outputStream.close();
            if (httpURLConnection.getResponseCode() == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                StringBuffer sb = new StringBuffer();
                String line = "";
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
                reader.close();
                httpURLConnection.disconnect();
                //如果上传不成功就保存为文件
                String result = sb.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * http POST请求（方法二）
     *
     * @param url  请求url
     * @param data 请求字符串
     */
    public static void httpPostRequest(String url, String data) {
        try {
            URL _url = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) _url.openConnection();
            OutputStream outputStream = httpURLConnection.getOutputStream();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestProperty("Content-type", "application/octet-stream");
            DataOutputStream dos = new DataOutputStream(outputStream);
            byte[] bytes = data.getBytes();
            dos.write(bytes, 0, bytes.length);
            dos.flush();
            dos.close();
            outputStream.close();
            if (httpURLConnection.getResponseCode() == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                StringBuffer sb = new StringBuffer();
                String line = "";
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
                reader.close();
                httpURLConnection.disconnect();
                //如果上传不成功就保存为文件
                String result = sb.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void download(String url, String filePath, ProgressListener progressListener) {
        OkHttpClient client = new OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .build();
        FileOutputStream outputStream = null;
        InputStream stream = null;
        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs();
            file.createNewFile();
            outputStream = new FileOutputStream(file);
            Response response = client.newCall(request).execute();
            long contentLength = response.body().contentLength();
            stream = response.body().byteStream();
            byte[] bytes = new byte[1024];
            int length;
            long sum = 0;
            while ((length = stream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, length);
                sum += length;
                if (progressListener != null) {
                    int progress = (int) ((sum / (contentLength * 1.0f)) * 100);
                    Log.i(TAG, "download: " + progress);
                    progressListener.onProgress(progress);
                }
            }
            if (file.exists()) {
                if (progressListener != null) {
                    progressListener.onSuccess(file.getPath());
                }
            } else {
                if (progressListener != null) {
                    progressListener.onFailure(new RuntimeException("file not exist"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            if (progressListener != null) {
                progressListener.onFailure(e);
            }
        } finally {
            try {
                outputStream.flush();
                stream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void downloadImg(String url, String filePath, ProgressListener progressListener) {
        OkHttpClient client = new OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .build();
        FileOutputStream outputStream = null;
        InputStream stream = null;
        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs();
            file.createNewFile();
            outputStream = new FileOutputStream(file);
            Response response = client.newCall(request).execute();
            long contentLength = response.body().contentLength();
            stream = response.body().byteStream();
            byte[] bytes = new byte[1024];
            int length;
            long sum = 0;
            while ((length = stream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, length);
                sum += length;
                if (progressListener != null) {
                    int progress = (int) ((sum / (contentLength * 1.0f)) * 100);
                    Log.i(TAG, "download: " + progress);
                    progressListener.onProgress(progress);
                }
            }
            if (file.exists()) {
                if (progressListener != null) {
                    progressListener.onSuccess(file.getPath());
                }
            } else {
                if (progressListener != null) {
                    progressListener.onFailure(new RuntimeException("file not exist"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            if (progressListener != null) {
                progressListener.onFailure(e);
            }
        } finally {
            try {
                outputStream.flush();
                stream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public interface ProgressListener {
        void onProgress(int progress);

        void onSuccess(String filePath);

        void onFailure(Throwable throwable);
    }
}
