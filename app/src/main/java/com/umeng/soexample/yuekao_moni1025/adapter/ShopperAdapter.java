package com.umeng.soexample.yuekao_moni1025.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.umeng.soexample.yuekao_moni1025.R;
import com.umeng.soexample.yuekao_moni1025.bean.Shop;
import com.umeng.soexample.yuekao_moni1025.bean.Shopper;

import java.util.List;

/**
 * Created by 择木 on 2018/10/25.
 */

public class ShopperAdapter  extends RecyclerView.Adapter<ShopperAdapter.ViewHolder>{
    private Context context;
    private List<Shopper<List<Shop>>> list;

    public ShopperAdapter(Context context, List<Shopper<List<Shop>>> list) {
        this.context = context;
        this.list = list;
    }

    public interface OnShopperClickListener {
        void onShopperClick(int position, boolean isCheck);
    }

    private OnShopperClickListener shopperClickListener;

    public void setOnShopperClickListener(OnShopperClickListener listener) {
        this.shopperClickListener = listener;
    }


    private ShopAdapter.OnAddJianShopListener shopListener;

    public void setOnAddJianShopListener(ShopAdapter.OnAddJianShopListener listener) {
        this.shopListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = View.inflate(context, R.layout.item_shopper, null);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Shopper<List<Shop>> shopper = list.get(position);
        holder.tvShopperName.setText(shopper.getSellerName());

        RecyclerView.LayoutManager pLayoutManager = new LinearLayoutManager(context);
        holder.rvShop.setLayoutManager(pLayoutManager);
        final ShopAdapter adapter = new ShopAdapter(context, shopper.getList());
        if (shopListener != null) {
            adapter.setOnAddJianShopListener(shopListener);
        }
        adapter.setOnShopClickListener(new ShopAdapter.OnShopClickListener() {
            @Override
            public void onShopClick(int position, boolean isChecked) {
                if (!isChecked) {
                    shopper.setChecked(false);
                    shopperClickListener.onShopperClick(position, false);
                } else {
                    boolean isAllShopSelected = true;
                    for (Shop shop : shopper.getList()) {
                        if (!shop.isChecked()) {
                            isAllShopSelected = false;
                            break;
                        }
                    }
                    shopper.setChecked(isAllShopSelected);
                    shopperClickListener.onShopperClick(position, true);
                }
                notifyDataSetChanged();
                shopListener.onChange(0, 0);
            }
        });

        holder.rvShop.setAdapter(adapter);

        holder.cbShopper.setOnCheckedChangeListener(null);

        holder.cbShopper.setChecked(shopper.isChecked());

        holder.cbShopper.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                shopper.setChecked(isChecked);

                List<Shop> shopList = shopper.getList();
                for (Shop shop : shopList) {
                    shop.setChecked(isChecked);
                }
                adapter.notifyDataSetChanged();
//                }
                if (shopperClickListener != null) {
                    shopperClickListener.onShopperClick(position, isChecked);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final RecyclerView rvShop;
        private final TextView tvShopperName;
        private final CheckBox cbShopper;

        public ViewHolder(View itemView) {
            super(itemView);
            cbShopper = itemView.findViewById(R.id.cb_shopper);
            tvShopperName = itemView.findViewById(R.id.tv_shopper_name);
            rvShop = itemView.findViewById(R.id.rv_shop);
        }
    }
}
