package com.umeng.soexample.yuekao_moni1025.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.umeng.soexample.yuekao_moni1025.bean.Banner;
import com.umeng.soexample.yuekao_moni1025.utils.StringUtils;

import java.util.List;

/**
 * Created by 择木 on 2018/10/25.
 */

public class BannerAdapter  extends PagerAdapter{
    private Context context;
    private List<Banner> list;

    public BannerAdapter(Context context, List<Banner> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView img = new ImageView(context);
        Glide.with(context).load(
                StringUtils.https2http(list.get(position).getIcon()))
                .into(img);
        container.addView(img);
        return img;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
