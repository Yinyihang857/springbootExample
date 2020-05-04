package com.sx.common.utils;

import com.alibaba.fastjson.*;
import com.alibaba.fastjson.serializer.JSONLibDataFormatSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.List;
import java.util.Map;

/**
 *
 * @Description: 基于基于FastJson封装JSON工具类,下方有各种请求示例
 * @author yinyihang
 * @date 2020年04月25日
 *
 */
public class JsonUtils {
    private static SerializeConfig config;
    private static SerializerFeature[] features = {
            //输出空值字段
            SerializerFeature.WriteMapNullValue,
            //如果数组结果为null,则输出为[],而不是null
            SerializerFeature.WriteNullListAsEmpty,
            //数值字段为null,则输出为0,而不是null
            SerializerFeature.WriteNullNumberAsZero,
            //Boolean字段为null,则输出为false,而不是null
            SerializerFeature.WriteNullBooleanAsFalse,
            //字符类型如果为null,则输出为" ",而不是null
            SerializerFeature.WriteNullStringAsEmpty
    };

    static {
        config = new SerializeConfig();
        //使用json-lib兼容的日期输出格式
        config.put(java.util.Date.class, new JSONLibDataFormatSerializer());
    }

    /**
     * 将一个对象装换为Json字符串
     */
    public static String toJSONString(Object object) {
        return JSONObject.toJSONString(object, config, features);
    }

    /**
     * 将Json字符串转换为Object类型的
     */
    public static Object toObject(String str) {
        return JSON.parse(str);
    }

    /**
     * 将Json字符串转换为实例
     */
    public static <T> T toObject(String str, Class<T> t) {
        return JSON.parseObject(str, t);
    }

    /**
     * 将Json转换为指定类型的List
     */
    public static <T> List<T> toList(String str, Class<T> t) {
        return JSON.parseArray(str, t);
    }

    /**
     * 将Json转换为Map
     */
    public static <T> Map<String, T> toMap(String str) {
        return (Map<String, T>) JSONObject.parseObject(str);
    }
}

/**
 *         使用例子
 *
 *         Map<String, Object> map = new HashMap<>();
 *         List<String> list = new ArrayList<>();
 *         list.add("22");
 *         list.add("33");
 *         map.put("code", "1");
 *         map.put("msg", "ok");
 *         map.put("data", list);
 *
 *         //将数据序列化为json字符串
 *         String c = JsonUtils.toJSONString(map);
 *
 *         System.out.println(c + "\n");
 *         //结果为：
 *         //c = {"msg":"ok","code":"1","data":["22","33"]}
 *
 *
 *         //json序列化为map
 *         Map<String, Object> a = JsonUtils.toMap(c);
 *         System.out.println(a.get("code") + "\n");
 *         System.out.println(a.get("msg") + "\n");
 *         System.out.println(a.get("data") + "\n");
 *
 *         //结果为：
 *         //1
 *         //ok
 *         //["22","33"]
 *
 * */
