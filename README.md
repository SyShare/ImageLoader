# ImageLoader
解决三方库所造成的耦合与依赖



# 继承BaseImageLoaderStrategy 重写对应的方法

   #1、如下[可以直接看Demo]

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

   #2、在application中初始化对应的加载策略,没有设置对应的图库加载类
