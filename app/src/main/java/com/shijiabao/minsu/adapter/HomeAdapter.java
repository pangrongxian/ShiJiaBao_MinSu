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
 * Created by prx on 2016/4/15 01:32.
 * Email:pangrongxian@gmail.com
 */
public class HomeAdapter extends BaseAdapter {

    private String[] IMAGE_URLS;

    private Context context;

    public HomeAdapter(Context context,String[] IMAGE_URLS) {
        this.IMAGE_URLS = IMAGE_URLS;
        this.context = context;
    }

    @Override
    public int getCount() {
        return IMAGE_URLS.length;
    }

    @Override
    public String getItem(int position) {
        return IMAGE_URLS[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View cv, ViewGroup parent) {

        if (cv == null){

            cv = View.inflate(context, R.layout.item_settlement_lv,null);

        }

        ImageView iv  = (ImageView) cv.findViewById(R.id.settlement_imageView);

        Glide.with(context)
                .load(getItem(position))
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .crossFade()
                .into(iv);

        return cv;
    }
}
