package com.umeng.soexample.bweismall.presenter;

import com.umeng.soexample.bweismall.callback.MyCallBack;
import com.umeng.soexample.bweismall.model.ModelImpl;
import com.umeng.soexample.bweismall.view.IView;

/**
 * Created by android_lhf：2018/12/29
 */
public class PresenterImpl implements Presenter {
    private IView iView;
    private ModelImpl model;

    public PresenterImpl(IView iView) {
        this.iView = iView;
        model = new ModelImpl();
    }
    @Override
    public void startRequestKey(String url, String keyword, int page, int count) {
        model.getDataKey(url, keyword, page, count, new MyCallBack() {
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
    public void startRequestQuan(String url, int page, int count) {
        model.getDataQuan(url, page, count, new MyCallBack() {
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
    public void startRequest(String url, String phone, String pwd) {
        model.getData(url, phone, pwd, new MyCallBack() {
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



    public void onDetatch(){
        if(model != null ){
            model = null;
        }
        if (iView != null){
            iView = null;
        }
    }
}
