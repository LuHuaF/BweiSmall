package com.umeng.soexample.bweismall.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lljjcoder.citypickerview.widget.CityPickerView;
import com.umeng.soexample.bweismall.Contacts;
import com.umeng.soexample.bweismall.R;
import com.umeng.soexample.bweismall.bean.XInZengBean;
import com.umeng.soexample.bweismall.presenter.RPresenter;
import com.umeng.soexample.bweismall.presenter.RPresenterImpl;
import com.umeng.soexample.bweismall.view.IView;
import com.umeng.soexample.bweismall.weight.CountView;

import java.util.HashMap;

/**
 * Created by android_lhf：2019/1/12
 */
public class XinZengActivity extends AppCompatActivity implements View.OnClickListener,IView{
    private TextView my_info;
    private EditText add_person;
    private EditText add_phone;
    private TextView add_text;
    private TextView add_local;
    private ImageView add_image;
    private EditText add_content;
    private TextView get_text;
    private TextView add_email;
    private Button add_save;
    private TextView mDi;
    private RPresenter presenter;
    private int userId;
    private String sessionId;
    private String name;
    private String phone;
    private String local;
    private String content;
    private String email;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 隐藏标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 隐藏状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_xinzeng);
        initView();
        SharedPreferences login1 = getSharedPreferences("lu", Context.MODE_PRIVATE);
        userId = login1.getInt("userId", 0);
        sessionId = login1.getString("sessionId", "");

    }

    private void initView() {
        my_info = (TextView) findViewById(R.id.my_info);
        add_person = (EditText) findViewById(R.id.add_person);
        add_phone = (EditText) findViewById(R.id.add_phone);
        add_text = (TextView) findViewById(R.id.add_text);
        add_local = (TextView) findViewById(R.id.add_local);
        add_image = (ImageView) findViewById(R.id.add_image);
        add_image.setOnClickListener(this);
        add_content = (EditText) findViewById(R.id.add_content);
        get_text = (TextView) findViewById(R.id.get_text);
        add_email = (TextView) findViewById(R.id.add_email);
        add_save = (Button) findViewById(R.id.add_save);
        add_save.setOnClickListener(this);
        presenter = new RPresenterImpl(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_save:
                name = add_person.getText().toString();
                phone = add_phone.getText().toString();
                local = add_local.getText().toString();
                content = add_content.getText().toString();
                email = add_email.getText().toString();
                if (name.isEmpty()|| phone.isEmpty()|| content.isEmpty()){
                    Toast.makeText(this, "请填写完整！！", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    HashMap<String,String> map=new HashMap<>();
                    map.put("realName",name);
                    map.put("phone",phone);
                    map.put("address",content);
                    map.put("zipCode",email);
                    HashMap<String,String> hashMap=new HashMap<>();
                    hashMap.put("userId",userId +"");
                    hashMap.put("sessionId",sessionId);
                    presenter.post(Contacts.XIN_SHOUHUO,map,hashMap,XInZengBean.class);
                }
                break;
            case R.id.add_image:
                CityPickerView cityPickerView = new CityPickerView(XinZengActivity.this);
                cityPickerView.setOnCityItemClickListener(new CityPickerView.OnCityItemClickListener() {
                    @Override
                    public void onSelected(String... citySelected) {
                        //省份
                        String province = citySelected[0];
                        //城市
                        String city = citySelected[1];
                        //区县
                        String district = citySelected[2];
                        //邮编
                        String code = citySelected[3];
                        add_local.setText(province + "-" + city + "-" + district);
                        add_content.setText(province+city+district);
                        add_email.setText(code+"");
                        Toast.makeText(XinZengActivity.this, province + "-" + city + "-" + district, Toast.LENGTH_LONG).show();
                    }
                });
                cityPickerView.show();
                break;
        }
    }

    @Override
    public void success(Object data) {
        //地址添加成功
        if (data instanceof XInZengBean){
            Toast.makeText(this, ((XInZengBean) data).getMessage(), Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this,ShouHuoActivity.class));
        }

    }

    @Override
    public void error(Object error) {
        Toast.makeText(this, (String)error, Toast.LENGTH_SHORT).show();
    }

}
