package com.example.lenovo.myapplication.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.lenovo.myapplication.R;

import java.util.ArrayList;

/**
 * Created by lenovo on 2016/4/5.
 */
public class PicLoopAdapter extends PagerAdapter {
    ArrayList<ImageView>list;
    Context context;
    public PicLoopAdapter(ArrayList<ImageView> list,Context context){
     this.list=list;
        this.context=context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
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
        //点击事件
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.e("test","click");
            }
        });
        return imageView;
    }
}
