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

public class JavaCTransUtil {

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

    /**
     * Map转换成JsonObject
     *
     * @param map map集合
     * @return jsonObject
     */
    public static JSONObject map2JsonObject(Map<String, String> map) {
        JSONObject jsonObject = new JSONObject();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            try {
                jsonObject.put(key, value);
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }
        return jsonObject;
    }
}
