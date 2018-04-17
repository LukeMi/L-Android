package com.lukemi.myandroid.webservice;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by android on 2017/5/16.
 */

public class Constant {
    /**
     * 命名空间
     */
    public static final String NAMESPACE = "http://WebXml.com.cn/";
    /**
     * 获取归属地信息URL
     */
    public static String URL = "http://www.webxml.com.cn/webservices/MobileCodeWS.asmx";
    /**
     * 获得归属地方法名
     */
    public static String GET_LOCATION_METHOD_NAME = "getMobileCodeInfo";
    /**
     * 获得归属地soapAction
     */
    public static String GET_LOCATION_SOAP_ACTION = "http://WebXml.com.cn/getMobileCodeInfo";
    /**
     * 获取归属地返回数据key
     */
    public static String GET_LOCATION_RESULT_NAME = "getMobileCodeInfoResult";

    /**
     * 获取字符串中的中文列表
     *
     * @param str_text
     * @return
     */
    public static List<String> GetChineseWord(String str_text) {
        List<String> chineseWordsList = new ArrayList<String>();
        String reg = "([\u4e00-\u9fa5]+)";
        Matcher matcher = Pattern.compile(reg).matcher(str_text);
        while (matcher.find()) {
            chineseWordsList.add(matcher.group());
        }
        return chineseWordsList;
    }
}
