package com.example.lenovo.myapplication.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by lenovo on 2016/5/3.
 */
public class ViewPagerAdapter extends PagerAdapter {
    ArrayList<View> list;

    public ViewPagerAdapter(ArrayList<View> list){
        this.list=list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    // 销毁视图
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // TODO Auto-generated method stub
        ((ViewPager) container).removeView(list.get(position));
    }

    // 创建视图
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // TODO Auto-generated method stub
        ((ViewPager) container).addView(list.get(position));

        return list.get(position);
    }
}
