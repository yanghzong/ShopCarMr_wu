package com.umeng.shopcar_oneone_twotwo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ExpandableListView elShow;
    private CheckBox cbAll;
    private TextView tvAllprice;
    String url = "http://www.zhaoapi.cn/product/getCarts?uid=71";
    private MyAdapter myAdapter;
    private List<ShopBean.DataBean> sellerData;
    private Button btnAddNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();


    }

    private void initView() {
        elShow = findViewById(R.id.el_show);
        cbAll = findViewById(R.id.cb_all);
        tvAllprice = findViewById(R.id.tv_allprice);
        btnAddNum = findViewById(R.id.btn_allnum);
        cbAll.setOnClickListener(this);
    }



    private void initData() {


        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(final Call call, Response response) throws IOException {
                final String string = response.body().string();
                runOnUiThread(new Runnable() {



                    @Override
                    public void run() {
                        Gson gson = new Gson();
                        ShopBean cartInfo = gson.fromJson(string, ShopBean.class);
                        sellerData = cartInfo.getData();

                        myAdapter = new MyAdapter(sellerData,MainActivity.this);
                        myAdapter.setOnCartListChangeListener(new MyAdapter.OnCartListChangeListener() {

                            @Override
                            public void SellerSelectedChange(int groupPosition) {
                                //先得到 checkbox的状态
                                boolean b = myAdapter.isCurrentSellerAllProductSelected(groupPosition);
                                myAdapter.changeCurrentSellerAllProductSelected(groupPosition,!b);
                                myAdapter.notifyDataSetChanged();
                                refreshAllSelectedAndTotalPriceAndTotalNumber();

                            }

                            @Override
                            public void changeCurrentProductSelected(int groupPosition, int childPosition) {
                                myAdapter.changeCurrentProductSelected(groupPosition,childPosition);
                                myAdapter.notifyDataSetChanged();
                                refreshAllSelectedAndTotalPriceAndTotalNumber();
                            }

                            @Override
                            public void ProductNumberChange(int groupPosition, int childPosition, int number) {
                                myAdapter.changeCurrentProductNumber(groupPosition,childPosition,number);
                                myAdapter.notifyDataSetChanged();
                                refreshAllSelectedAndTotalPriceAndTotalNumber();
                            }
                        });

                        elShow.setAdapter(myAdapter);
//
                        for (int i = 0; i < sellerData.size() ; i++) {
                            elShow.expandGroup(i);

                        }
                    }
                });
            }
        });

    }


    private void  refreshAllSelectedAndTotalPriceAndTotalNumber(){

        boolean allProductsSelected = myAdapter.isAllProductsSelected();
        cbAll.setChecked(allProductsSelected);
        Double totalPrice = myAdapter.calculateTotalPrice();
        tvAllprice.setText("总价：￥"+totalPrice);
        int totalNumber = myAdapter.calculateTotalNumber();
        btnAddNum.setText("去结算("+totalNumber+")");
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.cb_all:
                boolean allProductsSelected = myAdapter.isAllProductsSelected();
                myAdapter.changeAllProductsSelected(!allProductsSelected);
                myAdapter.notifyDataSetChanged();
                //刷新底部的方法
                refreshAllSelectedAndTotalPriceAndTotalNumber();
                break;

        }
    }

}
