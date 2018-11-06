package com.youloft.imageloader

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.support.annotation.DrawableRes
import android.widget.ImageView
import java.io.File


/**
 * Author by Administrator , Date on 2018/10/26.
 * PS: Not easy to write code, please indicate.
 */
open class LoaderOptions(private var context: Context) {

    enum class ImageCornerType {
        ALL,
        TOP_LEFT, TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT,
        TOP, BOTTOM, LEFT, RIGHT
    }

    var placeholderResId: Int = 0
    var errorResId: Int = 0
    var isCenterCrop: Boolean = false
    var isCenterInside: Boolean = false
    var skipCache: Boolean = false //是否缓存到本地
    var config: Bitmap.Config = Bitmap.Config.RGB_565
    var targetWidth: Int = 0
    var targetHeight: Int = 0
    var bitmapAngle: Float = 0.toFloat() //圆角角度
    var cornerType: ImageCornerType = ImageCornerType.ALL//圆角角度
    var placeholder: Drawable? = null
    lateinit var targetView: ImageView //targetView展示图片
    var url: String? = null
    var file: File? = null
    var drawableResId: Int = 0

    fun load(url: String?): LoaderOptions {
        this.url = url
        return this
    }


    fun load(file: File?): LoaderOptions {
        this.file = file
        return this
    }

    fun load(drawableResId: Int): LoaderOptions {
        this.drawableResId = drawableResId
        return this
    }


    fun into(targetView: ImageView) {
        this.targetView = targetView
        ImageLoaderUtil.getInstance().loadOptions(context, this)
    }

    fun placeholder(@DrawableRes placeholderResId: Int): LoaderOptions {
        this.placeholderResId = placeholderResId
        return this
    }

    fun placeholder(placeholder: Drawable): LoaderOptions {
        this.placeholder = placeholder
        return this
    }

    fun error(@DrawableRes errorResId: Int): LoaderOptions {
        this.errorResId = errorResId
        return this
    }

    fun centerCrop(): LoaderOptions {
        isCenterCrop = true
        return this
    }

    fun centerInside(): LoaderOptions {
        isCenterInside = true
        return this
    }

    fun config(config: Bitmap.Config): LoaderOptions {
        this.config = config
        return this
    }

    fun resize(targetWidth: Int, targetHeight: Int): LoaderOptions {
        this.targetWidth = targetWidth
        this.targetHeight = targetHeight
        return this
    }

    /**
     * 圆角
     * @param bitmapAngle   度数
     * @param cornerType    圆角角度
     * @return
     */
    fun angle(bitmapAngle: Float, cornerType: ImageCornerType): LoaderOptions {
        this.bitmapAngle = bitmapAngle
        this.cornerType = cornerType
        return this
    }

    fun skipCache(skipNetCache: Boolean): LoaderOptions {
        this.skipCache = skipNetCache
        return this
    }
}