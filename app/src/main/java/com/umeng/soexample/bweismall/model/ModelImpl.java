package com.umeng.soexample.bweismall.model;

import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;
import com.umeng.soexample.bweismall.bean.BannerBean;
import com.umeng.soexample.bweismall.bean.KeyWordBean;
import com.umeng.soexample.bweismall.bean.LoginBean;
import com.umeng.soexample.bweismall.bean.QuanBean;
import com.umeng.soexample.bweismall.bean.ReXiaoBean;
import com.umeng.soexample.bweismall.bean.RegisterBean;
import com.umeng.soexample.bweismall.bean.ZanBean;
import com.umeng.soexample.bweismall.callback.MyCallBack;
import com.umeng.soexample.bweismall.utils.OkHttpUtils;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


/**
 * Created by android_lhf：2018/12/27
 */
public class ModelImpl implements Model {
    private MyCallBack myCallBack;
    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    RegisterBean bean0 = (RegisterBean) msg.obj;
                    myCallBack.setData(bean0);
                    break;
                case 1:
                    LoginBean bean1 = (LoginBean) msg.obj;
                    myCallBack.setData(bean1);
                    break;
                case 2:
                    ReXiaoBean bean2 = (ReXiaoBean) msg.obj;
                    myCallBack.setData(bean2);
                    break;
                case 3:
                    BannerBean bean3 = (BannerBean) msg.obj;
                    myCallBack.setData(bean3);
                    break;
                case 4:
                    KeyWordBean bean4 = (KeyWordBean) msg.obj;
                    myCallBack.setData(bean4);
                    break;
                case 5:
                    QuanBean bean5 = (QuanBean) msg.obj;
                    myCallBack.setData(bean5);
                    break;
                    case 6:
                    ZanBean bean6 = (ZanBean) msg.obj;
                    myCallBack.setData(bean6);
                    break;
            }

        }
    };

    @Override
    public void getData(String url, String phone, String pwd, MyCallBack callBack) {
        this.myCallBack = callBack;
        if (url.equals("http://172.17.8.100/small/user/v1/register")) {
            OkHttpUtils.getInstance().postAsync(url + "?phone=" + phone + "&pwd=" + pwd, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String string = response.body().string();
                    RegisterBean bean = new Gson().fromJson(string, RegisterBean.class);
                    mHandler.sendMessage(mHandler.obtainMessage(0, bean));
                }
            });
        } else if (url.equals("http://172.17.8.100/small/user/v1/login")) {
            OkHttpUtils.getInstance().postAsync(url + "?phone=" + phone + "&pwd=" + pwd, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String string = response.body().string();
                    LoginBean bean = new Gson().fromJson(string, LoginBean.class);
                    mHandler.sendMessage(mHandler.obtainMessage(1, bean));
                }
            });
        } else if (url.equals("http://172.17.8.100/small/commodity/v1/commodityList")) {
            OkHttpUtils.getInstance().getAsync(url, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String string = response.body().string();
                    ReXiaoBean bean = new Gson().fromJson(string, ReXiaoBean.class);
                    mHandler.sendMessage(mHandler.obtainMessage(2, bean));
                }
            });
        }else if (url.equals("http://172.17.8.100/small/commodity/v1/bannerShow")){
            OkHttpUtils.getInstance().getAsync(url, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String string = response.body().string();
                    BannerBean bean = new Gson().fromJson(string, BannerBean.class);
                    mHandler.sendMessage(mHandler.obtainMessage(3, bean));
                }
            });
        }
    }

    @Override
    public void getDataKey(String url, String keyword, int page, int count, MyCallBack callBack) {
        this.myCallBack = callBack;

        OkHttpUtils.getInstance().getAsync(url + "?keyword=" + keyword + "&page=" + page + "&count=" + count, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                KeyWordBean bean = new Gson().fromJson(string, KeyWordBean.class);
                mHandler.sendMessage(mHandler.obtainMessage(4, bean));
            }
        });
    }

    @Override
    public void getDataQuan(String url, int page, int count, MyCallBack callBack) {
        this.myCallBack = callBack;
        OkHttpUtils.getInstance().getAsync(url + "?page=" + page + "&count=" + count, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                QuanBean quanBean = new Gson().fromJson(string, QuanBean.class);
                mHandler.sendMessage(mHandler.obtainMessage(5, quanBean));
            }
        });
    }

}