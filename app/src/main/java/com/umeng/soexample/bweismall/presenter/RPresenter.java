package com.umeng.soexample.bweismall.presenter;

import java.util.Map;

/**
 * Created by android_lhf：2019/1/7
 */
public interface RPresenter {
    void startRequest(String url, Map<String,Object> map,Map<String,String> headmap,Class kind);
    void startShoushop(String url,Map<String,String> map,Map<String,String> headmap,Class kind);
    void post(String url, Map<String,String> map,Map<String,String> headmap,Class kind);
}
