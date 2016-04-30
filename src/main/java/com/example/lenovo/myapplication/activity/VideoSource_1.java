package com.example.lenovo.myapplication.activity;

import android.app.SearchManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.adapter.PicLoopAdapter;
import com.example.lenovo.myapplication.adapter.RecyclerAdapter;
import com.example.lenovo.myapplication.object.PicLoopInfo;
import com.example.lenovo.myapplication.object.VideoInfo;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class VideoSource_1 extends AppCompatActivity {


    public static String IMAGE_CACHE_PATH = "imageloader/Cache"; // 图片缓存路径
    ViewPager viewPager;
    View dot0,dot1,dot2,dot3;
    PicLoopAdapter loopAdapter;
    ArrayList<ImageView> imageViews;
    ArrayList<PicLoopInfo> picLoopInfos;
    ArrayList<View> dotList;
    ImageLoader imageLoader;
    DisplayImageOptions options;
    int currentItem=0;
    ScheduledExecutorService scheduledExecutorService;
    Handler handler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            viewPager.setCurrentItem(currentItem);
        }
    };

    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_source_1);
        //初始化ImageLoader
        initImageLoader();
        // 获取图片加载实例
        imageLoader = ImageLoader.getInstance();
        options = new DisplayImageOptions.Builder()
                .showStubImage(R.drawable.top_banner_android)
                .showImageForEmptyUri(R.drawable.top_banner_android)
                .showImageOnFail(R.drawable.top_banner_android)
                .cacheInMemory(true).cacheOnDisc(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .imageScaleType(ImageScaleType.EXACTLY).build();
        initViews();
        startAd();
    }
    /*初始化视图*/
    private void initViews(){
        viewPager=(ViewPager)findViewById(R.id.disc_loop);
        dot0=findViewById(R.id.v_dot0);
        dot1=findViewById(R.id.v_dot1);
        dot2=findViewById(R.id.v_dot2);
        dot3=findViewById(R.id.v_dot3);
        dotList=new ArrayList<>();
        dotList.add(dot0);
        dotList.add(dot1);
        dotList.add(dot2);
        dotList.add(dot3);
        //获得轮播图片
        loopAdapter =new PicLoopAdapter(addPics(),getApplicationContext());
        viewPager.setAdapter(loopAdapter);
        viewPager.setOnPageChangeListener(new pageChangeListener());

        recyclerView=(RecyclerView)findViewById(R.id.video_source_rv);
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
        recyclerAdapter.setOnClickListener(new RecyclerAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(View view, int postion) {

            }

            @Override
            public void onItemLongClick(View view, int postion) {

            }
        });
    }

    /*加载图片资源*/
    private ArrayList<ImageView> addPics(){
        imageViews=new ArrayList<>();
        picLoopInfos=getPicInfos();
        for (int i=0;i<picLoopInfos.size();i++){
            ImageView imageView=new ImageView(this);
            //异步加载图片
            imageLoader.displayImage(picLoopInfos.get(i).getImageUrl(),imageView,options);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageViews.add(imageView);
            dotList.get(i).setVisibility(View.VISIBLE);
        }
        return imageViews;
    }
    /*初始化ImageLoader*/
    private void initImageLoader() {
        File cacheDir = com.nostra13.universalimageloader.utils.StorageUtils
                .getOwnCacheDirectory(getApplicationContext(),
                        IMAGE_CACHE_PATH);

        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true).cacheOnDisc(true).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                this).defaultDisplayImageOptions(defaultOptions)
                .memoryCache(new LruMemoryCache(12 * 1024 * 1024))
                .memoryCacheSize(12 * 1024 * 1024)
                .discCacheSize(32 * 1024 * 1024).discCacheFileCount(100)
                .discCache(new UnlimitedDiscCache(cacheDir))
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .tasksProcessingOrder(QueueProcessingType.LIFO).build();

        ImageLoader.getInstance().init(config);
    }

    private class pageChangeListener implements ViewPager.OnPageChangeListener{
        private int oldPostion=0;
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            currentItem=position;
            dotList.get(position).setBackgroundResource(R.drawable.dot_focus);
            dotList.get(oldPostion).setBackgroundResource(R.drawable.dot_normal);
            oldPostion=position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.discovery, menu);
        // Retrieve the SearchView and plug it into SearchManager
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        return true;
    }

    private void startAd() {
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        // 当Activity显示出来后，每两秒切换一次图片显示
        scheduledExecutorService.scheduleAtFixedRate(new ScrollTask(), 1, 2,
                TimeUnit.SECONDS);
    }
    /*模拟获得轮播图片信息*/
    private ArrayList<PicLoopInfo> getPicInfos(){
        ArrayList<PicLoopInfo> picList=new ArrayList<>();
        PicLoopInfo picLoopInfo1=new PicLoopInfo();
        picLoopInfo1.setImageUrl("http://g.hiphotos.baidu.com/image/w%3D310/sign=bb99d6add2c8a786be2a4c0f5708c9c7/d50735fae6cd7b8900d74cd40c2442a7d9330e29.jpg");
        picList.add(picLoopInfo1);

        PicLoopInfo picLoopInfo2=new PicLoopInfo();
        picLoopInfo2.setImageUrl("http://g.hiphotos.baidu.com/image/w%3D310/sign=7cbcd7da78f40ad115e4c1e2672e1151/eaf81a4c510fd9f9a1edb58b262dd42a2934a45e.jpg");
        picList.add(picLoopInfo2);

        PicLoopInfo picLoopInfo3=new PicLoopInfo();
        picLoopInfo3.setImageUrl("http://e.hiphotos.baidu.com/image/w%3D310/sign=392ce7f779899e51788e3c1572a6d990/8718367adab44aed22a58aeeb11c8701a08bfbd4.jpg");
        picList.add(picLoopInfo3);

        PicLoopInfo picLoopInfo4=new PicLoopInfo();
        picLoopInfo4.setImageUrl("http://d.hiphotos.baidu.com/image/w%3D310/sign=54884c82b78f8c54e3d3c32e0a282dee/a686c9177f3e670932e4cf9338c79f3df9dc55f2.jpg");
        picList.add(picLoopInfo4);
        return picList;
    }

    private class ScrollTask implements Runnable{

        @Override
        public void run() {
            synchronized (viewPager){
                currentItem = (currentItem + 1) % imageViews.size();
                handler.obtainMessage().sendToTarget();
            }
        }
    }

    private ArrayList<VideoInfo> getVideoInfos(){
        ArrayList<VideoInfo> data=new ArrayList<>();
        VideoInfo videoInfo=new VideoInfo();
        videoInfo.setImgHeight(1000);
        videoInfo.setUserName("flyingharry");
        VideoInfo videoInfo1=new VideoInfo();
        videoInfo1.setUserName("happen");
        videoInfo1.setImgHeight(800);

        VideoInfo videoInfo2=new VideoInfo();
        videoInfo2.setUserName("happen");
        videoInfo2.setImgHeight(2000);

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
