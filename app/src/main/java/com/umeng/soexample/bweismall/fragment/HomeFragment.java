package com.umeng.soexample.bweismall.fragment;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;
import com.umeng.soexample.bweismall.MainActivity;
import com.umeng.soexample.bweismall.R;
import com.umeng.soexample.bweismall.adapter.HomePageAdapter;
import com.umeng.soexample.bweismall.adapter.MoLiAdapter;
import com.umeng.soexample.bweismall.adapter.PinZhiAdapter;
import com.umeng.soexample.bweismall.adapter.ReXiaoAdapter;
import com.umeng.soexample.bweismall.base.BaseFragment;
import com.umeng.soexample.bweismall.bean.BannerBean;
import com.umeng.soexample.bweismall.bean.KeyWordBean;
import com.umeng.soexample.bweismall.bean.ReXiaoBean;
import com.umeng.soexample.bweismall.presenter.PresenterImpl;
import com.umeng.soexample.bweismall.ui.SouActivity;
import com.umeng.soexample.bweismall.ui.XiangQingActivity;
import com.umeng.soexample.bweismall.view.IView;
import com.xw.repo.XEditText;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by android_lhf：2019/1/2
 */
public class HomeFragment extends BaseFragment implements IView, View.OnClickListener {
    private ReXiaoAdapter reXiaoAdapter;
    private MoLiAdapter moLiAdapter;
    private PinZhiAdapter pinZhiAdapter;
    private List<ReXiaoBean.ResultBean.PzshBean.CommodityListBeanX> list2 = new ArrayList<>();
    private List<ReXiaoBean.ResultBean.RxxpBean.CommodityListBean> list = new ArrayList<>();
    private List<ReXiaoBean.ResultBean.MlssBean.CommodityListBeanXX> list1 = new ArrayList<>();
    private List<BannerBean.ResultBean> list3 = new ArrayList<>();
    private ArrayList<String> datas = new ArrayList<>();
    private List<KeyWordBean.ResultBean> list4 = new ArrayList<>();
    private RecyclerView recyReXiao;
    private RecyclerView recyMoLi;
    private RecyclerView recyPinZhi;
    private PresenterImpl presenter;
    private Bundle bundle;
    private XBanner mXbanner;
    private ImageButton mSerch;
    private EditText mEditSou;
    private String mUrl = "http://172.17.8.100/small/commodity/v1/commodityList";
    private String url = "http://172.17.8.100/small/commodity/v1/bannerShow";
    private String KryUrl = "http://172.17.8.100/small/commodity/v1/findCommodityByKeyword";
    private int page = 1;
    private int count = 10;

