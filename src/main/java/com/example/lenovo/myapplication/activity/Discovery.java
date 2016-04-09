package com.example.lenovo.myapplication.activity;


import android.graphics.Bitmap;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.adapter.PicLoopAdapter;
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

public class Discovery extends AppCompatActivity {
    public static String IMAGE_CACHE_PATH = "imageloader/Cache"; // 图片缓存路径
    ViewPager viewPager;
    View dot0,dot1,dot2,dot3;
    PicLoopAdapter adapter;
    ArrayList<ImageView> imageViews;
    ArrayList<PicLoopInfo> picLoopInfos;
    ArrayList<View> dotList;
    ImageLoader imageLoader;
    DisplayImageOptions options;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discovery);
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
        addPics();
        adapter=new PicLoopAdapter(imageViews);
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new pageChangeListener());
    }
    /*加载图片资源*/
    private void addPics(){
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
            dotList.get(position).setBackgroundResource(R.drawable.dot_focus);
            dotList.get(oldPostion).setBackgroundResource(R.drawable.dot_normal);
            oldPostion=position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
    /*获得图片信息*/
    private ArrayList<PicLoopInfo> getPicInfos(){
        ArrayList<PicLoopInfo> picList=new ArrayList<>();

        return picList;
    }






}
