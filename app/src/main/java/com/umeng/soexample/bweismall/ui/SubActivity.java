package com.umeng.soexample.bweismall.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.soexample.bweismall.Contacts;
import com.umeng.soexample.bweismall.R;
import com.umeng.soexample.bweismall.bean.ChaAddressBean;
import com.umeng.soexample.bweismall.bean.DingBean;
import com.umeng.soexample.bweismall.bean.ShopDataBean;
import com.umeng.soexample.bweismall.bean.ShouHuoBean;
import com.umeng.soexample.bweismall.adapter.CartAddressAdapter;
import com.umeng.soexample.bweismall.presenter.RPresenter;
import com.umeng.soexample.bweismall.presenter.RPresenterImpl;
import com.umeng.soexample.bweismall.view.IView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by android_lhf：2019/1/11
 */
public class SubActivity extends AppCompatActivity implements View.OnClickListener, IView {
    private CardView no_shop_address_error;
    private TextView set_shop_address_name;
    private TextView set_shop_address_phone;
    private TextView set_shop_address_address;
    private CardView shop_address_receive_card;
    private RecyclerView recy;
    private TextView commit_order_bottom_text;
    private Button commit_order_bottom_btn;
    private RPresenter presenter;
    private SharedPreferences sp;
    private int userId;
    private String sessionId;
    private CartAddressAdapter adapter;
    private float money = 0;
    private int count1;
    private List<ShopDataBean.ResultBean> list;
    private List<ChaAddressBean.ResultBean> mdata = new ArrayList<>();
    private int address_id;
    private JSONObject jsonObject;
    private JSONArray jsonArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 隐藏标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 隐藏状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sub);
        initView();
        Intent intent = getIntent();
        list = (List<ShopDataBean.ResultBean>) intent.getSerializableExtra("bbb");
        adapter = new CartAddressAdapter(list, this);
        recy.setAdapter(adapter);
        for (int i = 0; i < list.size(); i++) {
            count1 = list.get(i).getCount();
            money += list.get(i).getPrice() * count1;
        }
        commit_order_bottom_text.setText("共" + list.size() + "件商品，共" + money + "钱");
        adapter.setJiaJIan(new CartAddressAdapter.setJia() {
            private int count2;

            @Override
            public void setData() {
                float p = 0;
                for (int i = 0; i < list.size(); i++) {
                    count2 = list.get(i).getCount();
                    p += list.get(i).getPrice() * count2;
                }
                commit_order_bottom_text.setText("共" + count2 + "件商品，共" + p + "元钱");
            }
        });
        sp = getSharedPreferences("lu", Context.MODE_PRIVATE);
        userId = sp.getInt("userId", 0);
        sessionId = sp.getString("sessionId", "");
        HashMap<String, String> map = new HashMap<>();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("userId", userId + "");
        hashMap.put("sessionId", sessionId);
        presenter.startShoushop(Contacts.CHA_SHOUHUO, map, hashMap, ShouHuoBean.class);
    }

    private void initView() {
        set_shop_address_name = (TextView) findViewById(R.id.set_shop_address_name);
        set_shop_address_phone = (TextView) findViewById(R.id.set_shop_address_phone);
        set_shop_address_address = (TextView) findViewById(R.id.set_shop_address_address);
        shop_address_receive_card = (CardView) findViewById(R.id.shop_address_receive_card);
        no_shop_address_error = findViewById(R.id.no_shop_address_error);
        recy = (RecyclerView) findViewById(R.id.commit_order_recycle);
        commit_order_bottom_text = (TextView) findViewById(R.id.commit_order_bottom_text);
        commit_order_bottom_btn = (Button) findViewById(R.id.commit_order_bottom_btn);
        commit_order_bottom_btn.setOnClickListener(this);
        recy.setLayoutManager(new LinearLayoutManager(this));
        presenter = new RPresenterImpl(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.commit_order_bottom_btn:
                try {
                    jsonArray = new JSONArray();
                    for (int i = 0; i < list.size(); i++) {
                        jsonObject = new JSONObject();
                        jsonObject.put("commodityId", list.get(i).getCommodityId());
                        jsonObject.put("amount", list.get(i).getCount());
                        jsonArray.put(jsonObject);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                HashMap<String, String> map = new HashMap<>();
                HashMap<String, String> hashMap = new HashMap<>();
                map.put("orderInfo", jsonArray.toString());
                map.put("totalPrice", money + "");
                map.put("addressId", address_id + "");
                hashMap.put("userId", userId + "");
                hashMap.put("sessionId", sessionId);
                presenter.post(Contacts.BASE_CREAT_DAN, map, hashMap, DingBean.class);
                finish();
                break;
            case R.id.no_shop_address_error:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        finish();
    }

    @Override
    public void success(Object data) {
        if (data instanceof ShouHuoBean) {
            List<ShouHuoBean.ResultBean> result = ((ShouHuoBean) data).getResult();
            no_shop_address_error.setVisibility(View.GONE);
            shop_address_receive_card.setVisibility(View.VISIBLE);
            for (int i = 0; i < result.size(); i++) {
                if (result.get(i).getWhetherDefault() == 1) {
                    //默认地址的id
                    address_id = result.get(i).getId();
                    set_shop_address_name.setText("姓名："+result.get(i).getRealName());
                    set_shop_address_phone.setText("手机号："+result.get(i).getPhone());
                    set_shop_address_address.setText("地址："+result.get(i).getAddress());
                }
            }
        } else if (data instanceof DingBean) {
            Toast.makeText(this, ((DingBean) data).getMessage(), Toast.LENGTH_SHORT).show();
        }
        adapter.notifyDataSetChanged();
    }



    @Override
    public void error(Object error) {

    }
}
