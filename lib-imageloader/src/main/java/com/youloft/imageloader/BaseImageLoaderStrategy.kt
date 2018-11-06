package com.youloft.imageloader

import android.content.Context

/**
 * Author by Administrator , Date on 2018/10/26.
 * PS: Not easy to write code, please indicate.
 * 采用策略模式加载
 */
interface BaseImageLoaderStrategy {
    /**
     *设置加载配置
     */
    fun loadImage(context: Context, options: LoaderOptions)

    /**
     * 恢复图片加载
     */
    fun resumeRequests(context: Context)

    /**
     * 暂停图片加载
     */
    fun pauseRequests(context: Context)

    /**
     * 清理内存缓存
     */
    fun clearMemoryCache()

    /**
     * 清理磁盘缓存
     */
    fun clearDiskCache()

}