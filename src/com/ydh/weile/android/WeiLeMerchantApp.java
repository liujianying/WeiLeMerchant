package com.ydh.weile.android;

import android.app.Application;
import android.os.Build;

public class WeiLeMerchantApp extends Application{


    /**
     * 定位到的用户的纬度
     */
    public static double latitude = -1000;
    /**
     * 定位到的用户的经度
     */
    public static double longitude = -1000;


    public static WeiLeMerchantApp wlmApp;
    /**SDK版本号*/
    public static int SDK_INT = Build.VERSION.SDK_INT;
    public static WeiLeMerchantApp getInstance() {
        return wlmApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        wlmApp = this;

    }

}
