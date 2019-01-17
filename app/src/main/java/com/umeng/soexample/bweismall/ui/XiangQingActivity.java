package com.umeng.soexample.bweismall.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.recker.flybanner.FlyBanner;
import com.umeng.soexample.bweismall.Contacts;
import com.umeng.soexample.bweismall.R;
import com.umeng.soexample.bweismall.bean.PingBean;
import com.umeng.soexample.bweismall.bean.ReXiaoBean;
import com.umeng.soexample.bweismall.bean.RegisterBean;
import com.umeng.soexample.bweismall.bean.ShangBean;
import com.umeng.soexample.bweismall.bean.ShopDataBean;
import com.umeng.soexample.bweismall.presenter.RPresenterImpl;
import com.umeng.soexample.bweismall.view.IView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by android_lhf：2019/1/10
 */
public class XiangQingActivity extends AppCompatActivity implements IView, View.OnClickListener {

    private FlyBanner mGoodsPageBanner;
    private TextView mGoodsPagePrice;
    private TextView mGoodsPageSold;
    private TextView mGoodsPageTitle;
    private TextView mGoodsPageWeight;
    private WebView mGoodsPageImgXiangqing;
    private TextView mGoodsPageJieshao;
    private RecyclerView mDetailsRecviewComments;
    private TextView mDetailsTextviewComments;
    private MyScrollView mDetailsScrollChangecolor;
    private ImageView mDetailsImageReturn;
    private TextView mDetailsTextGoods;
    private TextView mDetailsTextDetails;
    private TextView mDetailsTextComments;
    private RelativeLayout mDetailsRelativeChanger;
    private RelativeLayout mDetailsRelatChangecolor;
    private ImageView mBtnCart;
    private ImageView mBtnBuy;
    private RPresenterImpl presenter;
    private int userId;
    private String sessionId;
    private int commodityId;
    private List<ShopDataBean.ResultBean> goodList = new ArrayList<>();
    private Map<String, String> head = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 隐藏标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 隐藏状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.avtivity_shangxing);
        initView();
        presenter = new RPresenterImpl(this);
        Scrollview();
        isWhatCommonList();
        talkAbout();

    }

    @Override
    public void success(Object data) {

        if (data instanceof ShangBean) {
            ShangBean.ResultBean bean = ((ShangBean) data).getResult();
            String imgStr = bean.getPicture();
            String[] imgStrs = imgStr.split("\\,");
            ArrayList<String> imgList = new ArrayList<>();
            imgList.clear();
            for (int i = 0; i < imgStrs.length; i++) {
                imgList.add(imgStrs[i]);
            }
            mGoodsPageBanner.setImagesUrl(imgList);
            mGoodsPagePrice.setText("￥：" + bean.getPrice() + ".00");
            mGoodsPageSold.setText("已售" + bean.getSaleNum() + "件");
            mGoodsPageTitle.setText(bean.getCommodityName());
            mGoodsPageWeight.setText("重量：" + bean.getWeight() + "KG");

            String s = bean.getDetails().toString().trim();
            String dataStr = "<html>"
                    + "<head>"
                    + "<title>欢迎您</title>"
                    + "</head>"
                    + "<body>"
                    + s
                    + "</body>"
                    + "</html>";
            WebSettings webSettings = mGoodsPageImgXiangqing.getSettings();

            webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
            webSettings.setUseWideViewPort(true);
            webSettings.setLoadWithOverviewMode(true);
            webSettings.setDisplayZoomControls(false);
            webSettings.setJavaScriptEnabled(true);
            webSettings.setAllowFileAccess(true);
            webSettings.setBuiltInZoomControls(true);
            webSettings.setSupportZoom(true);
            mGoodsPageImgXiangqing.setWebViewClient(new WebViewClient());
            mGoodsPageImgXiangqing.loadDataWithBaseURL(null, dataStr, "text/html", "utf-8", null);
            mGoodsPageJieshao.setText(bean.getCategoryName());
            mGoodsPageWeight.setText(bean.getWeight() + "KG");

            Map<String, String> map = new HashMap<>();
            map.put("commodityId", commodityId + "");
            map.put("page", 1 + "");
            map.put("count", 10 + "");
            presenter.startShoushop(Contacts.SHOU_PINLIN, map, null, PingBean.class);
        } else if (data instanceof RegisterBean) {
            Toast.makeText(this, ((RegisterBean) data).getMessage(), Toast.LENGTH_SHORT).show();
        } else if (data instanceof ShopDataBean) {
            Toast.makeText(this, ((ShopDataBean) data).getMessage(), Toast.LENGTH_SHORT).show();
            if (((ShopDataBean) data).getStatus().equals("0000")) {
                List<ShopDataBean.ResultBean> result = ((ShopDataBean) data).getResult();
                Boolean isAdd = true;
                for (int i = 0; i < result.size(); i++) {
                    goodList.add(result.get(i));
                    if (result.get(i).getCommodityId() == commodityId) {
                        isAdd = false;
                        result.get(i).setCount(result.get(i).getCount() + 1);
                    }
                }
                if(isAdd) {
                    ShopDataBean.ResultBean bean = new ShopDataBean.ResultBean(commodityId, 1);
                    goodList.add(bean);
                }
                Map<String, Object> body = new HashMap<>();
                body.put("data", goodList.toString());
                presenter.startRequest(Contacts.JIA_CART, body, head, RegisterBean.class);
            }


        }
    }

    @Override
    public void error(Object error) {

    }

    private void talkAbout() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mDetailsRecviewComments.setLayoutManager(manager);
    }

    private void isWhatCommonList() {
        SharedPreferences shopping = getSharedPreferences("lu", Context.MODE_PRIVATE);
        userId = shopping.getInt("userId", 0);
        sessionId = shopping.getString("sessionId", null);
        Intent intent = getIntent();
        final Parcelable rxxpBean = intent.getBundleExtra("rxxpBean").getParcelable("bean");
        if (rxxpBean instanceof ReXiaoBean.ResultBean.RxxpBean.CommodityListBean) {
            Map<String, String> map = new HashMap<>();
            commodityId = ((ReXiaoBean.ResultBean.RxxpBean.CommodityListBean) rxxpBean).getCommodityId();
            Log.e("commodityId", commodityId + "");
            map.put("commodityId", String.valueOf(commodityId));

            Map<String, String> headMap = new HashMap<>();
            headMap.put("userId", String.valueOf(userId));
            headMap.put("sessionId", sessionId);
            presenter.startShoushop(Contacts.SHOU_XIANGQING, map, headMap, ShangBean.class);

        } else if (rxxpBean instanceof ReXiaoBean.ResultBean.MlssBean.CommodityListBeanXX) {
            Map<String, String> map = new HashMap<>();
            commodityId = ((ReXiaoBean.ResultBean.MlssBean.CommodityListBeanXX) rxxpBean).getCommodityId();
            map.put("commodityId", String.valueOf(commodityId));

            Map<String, String> headMap = new HashMap<>();
            headMap.put("userId", String.valueOf(userId));
            headMap.put("sessionId", sessionId);
            presenter.startShoushop(Contacts.SHOU_XIANGQING, map, headMap, ShangBean.class);
        } else if (rxxpBean instanceof ReXiaoBean.ResultBean.PzshBean.CommodityListBeanX) {
            Map<String, String> map = new HashMap<>();
            commodityId = ((ReXiaoBean.ResultBean.PzshBean.CommodityListBeanX) rxxpBean).getCommodityId();
            map.put("commodityId", String.valueOf(commodityId));
            Map<String, String> headMap = new HashMap<>();
            headMap.put("userId", String.valueOf(userId));
            headMap.put("sessionId", sessionId);
            presenter.startShoushop(Contacts.SHOU_XIANGQING, map, headMap, ShangBean.class);
        }
    }

    private void Scrollview() {
        mDetailsScrollChangecolor.setScrollviewListener(new MyScrollView.ScrollviewListener() {
            @Override
            public void OnScrollChange(MyScrollView scrollView, int l, int t, int oldl, int oldt) {
                if (t <= 0) {
                    mDetailsRelativeChanger.setVisibility(View.GONE);
                    mDetailsRelatChangecolor.setBackgroundColor(Color.argb(0, 0, 0, 0));
                } else if (t > 0 && t < 200) {
                    mDetailsRelativeChanger.setVisibility(View.VISIBLE);
                    float scale = (float) t / 200;
                    float alpha = 255 * scale;
                    mDetailsRelatChangecolor.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
                }
                if (0 < t && t < 700) {
                    mDetailsTextGoods.setBackgroundColor(Color.RED);
                    mDetailsTextDetails.setBackgroundColor(Color.WHITE);
                    mDetailsTextComments.setBackgroundColor(Color.WHITE);
                } else if (701 < t && t < 701 + mGoodsPageImgXiangqing.getHeight()) {
                    mDetailsTextGoods.setBackgroundColor(Color.WHITE);
                    mDetailsTextDetails.setBackgroundColor(Color.RED);
                    mDetailsTextComments.setBackgroundColor(Color.WHITE);
                } else {
                    mDetailsTextGoods.setBackgroundColor(Color.WHITE);
                    mDetailsTextDetails.setBackgroundColor(Color.WHITE);
                    mDetailsTextComments.setBackgroundColor(Color.RED);
                }
            }
        });
    }

    private void initView() {
        mGoodsPageBanner = findViewById(R.id.goods_page_banner);
        mGoodsPagePrice = findViewById(R.id.goods_page_price);
        mGoodsPageSold = findViewById(R.id.goods_page_sold);
        mGoodsPageTitle = findViewById(R.id.goods_page_title);
        mGoodsPageWeight = findViewById(R.id.goods_page_weight);
        mGoodsPageImgXiangqing = findViewById(R.id.goods_page_img_xiangqing);
        mGoodsPageJieshao = findViewById(R.id.goods_page_jieshao);
        mDetailsRecviewComments = findViewById(R.id.details_recview_comments);
        mDetailsTextviewComments = findViewById(R.id.details_textview_comments);
        mDetailsScrollChangecolor = findViewById(R.id.details_scroll_changecolor);
        mDetailsImageReturn = findViewById(R.id.details_image_return);
        mDetailsTextGoods = findViewById(R.id.details_text_goods);
        mDetailsTextDetails = findViewById(R.id.details_text_details);
        mDetailsTextComments = findViewById(R.id.details_text_comments);
        mDetailsRelativeChanger = findViewById(R.id.details_relative_changer);
        mDetailsRelatChangecolor = findViewById(R.id.details_relat_changecolor);
        mBtnCart = findViewById(R.id.Btn_Cart);
        mBtnBuy = findViewById(R.id.btn_buy);
        mBtnCart.setOnClickListener(this);
        mDetailsImageReturn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.Btn_Cart:
                SharedPreferences shopping = getSharedPreferences("lu", Context.MODE_PRIVATE);
                userId = shopping.getInt("userId", 0);
                sessionId = shopping.getString("sessionId", null);
                head.put("userId", String.valueOf(userId));
                head.put("sessionId", sessionId);
                presenter.startShoushop(Contacts.CHA_CART,null,head,ShopDataBean.class);
                break;
        }
    }

}