    @Override
    protected void initData() {
        recyReXiao = getActivity().findViewById(R.id.myhome_home_rexiao_list);
        recyMoLi = getActivity().findViewById(R.id.myhome_home_moli_list);
        recyPinZhi = getActivity().findViewById(R.id.myhome_home_pinzhi_list);
        mSerch = getActivity().findViewById(R.id.myhome_serch);
        mEditSou = getActivity().findViewById(R.id.Edit_Sou);
        mXbanner = getActivity().findViewById(R.id.xbanner);
        reXiaoAdapter = new ReXiaoAdapter(list, getActivity());
        moLiAdapter = new MoLiAdapter(list1, getActivity());
        pinZhiAdapter = new PinZhiAdapter(list2, getActivity());
        recyReXiao.setAdapter(reXiaoAdapter);
        recyMoLi.setAdapter(moLiAdapter);
        recyPinZhi.setAdapter(pinZhiAdapter);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyPinZhi.setLayoutManager(staggeredGridLayoutManager);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyMoLi.setLayoutManager(layoutManager);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        recyReXiao.setLayoutManager(gridLayoutManager);
        presenter = new PresenterImpl(this);
        presenter.startRequest(mUrl, null, null);
        //轮播图
        presenter.startRequest(url, null, null);
        mSerch.setOnClickListener(this);
        bundle = new Bundle();
        reXiaoAdapter.setClick(new ReXiaoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position , int id) {
                ReXiaoBean.ResultBean.RxxpBean.CommodityListBean bean = new ReXiaoBean.ResultBean.RxxpBean.CommodityListBean();
                bean.setSaleNum(list.get(position).getSaleNum());
                bean.setPrice(list.get(position).getPrice());
                bean.setCommodityId(list.get(position).getCommodityId());
                bean.setCommodityName(list.get(position).getCommodityName());
                bean.setMasterPic(list.get(position).getMasterPic());
                bundle.putParcelable("bean",bean);
                Intent intent = new Intent(getActivity(), XiangQingActivity.class);
                intent.putExtra("rxxpBean",bundle);
                startActivity(intent);
            }
        });
        moLiAdapter.setClick(new MoLiAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position ,int id) {
                ReXiaoBean.ResultBean.MlssBean.CommodityListBeanXX bean = new ReXiaoBean.ResultBean.MlssBean.CommodityListBeanXX();
                bean.setSaleNum(list1.get(position).getSaleNum());
                bean.setPrice(list1.get(position).getPrice());
                bean.setCommodityId(list1.get(position).getCommodityId());
                bean.setCommodityName(list1.get(position).getCommodityName());
                bean.setMasterPic(list1.get(position).getMasterPic());
                bundle.putParcelable("bean",bean);
                Intent intent = new Intent(getActivity(), XiangQingActivity.class);
                intent.putExtra("rxxpBean",bundle);
                startActivity(intent);
            }
        });
        pinZhiAdapter.setClick(new PinZhiAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position, int id) {
                ReXiaoBean.ResultBean.PzshBean.CommodityListBeanX bean = new ReXiaoBean.ResultBean.PzshBean.CommodityListBeanX();
                bean.setSaleNum(list2.get(position).getSaleNum());
                bean.setPrice(list2.get(position).getPrice());
                bean.setCommodityId(list2.get(position).getCommodityId());
                bean.setCommodityName(list2.get(position).getCommodityName());
                bean.setMasterPic(list2.get(position).getMasterPic());
                bundle.putParcelable("bean",bean);
                Intent intent = new Intent(getActivity(), XiangQingActivity.class);
                intent.putExtra("rxxpBean",bundle);
                startActivity(intent);
            }

        });

    }
    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void success(Object data) {

        if (data instanceof ReXiaoBean) {
            ReXiaoBean reXiaoBean = (ReXiaoBean) data;
            list.addAll(reXiaoBean.getResult().getRxxp().get(0).getCommodityList());
            list1.addAll(reXiaoBean.getResult().getMlss().get(0).getCommodityList());
            list2.addAll(reXiaoBean.getResult().getPzsh().get(0).getCommodityList());
            reXiaoAdapter.notifyDataSetChanged();
            moLiAdapter.notifyDataSetChanged();
        }
        if (data instanceof BannerBean) {
            //轮播图
            final BannerBean bannerBean = (BannerBean) data;
            list3.addAll(bannerBean.getResult());
            for (int i = 0; i < list3.size(); i++) {
                datas.add(list3.get(i).getImageUrl());
            }
            if (!datas.isEmpty()) {
                mXbanner.setData(bannerBean.getResult(), null);
                mXbanner.loadImage(new XBanner.XBannerAdapter() {
                    @Override
                    public void loadBanner(XBanner banner, Object model, View view, int position) {
                        Glide.with(getActivity()).load(bannerBean.getResult().get(position).getImageUrl()).into((ImageView) view);
                    }
                });
                mXbanner.setPageChangeDuration(1000);
                mXbanner.setPageTransformer(Transformer.Default);
            }
        }
        if (data instanceof KeyWordBean){
            KeyWordBean keyWordBean = (KeyWordBean) data;
            if (keyWordBean.getMessage().equals("1001")) {
                Toast.makeText(getActivity(), "请输入你要查询的内容", Toast.LENGTH_SHORT);
            }else {
                String sou = mEditSou.getText().toString().trim();
                presenter.startRequestKey(KryUrl,sou,page,count);
            }
        }

    }

    @Override
    public void error(Object error) {

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.myhome_serch:
                if (mEditSou.getText().toString().trim().equals("")){
                    Toast.makeText(getActivity(),"请输入你要查询的东西",Toast.LENGTH_SHORT).show();
                }else {
                    String sou = mEditSou.getText().toString().trim();
                    Intent intent = new Intent(getActivity(), SouActivity.class);
                    intent.putExtra("key", sou);
                    startActivity(intent);
                }
                break;
        }
    }
}
