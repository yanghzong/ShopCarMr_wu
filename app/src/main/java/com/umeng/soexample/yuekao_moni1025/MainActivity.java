package com.umeng.soexample.yuekao_moni1025;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.umeng.soexample.yuekao_moni1025.fen.view.Fenfragment;
import com.umeng.soexample.yuekao_moni1025.home.view.Homefragment;
import com.umeng.soexample.yuekao_moni1025.shopcar.view.Shopfragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TabLayout tbButtom;
    private ViewPager vpZhu;
    private List<Fragment> fragmentList;
    private List<String> titles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tbButtom = findViewById(R.id.tb_buttom);
        vpZhu = findViewById(R.id.vp_zhu);
        fragmentList = new ArrayList<>();
        fragmentList.add(new Homefragment());
        fragmentList.add(new Fenfragment());
        fragmentList.add(new Shopfragment());


        titles = new ArrayList<>();

        titles.add("首页");
        titles.add("分类");
        titles.add("购物车");

        vpZhu.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return fragmentList.size();
            }

            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return  titles.get(position);
            }
        });
        tbButtom.setTabMode(TabLayout.MODE_SCROLLABLE);
        tbButtom.setupWithViewPager(vpZhu);

    }
}
