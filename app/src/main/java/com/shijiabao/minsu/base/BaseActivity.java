package com.shijiabao.minsu.base;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by prx on 2016/4/19 20:31.
 * Email:pangrongxian@gmail.com
 */
public abstract class BaseActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView(savedInstanceState);//加载layout布局文件，初始化控件，为控件挂上事件方法
        loadData();//调用API获取数据
        listener();
    }
    protected abstract void initView(Bundle savedInstanceState);
    protected abstract void loadData();
    protected abstract void listener();
}
