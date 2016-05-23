package com.example.lenovo.myapplication.activity.discovery;

import android.app.LocalActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.activity.SearchActivity;
import com.example.lenovo.myapplication.adapter.ViewPagerAdapter;

import java.util.ArrayList;

public class Discovery extends AppCompatActivity {
    Toolbar toolbar;
    ViewPager sourcePager;
    ArrayList<View> list;
    LocalActivityManager manager;
    ViewPagerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discovery);

        manager=new LocalActivityManager(this,true);
        manager.dispatchCreate(savedInstanceState);

        initViews();

    }

    /*模拟获得标签*/
    private ArrayList<View> getTabList(int num){
        list=new ArrayList<>();
//        for (int i=0;i<num;i++){
//            Intent in1=new Intent(getApplicationContext(), VideoSource_1.class);
//            View view1=getView("",in1);
//            list.add(view1);
//        }
        Intent in1=new Intent(getApplicationContext(), VideoSource_1.class);
        View view1=getView("",in1);
        Intent in2=new Intent(getApplicationContext(), VideoSource_2.class);
        View view2=getView("",in2);
        Intent in3=new Intent(getApplicationContext(), VideoSource_3.class);
        View view3=getView("",in3);
        Intent in4=new Intent(getApplicationContext(), VideoSource_2.class);
        View view4=getView("",in4);
        Intent in5=new Intent(getApplicationContext(), VideoSource_3.class);
        View view5=getView("",in5);
        list.add(view1);
        list.add(view2);
        list.add(view3);
        list.add(view4);
        list.add(view5);
        return list;
    }
    /*初始化视图*/
    private void initViews(){
        toolbar=(Toolbar)findViewById(R.id.disc_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setOnMenuItemClickListener(new menuItemListener());


        sourcePager=(ViewPager)findViewById(R.id.disc_video_vp);
        adapter=new ViewPagerAdapter(getTabList(8)); //测试
        sourcePager.setAdapter(adapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.disc_tabLayout);
        tabLayout.setupWithViewPager(sourcePager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        String[] titles = new String[]{"爱奇艺", "优酷", "搜狐", "土豆", "乐视","Bilibili", "T2", "Tb3", "Tab4", "Tab5555555555"};
        for (int i=0;i<tabLayout.getTabCount();i++){
            tabLayout.getTabAt(i).setText(titles[i]);
        }

    }


    private class menuItemListener implements Toolbar.OnMenuItemClickListener{

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            int id =item.getItemId();
            if(id==R.id.action_search){
                Intent intent=new Intent(getApplicationContext(),SearchActivity.class);
                startActivity(intent);
            }
            return false;
        }
    }
    /*搜索框*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.discovery, menu);
        // Retrieve the SearchView and plug it into SearchManager
//        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
//        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
//        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        return true;
    }


    private View getView(String id, Intent intent){
        return manager.startActivity(id,intent).getDecorView();
    }




}
