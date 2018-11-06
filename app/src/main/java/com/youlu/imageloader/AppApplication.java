package com.youlu.imageloader;

import android.app.Application;
import android.content.Context;

import com.youloft.imageloader.ImageLoaderUtil;

/**
 * @date：2018/7/20
 * @author: SyShare
 */
public class AppApplication extends Application {


    public static Context context;

    public static void exitAPP() {
        System.exit(0);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        ImageLoaderUtil.getInstance().setImageLoader(new GlideLoaderStrategy());
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        ImageLoaderUtil.getInstance().clearDiskCache();
        ImageLoaderUtil.getInstance().clearMemoryCache();
    }
}
