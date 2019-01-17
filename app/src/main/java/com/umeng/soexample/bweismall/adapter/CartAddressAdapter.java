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
import com.umeng.soexample.bweismall.bean.ShopDataBean;
import com.umeng.soexample.bweismall.weight.CountView;

import java.util.List;

/**
 * Created by android_lhf：2019/1/15
 */
public class CartAddressAdapter extends RecyclerView.Adapter<CartAddressAdapter.ViewHolder> {

    private List<ShopDataBean.ResultBean> list;
    private Context context;
    public CartAddressAdapter(List<ShopDataBean.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }
    @NonNull
    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.cart_cha,null);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Glide.with(context).load(list.get(position).getPic()).into(holder.frag_shopping_shopCar_item_img);
        holder.frag_shopping_shopCar_item_name.setText(list.get(position).getCommodityName());
        holder.frag_shopping_shopCar_item_price.setText(list.get(position).getPrice()+"");
        holder.jiajian_View.setNumber(list.get(position).getCount());
        holder.jiajian_View.setOnChangeNumber(new CountView.OnChangeNumber() {
            @Override
            public void getNumber(int number) {
                list.get(position).setCount(number);
                if (setjia!=null){
                    setjia.setData();
                }
                notifyDataSetChanged();
            }
        });
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
    public interface setJia{
        void setData();
    }
    private setJia setjia;
    public void setJiaJIan(setJia setjia){
        this.setjia=setjia;
    }
}
