package com.umeng.soexample.yuekao_moni1025.fen.view;

import com.umeng.soexample.yuekao_moni1025.bean.FenLei;
import com.umeng.soexample.yuekao_moni1025.bean.MessageBean;

import java.util.List;

/**
 * Created by 择木 on 2018/10/25.
 */

public interface FenView{
    void  success(MessageBean<List<FenLei>> data);
    void  successright(MessageBean<List<FenLei>> data);
    void failed(Exception e);

}
