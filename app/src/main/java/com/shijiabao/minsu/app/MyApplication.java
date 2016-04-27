package com.shijiabao.minsu.app;

import android.app.Application;
import android.content.SharedPreferences;

import com.baidu.mapapi.SDKInitializer;
import com.shijiabao.minsu.pototpicker.utils.CustomConstants;

/**
 * Created by prx on 2016/4/19 21:24.
 * Email:pangrongxian@gmail.com
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {//初始化
        super.onCreate();

//        //初始化SDK中的Context
//        SDKInitializer.initialize(getApplicationContext());

        removeTempFromPref();

    }

    private void removeTempFromPref() {
        SharedPreferences sp = getSharedPreferences(
                CustomConstants.APPLICATION_NAME, MODE_PRIVATE);
        sp.edit().remove(CustomConstants.PREF_TEMP_IMAGES).commit();
    }


}
