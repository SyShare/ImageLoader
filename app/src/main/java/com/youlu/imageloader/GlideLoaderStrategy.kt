package com.youlu.imageloader

import android.content.Context
import android.graphics.drawable.Drawable
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.youloft.imageloader.BaseImageLoaderStrategy
import com.youloft.imageloader.LoaderOptions

/**
 * Author by Administrator , Date on 2018/10/26.
 * PS: Not easy to write code, please indicate.
 */
class GlideLoaderStrategy : BaseImageLoaderStrategy {



    override fun loadImage(context: Context, options: LoaderOptions) {
        val requestOptions = RequestOptions()

        when {
            options.errorResId > 0 -> requestOptions.error(options.errorResId)
            options.placeholderResId > 0 -> requestOptions.placeholder(options.placeholderResId)
        }

        when {
            options.isCenterCrop -> requestOptions.centerCrop()
            options.isCenterInside -> requestOptions.centerInside()
            else -> requestOptions.fitCenter()
        }
        if (options.skipCache) {
            requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE)
        } else {
            requestOptions.diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        }

        if (options.targetHeight > 0 && options.targetWidth > 0) {
            requestOptions.override(options.targetWidth, options.targetHeight)
        }

        if (options.bitmapAngle > 0) {
            //设置圆角角度
//            requestOptions.transform(RoundedCornersTransformation(
//                    10,
//                    0, LoaderOptions.ImageCornerType.ALL))
        }

        val requestManager = Glide.with(context)
                .applyDefaultRequestOptions(requestOptions)

        val requestBuilder: RequestBuilder<Drawable>?

        requestBuilder = if (options.file != null && options.file!!.exists()) {
            requestManager.load(options.file)
        } else if (options.drawableResId > 0) {
            requestManager.load(options.drawableResId)
        } else {
            requestManager.load(options.url)
        }
        requestBuilder.into(options.targetView)
    }

    override fun pauseRequests(context: Context) {
    }

    override fun resumeRequests(context: Context) {
    }

    override fun clearMemoryCache() {
        Glide.get(AppApplication.context).clearMemory()
    }

    override fun clearDiskCache() {
        Glide.get(AppApplication.context).clearDiskCache()
    }
}