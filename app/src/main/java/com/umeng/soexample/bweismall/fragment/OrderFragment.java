package com.umeng.soexample.bweismall.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.umeng.soexample.bweismall.R;
import com.umeng.soexample.bweismall.fragment.dingdan.AllDingdan_fragment;
import com.umeng.soexample.bweismall.fragment.dingdan.Complete_fragment;
import com.umeng.soexample.bweismall.fragment.dingdan.Evaluation_fragment;
import com.umeng.soexample.bweismall.fragment.dingdan.Harvested_fragment;
import com.umeng.soexample.bweismall.fragment.dingdan.Payment_fragment;

/**
 * Created by android_lhf：2019/1/2
 */
public class OrderFragment extends Fragment implements View.OnClickListener {
    private RadioButton mDingdan;
    private RadioButton mFukuan;
    private RadioButton mShouhuo;
    private RadioButton mPingjia;
    private RadioButton mFinsh;
    private RadioGroup mListRg;
    private FrameLayout mFramlayout;
    private FragmentManager manager;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_order, null);
        initView(view);
        manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.framlayout,new AllDingdan_fragment()).commit();
        return view;
    }

    private void initView(View view) {
        mDingdan = (RadioButton) view.findViewById(R.id.dingdan);
        mDingdan.setOnClickListener(this);
        mFukuan = (RadioButton) view.findViewById(R.id.fukuan);
        mFukuan.setOnClickListener(this);
        mShouhuo = (RadioButton) view.findViewById(R.id.shouhuo);
        mShouhuo.setOnClickListener(this);
        mPingjia = (RadioButton) view.findViewById(R.id.pingjia);
        mPingjia.setOnClickListener(this);
        mFinsh = (RadioButton) view.findViewById(R.id.finsh);
        mFinsh.setOnClickListener(this);
        mFramlayout = (FrameLayout) view.findViewById(R.id.framlayout);
    }

    @Override
    public void onClick(View view) {
        FragmentTransaction transaction = manager.beginTransaction();
        switch (view.getId()) {
            case R.id.dingdan:
                transaction.replace(R.id.framlayout, new AllDingdan_fragment());
                break;
            case R.id.fukuan:
                transaction.replace(R.id.framlayout, new Payment_fragment());
                break;
            case R.id.shouhuo:
                transaction.replace(R.id.framlayout, new Harvested_fragment());
                break;
            case R.id.pingjia:
                transaction.replace(R.id.framlayout, new Evaluation_fragment());
                break;
            case R.id.finsh:
                transaction.replace(R.id.framlayout, new Complete_fragment());
                break;
        }
        transaction.commit();
    }
}
