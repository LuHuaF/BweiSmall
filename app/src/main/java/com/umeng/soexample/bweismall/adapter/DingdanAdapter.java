package com.umeng.soexample.bweismall.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.umeng.soexample.bweismall.R;
import com.umeng.soexample.bweismall.bean.DingdanQueryBean;

import java.util.List;

public class DingdanAdapter extends RecyclerView.Adapter<DingdanAdapter.ViewHolder> {
    private List<DingdanQueryBean.OrderListBean> list;
    private Context context;
    private DingdanShangpinAdapter adapter;
    private float a=0;


    public DingdanAdapter(List<DingdanQueryBean.OrderListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.dingdan_recy, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.dingdanhao.setText(list.get(position).getOrderId());
        holder.dingdan_recy.setLayoutManager(new LinearLayoutManager(context));
        List<DingdanQueryBean.OrderListBean.DetailListBean> detailList = list.get(position).getDetailList();
        for (int i = 0; i <detailList.size() ; i++) {
            a+=detailList.get(i).getCommodityPrice();
        }
        holder.dingdanxinxi.setText("共"+detailList.size()+"件商品,需付款"+a+"元");
        adapter = new DingdanShangpinAdapter(detailList,context);
        holder.dingdan_recy.setAdapter(adapter);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView dingdanhao;
        private TextView shijian;
        private RecyclerView dingdan_recy;
        private TextView dingdanxinxi;
        private Button quxiao;
        private Button zhifu;
        public ViewHolder(View itemView) {
            super(itemView);
            dingdanhao=itemView.findViewById(R.id.dingdanhao);
            shijian=itemView.findViewById(R.id.shijian);
            dingdanxinxi=itemView.findViewById(R.id.dingdanxinxi);
            quxiao=itemView.findViewById(R.id.quxiao);
            zhifu=itemView.findViewById(R.id.zhifu);
            dingdan_recy=itemView.findViewById(R.id.dingdan_recy);
        }
    }
}
