package com.youlu.imageloader

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.youloft.imageloader.ImageLoaderUtil
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ImageLoaderUtil.getInstance()
                .with(this)
                .load(url = null)
                .error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher)
                .into(img)
    }
}
