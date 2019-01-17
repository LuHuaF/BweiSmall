package com.umeng.soexample.bweismall.network;

import retrofit2.http.HeaderMap;
import retrofit2.http.PUT;
import rx.Observable;

import java.util.HashMap;
import java.util.Map;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by android_lhf：2019/1/5
 */
public interface MyApiService {
    //Retrofit + Rxjava
    @GET
    Observable<ResponseBody> get(@Url String url,@QueryMap Map<String, String> map,@HeaderMap Map<String, String> headmap);

    @POST
    Observable<ResponseBody> post(@Url String url, @QueryMap Map<String, String> map,@HeaderMap Map<String, String> headmap);
    //这是Retrofit的使用
    @PUT
    Observable<ResponseBody> put(@Url String url, @QueryMap Map<String, Object> map, @HeaderMap Map<String, String> headmap);


}
