package com.umeng.soexample.bweismall.presenter;

/**
 * Created by android_lhf：2018/12/29
 */
public interface Presenter {
    void startRequest(String url , String phone , String pwd);
    void startRequestKey(String url , String keyword , int page , int  count);
    void startRequestQuan(String url , int page , int  count);
}
