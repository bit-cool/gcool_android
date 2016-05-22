package com.example.lenovo.myapplication.activity.me;

import android.app.LocalActivityManager;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.adapter.MeAdapter;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Me extends AppCompatActivity {
    ViewPager viewPager;
    LocalActivityManager manager;
    TextView Edit,name,qianMing,ZuoPin,shouCang,AMD;
    ImageView headPic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);
        manager=new LocalActivityManager(this,true);
        manager.dispatchCreate(savedInstanceState);
        initViews();
        setContents();
    }
    //从网络获得基本信息
    private void setContents() {

    }

    private void initViews() {

        Edit=(TextView)findViewById(R.id.me_edit);
        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getApplicationContext(),Me_Edit.class);
                startActivity(intent);
            }
        });

        name=(TextView)findViewById(R.id.me_name);
        qianMing=(TextView)findViewById(R.id.me_qianming);
        ZuoPin=(TextView)findViewById(R.id.me_zuopinshu);
        shouCang=(TextView)findViewById(R.id.me_shoucangshu);
        AMD=(TextView)findViewById(R.id.me_amdshu);

        Toolbar toolbar=(Toolbar)findViewById(R.id.me_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id=item.getItemId();
                if(id==R.id.action_me_settings){
                    Intent intent=new Intent(getApplicationContext(),SettingActivity.class);
                    startActivity(intent);
                }
                return false;
            }
        });

        viewPager=(ViewPager)findViewById(R.id.me_vp);
        Intent in1=new Intent(getApplicationContext(), Me_Opus.class);
        View view1=getView("",in1);
        Intent in2=new Intent(getApplicationContext(), Me_Collections.class);
        View view2=getView("",in2);
        Intent in3=new Intent(getApplicationContext(), Me_Amds.class);
        View view3=getView("",in3);
        ArrayList<View> list=new ArrayList<>();
        list.add(view1);
        list.add(view2);
        list.add(view3);
        MeAdapter adapter=new MeAdapter(list);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout= (TabLayout)findViewById(R.id.me_tabLayout);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.getTabAt(0).setText("我的作品");
        tabLayout.getTabAt(1).setText("我的收藏");
        tabLayout.getTabAt(2).setText("我的AMD");
    }

    private View getView(String id, Intent intent){
        return manager.startActivity(id,intent).getDecorView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.me, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
