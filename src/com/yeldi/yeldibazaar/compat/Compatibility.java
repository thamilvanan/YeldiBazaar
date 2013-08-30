package com.yeldi.yeldibazaar.compat;

import android.os.Build;

import com.yeldi.yeldibazaar.Utils;

public abstract class Compatibility {

    protected static boolean hasApi(int apiLevel) {
        return getApi() >= apiLevel;
    }

    protected static int getApi() {
        return Build.VERSION.SDK_INT;
    }

}
