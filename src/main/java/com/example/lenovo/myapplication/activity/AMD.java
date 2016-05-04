package com.example.lenovo.myapplication.activity;

import android.app.LocalActivityManager;
import android.app.SearchManager;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.adapter.AMDTabAdapter;

import java.util.ArrayList;
import java.util.List;

public class AMD extends AppCompatActivity {
    LocalActivityManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amd);
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
        AMDTabAdapter adapter=new AMDTabAdapter(list);

        ViewPager typePager=(ViewPager)findViewById(R.id.amd_vp);
        typePager.setAdapter(adapter);
        TabLayout tabLayout= (TabLayout)findViewById(R.id.amd_tabLayout);

        tabLayout.setupWithViewPager(typePager);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.getTabAt(0).setText("正在进行");
        tabLayout.getTabAt(1).setText("已完成");

        Toolbar toolbar=(Toolbar)findViewById(R.id.amd_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setOnMenuItemClickListener(new menuItemListener());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.amd_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent intent=new Intent(getApplicationContext(),AMD_New.class);
                startActivity(intent);
            }
        });
    }

    private class menuItemListener implements Toolbar.OnMenuItemClickListener{

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            return false;
        }
    }
    /*搜索框*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.discovery, menu);
        // Retrieve the SearchView and plug it into SearchManager
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        return true;
    }

    private View getView(String id, Intent intent){
        return manager.startActivity(id,intent).getDecorView();
    }
}
