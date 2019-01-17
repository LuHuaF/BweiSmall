package com.umeng.soexample.bweismall.model;

import com.google.gson.Gson;
import com.umeng.soexample.bweismall.callback.MyCallBack;
import com.umeng.soexample.bweismall.network.RetrofitUtils;

import java.util.Map;

/**
 * Created by android_lhf：2019/1/7
 */
public class RModelImpl implements RModel {

    @Override
    public void getData(String url, Map<String, Object> map, Map<String, String> headmap, final Class kind, final MyCallBack callBack) {
        RetrofitUtils.getInstance().put(url,map,headmap).setHttpListener(new RetrofitUtils.HttpListener() {
            @Override
            public void onSuccess(String jsonStr) {
                Gson gson = new Gson();
                Object loginBean = gson.fromJson(jsonStr,kind);
                callBack.setData(loginBean);
            }

            @Override
            public void onError(String error) {

            }
        });
    }

    @Override
    public void getShoushop(String url,Map<String,String> map, Map<String, String> headmap,final Class kind, final MyCallBack callBack) {
        RetrofitUtils.getInstance().get(url,map,headmap).setHttpListener(new RetrofitUtils.HttpListener() {
            @Override
            public void onSuccess(String jsonStr) {
                Gson gson = new Gson();
                Object s = gson.fromJson(jsonStr, kind);
                callBack.setData(s);
            }

            @Override
            public void onError(String error) {

            }
        });
    }

    @Override
    public void post(String url, Map<String, String> map, Map<String, String> headmap, final Class kind, final MyCallBack callBack) {
        RetrofitUtils.getInstance().post(url,map,headmap).setHttpListener(new RetrofitUtils.HttpListener() {
            @Override
            public void onSuccess(String jsonStr) {
                Gson gson = new Gson();
                Object s =gson.fromJson(jsonStr,kind);
                callBack.setData(s);

            }

            @Override
            public void onError(String error) {

            }
        });
    }

}
