package com.umeng.soexample.bweismall.callback;

/**
 * Created by android_lhf：2018/12/27
 */
public interface MyCallBack<T> {
    void setData(T user);
    void setError(T error);
}
