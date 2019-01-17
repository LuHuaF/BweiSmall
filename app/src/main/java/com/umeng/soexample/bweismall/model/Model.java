package com.umeng.soexample.bweismall.model;

import com.umeng.soexample.bweismall.callback.MyCallBack;


/**
 * Created by android_lhf：2018/12/27
 */
public interface Model {
    void getData(String url , String phone , String pwd , MyCallBack callBack);
    void getDataKey(String url , String keyword , int page , int  count , MyCallBack callBack);
    void getDataQuan(String url , int page , int  count , MyCallBack callBack);
}
