package com.example.lenovo.myapplication.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by lenovo on 2016/4/5.
 */
public class PicLoopAdapter extends PagerAdapter {
    ArrayList<ImageView>list;
    public PicLoopAdapter(ArrayList<ImageView> list){
     this.list=list;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return false;
    }
    // 销毁视图
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // TODO Auto-generated method stub
        ((ViewPager) container).removeView((View) object);
    }

    // 创建视图
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // TODO Auto-generated method stub
        ImageView imageView=list.get(position);
        ((ViewPager) container).addView(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
        return list.get(position);
    }
}
