package com.example.lenovo.myapplication.activity;

import android.app.LocalActivityManager;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.adapter.DiscAdapter;

import java.util.ArrayList;

public class Discovery extends AppCompatActivity {
    public static String IMAGE_CACHE_PATH = "imageloader/Cache"; // 图片缓存路径
    Toolbar toolbar;
    ViewPager sourcePager;
    ArrayList<View> list;
    LocalActivityManager manager;
    DiscAdapter adapter;


//    ViewPager viewPager;
//    View dot0,dot1,dot2,dot3;
//    PicLoopAdapter loopAdapter;
//    ArrayList<ImageView> imageViews;
//    ArrayList<PicLoopInfo> picLoopInfos;
//    ArrayList<View> dotList;
//    ImageLoader imageLoader;
//    DisplayImageOptions options;
//    int currentItem=0;
//    ScheduledExecutorService scheduledExecutorService;
//    Handler handler =new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//           viewPager.setCurrentItem(currentItem);
//        }
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discovery);

        manager=new LocalActivityManager(this,true);
        manager.dispatchCreate(savedInstanceState);

//        //初始化ImageLoader
//        initImageLoader();
//        // 获取图片加载实例
//        imageLoader = ImageLoader.getInstance();
//        options = new DisplayImageOptions.Builder()
//                .showStubImage(R.drawable.top_banner_android)
//                .showImageForEmptyUri(R.drawable.top_banner_android)
//                .showImageOnFail(R.drawable.top_banner_android)
//                .cacheInMemory(true).cacheOnDisc(true)
//                .bitmapConfig(Bitmap.Config.RGB_565)
//                .imageScaleType(ImageScaleType.EXACTLY).build();
        initViews();
//        startAd();
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
        adapter=new DiscAdapter(getTabList(8)); //测试
        sourcePager.setAdapter(adapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.disc_tabLayout);
        tabLayout.setupWithViewPager(sourcePager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        String[] titles = new String[]{"爱奇艺", "优酷", "搜狐", "土豆", "乐视","Bilibili", "T2", "Tb3", "Tab4", "Tab5555555555"};
        for (int i=0;i<tabLayout.getTabCount();i++){
            tabLayout.getTabAt(i).setText(titles[i]);
        }
//        viewPager=(ViewPager)findViewById(R.id.disc_loop);
//        dot0=findViewById(R.id.v_dot0);
//        dot1=findViewById(R.id.v_dot1);
//        dot2=findViewById(R.id.v_dot2);
//        dot3=findViewById(R.id.v_dot3);
//        dotList=new ArrayList<>();
//        dotList.add(dot0);
//        dotList.add(dot1);
//        dotList.add(dot2);
//        dotList.add(dot3);
//        //获得图片
//        loopAdapter=new PicLoopAdapter(addPics(),getApplicationContext());
//        viewPager.setAdapter(loopAdapter);
//        viewPager.setOnPageChangeListener(new pageChangeListener());
    }
//    /*加载图片资源*/
//    private ArrayList<ImageView> addPics(){
//        imageViews=new ArrayList<>();
//        picLoopInfos=getPicInfos();
//        for (int i=0;i<picLoopInfos.size();i++){
//            ImageView imageView=new ImageView(this);
//            //异步加载图片
//            imageLoader.displayImage(picLoopInfos.get(i).getImageUrl(),imageView,options);
//            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            imageViews.add(imageView);
//            dotList.get(i).setVisibility(View.VISIBLE);
//        }
//        return imageViews;
//    }
//    /*初始化ImageLoader*/
//    private void initImageLoader() {
//        File cacheDir = com.nostra13.universalimageloader.utils.StorageUtils
//                .getOwnCacheDirectory(getApplicationContext(),
//                        IMAGE_CACHE_PATH);
//
//        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
//                .cacheInMemory(true).cacheOnDisc(true).build();
//
//        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
//                this).defaultDisplayImageOptions(defaultOptions)
//                .memoryCache(new LruMemoryCache(12 * 1024 * 1024))
//                .memoryCacheSize(12 * 1024 * 1024)
//                .discCacheSize(32 * 1024 * 1024).discCacheFileCount(100)
//                .discCache(new UnlimitedDiscCache(cacheDir))
//                .threadPriority(Thread.NORM_PRIORITY - 2)
//                .tasksProcessingOrder(QueueProcessingType.LIFO).build();
//
//        ImageLoader.getInstance().init(config);
//    }

//    private class pageChangeListener implements ViewPager.OnPageChangeListener{
//        private int oldPostion=0;
//        @Override
//        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//        }
//
//        @Override
//        public void onPageSelected(int position) {
//            currentItem=position;
//            dotList.get(position).setBackgroundResource(R.drawable.dot_focus);
//            dotList.get(oldPostion).setBackgroundResource(R.drawable.dot_normal);
//            oldPostion=position;
//        }
//
//        @Override
//        public void onPageScrollStateChanged(int state) {
//
//        }
//    }

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

//    private void startAd() {
//        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
//        // 当Activity显示出来后，每两秒切换一次图片显示
//        scheduledExecutorService.scheduleAtFixedRate(new ScrollTask(), 1, 2,
//                TimeUnit.SECONDS);
//    }
//    /*获得图片信息*/
//    private ArrayList<PicLoopInfo> getPicInfos(){
//        ArrayList<PicLoopInfo> picList=new ArrayList<>();
//        PicLoopInfo picLoopInfo1=new PicLoopInfo();
//        picLoopInfo1.setImageUrl("http://g.hiphotos.baidu.com/image/w%3D310/sign=bb99d6add2c8a786be2a4c0f5708c9c7/d50735fae6cd7b8900d74cd40c2442a7d9330e29.jpg");
//        picList.add(picLoopInfo1);
//
//        PicLoopInfo picLoopInfo2=new PicLoopInfo();
//        picLoopInfo2.setImageUrl("http://g.hiphotos.baidu.com/image/w%3D310/sign=7cbcd7da78f40ad115e4c1e2672e1151/eaf81a4c510fd9f9a1edb58b262dd42a2934a45e.jpg");
//        picList.add(picLoopInfo2);
//
//        PicLoopInfo picLoopInfo3=new PicLoopInfo();
//        picLoopInfo3.setImageUrl("http://e.hiphotos.baidu.com/image/w%3D310/sign=392ce7f779899e51788e3c1572a6d990/8718367adab44aed22a58aeeb11c8701a08bfbd4.jpg");
//        picList.add(picLoopInfo3);
//
//        PicLoopInfo picLoopInfo4=new PicLoopInfo();
//        picLoopInfo4.setImageUrl("http://d.hiphotos.baidu.com/image/w%3D310/sign=54884c82b78f8c54e3d3c32e0a282dee/a686c9177f3e670932e4cf9338c79f3df9dc55f2.jpg");
//        picList.add(picLoopInfo4);
//        return picList;
//    }
//
//    private class ScrollTask implements Runnable{
//
//        @Override
//        public void run() {
//            synchronized (viewPager){
//                currentItem = (currentItem + 1) % imageViews.size();
//                handler.obtainMessage().sendToTarget();
//            }
//        }
//    }

    private View getView(String id, Intent intent){
        return manager.startActivity(id,intent).getDecorView();
    }




}
