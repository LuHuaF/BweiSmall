package com.umeng.soexample.bweismall.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.soexample.bweismall.MainActivity;
import com.umeng.soexample.bweismall.R;
import com.umeng.soexample.bweismall.bean.LoginBean;
import com.umeng.soexample.bweismall.presenter.PresenterImpl;
import com.umeng.soexample.bweismall.view.IView;
import com.xw.repo.XEditText;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Created by android_lhf：2018/12/29
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener,IView {
    private SharedPreferences.Editor mEditor;
    private SharedPreferences mShared;
    private SharedPreferences sp;
    private boolean isLogin;
    private XEditText mEtPhone;
    private XEditText mEtPassword;
    private CheckBox mCheckBoxPassword;
    private TextView mTxtZhuCe;
    private Button mBtnLogin;
    private PresenterImpl presenter;
    private String mUrl = "http://172.17.8.100/small/user/v1/login";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 隐藏标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 隐藏状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        initView();
        mShared = getSharedPreferences("lu", MODE_PRIVATE);
        mEditor = mShared.edit();
        isLogin = mShared.getBoolean("isLogin", false);
        presenter = new PresenterImpl(this);
    }



    private void initView() {
        mEtPhone = findViewById(R.id.et_Phone);
        mEtPassword = findViewById(R.id.et_password);
        mCheckBoxPassword = findViewById(R.id.checkBox_password);
        mTxtZhuCe = findViewById(R.id.Txt_ZhuCe);
        mBtnLogin = findViewById(R.id.btn_login);
        mCheckBoxPassword.setOnClickListener(this);
        mBtnLogin.setOnClickListener(this);
        mTxtZhuCe.setOnClickListener(this);
        sp = this.getSharedPreferences("lu", Context.MODE_PRIVATE);
        if (sp.getBoolean("auto", false)){
            mEtPhone.setText(sp.getString("uname",null));
            mEtPassword.setText(sp.getString("upswd", null));

            mCheckBoxPassword.setChecked(true);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Txt_ZhuCe:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.btn_login:
                String phone = mEtPhone.getText().toString().trim();
                String pwd = mEtPassword.getText().toString().trim();
                if (isChinaPhoneLegal(mEtPhone.getText().toString())){
                    presenter.startRequest(mUrl,phone,pwd);
                }else{
                    Toast.makeText(this,"请输入正确的手机号和密码",Toast.LENGTH_SHORT).show();
                }
                boolean autoLogin = mCheckBoxPassword.isChecked();
                if (autoLogin) {
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("uname",phone);
                    editor.putString("upswd", pwd);
                    editor.putBoolean("auto", true);
                    editor.commit();
                } else {
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("uname",null);
                    editor.putString("upswd", null);
                    editor.putBoolean("auto", false);
                    editor.commit();
                }
                break;
        }

    }
    private boolean isChinaPhoneLegal(String s)
            throws PatternSyntaxException {
        String regExp = "^((13[0-9])|(15[^4])|(18[0-9])|(17[0-8])|(147,145))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(s);
        return m.matches();
    }

    @Override
    public void success(Object data) {
        LoginBean loginBean = (LoginBean) data;
        if (loginBean.getStatus().equals("1001")) {
            Toast.makeText(this, loginBean.getMessage(), Toast.LENGTH_SHORT).show();
        } else {
            mEditor = sp.edit();
            mEditor.putString("headPic",loginBean.getResult().getHeadPic());
            mEditor.putString("sessionId",loginBean.getResult().getSessionId());
            mEditor.putString("nickName",loginBean.getResult().getNickName());
            mEditor.putString("phone",loginBean.getResult().getPhone());
            mEditor.putString("pwd",mEtPassword.getText().toString());
            mEditor.putInt("userId",loginBean.getResult().getUserId());
            mEditor.putInt("sex",loginBean.getResult().getSex());
            mEditor.commit();
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

    @Override
    public void error(Object error) {
        String e = (String) error;
        Toast.makeText(this, e, Toast.LENGTH_SHORT).show();
    }
}