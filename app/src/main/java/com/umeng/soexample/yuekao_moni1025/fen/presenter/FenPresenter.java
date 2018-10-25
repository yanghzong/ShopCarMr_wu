package com.umeng.soexample.yuekao_moni1025.fen.presenter;

import com.google.gson.reflect.TypeToken;
import com.umeng.soexample.yuekao_moni1025.bean.FenLei;
import com.umeng.soexample.yuekao_moni1025.bean.MessageBean;
import com.umeng.soexample.yuekao_moni1025.fen.model.FenModel;
import com.umeng.soexample.yuekao_moni1025.fen.view.FenView;
import com.umeng.soexample.yuekao_moni1025.inter.ICallBack;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by 择木 on 2018/10/25.
 */

public class FenPresenter {

    private FenView iv;
    private FenModel model;

    public  void  attach(FenView iv){
        this.iv=iv;
        model=new FenModel();
    }
    public void detach(){
        if (iv!=null){
            iv=null;
        }

    }

    public void getData(){
        String url="http://www.zhaoapi.cn/product/getProductCatagory";
        Type type=new TypeToken<MessageBean<List<FenLei>>>(){}.getType();

        model.getData(url, new ICallBack() {
            @Override
            public void success(Object obj) {
                MessageBean<List<FenLei>> data= (MessageBean<List<FenLei>>) obj;
                iv.success(data);
                iv.successright(data);

            }

            @Override
            public void failed(Exception e) {
               iv.failed(e);
            }
        },type);

    }
}
