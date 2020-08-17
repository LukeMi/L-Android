package com.lukemi.android.common.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * 网络请求工具类
 *
 * @author Jimmy
 */
public class HttpUtils {

    /**
     * 通过GET请求方式获取Json数据
     *
     * @param webSite 网址
     * @return JSON 字符串
     */
    public static String getString(String webSite) {
        // TODO Auto-generated method stub
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
}
