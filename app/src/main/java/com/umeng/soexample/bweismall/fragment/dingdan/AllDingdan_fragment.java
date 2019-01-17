package com.umeng.soexample.bweismall.fragment.dingdan;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.umeng.soexample.bweismall.Contacts;
import com.umeng.soexample.bweismall.R;
import com.umeng.soexample.bweismall.adapter.DingdanAdapter;
import com.umeng.soexample.bweismall.bean.DingdanQueryBean;
import com.umeng.soexample.bweismall.presenter.RPresenterImpl;
import com.umeng.soexample.bweismall.view.IView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by android_lhf：2019/1/16
 */
public class AllDingdan_fragment extends Fragment  implements IView {
    private RecyclerView wai_rexy;
    private RPresenterImpl presenter;
    private int userId;
    private String sessionId;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.alldingdan_fragment, null);
        initView(view);
        SharedPreferences login1 = getActivity().getSharedPreferences("lu", Context.MODE_PRIVATE);
        userId = login1.getInt("userId", 0);
        sessionId = login1.getString("sessionId", "");
        HashMap<String,String> map=new HashMap<>();
        HashMap<String,String> hashMap=new HashMap<>();
        map.put("status",0+"");
        map.put("page",1+"");
        map.put("count",5+"");
        hashMap.put("userId",userId+"");
        hashMap.put("sessionId",sessionId);
        presenter.startShoushop(Contacts.BASE_CREAT_QUERY,map,hashMap,DingdanQueryBean.class);
        return view;
    }
    private void initView(View view) {
        wai_rexy = (RecyclerView) view.findViewById(R.id.wai_rexy);
        wai_rexy.setLayoutManager(new LinearLayoutManager(getActivity()));
        presenter=new RPresenterImpl(this);
    }

    @Override
    public void success(Object data) {
        if (data instanceof DingdanQueryBean){
            List<DingdanQueryBean.OrderListBean> list = ((DingdanQueryBean) data).getOrderList();
            DingdanAdapter adapter=new DingdanAdapter(list,getActivity());
            wai_rexy.setAdapter(adapter);
        }
    }

    @Override
    public void error(Object error) {

    }

}
