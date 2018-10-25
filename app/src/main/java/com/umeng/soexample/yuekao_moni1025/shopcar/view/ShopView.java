package com.umeng.soexample.yuekao_moni1025.shopcar.view;

import com.umeng.soexample.yuekao_moni1025.bean.MessageBean;
import com.umeng.soexample.yuekao_moni1025.bean.Shop;
import com.umeng.soexample.yuekao_moni1025.bean.Shopper;

import java.util.List;

/**
 * Created by 择木 on 2018/10/25.
 */

public interface ShopView {
    void success(MessageBean<List<Shopper<List<Shop>>>> data);

    void failed(Exception e);
}
