package com.example.lenovo.myapplication.activity.me;

import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.adapter.RecyclerAdapter;
import com.example.lenovo.myapplication.object.VideoInfo;

import java.util.ArrayList;

public class Me_Opus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me__opus);
        initViews();
    }

    private void initViews() {
        RecyclerView recyclerView;
        RecyclerAdapter recyclerAdapter;
        recyclerView=(RecyclerView)findViewById(R.id.me_opus_rv);
        recyclerAdapter=new RecyclerAdapter(getApplicationContext(),getVideoInfos());
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));  //设置RecyclerView布局管理器为2列垂直排布
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDraw(c, parent, state);
            }
        });
        recyclerView.setAdapter(recyclerAdapter);
        //这里实现点击视频事件
        recyclerAdapter.setOnClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {

            }

            @Override
            public void onItemLongClick(View view, int postion) {

            }
        });
    }
    /*
   * 图片高度从服务器获得
   * */
    private ArrayList<VideoInfo> getVideoInfos(){
        ArrayList<VideoInfo> data=new ArrayList<>();
        VideoInfo videoInfo=new VideoInfo();
        videoInfo.setImgHeight(1000);
        videoInfo.setUserName("flyingharry");
        videoInfo.setImag(R.drawable.fenlei_01);

        VideoInfo videoInfo1=new VideoInfo();
        videoInfo1.setUserName("happen");
        videoInfo1.setImgHeight(800);
        videoInfo1.setImag(R.drawable.fenlei_02);

        VideoInfo videoInfo2=new VideoInfo();
        videoInfo2.setUserName("happen");
        videoInfo2.setImgHeight(900);
        videoInfo2.setImag(R.drawable.fenlei_03);

        data.add(videoInfo);
        data.add(videoInfo1);
        data.add(videoInfo);
        data.add(videoInfo2);
        data.add(videoInfo1);
        data.add(videoInfo2);
        data.add(videoInfo1);
        return data;
    }


}
