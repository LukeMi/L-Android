package com.luke.android.demo.util;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * JAVA类之间的转换帮助类
 * Created by mzchen on 2016/12/2.
 */

public class JavaClassTransUtil {

    /**
     * 将 JsonObject 转换成 Map<String,Sting>集合
     *
     * @param jsonObject JsonObject
     * @return Map
     */
    public static Map<String, String> JsonObject2Map(JSONObject jsonObject) {
        Map<String, String> map = new HashMap<>();
        Iterator<String> sIterator = jsonObject.keys();
        while (sIterator.hasNext()) {
            String key = sIterator.next();
            String value = jsonObject.optString(key);
            map.put(key, value);
        }
        return map;
    }

    /**
     * 将 JsonObject 转换成 Map<String,Sting>集合
     *
     * @param jsonStr JsonObject字符串
     * @return Map 当不是Json
     */
    public static Map<String, String> JsonObject2Map(String jsonStr) {
        JSONObject hostObject = null;
        try {
            hostObject = new JSONObject(jsonStr);
            return JsonObject2Map(hostObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
