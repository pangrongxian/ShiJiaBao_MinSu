package com.shijiabao.minsu.app;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.shijiabao.minsu.pototpicker.utils.CustomConstants;

import cn.sharesdk.framework.ShareSDK;
import cn.smssdk.SMSSDK;

/**
 * Created by prx on 2016/4/19 21:24.
 * Email:pangrongxian@gmail.com
 */
public class MyApplication extends Application {

    public static Context context;

    @Override
    public void onCreate() {//初始化
        super.onCreate();

        context = getApplicationContext();

//        //初始化SDK中的Context
        SDKInitializer.initialize(getApplicationContext());
        removeTempFromPref();

        ShareSDK.initSDK(this);

        SMSSDK.initSDK(this,"123c05bcf3380","58292a9bf150cd3f2fb2e7418d48defe");

    }

    public static Context getContext() {//获取全局环境
        return context;
    }

    private void removeTempFromPref() {
        SharedPreferences sp = getSharedPreferences(
                CustomConstants.APPLICATION_NAME, MODE_PRIVATE);
        sp.edit().remove(CustomConstants.PREF_TEMP_IMAGES).commit();
    }


}
