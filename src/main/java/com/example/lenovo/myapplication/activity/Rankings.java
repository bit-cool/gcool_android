package com.example.lenovo.myapplication.activity;

import android.app.LocalActivityManager;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.activity.amd.AMD_Doing;
import com.example.lenovo.myapplication.activity.amd.AMD_Finished;
import com.example.lenovo.myapplication.adapter.ViewPagerAdapter;
import com.example.lenovo.myapplication.constances.Constances;

import java.util.ArrayList;

public class Rankings extends AppCompatActivity {
    LocalActivityManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rankings);
        manager=new LocalActivityManager(this,true);
        manager.dispatchCreate(savedInstanceState);
        initViews();
    }

    private void initViews() {
        Intent intent1=new Intent(getApplicationContext(),AMD_Doing.class);
        View view1=getView("",intent1);
        Intent intent2=new Intent(getApplicationContext(),AMD_Finished.class);
        View view2=getView("",intent2);
        ArrayList<View> list=new ArrayList<>();
        list.add(view1);
        list.add(view2);
        ViewPagerAdapter adapter=new ViewPagerAdapter(list);

        ViewPager typePager=(ViewPager)findViewById(R.id.rank_video_vp);
        typePager.setAdapter(adapter);
        TabLayout tabLayout= (TabLayout)findViewById(R.id.rank_tabLayout);

        tabLayout.setupWithViewPager(typePager);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        for (int i=0;i< 2/*Constances.getRankTabs().size()*/;i++){
            tabLayout.getTabAt(i).setText(Constances.getRankTabs().get(i));
        }

    }

    private View getView(String id, Intent intent){
        return manager.startActivity(id,intent).getDecorView();
    }
}
