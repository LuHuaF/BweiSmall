package com.umeng.soexample.bweismall;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.umeng.soexample.bweismall.fragment.CartFragment;
import com.umeng.soexample.bweismall.fragment.CircleFragment;
import com.umeng.soexample.bweismall.fragment.HomeFragment;
import com.umeng.soexample.bweismall.fragment.MyFragment;
import com.umeng.soexample.bweismall.fragment.OrderFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private ImageView mImage1;
    private ImageView mImage2;
    private ImageView mImage3;
    private ImageView mImage4;
    private ImageView mImage5;
    private ViewPager mViewPage;
    private List<Fragment> list;
    private HomeFragment homeFragment = new HomeFragment();
    private CircleFragment circleFragment = new CircleFragment();
    private CartFragment cartFragment = new CartFragment();
    private OrderFragment orderFragment = new OrderFragment();
    private MyFragment myFragment = new MyFragment();
    private int page = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 隐藏标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 隐藏状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mImage1 = findViewById(R.id.image1);
        mImage1.setOnClickListener(this);
        mImage2 = findViewById(R.id.image2);
        mImage2.setOnClickListener(this);
        mImage3 = findViewById(R.id.image3);
        mImage3.setOnClickListener(this);
        mImage4 = findViewById(R.id.image4);
        mImage4.setOnClickListener(this);
        mImage5 = findViewById(R.id.image5);
        mImage5.setOnClickListener(this);
        mViewPage = findViewById(R.id.viewpage);
        list = new ArrayList<>();
        list.add(homeFragment);
        list.add(circleFragment);
        list.add(cartFragment);
        list.add(orderFragment);
        list.add(myFragment);
        mViewPage.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
        getData(page);
        mViewPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                getData(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
//        mViewPage.offsetLeftAndRight(list.size());
        mViewPage.setOffscreenPageLimit(list.size()); //预加载

    }




    private void getData(int page) {
        mViewPage.setCurrentItem(page);

        if (page ==0){
            mImage1.setImageDrawable(getResources().getDrawable(R.mipmap.dibu11));
        }else{
            mImage1.setImageDrawable(getResources().getDrawable(R.mipmap.dibu1));
        }
        if (page == 1){
            mImage2.setImageDrawable(getResources().getDrawable(R.mipmap.dibu22));
        }else{
            mImage2.setImageDrawable(getResources().getDrawable(R.mipmap.dibu2));
        }
        if (page == 3){
            mImage4.setImageDrawable(getResources().getDrawable(R.mipmap.dibu44));
        }else{
            mImage4.setImageDrawable(getResources().getDrawable(R.mipmap.dibu4));
        }
        if (page == 4){
            mImage5.setImageDrawable(getResources().getDrawable(R.mipmap.dibu55));
        }else{
            mImage5.setImageDrawable(getResources().getDrawable(R.mipmap.dibu5));
        }


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image1:
                page = 0;
                break;
            case R.id.image2:
                page = 1;
                break;
            case R.id.image3:
                page = 2;
                break;
            case R.id.image4:
                page = 3;
                break;
            case R.id.image5:
                page = 4;
                break;
        }
        getData(page);
    }
}
