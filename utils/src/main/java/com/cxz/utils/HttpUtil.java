package com.cxz.utils;

import com.alibaba.fastjson.JSON;
import okhttp3.*;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author cxz
 * @Title:
 * @Package
 * @Description:
 * @date 2020/12/28 16:55
 */
public class HttpUtil {

    public static <T> T getInfo(String url, Class<T> entity) {

        String resStr = getInfo(url);
        if (!StringUtils.isEmpty(resStr)) {
            return JSON.parseObject(resStr, entity);
        }
        return null;
    }

    public static String getInfo(String url) {
        String resStr = "";
        //创建OkHttpClient对象。
        OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(30000, TimeUnit.MILLISECONDS)
                .readTimeout(30000, TimeUnit.MILLISECONDS)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            resStr = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resStr;
    }

    public static String getInfo(String url,Map<String ,String> head) {
        String resStr = "";
        //创建OkHttpClient对象。
        Headers headerbuild = Headers.of(head);
        OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(30000, TimeUnit.MILLISECONDS)
                .readTimeout(30000, TimeUnit.MILLISECONDS)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .headers(headerbuild)
                .build();
        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            resStr = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resStr;
    }


    public static String postInfo(String url, String json , Map<String ,String> head ){
        String resStr = "";
        //创建OkHttpClient对象。
        OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(30000, TimeUnit.MILLISECONDS)
                .readTimeout(30000, TimeUnit.MILLISECONDS)
                .build();
        //数据类型为json格式，
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        Headers headerbuild = Headers.of(head);
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .headers(headerbuild)
                .build();

        Call call = client.newCall(request);
        try {
            call.timeout().timeout(30000,TimeUnit.MILLISECONDS);
            Response response = call.execute();
            resStr = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resStr;
    }


}