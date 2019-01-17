package com.umeng.soexample.bweismall.model;

import com.umeng.soexample.bweismall.callback.MyCallBack;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by android_lhf：2019/1/7
 */
public interface RModel {
    //put
    void getData(String url, Map<String,Object> map,Map<String,String> headmap,Class kind, MyCallBack callBack);
    //get
    void getShoushop(String url,Map<String,String> map,Map<String,String> headmap,Class kind,MyCallBack callBack);
    //post
    void post(String url, Map<String,String> map,Map<String,String> headmap,Class kind, MyCallBack callBack);
}
