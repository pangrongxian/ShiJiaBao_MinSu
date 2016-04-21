package com.shijiabao.minsu.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.shijiabao.minsu.R;
import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.abslistview.CommonAdapter;

import java.util.List;

/**
 * Created by prx on 2016/4/16 01:47.
 * Email:pangrongxian@gmail.com
 */
class DemoAdapter extends CommonAdapter {


    public DemoAdapter(Context context, int layoutId, List datas) {
        super(context, layoutId, datas);
    }

    @Override
    public void convert(ViewHolder holder, Object o) {
        //
    }
}

