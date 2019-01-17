package com.umeng.soexample.bweismall.utils;

import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by android_lhf：2018/12/28
 */
public class OkHttpUtils {
    private final OkHttpClient okHttpClient;

    public OkHttpUtils() {
        //日志拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
//                .callTimeout(20, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .build();
    }

    public static OkHttpUtils getInstance(){
        return OkHttpHoulder.utils;
    }
    static class OkHttpHoulder{
        private static final OkHttpUtils utils=new OkHttpUtils();
    }

    //封装同步的Get请求方式
    public String getSync(String urlStr) throws IOException {
        Request request = new Request.Builder().url(urlStr).build();
        return okHttpClient.newCall(request).execute().body().string();
    }

    //同步的Post
    public String postAync(String urlStr,String key,String value) throws IOException {
        RequestBody body =new FormBody.Builder().add(key,value).build();
        Request request = new Request.Builder().url(urlStr).post(body).build();
        return okHttpClient.newCall(request).execute().body().string();
    }
    public void asyncPost2(String mUrl, HashMap<String, String> map, Callback callback) {

        FormBody.Builder body = new FormBody.Builder();
        for (String key : map.keySet()) {

            body.add(key, map.get(key));
        }
        Request request = new Request.Builder().url(mUrl).addHeader("userId", map.get("userId")).addHeader("sessionId", map.get("sessionId")).post(body.build()).build();
        okHttpClient.newCall(request).enqueue(callback);
    }

    //异步的get和post

    public void getAsync(String url, Callback callback) {
        Request request = new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(callback);
    }

    public void postAsync(String url, Callback callback) {
        RequestBody body = new FormBody.Builder().add("key", "value").build();
        Request request = new Request.Builder().url(url).post(body).build();
        okHttpClient.newCall(request).enqueue(callback);
    }
}
