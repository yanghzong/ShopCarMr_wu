package com.umeng.soexample.yuekao_moni1025.home.model;

import com.umeng.soexample.yuekao_moni1025.inter.ICallBack;
import com.umeng.soexample.yuekao_moni1025.utils.HttpUtils;

import java.lang.reflect.Type;

/**
 * Created by 择木 on 2018/10/25.
 */


public class HomeModel {
    public void getData(String url, ICallBack callBack, Type type){
        HttpUtils.getInstance().get(url,callBack,type);
    }
}
