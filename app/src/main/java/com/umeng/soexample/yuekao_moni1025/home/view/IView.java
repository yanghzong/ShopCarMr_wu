package com.umeng.soexample.yuekao_moni1025.home.view;

import com.umeng.soexample.yuekao_moni1025.bean.Banner;
import com.umeng.soexample.yuekao_moni1025.bean.MessageBean;

import java.util.List;

/**
 * Created by 择木 on 2018/10/25.
 */

public interface IView {
    void failed(Exception e);
    void getBanner(MessageBean<List<Banner>> banners);

}
