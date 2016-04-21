package com.shijiabao.minsu.adapter;

import android.content.Context;

import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.abslistview.CommonAdapter;

import java.util.List;

/**
 * Created by prx on 2016/4/20 23:30.
 * Email:pangrongxian@gmail.com
 */
public class TestAdapter extends CommonAdapter<Integer> {

    public TestAdapter(Context context, int layoutId, List<Integer> datas) {
        super(context, layoutId, datas);
    }

    @Override
    public void convert(ViewHolder holder, Integer integer) {

    }
}
