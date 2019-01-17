package com.umeng.soexample.bweismall.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.umeng.soexample.bweismall.R;
import com.umeng.soexample.bweismall.bean.ShopDataBean;
import com.umeng.soexample.bweismall.weight.CountView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by android_lhf：2019/1/11
 */
public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {


    private List<ShopDataBean.ResultBean> data;
    private Context context;

    public CartAdapter(List<ShopDataBean.ResultBean> data, Context context) {
        this.data = data;
        this.context = context;
    }

    public void setData(List<ShopDataBean.ResultBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.cart_item, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.itemView.setTag(i);
        viewHolder.item_title.setText(data.get(i).getCommodityName());
        viewHolder.item_price.setText("￥" + data.get(i).getPrice());
        Glide.with(context).load(data.get(i).getPic()).into(viewHolder.item_image);
        viewHolder.item_check.setChecked(data.get(i).isChildCheck());

        viewHolder.item_count.setNumber(data.get(i).getCount());

        viewHolder.item_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClick != null) {
                    onItemClick.onClick(view, i);
                }
            }
        });
        viewHolder.item_count.setOnChangeNumber(new CountView.OnChangeNumber() {
            @Override
            public void getNumber(int number) {
                if (onItemClick != null) {
                    onItemClick.onNumber(i, number);
                }
            }
        });
        viewHolder.item_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClick != null) {
                    onItemClick.onDelete(view, i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setCheckStatus(int position, boolean status) {
        data.get(position).setChildCheck(status);
    }

    public boolean thisCheckStatus(int position) {
        boolean childCheck = data.get(position).isChildCheck();
        if (childCheck) {
            return true;
        } else {
            return false;
        }
    }

    public boolean thisShopStatus() {
        for (int i = 0; i < data.size(); i++) {
            boolean childCheck = data.get(i).isChildCheck();
            if (childCheck) {
                return true;
            }
        }
        return false;
    }

    //所有的checkbox的状态
    public boolean allCheckStatus() {
        for (int i = 0; i < data.size(); i++) {
            boolean childCheck = data.get(i).isChildCheck();
            if (!childCheck) {
                return false;
            }
        }
        return true;
    }

    public void setAllCheckStatus(boolean status) {
        for (int i = 0; i < data.size(); i++) {
            data.get(i).setChildCheck(status);
        }
    }

    public double getAllPrice() {
        int mprice = 0;
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).isChildCheck()) {
                mprice += data.get(i).getPrice() * data.get(i).getCount();
            }
        }
        return mprice;
    }

    public int getAllCount() {
        int mcount = 0;
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).isChildCheck()) {
                mcount += data.get(i).getCount();
            }
        }
        return mcount;
    }

    public void setShopCount(int position, int number) {
        data.get(position).setCount(number);
    }

    public interface OnItemClick {
        void onClick(View view, int position);

        void onDelete(View view, int position);

        void onNumber(int position, int number);
    }

    private OnItemClick onItemClick;

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView item_image;
        TextView item_title;
        TextView item_price;
        CheckBox item_check;
        Button item_delete;
        CountView item_count;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_image = itemView.findViewById(R.id.item_image);
            item_title = itemView.findViewById(R.id.item_title);
            item_price = itemView.findViewById(R.id.item_price);
            item_check = itemView.findViewById(R.id.item_check);
            item_delete = itemView.findViewById(R.id.item_delete);
            item_count = itemView.findViewById(R.id.item_count);
        }
    }
}
