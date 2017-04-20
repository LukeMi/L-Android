package com.lukemi.android.collection.util;

import android.text.TextUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * 数据压缩帮助类
 * Created by mzchen on 2016/12/9.
 */

public class ZipUtil {

    /**
     * 将一个字符串按照zip方式压缩
     *
     * @param unzipStr 待压缩的字符串
     * @return 压缩后的字符串
     * @throws IOException
     */
    public static String zipString(String unzipStr) throws IOException {
        if (TextUtils.isEmpty(unzipStr)){
            return unzipStr;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzip = new GZIPOutputStream(out);
        gzip.write(unzipStr.getBytes());
        gzip.close();
        return out.toString("ISO-8859-1");
    }


    /**
     * 将一个字符串按照zip方式解压缩
     *
     * @param zipStr 待解压的字符串
     * @return 解压后的字符串
     * @throws IOException
     */
    public static String unzipString(String zipStr) throws IOException {
        if (TextUtils.isEmpty(zipStr)){
            return zipStr;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(
                zipStr.getBytes("ISO-8859-1"));
        GZIPInputStream gunzip = new GZIPInputStream(in);
        byte[] buffer = new byte[256];
        int n;
        while ((n = gunzip.read(buffer)) >= 0) {
            out.write(buffer, 0, n);
        }
        // toString()使用平台默认编码，也可以显式的指定如toString("GBK")
        return out.toString("utf-8");
    }


    /**
     * 判断字符串的编码格式
     *
     * @param encodeStr 待判断的编码的字符串
     * @return 编码字符集（默认为空字符串，未加入的编码格式）
     */
    public static String showStringCharset(String encodeStr) {
        if (TextUtils.isEmpty(encodeStr)){
            return "";
        }
        String defaultEncodes = "";
        List<String> encodeds = new ArrayList<>();
        encodeds.add("UTF-8");
        encodeds.add("UTF-16");
        encodeds.add("GBK");
        encodeds.add("ISO-8859-1");
        encodeds.add("ISO-10");
        for (int i = 0; i < encodeds.size(); i++) {
            String code = encodeds.get(i);
            try {
                if (encodeStr.equals(new String(encodeStr.getBytes(code), code))) {
                    defaultEncodes = code;
                    break;
                }
            } catch (UnsupportedEncodingException e) {
                continue;
            }
        }
        return defaultEncodes;
    }
}
