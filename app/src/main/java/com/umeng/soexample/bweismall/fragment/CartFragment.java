package com.umeng.soexample.bweismall.fragment;

import android.content.Context;
import android.content.Intent;
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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.soexample.bweismall.Contacts;
import com.umeng.soexample.bweismall.R;
import com.umeng.soexample.bweismall.adapter.CartAdapter;
import com.umeng.soexample.bweismall.bean.ShopDataBean;
import com.umeng.soexample.bweismall.presenter.RPresenterImpl;
import com.umeng.soexample.bweismall.ui.SubActivity;
import com.umeng.soexample.bweismall.view.IView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by android_lhf：2019/1/2
 */
public class CartFragment extends Fragment implements IView {

    @BindView(R.id.shop_all)
    CheckBox shopAll;
    @BindView(R.id.shop_all_price)
    TextView ShopAllPrice;
    @BindView(R.id.shop_close)
    Button shopClose;
    @BindView(R.id.shop_footer)
    RelativeLayout shopFooter;
    @BindView(R.id.shop_recy)
    RecyclerView shopRecy;
    Unbinder unbinder;
    private RPresenterImpl presenter;
    private CartAdapter adapter;
    private SharedPreferences sp;
    private boolean a=false;
    private List<ShopDataBean.ResultBean> mdata = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        unbinder = ButterKnife.bind(this, view);
        sp = getActivity().getSharedPreferences("lu", Context.MODE_PRIVATE);
        presenter = new RPresenterImpl(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        shopRecy.setLayoutManager(layoutManager);
        Map<String, String> headmap = new HashMap<>();
        int userId = sp.getInt("userId", 0);
        String sessionId = sp.getString("sessionId", null);
        headmap.put("userId", userId + "");
        headmap.put("sessionId", sessionId);
        adapter = new CartAdapter(mdata, getActivity());
        shopRecy.setAdapter(adapter);
        Map<String, Object> map = new HashMap<>();
        presenter.startShoushop(Contacts.CHA_CART, null, headmap, ShopDataBean.class);

        adapter.setOnItemClick(new CartAdapter.OnItemClick() {
            @Override
            public void onClick(View view, int position) {
                boolean status = adapter.thisCheckStatus(position);
                adapter.setCheckStatus(position, !status);
                adapter.notifyDataSetChanged();
                FlushFooter();
            }

            @Override
            public void onDelete(View view, int position) {
                mdata.remove(position);
                adapter.notifyDataSetChanged();
                FlushFooter();
            }

            @Override
            public void onNumber(int position, int number) {
                adapter.setShopCount(position, number);
                adapter.notifyDataSetChanged();
                FlushFooter();
            }
        });
        shopAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean status = adapter.allCheckStatus();
                adapter.setAllCheckStatus(!status);
                shopAll.setChecked(!status);
                adapter.notifyDataSetChanged();
                FlushFooter();
            }
        });

        return view;
    }

    private void FlushFooter() {
        boolean status = adapter.allCheckStatus();
        shopAll.setChecked(status);
        double allPrice = adapter.getAllPrice();
        ShopAllPrice.setText("￥" + allPrice);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void success(Object data) {
        ShopDataBean shopDataBean = (ShopDataBean) data;
        mdata.clear();
        mdata.addAll(shopDataBean.getResult());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void error(Object error) {

    }

    @OnClick({ R.id.shop_close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shop_close:
                Intent intent = new Intent(getActivity(), SubActivity.class);
                for (int i = 0; i < mdata.size(); i++) {
                    boolean checked = mdata.get(i).isChildCheck();
                    if (checked == true) {
                        Bundle bundle = new Bundle();
                        List<ShopDataBean.ResultBean> mlist =new ArrayList<>();
                        mlist.add(mdata.get(i));
                        bundle.putSerializable("bbb", (Serializable) mlist);
                        intent.putExtras(bundle);
                        a = true;
                    }
                }
                if (a) {
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), "请选择你要购买的商品", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
