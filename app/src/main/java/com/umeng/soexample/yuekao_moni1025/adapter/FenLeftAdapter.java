package com.umeng.soexample.yuekao_moni1025.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.umeng.soexample.yuekao_moni1025.R;
import com.umeng.soexample.yuekao_moni1025.bean.FenLei;

import java.util.List;

/**
 * Created by 择木 on 2018/10/25.
 */
public class FenLeftAdapter  extends RecyclerView.Adapter<FenLeftAdapter.ViewHolder>{
    private Context context;
    private List<FenLei> list;
    private ViewHolder holder;

    public FenLeftAdapter(Context context, List<FenLei> list) {
        this.context = context;
        this.list = list;
    }
    public interface OnClickListener {
        void onClick(List<FenLei.ListBean> list);
    }

    private OnClickListener listener;

    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public FenLeftAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=View.inflate(context, R.layout.item_left,null);
        holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull FenLeftAdapter.ViewHolder holder, final int position) {

        holder.tvLeftName.setText(list.get(position).getName());
        holder.tvLeftName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<FenLei.ListBean> list1 = list.get(position).getList();
                listener.onClick(list1);

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvLeftName;

        public ViewHolder(View itemView) {
            super(itemView);
            tvLeftName = itemView.findViewById(R.id.tv_left_name);
        }
    }
}
