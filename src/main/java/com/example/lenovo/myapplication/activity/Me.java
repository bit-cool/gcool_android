package com.example.lenovo.myapplication.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.lenovo.myapplication.R;

public class Me extends AppCompatActivity {
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);
        viewPager=(ViewPager)findViewById(R.id.me_vp);
        TabLayout tabLayout= (TabLayout)findViewById(R.id.me_tabLayout);

//        tabLayout.setupWithViewPager(viewPager);
//        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
//        tabLayout.setTabMode(TabLayout.MODE_FIXED);
//        tabLayout.getTabAt(0).setText("我的作品");
//        tabLayout.getTabAt(1).setText("我的收藏");
//        tabLayout.getTabAt(2).setText("我的AMD");
    }
}
