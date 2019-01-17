package com.umeng.soexample.bweismall.view;

/**
 * Created by android_lhf：2018/12/27
 */
public interface IView<T> {
    void success(T data);
    void error(T error);
}
