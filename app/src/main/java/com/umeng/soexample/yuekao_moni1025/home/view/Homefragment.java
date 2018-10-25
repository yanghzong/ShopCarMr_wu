package com.umeng.soexample.yuekao_moni1025.home.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.umeng.soexample.yuekao_moni1025.LiuActivity;
import com.umeng.soexample.yuekao_moni1025.MainActivity;
import com.umeng.soexample.yuekao_moni1025.R;
import com.umeng.soexample.yuekao_moni1025.adapter.BannerAdapter;
import com.umeng.soexample.yuekao_moni1025.bean.Banner;
import com.umeng.soexample.yuekao_moni1025.bean.MessageBean;
import com.umeng.soexample.yuekao_moni1025.home.presenter.HomePresenter;

import java.net.IDN;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 择木 on 2018/10/25.
 */

public class Homefragment extends Fragment implements IView{

    private EditText edSou;
    private ViewPager vpBanner;
    private List<Banner> bannerList;
    private BannerAdapter bannerAdapter;
    private HomePresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.item_home,container,false);
        edSou = v.findViewById(R.id.ed_sou);
        vpBanner = v.findViewById(R.id.vp_banner);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bannerList = new ArrayList<>();
        bannerAdapter = new BannerAdapter(getActivity(),bannerList);
        vpBanner.setAdapter(bannerAdapter);

        presenter = new HomePresenter();
        presenter.attach(this);
        presenter.getBanner();

        edSou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity(), LiuActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void failed(Exception e) {

    }

    @Override
    public void getBanner(MessageBean<List<Banner>> banners) {

        if (banners!=null){
            List<Banner> data=banners.getData();
            if (data!=null){
                bannerList.clear();
                bannerList.addAll(data);
                bannerAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter!=null){
            presenter.detach();
        }
    }
}
