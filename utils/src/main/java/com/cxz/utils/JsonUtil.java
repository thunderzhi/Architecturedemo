package com.cxz.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author cxz
 * @Title:
 * @Package
 * @Description:
 * @date 2020/12/8 19:11
 */
public class JsonUtil {
    /**
     * Convert json string to a Java object.
     *
     * @param json string
     * @return Java object
     * @throws IOException
     */
    public static <T> T toObject(Class<T> cls, String json) {
        return JSON.parseObject(json, cls);
    }

    public static <T> List<T> toList(Class<T> cls, String json) {
        return JSON.parseArray(json, cls);
    }

    /**
     * Convert Java object to json string.
     *
     * @param obj
     * @return String
     * @throws JsonProcessingException
     */
//    public static String toJson(Object obj) {
//
//        return JSON.toJSONString(obj);
//    }

    public static String toJson(Object obj, String dateFormat) {
        return JSON.toJSONStringWithDateFormat(obj, dateFormat, SerializerFeature.WriteDateUseDateFormat);
    }
    public static String toJson(Object obj) {
        return JSON.toJSONStringWithDateFormat(obj, "yyyy-MM-dd HH:mm:ss", SerializerFeature.WriteDateUseDateFormat);
    }

    public static Map<String,Object> toMap(String str){
        Gson gson = new Gson();
        Map<String, Object> map = new HashMap<String, Object>();
        return gson.fromJson(str, map.getClass());
    }

    public static <K, V> Map<K, V> parseToMap(String json,
                                              Class<K> keyType,
                                              Class<V> valueType) {
        return JSON.parseObject(json,
                new TypeReference<Map<K, V>>(keyType, valueType) {
                });
    }
    public static String streamToStr(InputStream inputStream) throws IOException {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }
        String str = result.toString(StandardCharsets.UTF_8.name());
        return str;
    }
}
