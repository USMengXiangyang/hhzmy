package com.hhzmy.dataneturl;

import android.app.Application;

import com.hhzmy.test.R;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by asus on 2016/11/9.
 */
public class DataImageLoader extends Application {
    @Override
    public void onCreate() {

        super.onCreate();
        SetImage();
    }

    private void SetImage() {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true).cacheOnDisc(true)
                .showImageOnFail(R.mipmap.ic_launcher)
                .showImageOnFail(R.mipmap.ic_launcher).considerExifParams(true)
                .showImageOnLoading(R.mipmap.ic_launcher).build();
        int max = (int) (Runtime.getRuntime().maxMemory() / 8);
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(
                getApplicationContext())
                .memoryCache(new UsingFreqLimitedMemoryCache(max))
                .discCache(new UnlimitedDiskCache(getCacheDir()))
                .threadPoolSize(3).threadPriority(Thread.NORM_PRIORITY - 1)
                .defaultDisplayImageOptions(options).build();
        ImageLoader.getInstance().init(configuration);
    }
}
