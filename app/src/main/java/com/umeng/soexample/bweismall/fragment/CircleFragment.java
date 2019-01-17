package com.umeng.soexample.bweismall.fragment;


import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.umeng.soexample.bweismall.R;
import com.umeng.soexample.bweismall.adapter.QuanAdapter;
import com.umeng.soexample.bweismall.base.BaseFragment;
import com.umeng.soexample.bweismall.bean.LoginBean;
import com.umeng.soexample.bweismall.bean.QuanBean;
import com.umeng.soexample.bweismall.bean.ZanBean;
import com.umeng.soexample.bweismall.presenter.PresenterImpl;
import com.umeng.soexample.bweismall.presenter.RPresenterImpl;
import com.umeng.soexample.bweismall.view.IView;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class CircleFragment extends BaseFragment implements IView, XRecyclerView.LoadingListener {

    private List<QuanBean.ResultBean> list = new ArrayList<>();
    private String mUrl = "http://172.17.8.100/small/circle/v1/findCircleList";

    private PresenterImpl presenter;
    private RPresenterImpl rPresenter;
    private QuanAdapter adapter;
    private int page = 1;
    private int count = 10;
    private XRecyclerView mXRecy;
    private TextView mQzanshu;
    private SharedPreferences.Editor mEditor;

    @Override
    protected void initData() {
        mXRecy = getActivity().findViewById(R.id.Q_XRecy);
        mQzanshu = getActivity().findViewById(R.id.Q_zanshu);
        adapter = new QuanAdapter(getActivity(), list);
        mXRecy.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mXRecy.setLayoutManager(layoutManager);
        mXRecy.setLoadingListener(this);
        mXRecy.setLoadingMoreProgressStyle(ProgressStyle.Pacman);
        mXRecy.getDefaultRefreshHeaderView().setRefreshTimeVisible(true);

        presenter = new PresenterImpl(this);
        rPresenter = new  RPresenterImpl(this);
        presenter.startRequestQuan(mUrl,page,count);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_circle;
    }

    @Override
    public void success(Object data) {
        if (data instanceof QuanBean){
            QuanBean quanBean = (QuanBean) data;
            List<QuanBean.ResultBean> list1 =quanBean.getResult();
            list.addAll(list1);
            adapter.notifyDataSetChanged();
        }

    }

    @Override
    public void error(Object error) {

    }

    @Override
    public void onRefresh() {
        page = 1;
        list.clear();
        presenter.startRequestQuan(mUrl,page,count);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mXRecy.refreshComplete();
            }
        },2000);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onLoadMore() {
        page++;
        presenter.startRequestQuan(mUrl,page,count);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mXRecy.refreshComplete();
            }
        },2000);
        adapter.notifyDataSetChanged();
    }

}
