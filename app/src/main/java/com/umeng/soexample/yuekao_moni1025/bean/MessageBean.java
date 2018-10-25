package com.umeng.soexample.yuekao_moni1025.bean;

import java.util.List;

/**
 * Created by 择木 on 2018/10/25.
 */

public class MessageBean <T>{
    /**
     * msg :
     * code : 0
     * data : [{"aid":1,"createtime":"2017-12-26T21:49:44","icon":"https://www.zhaoapi.cn/images/quarter/ad1.png","productId":null,"title":"第十三界瑞丽模特大赛","type":0,"url":"http://m.mv14449315.icoc.bz/index.jsp"},{"aid":2,"createtime":"2017-12-26T21:49:44","icon":"https://www.zhaoapi.cn/images/quarter/ad2.png","productId":null,"title":"文化艺术节","type":0,"url":"http://m.mv14449315.icoc.bz/index.jsp"},{"aid":3,"createtime":"2017-12-26T21:49:44","icon":"https://www.zhaoapi.cn/images/quarter/ad3.png","productId":null,"title":"直播封面标准","type":0,"url":"http://m.mv14449315.icoc.bz/index.jsp"},{"aid":4,"createtime":"2017-12-26T21:49:44","icon":"https://www.zhaoapi.cn/images/quarter/ad4.png","productId":"1","title":"人气谁最高，金主谁最豪气","type":1,"url":""}]
     */

    private String msg;
    private String code;
    private T data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
