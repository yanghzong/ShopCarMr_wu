package com.umeng.soexample.yuekao_moni1025.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.umeng.soexample.yuekao_moni1025.AddJianView;
import com.umeng.soexample.yuekao_moni1025.R;
import com.umeng.soexample.yuekao_moni1025.bean.Shop;
import com.umeng.soexample.yuekao_moni1025.utils.StringUtils;

import java.util.List;

/**
 * Created by 择木 on 2018/10/25.
 */

public class ShopAdapter  extends RecyclerView.Adapter<ShopAdapter.ViewHolder>{
    private Context context;
    private List<Shop> list;

    public interface OnShopClickListener {
        void onShopClick(int position, boolean isChecked);
    }

    private OnShopClickListener shopClickListener;

    public void setOnShopClickListener(OnShopClickListener listener) {
        this.shopClickListener = listener;
    }

    public interface OnAddJianShopListener {
        void onChange(int position, int num);
    }

    private OnAddJianShopListener shopListener;

    public void setOnAddJianShopListener(OnAddJianShopListener listener) {
        this.shopListener = listener;
    }

    public ShopAdapter(Context context, List<Shop> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = View.inflate(context, R.layout.item_shopcar, null);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Shop shop = list.get(position);
        String images = shop.getImages();
        if (!TextUtils.isEmpty(images)) {
            String[] strings = images.split("\\|");
            if (strings.length > 0) {
                Glide.with(context)
                        .load(StringUtils.https2http(strings[0]))
                        .into(holder.imgShop);
            }
        }

        holder.tvShopName.setText(shop.getTitle());
        holder.tvSinglePrice.setText(String.valueOf(shop.getPrice()));

        holder.advShop.setNum(shop.getNum());
        holder.advShop.setOnAddJianClickListener(new AddJianView.OnAddJianClickListener() {
            @Override
            public void add(int num) {
                shop.setNum(num);

                if (shopListener != null) {
                    shopListener.onChange(position, num);
                }
            }

            @Override
            public void jian(int num) {
                shop.setNum(num);
                if (shopListener != null) {
                    shopListener.onChange(position, num);
                }
            }
        });


        holder.cbShop.setOnCheckedChangeListener(null);
        holder.cbShop.setChecked(shop.isChecked());
        holder.cbShop.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                shop.setChecked(isChecked);
                if (shopClickListener != null) {
                    shopClickListener.onShopClick(position, isChecked);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private CheckBox cbShop;
        private ImageView imgShop;
        private TextView tvShopName;
        private TextView tvSinglePrice;
        private AddJianView advShop;

        public ViewHolder(View itemView) {
            super(itemView);
            cbShop = itemView.findViewById(R.id.cb_shop);
            imgShop = itemView.findViewById(R.id.img_shop);
            tvSinglePrice = itemView.findViewById(R.id.tv_single_price);
            advShop = itemView.findViewById(R.id.adv_shop);
            tvShopName = itemView.findViewById(R.id.tv_shop_name);
        }
    }
}
