package com.umeng.soexample.bweismall.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.umeng.soexample.bweismall.R;
import com.umeng.soexample.bweismall.presenter.RPresenterImpl;
import com.umeng.soexample.bweismall.ui.ShouHuoActivity;
import com.umeng.soexample.bweismall.ui.ZiLiaoActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by android_lhf：2019/1/2
 */
public class MyFragment extends Fragment {
    private ImageView my_home_tou;
    private TextView my_xiaojiejei;
    private TextView my_home_ziliao;
    private SharedPreferences sp;
    @BindView(R.id.my_home_ziliao)
    TextView myHomeZiliao;
    @BindView(R.id.my_home_quanzi)
    TextView myHomeQuanzi;
    @BindView(R.id.my_home_zuji)
    TextView myHomeZuji;
    @BindView(R.id.my_home_qianbao)
    TextView myHomeQianbao;
    @BindView(R.id.my_home_address)
    TextView myHomeAddress;
    @BindView(R.id.my_home_tou)
    ImageView myHomeTou;
    @BindView(R.id.my_xiaojiejie)
    TextView myXiaojiejie;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = View.inflate(getActivity(), R.layout.fragment_my, null);
        my_home_tou = view.findViewById(R.id.my_home_tou);
        my_xiaojiejei = view.findViewById(R.id.my_xiaojiejie);
        my_home_ziliao = view.findViewById(R.id.my_home_ziliao);
        unbinder = ButterKnife.bind(this, view);

        return view;

    }
    @Override
    public void onStart() {
        super.onStart();
        sp = getActivity().getSharedPreferences("lu", Context.MODE_PRIVATE);
        String nickName = sp.getString("nickName",null);
        String headPic = sp.getString("headPic",null);
        my_xiaojiejei.setText(nickName);
        Glide.with(getActivity()).load(headPic).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(my_home_tou);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.my_home_ziliao, R.id.my_home_quanzi, R.id.my_home_zuji, R.id.my_home_qianbao, R.id.my_home_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.my_home_ziliao:
                startActivity(new Intent(getActivity(), ZiLiaoActivity.class));
                break;
            case R.id.my_home_quanzi:
                break;
            case R.id.my_home_zuji:
                break;
            case R.id.my_home_qianbao:
                break;
            case R.id.my_home_address:
                startActivity(new Intent(getActivity(),ShouHuoActivity.class));
                break;
        }
    }
}
