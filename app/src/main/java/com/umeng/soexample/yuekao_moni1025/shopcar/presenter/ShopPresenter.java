package com.umeng.soexample.yuekao_moni1025.shopcar.presenter;

import com.google.gson.reflect.TypeToken;
import com.umeng.soexample.yuekao_moni1025.bean.MessageBean;
import com.umeng.soexample.yuekao_moni1025.bean.Shop;
import com.umeng.soexample.yuekao_moni1025.bean.Shopper;
import com.umeng.soexample.yuekao_moni1025.inter.ICallBack;
import com.umeng.soexample.yuekao_moni1025.shopcar.model.ShopModel;
import com.umeng.soexample.yuekao_moni1025.shopcar.view.ShopView;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by 择木 on 2018/10/25.
 */

public class ShopPresenter {
    private ShopView iv;
    private ShopModel model;

    public void attach(ShopView iv) {
        this.iv = iv;
        model = new ShopModel();
    }

    public void getData() {
        String url = "http://www.zhaoapi.cn/product/getCarts?uid=1538";
        Type type = new TypeToken<MessageBean<List<Shopper<List<Shop>>>>>() {
        }.getType();

        model.getData(url, new ICallBack() {
            @Override
            public void success(Object obj) {
                MessageBean<List<Shopper<List<Shop>>>> data = (MessageBean<List<Shopper<List<Shop>>>>) obj;
                iv.success(data);
            }

            @Override
            public void failed(Exception e) {
                iv.failed(e);
            }
        }, type);

    }

    public void detach() {
        if (iv != null) {
            iv = null;
        }
    }
}
