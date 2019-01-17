package com.umeng.soexample.bweismall.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.umeng.soexample.bweismall.R;
import com.umeng.soexample.bweismall.bean.ChaAddressBean;

import java.util.List;

/**
 * Created by android_lhf：2019/1/14
 */
public class ChaAdapter extends RecyclerView.Adapter<ChaAdapter.ViewHolder> {
    private List<ChaAddressBean.ResultBean> list;
    private Context context;
    public ChaAdapter(List<ChaAddressBean.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ChaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.addres_item,null);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ChaAdapter.ViewHolder holder, final int position) {
        holder.tv_ads_name.setText(list.get(position).getRealName()+"");
        holder.tv_address.setText(list.get(position).getAddress()+"");
        holder.tv_ads_phone.setText(list.get(position).getPhone()+"");
        if (list.get(position).getWhetherDefault()==1){
            holder.cb_ads_check.setChecked(true);
        }else{
            holder.cb_ads_check.setChecked(false);
        }
        holder.cb_ads_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (setmoren!=null){
                    for (int i = 0; i <list.size() ; i++) {
                        list.get(i).setWhetherDefault(0);
                    }
                    list.get(position).setWhetherDefault(1);
                    notifyDataSetChanged();
                    setmoren.setChecked(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if(list.size()!=0){
            return list.size();
        }else{
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_ads_name;
        private TextView tv_ads_phone;
        private TextView tv_address;
        private CheckBox cb_ads_check;
        private Button btn_ads_update;
        private Button btn_ads_delete;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_ads_name=itemView.findViewById(R.id.tv_ads_name);
            tv_ads_phone=itemView.findViewById(R.id.tv_ads_phone);
            tv_address=itemView.findViewById(R.id.tv_address);
            cb_ads_check=itemView.findViewById(R.id.cb_ads_check);
            btn_ads_update=itemView.findViewById(R.id.btn_ads_update);
            btn_ads_delete=itemView.findViewById(R.id.btn_ads_delete);
        }
    }
    public interface setMoren{
        void setChecked(int position);
    }
    private setMoren setmoren;
    public void setMorenChecked(setMoren setmoren){
        this.setmoren=setmoren;
    }
}
