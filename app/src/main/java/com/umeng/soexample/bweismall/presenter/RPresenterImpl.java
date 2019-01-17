package com.umeng.soexample.bweismall.presenter;

import com.umeng.soexample.bweismall.callback.MyCallBack;
import com.umeng.soexample.bweismall.model.RModelImpl;
import com.umeng.soexample.bweismall.view.IView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by android_lhf：2019/1/7
 */
public class RPresenterImpl implements RPresenter {
    private IView iView;
    private RModelImpl model;

    public RPresenterImpl(IView iView) {
        this.iView = iView;
        model = new RModelImpl();
    }
    @Override
    public void startRequest(String url, Map<String, Object> map, Map<String, String> headmap, Class kind) {
        model.getData(url, map, headmap, kind, new MyCallBack() {
            @Override
            public void setData(Object user) {
                iView.success(user);
            }

            @Override
            public void setError(Object error) {
                iView.error(error);
            }
        });
    }


    @Override
    public void startShoushop(final String url, Map<String, String> map, Map<String, String> headmap, Class kind) {
        model.getShoushop(url, map, headmap, kind, new MyCallBack() {
            @Override
            public void setData(Object user) {
                iView.success(user);
            }

            @Override
            public void setError(Object error) {
                iView.error(error);
            }
        });
    }

    @Override
    public void post(String url, Map<String, String> map, Map<String, String> headmap, Class kind) {
        model.post(url, map, headmap, kind, new MyCallBack() {
            @Override
            public void setData(Object user) {
                iView.success(user);
            }

            @Override
            public void setError(Object error) {
                iView.error(error);
            }
        });
    }
}
