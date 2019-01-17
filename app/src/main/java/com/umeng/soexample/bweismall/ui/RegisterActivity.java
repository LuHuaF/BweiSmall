package com.umeng.soexample.bweismall.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.soexample.bweismall.MainActivity;
import com.umeng.soexample.bweismall.R;
import com.umeng.soexample.bweismall.bean.RegisterBean;
import com.umeng.soexample.bweismall.presenter.PresenterImpl;
import com.umeng.soexample.bweismall.view.IView;
import com.xw.repo.XEditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Created by android_lhf：2018/12/29
 */
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener ,IView {
    private XEditText mEtNa;
    private XEditText mEtPass;
    private TextView mTexYiyou;
    private Button mBtnRegister;
    private PresenterImpl presenter;
    private String mUrl = "http://172.17.8.100/small/user/v1/register";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 隐藏标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 隐藏状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_regist);
        initView();
        presenter = new PresenterImpl(this);
    }

    private void initView() {
        mEtNa = (XEditText) findViewById(R.id.et_Na);
        mEtPass = (XEditText) findViewById(R.id.et_pass);
        mTexYiyou = (TextView) findViewById(R.id.Tex_Yiyou);
        mBtnRegister = (Button) findViewById(R.id.btn_register);
        mTexYiyou.setOnClickListener(this);
        mBtnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.et_Na:

                break;
            case R.id.btn_register:
                String phone = mEtNa.getText().toString().trim();
                String pwd = mEtPass.getText().toString().trim();
                if (isChinaPhoneLegal(mEtNa.getText().toString())){
                }else {
                    mEtNa.setText("请输入正确的手机号");
                }
                if (validatePhonePass(mEtPass.getText().toString())){
                    if (phone.isEmpty() || pwd.isEmpty()) {
                        Toast.makeText(this, "账号和密码都不能为空", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        presenter.startRequest(mUrl,phone,pwd);
                    }
                }else {
                    Toast.makeText(this,"密码：字符+数字，请重新输入，不少于六位数",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.Tex_Yiyou:
                    startActivity(new Intent(this, LoginActivity.class));
                    finish();
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
    public static boolean validatePhonePass(String pass) {
        String passRegex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$";
        return !TextUtils.isEmpty(pass) && pass.matches(passRegex);
    }

    @Override
    public void success(Object data) {
        RegisterBean registerBean = (RegisterBean) data;
        if (registerBean.getStatus().equals("1001")) {
            Toast.makeText(this, registerBean.getMessage(), Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(this, registerBean.getMessage(), Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    public void error(Object error) {
        Toast.makeText(this, error.toString() + "失败", Toast.LENGTH_SHORT).show();
    }
}
