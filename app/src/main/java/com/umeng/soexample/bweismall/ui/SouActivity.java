package com.umeng.soexample.bweismall.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.view.Window;
import android.view.WindowManager;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.umeng.soexample.bweismall.R;
import com.umeng.soexample.bweismall.adapter.SouAdapter;
import com.umeng.soexample.bweismall.bean.KeyWordBean;
import com.umeng.soexample.bweismall.presenter.PresenterImpl;
import com.umeng.soexample.bweismall.view.IView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by android_lhf：2019/1/4
 */
public class SouActivity extends AppCompatActivity implements IView, XRecyclerView.LoadingListener {
    private List<KeyWordBean.ResultBean> list = new ArrayList<>();
    private String KryUrl = "http://172.17.8.100/small/commodity/v1/findCommodityByKeyword";
    private int page = 1;
    private int count = 10;
    private PresenterImpl presenter;
    private XRecyclerView mXRecr;
    private SouAdapter souAdapter;
    private String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 隐藏标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 隐藏状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sou);
        Intent intent = getIntent();
        key = intent.getStringExtra("key");
        mXRecr = findViewById(R.id.xrecy);
        souAdapter = new SouAdapter(list, this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        mXRecr.setLayoutManager(gridLayoutManager);
        mXRecr.setAdapter(souAdapter);
        mXRecr.setLoadingListener(this);
        mXRecr.setLoadingMoreProgressStyle(ProgressStyle.Pacman);
        mXRecr.getDefaultRefreshHeaderView().setRefreshTimeVisible(true);
        presenter = new PresenterImpl(this);
        presenter.startRequestKey(KryUrl, key, page, count);
    }

    @Override
    public void success(Object data) {

        KeyWordBean keyWordBean = (KeyWordBean) data;
        List<KeyWordBean.ResultBean> list1 = keyWordBean.getResult();
        list.addAll(list1);
        souAdapter.notifyDataSetChanged();
    }

    @Override
    public void error(Object error) {

    }

    @Override
    public void onRefresh() {
        page = 1;
        list.clear();
        presenter.startRequestKey(KryUrl, key, page, count);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mXRecr.refreshComplete();
            }
        }, 2000);
        souAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoadMore() {
        page++;
        presenter.startRequestKey(KryUrl, key, page, count);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mXRecr.refreshComplete();
            }
        }, 2000);
        souAdapter.notifyDataSetChanged();
    }
}
