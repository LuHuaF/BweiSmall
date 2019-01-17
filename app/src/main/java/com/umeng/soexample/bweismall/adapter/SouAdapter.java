package com.umeng.soexample.bweismall.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.umeng.soexample.bweismall.R;
import com.umeng.soexample.bweismall.bean.KeyWordBean;

import java.util.List;

/**
 * Created by android_lhf：2019/1/4
 */
public class SouAdapter extends RecyclerView.Adapter<SouAdapter.ViewHolder> {
    private List<KeyWordBean.ResultBean> list;
    private Context context;

    public SouAdapter(List<KeyWordBean.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolder holder = null;
        View view = LayoutInflater.from(context).inflate(R.layout.my_sou_item,parent,false);
        holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getMasterPic()).into(holder.mImage);
        holder.mJs.setText(list.get(position).getCommodityName());
        holder.mPrice.setText("￥"+list.get(position).getPrice()+".00");
        holder.mYs.setText("已售"+list.get(position).getSaleNum()+"件");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImage;
        private TextView mJs,mPrice,mYs;
        public ViewHolder(View itemView) {
            super(itemView);
            mImage = itemView.findViewById(R.id.S_Image);
            mJs = itemView.findViewById(R.id.S_Js);
            mPrice = itemView.findViewById(R.id.S_Price);
            mYs = itemView.findViewById(R.id.S_Ys);
        }
    }
}
