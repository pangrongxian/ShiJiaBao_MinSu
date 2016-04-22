package com.shijiabao.minsu.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import com.shijiabao.minsu.R;
import com.shijiabao.minsu.adapter.HomeItemRecyclerViewAdapter;
import com.shijiabao.minsu.common.DividerGridItemDecoration;
import com.shijiabao.minsu.ui.view.AppointmentActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeItemActivity extends AppCompatActivity {

    @Bind(R.id.appointmentBtn)
    Button appointmentBtn;
    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    private HomeItemRecyclerViewAdapter mAdapter;

    public HomeItemActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_item2);
        ButterKnife.bind(this);
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
        for (int i = 'A'; i < 'z'; i++) {
            mDatas.add("" + (char) i);
        }
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.home_item_recyclerView);
    }

    @OnClick(R.id.appointmentBtn)
    public void onClick() {
        //点击跳转到预约界面
        Intent intent = new Intent(this, AppointmentActivity.class);
        startActivity(intent);
    }
}
