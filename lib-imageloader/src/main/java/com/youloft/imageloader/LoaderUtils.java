package com.youloft.imageloader;

import android.support.annotation.Nullable;

/**
 * Author by Administrator , Date on 2018/10/26.
 * PS: Not easy to write code, please indicate.
 */
public class LoaderUtils {

    static <T> T checkNotNull(@Nullable T object, String message) {
        if (object == null) {
            throw new NullPointerException(message);
        }
        return object;
    }
}
