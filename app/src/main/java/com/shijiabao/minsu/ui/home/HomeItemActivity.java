package com.shijiabao.minsu.ui.home;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.shijiabao.minsu.R;
import com.shijiabao.minsu.adapter.HomeItemRecyclerViewAdapter;
import com.shijiabao.minsu.common.DividerGridItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class HomeItemActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    private HomeItemRecyclerViewAdapter mAdapter;

    public HomeItemActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_item2);
        initView();
        initData();
        bindAdapter();
    }

    private void bindAdapter() {
        mAdapter = new HomeItemRecyclerViewAdapter(this, mDatas);
        //设置 RecyclerView 横向滑动
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addItemDecoration(new DividerGridItemDecoration(this));
//        // 设置item动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void initData() {
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++)
        {
            mDatas.add("" + (char) i);
        }
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.home_item_recyclerView);
    }
}
