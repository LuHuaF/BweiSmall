package com.umeng.soexample.bweismall.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.umeng.soexample.bweismall.R;
import com.umeng.soexample.bweismall.bean.QuanBean;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by android_lhf：2019/1/4
 */
public class QuanAdapter extends RecyclerView.Adapter<QuanAdapter.ViewHolder> implements View.OnClickListener {
    private Context context;
    private List<QuanBean.ResultBean> list;

    public QuanAdapter(Context context, List<QuanBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolder holder = null;
        View view = LayoutInflater.from(context).inflate(R.layout.my_quan_item, parent, false);
        holder = new ViewHolder(view);
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        Glide.with(context).load(list.get(position).getHeadPic()).into(holder.mImageTou);
        holder.mNime.setText(list.get(position).getNickName());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dataString = format.format(list.get(position).getCreateTime());
        holder.mTime.setText(dataString);
        holder.mPing.setText(list.get(position).getContent());
        Glide.with(context).load(list.get(position).getImage()).into(holder.mImage);
        holder.mZan.setText(list.get(position).getGreatNum() + "");
        holder.mCheck.setOnClickListener(this);
        if (list.get(position).getWhetherGreat()==1){
            holder.mCheck.setImageResource(R.mipmap.dianzan11);
        }else {
            /*holder.mCheck.setImageResource(R.mipmap.dianzan);*/
        }
        holder.mCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (setonitem != null){
                    setonitem.setData(holder.mCheck,position);
                    notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public int getItemCount(){
            return list.size();
    }

    @Override
    public void onClick(View view) {

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageTou;
        private TextView mNime;
        private TextView mTime;
        private TextView mPing;
        private ImageView mImage;
        private TextView mZan;
        private ImageView mCheck;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageTou = itemView.findViewById(R.id.Q_Tou);
            mNime = itemView.findViewById(R.id.Q_Nime);
            mTime = itemView.findViewById(R.id.Q_Time);
            mPing = itemView.findViewById(R.id.Q_ping);
            mImage = itemView.findViewById(R.id.Q_Image);
            mZan = itemView.findViewById(R.id.Q_zanshu);
            mCheck = itemView.findViewById(R.id.Q_Check);
        }
    }

    public interface setOnitem{
        void setData(ImageView view,int position);
    }
    private setOnitem setonitem;
    public void setList(setOnitem setonitem){
        this.setonitem=setonitem;
    }
/*

    public interface ItemClick {
        void setOnItem(View v, int position);
    }

    private ItemClick itemClick;

    public void setOnItemClick(ItemClick itemClick) {
        this.itemClick = itemClick;
    }

    @Override
    public void onClick(View v) {
        if (itemClick != null) {
            itemClick.setOnItem(v, (int) v.getTag());
        }
    }
*/
}
