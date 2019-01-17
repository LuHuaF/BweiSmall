package com.umeng.soexample.bweismall.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.umeng.soexample.bweismall.R;
import com.umeng.soexample.bweismall.bean.DingdanQueryBean;
import com.umeng.soexample.bweismall.weight.CountView;

import java.util.List;

public class DingdanShangpinAdapter extends RecyclerView.Adapter<DingdanShangpinAdapter.ViewHolder> {
    private List<DingdanQueryBean.OrderListBean.DetailListBean> list;
    private Context context;


    public DingdanShangpinAdapter(List<DingdanQueryBean.OrderListBean.DetailListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.gouwu_address_cha, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String commodityPic = list.get(position).getCommodityPic();
        String[] split = commodityPic.split("\\,");
        Glide.with(context).load(split[0]).into(holder.frag_shopping_shopCar_item_img);
        holder.frag_shopping_shopCar_item_name.setText(list.get(position).getCommodityName());
        holder.frag_shopping_shopCar_item_price.setText(list.get(position).getCommodityPrice()+"");
        holder.jiajian_View.setNumber(list.get(position).getCommodityCount());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView frag_shopping_shopCar_item_img;
        private TextView frag_shopping_shopCar_item_name;
        private TextView frag_shopping_shopCar_item_price;
        private CountView jiajian_View;
        public ViewHolder(View itemView) {
            super(itemView);
            frag_shopping_shopCar_item_img=itemView.findViewById(R.id.frag_shopping_shopCar_item_img);
            frag_shopping_shopCar_item_name=itemView.findViewById(R.id.frag_shopping_shopCar_item_name);
            frag_shopping_shopCar_item_price=itemView.findViewById(R.id.frag_shopping_shopCar_item_price);
            jiajian_View=itemView.findViewById(R.id.jiajian_View);
        }
    }
}
