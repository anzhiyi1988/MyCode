package com.thunisoft.sffx.monitor.util;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author hyrj
 *
 */
public class Util {

    /**
     * 
     * 获得传入时间和当前时间的毫秒差值
     * 
     * @param start
     * @return
     */
    public static long getTime(long start) {

        long end = System.currentTimeMillis();

        long time = end - start;

        return time;
    }

    //  protected String toJsonStr(Object obj) {
    //  JSONObject jsonStr = JSONObject.fromObject(obj);
    //  return jsonStr.toString();
    //}

    public static String toJsonStr(List<?> list) {
        JSONArray jsonStr = JSONArray.fromObject(list);
        return jsonStr.toString();
    }

    public static String toJsonStr(Map<?, ?> map) {
        JSONObject jsonStr = JSONObject.fromObject(map);
        return jsonStr.toString();
    }

    public static String obj2JsonStr(Object obj) {
        JSONObject jsonObj = JSONObject.fromObject(obj);
        return jsonObj.toString();
    }

    public static JSONObject ojb2Json(String str) {
        JSONObject jsonObj = JSONObject.fromObject(str);
        return jsonObj;

    }

    public static Object Json2Bean(String str, Class<?> beanClass) {
        JSONObject jsonObject = JSONObject.fromObject(str);
        Object Obj = JSONObject.toBean(jsonObject, beanClass);
        return Obj;
    }
}
