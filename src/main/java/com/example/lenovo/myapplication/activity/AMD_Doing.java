package com.example.lenovo.myapplication.activity;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.adapter.AMDListAdapter;
import com.example.lenovo.myapplication.adapter.PicLoopAdapter;
import com.example.lenovo.myapplication.object.AmdInfo;
import com.example.lenovo.myapplication.object.PicLoopInfo;
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

public class AMD_Doing extends AppCompatActivity {
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amd__doing);
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

    private void initViews() {
        viewPager=(ViewPager)findViewById(R.id.amd_doing_vp);
        dot0=findViewById(R.id.amd_doing_dot0);
        dot1=findViewById(R.id.amd_doing_dot1);
        dot2=findViewById(R.id.amd_doing_dot2);
        dot3=findViewById(R.id.amd_doing_dot3);
        dotList=new ArrayList<>();
        dotList.add(dot0);
        dotList.add(dot1);
        dotList.add(dot2);
        dotList.add(dot3);
        //获得轮播图片
        loopAdapter =new PicLoopAdapter(addPics(),getApplicationContext());
        viewPager.setAdapter(loopAdapter);
        viewPager.setOnPageChangeListener(new pageChangeListener());

        ListView listView=(ListView)findViewById(R.id.amd_doing_lv);
        AMDListAdapter amdListAdapter=new AMDListAdapter(getApplicationContext(),addList());
        listView.setAdapter(amdListAdapter);

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

    //网络请求获得
    private ArrayList<AmdInfo> addList(){
        ArrayList<AmdInfo> list=new ArrayList<>();
        AmdInfo amdInfo1=new AmdInfo();
        amdInfo1.setName("#大胃王挑战赛#");
        amdInfo1.setAgreeNum("4379");
        amdInfo1.setAuthor("HAPPEN");
        amdInfo1.setTag("搞笑");
        list.add(amdInfo1);
        list.add(amdInfo1);
        list.add(amdInfo1);
        list.add(amdInfo1);
        list.add(amdInfo1);
        list.add(amdInfo1);
        list.add(amdInfo1);
        list.add(amdInfo1);
        return list;

    }

}
