package com.example.lenovo.myapplication.activity;

import android.app.LocalActivityManager;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.activity.discovery.Discovery;
import com.example.lenovo.myapplication.activity.me.Me;
import com.example.lenovo.myapplication.adapter.MainAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<View>list;
    ArrayList<ImageView> tabList;
    ArrayList<TextView> tvList;
    ViewPager viewPager;
    MainAdapter adapter;
    LocalActivityManager manager;
//    ImageView ivShot, ivRank, ivDisc, ivMe, ivAmd;
//    TextView tvShot,tvRank,tvDisc,tvMe,tvAmd;
    final static int SHOT=0;
    final static int RANK=1;
    final static int DISCOVERY=2;
    final static int AMD=3;
    final static int ME=4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager=new LocalActivityManager(this,true);
        manager.dispatchCreate(savedInstanceState);
        initViews();
        viewPager=(ViewPager)findViewById(R.id.main_viewpager);
        adapter=new MainAdapter(list);
        viewPager.setAdapter(adapter);
        TabLayout tabLayout=(TabLayout)findViewById(R.id.main_tabLayout);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        String[] titles = new String[]{"拍摄", "排行榜", "发现", "AMD", "我","Tab2221", "T2", "Tb3", "Tab4", "Tab5555555555"};
        for (int i=0;i<tabLayout.getTabCount();i++){
            tabLayout.getTabAt(i).setText(titles[i]);
            tabLayout.getTabAt(i).setIcon(R.drawable.ic_launcher);
        }

//        ivShot.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                viewPager.setCurrentItem(SHOT);
//                changeTabs(SHOT);
//            }
//        });
//        ivRank.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                viewPager.setCurrentItem(RANK);
//                changeTabs(RANK);
//            }
//        });
//        ivDisc.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                viewPager.setCurrentItem(DISCOVERY);
//                changeTabs(DISCOVERY);
//            }
//        });
//        ivMe.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                viewPager.setCurrentItem(ME);
//                changeTabs(ME);
//            }
//        });
//        ivAmd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                viewPager.setCurrentItem(AMD);
//                changeTabs(AMD);
//            }
//        });
        //默认tab
        //changeTabs(DISCOVERY);
//        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                changeTabs(position);
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
    }

    private void initViews(){
//        ivShot =(ImageView)findViewById(R.id.main_shot);
//        ivRank =(ImageView)findViewById(R.id.main_rank);
//        ivDisc =(ImageView)findViewById(R.id.main_disc);
//        ivMe =(ImageView)findViewById(R.id.main_me);
//        ivAmd =(ImageView)findViewById(R.id.main_amd);
//
//        tvShot=(TextView)findViewById(R.id.main_tv_shot);
//        tvRank=(TextView)findViewById(R.id.main_tv_rank);
//        tvDisc=(TextView)findViewById(R.id.main_tv_disc);
//        tvAmd=(TextView)findViewById(R.id.main_tv_amd);
//        tvMe=(TextView)findViewById(R.id.main_tv_me);

        Intent in1=new Intent(getApplicationContext(), Filming.class);
        View view1=getView("filming",in1);
        Intent in2=new Intent(getApplicationContext(), Rankings.class);
        View view2=getView("rankings",in2);
        Intent in3=new Intent(getApplicationContext(), Discovery.class);
        View view3=getView("discovery",in3);
        Intent in4=new Intent(getApplicationContext(), com.example.lenovo.myapplication.activity.amd.AMD.class);
        View view4=getView("Amd",in4);
        Intent in5=new Intent(getApplicationContext(), Me.class);
        View view5=getView("Me",in5);


//        tvList=new ArrayList<TextView>();
//        tabList=new ArrayList<ImageView>();
        list=new ArrayList<>();

//        SpannableString spannableString1=new SpannableString("拍摄");
//        SpannableString spannableString2=new SpannableString("排行榜");
//        SpannableString spannableString3=new SpannableString("发现");
//        SpannableString spannableString4=new SpannableString("AMD");
//        SpannableString spannableString5=new SpannableString("我");

//        spannableStringList=new ArrayList<>();


//        tvList.add(tvShot);
//        tvList.add(tvRank);
//        tvList.add(tvDisc);
//        tvList.add(tvAmd);
//        tvList.add(tvMe);
//
//        tabList.add(ivShot);
//        tabList.add(ivRank);
//        tabList.add(ivDisc);
//        tabList.add(ivAmd);
//        tabList.add(ivMe);

        list.add(view1);
        list.add(view2);
        list.add(view3);
        list.add(view4);
        list.add(view5);
    }

    private View getView(String id, Intent intent){
        return manager.startActivity(id,intent).getDecorView();
    }

//    private void changeTabs(int tabId){
//
//        for (int i=0;i<5;i++){
//            if(i==tabId){
//                tvList.get(i).setBackgroundResource(R.color.colorPrimary);
//                tvList.get(i).setTextColor(this.getResources().getColor(R.color.White));
//            }else {
//                tvList.get(i).setBackgroundResource(R.color.Transpanrent);
//                tvList.get(i).setTextColor(this.getResources().getColor(R.color.colorPrimary));
//            }
//        }
//    }



}
