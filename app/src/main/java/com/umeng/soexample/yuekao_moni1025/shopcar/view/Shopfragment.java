package com.umeng.soexample.yuekao_moni1025.shopcar.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.soexample.yuekao_moni1025.R;
import com.umeng.soexample.yuekao_moni1025.adapter.ShopAdapter;
import com.umeng.soexample.yuekao_moni1025.adapter.ShopperAdapter;
import com.umeng.soexample.yuekao_moni1025.bean.MessageBean;
import com.umeng.soexample.yuekao_moni1025.bean.Shop;
import com.umeng.soexample.yuekao_moni1025.bean.Shopper;
import com.umeng.soexample.yuekao_moni1025.shopcar.presenter.ShopPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 择木 on 2018/10/25.
 */

public class Shopfragment extends Fragment implements ShopView{


    private TextView txtEditFinish;
    private CheckBox cbQuanxuan;
    private TextView tvPrice;
    private Button btnJieSuan;
    private RecyclerView rvShopper;
    private List<Shopper<List<Shop>>> list;
    private ShopPresenter presenter;
    private ShopperAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.item_shop,container,false);
        initView(v);

        return v;
    }

    private void initView(View v) {
        txtEditFinish = v.findViewById(R.id.txt_edit_or_finish);
        cbQuanxuan = v.findViewById(R.id.cb_quanxuan);
        tvPrice = v.findViewById(R.id.tv_zongjia);
        btnJieSuan = v.findViewById(R.id.btn_jiesuan);
        rvShopper = v. findViewById(R.id.rv_shopper);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        list = new ArrayList<>();
        // 商家的列表
        adapter = new ShopperAdapter(getActivity(), list);
        adapter.setOnShopperClickListener(new ShopperAdapter.OnShopperClickListener() {
            @Override
            public void onShopperClick(int position, boolean isCheck) {
                if (!isCheck) {
                    cbQuanxuan.setChecked(false);
                } else {
                    boolean isAllShopperChecked = true;
                    for (Shopper<List<Shop>> listShopper : list) {
                        if (!listShopper.isChecked()) {
                            isAllShopperChecked = false;
                            break;
                        }
                    }
                    cbQuanxuan.setChecked(isAllShopperChecked);
                }

                calculatePrice();
            }
        });


        adapter.setOnAddJianShopListener(new ShopAdapter.OnAddJianShopListener() {
            @Override
            public void onChange(int position, int num) {
                calculatePrice();
            }
        });


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvShopper.setLayoutManager(layoutManager);
        rvShopper.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        rvShopper.setAdapter(adapter);

        presenter = new ShopPresenter();
        presenter.attach(this);
        presenter.getData();
    }
    private void calculatePrice() {
        float totalPrice = 0;
        for (Shopper<List<Shop>> listShopper : list) {
            List<Shop> list = listShopper.getList();
            for (Shop shop : list) {
                if (shop.isChecked()) {
                    totalPrice += shop.getNum() * shop.getPrice();
                }
            }
        }

        tvPrice.setText("总价：" + totalPrice);


    }

    public void success(MessageBean<List<Shopper<List<Shop>>>> data) {
        if (data != null) {
            List<Shopper<List<Shop>>> shoppers = data.getData();
            if (shoppers != null) {
                list.clear();
                list.addAll(shoppers);
                adapter.notifyDataSetChanged();
            }
        }
    }


    public void failed(Exception e) {
        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detach();
        }
    }
}
