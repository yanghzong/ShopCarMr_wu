package com.umeng.soexample.yuekao_moni1025.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.umeng.soexample.yuekao_moni1025.R;
import com.umeng.soexample.yuekao_moni1025.bean.FenLei;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 择木 on 2018/10/25.
 */

public class FenRightAdapter  extends RecyclerView.Adapter<FenRightAdapter.ViewHolder>{
    private Context context;
    private List<FenLei.ListBean> list=new ArrayList<>();
    private ViewHolder holder;

    public FenRightAdapter(Context context) {
        this.context = context;

    }
    public void  setList( List<FenLei.ListBean> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FenRightAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=View.inflate(context, R.layout.item_right,null);
        holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull FenRightAdapter.ViewHolder holder, int position) {
        holder.tvRightName.setText(list.get(position).getName());
        Glide.with(context).load(list.get(position).getIcon()).into(holder.imgRight);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvRightName;
        private final ImageView imgRight;

        public ViewHolder(View itemView) {
            super(itemView);
            imgRight = itemView.findViewById(R.id.img_right);
            tvRightName = itemView.findViewById(R.id.tv_right_name);
        }
    }
}
