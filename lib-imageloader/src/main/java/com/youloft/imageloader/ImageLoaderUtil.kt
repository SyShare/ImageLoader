package com.youloft.imageloader

import android.content.Context
import android.support.annotation.NonNull
import java.io.File

/**
 * Author by Administrator , Date on 2018/10/26.
 * PS: Not easy to write code, please indicate.
 */
class ImageLoaderUtil private constructor() {


    private var sLoader: BaseImageLoaderStrategy? = null

    companion object {
        @JvmStatic
        fun getInstance(): ImageLoaderUtil {
            return SingletonHolder.INSTANCE
        }
    }

    private object SingletonHolder {
        val INSTANCE = ImageLoaderUtil()
    }

    //实现后的加载框架方法
    fun setImageLoader(loader: BaseImageLoaderStrategy?) {
        if (loader != null) {
            sLoader = loader
        }
    }

    fun with(@NonNull context: Context): LoaderOptions {
        return LoaderOptions(context)
    }

    fun loadOptions(context: Context, options: LoaderOptions) {
        sLoader?.loadImage(LoaderUtils.checkNotNull(context,"context is null"), options)
    }

    fun clearMemoryCache() {
        sLoader?.clearMemoryCache()
    }

    fun clearDiskCache() {
        sLoader?.clearDiskCache()
    }

}