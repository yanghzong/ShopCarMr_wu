package com.umeng.soexample.yuekao_moni1025.home.presenter;


import com.google.gson.reflect.TypeToken;
import com.umeng.soexample.yuekao_moni1025.bean.Banner;
import com.umeng.soexample.yuekao_moni1025.bean.MessageBean;
import com.umeng.soexample.yuekao_moni1025.home.model.HomeModel;
import com.umeng.soexample.yuekao_moni1025.home.view.IView;
import com.umeng.soexample.yuekao_moni1025.inter.ICallBack;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by 择木 on 2018/10/25.
 */

public class HomePresenter {
    private IView iv;
    private HomeModel model;

    public void attach(IView iv){
        this.iv=iv;
        model=new HomeModel();
    }

    public void getBanner(){
        String url="http://www.zhaoapi.cn/ad/getAd";

        Type type=new TypeToken<MessageBean<List<Banner>>>(){}.getType();

        model.getData(url, new ICallBack() {
            @Override
            public void success(Object obj) {
                MessageBean<List<Banner>> data= (MessageBean<List<Banner>>) obj;
                iv.getBanner(data);
            }

            @Override
            public void failed(Exception e) {
                iv.failed(e);
            }
        },type);
    }


    public void detach() {
        if (iv!=null){
            iv=null;
        }
    }
}
