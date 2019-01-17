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
import com.umeng.soexample.bweismall.bean.ReXiaoBean;
import java.util.List;

/**
 * Created by android_lhf：2019/1/3
 */
public class PinZhiAdapter extends RecyclerView.Adapter<PinZhiAdapter.ViewHolder> {
    private List<ReXiaoBean.ResultBean.PzshBean.CommodityListBeanX> list;
    private Context context;

    public PinZhiAdapter(List<ReXiaoBean.ResultBean.PzshBean.CommodityListBeanX> list, Context context) {
        this.list = list;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolder holder = null;
        View view = LayoutInflater.from(context).inflate(R.layout.my_pinzhi_item,parent,false);
        holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Glide.with(context).load(list.get(position).getMasterPic()).into(holder.mImage);
        holder.mJs.setText(list.get(position).getCommodityName());
        holder.mPrice.setText("￥"+list.get(position).getPrice()+".00");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null){
                    listener.onItemClick(view,position,list.get(position).getCommodityId());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImage;
        private TextView mJs,mPrice;
        public ViewHolder(View itemView) {
            super(itemView);
            mImage = itemView.findViewById(R.id.Pin_Image);
            mJs = itemView.findViewById(R.id.Pin_Js);
            mPrice = itemView.findViewById(R.id.Pin_Jg);
        }
    }
    public OnItemClickListener listener;
    public interface OnItemClickListener{
        void onItemClick(View v,int positon ,int id);
    }

    public void setClick(OnItemClickListener listener){
        this.listener = listener;
    }
}
