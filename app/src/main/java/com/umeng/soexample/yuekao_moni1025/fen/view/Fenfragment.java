package com.umeng.soexample.yuekao_moni1025.fen.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.umeng.soexample.yuekao_moni1025.R;
import com.umeng.soexample.yuekao_moni1025.adapter.FenLeftAdapter;
import com.umeng.soexample.yuekao_moni1025.adapter.FenRightAdapter;
import com.umeng.soexample.yuekao_moni1025.bean.FenLei;
import com.umeng.soexample.yuekao_moni1025.bean.MessageBean;
import com.umeng.soexample.yuekao_moni1025.fen.presenter.FenPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 择木 on 2018/10/25.
 */

public class Fenfragment extends Fragment implements FenView {

    private static final String TAG = "Fenfragment";
    private RecyclerView rvLeft;
    private RecyclerView rvRight;
    private List<FenLei> fenLeiList;
    private List<FenLei> fenLeiRight;
    private FenLeftAdapter leftAdapter;
    private FenPresenter presenter;
    private FenRightAdapter rightAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.item_fen,container,false);
        rvLeft = v.findViewById(R.id.rv_left);
        rvRight = v.findViewById(R.id.rv_right);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        fenLeiList = new ArrayList<>();

        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        rvLeft.setLayoutManager(layoutManager);

        leftAdapter = new FenLeftAdapter(getActivity(),fenLeiList);

        rvLeft.setAdapter(leftAdapter);


        fenLeiRight = new ArrayList<>();

        RecyclerView.LayoutManager rlayoutManager=new GridLayoutManager(getActivity(),3);
        rvRight.setLayoutManager(rlayoutManager);



        rightAdapter = new FenRightAdapter(getActivity());
        presenter = new FenPresenter();
        presenter.attach(this);
        presenter.getData();

        leftAdapter.setOnClickListener(new FenLeftAdapter.OnClickListener() {
            @Override
            public void onClick(List<FenLei.ListBean> list) {
                rightAdapter.setList(list);
            }
        });
        rvRight.setAdapter(rightAdapter);

    }


    @Override
    public void success(MessageBean<List<FenLei>> data) {
        if (data!=null){
            fenLeiList.clear();
            fenLeiList.addAll(data.getData());
            List<FenLei.ListBean> list = fenLeiList.get(0).getList();
            rightAdapter.setList(list);
            leftAdapter.notifyDataSetChanged();

        }
    }

    @Override
    public void successright(MessageBean<List<FenLei>> data) {
        Log.i(TAG, "successright66666: "+data);
        if (data!=null){
            fenLeiRight.clear();
            fenLeiRight.addAll(data.getData());
            rightAdapter.notifyDataSetChanged();

        }
    }

    @Override
    public void failed(Exception e) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter!=null){
            presenter.detach();
        }
    }
}
