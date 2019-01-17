package com.umeng.soexample.bweismall.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.soexample.bweismall.Contacts;
import com.umeng.soexample.bweismall.R;
import com.umeng.soexample.bweismall.adapter.ChaAdapter;
import com.umeng.soexample.bweismall.bean.ChaAddressBean;
import com.umeng.soexample.bweismall.bean.MoreAddresBean;
import com.umeng.soexample.bweismall.presenter.RPresenter;
import com.umeng.soexample.bweismall.presenter.RPresenterImpl;
import com.umeng.soexample.bweismall.view.IView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by android_lhf：2019/1/12
 */
public class ShouHuoActivity extends AppCompatActivity implements View.OnClickListener ,IView{
    private TextView Text_address_success;
    private Button Btn_address_add;
    private RecyclerView recy;
    private List<ChaAddressBean.ResultBean> result=new ArrayList<>();
    private RPresenter presenter;
    private int userId;
    private String sessionId;
    private ChaAdapter adapter;
    private SharedPreferences login1;
    private SharedPreferences sp;
    private SharedPreferences.Editor edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 隐藏标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 隐藏状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_shouhuo);
        initView();
        login1 = getSharedPreferences("lu", Context.MODE_PRIVATE);
        userId = login1.getInt("userId", 0);
        sessionId = login1.getString("sessionId", "");
        HashMap<String, String> map = new HashMap<>();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("userId",userId +"");
        hashMap.put("sessionId",sessionId);

        presenter.startShoushop(Contacts.CHA_SHOUHUO,map,hashMap,ChaAddressBean.class);

    }

    private void initView() {
        Text_address_success = (TextView) findViewById(R.id.Text_address_success);
        Text_address_success.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Btn_address_add = (Button) findViewById(R.id.Btn_address_add);
        Btn_address_add.setOnClickListener(this);
        recy = (RecyclerView) findViewById(R.id.recy);
        recy.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ChaAdapter(result,this);
        recy.setAdapter(adapter);
        presenter=new RPresenterImpl(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Btn_address_add:
                startActivity(new Intent(this,XinZengActivity.class));
                break;
        }
    }

    @Override
    public void success(Object data) {
        if (data instanceof ChaAddressBean){
//            result = ((ChaAddressBean) data).getResult();
            ChaAddressBean bean = (ChaAddressBean) data;
            result.clear();
            result.addAll(bean.getResult());
            adapter.notifyDataSetChanged();
           adapter.setMorenChecked(new ChaAdapter.setMoren() {
                @Override
                public void setChecked(int position) {
                    int id = result.get(position).getId();
                    userId = login1.getInt("userId", 0);
                    sessionId = login1.getString("sessionId", "");
                    HashMap<String, String> map = new HashMap<>();
                    HashMap<String, String> hashMap = new HashMap<>();
                    map.put("id",id+"");
                    hashMap.put("userId",userId +"");
                    hashMap.put("sessionId",sessionId);
                    presenter.post(Contacts.MOREN_ADDRES,map,hashMap,MoreAddresBean.class);
                }
            });
        }else if (data instanceof MoreAddresBean){
            Toast.makeText(this, ((MoreAddresBean) data).getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void error(Object error) {
    }

}
