package com.shijiabao.minsu.fragment.travels;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.shijiabao.minsu.R;
import com.shijiabao.minsu.adapter.StaggeredHomeAdapter;
import java.util.ArrayList;
import java.util.List;


public class NewestFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    private StaggeredHomeAdapter mStaggeredHomeAdapter;

    private SwipeRefreshLayout NewsSwipeRefresh;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            NewsSwipeRefresh.setRefreshing(false);
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_newest, container, false);

        initView(view);

        initData();

        bindAdapter();

        listener();

        return view;
    }

    private void listener() {
        NewsSwipeRefresh.setOnRefreshListener(this);//刷新监听
        NewsSwipeRefresh.setColorSchemeResources(R.color.colorPrimary,R.color.colorAccent,R.color.comment,R.color.fraction);

    }

    private void bindAdapter() {
        mStaggeredHomeAdapter = new StaggeredHomeAdapter(getActivity(), mDatas);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(mStaggeredHomeAdapter);
        // 设置item动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        initEvent();
    }

    private void initView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.travels_newest_recycler);
        NewsSwipeRefresh = (SwipeRefreshLayout)view.findViewById(R.id.NewsSwipeRefresh);//刷新控件
    }


    private void initData() {
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++) {
            mDatas.add("" + (char) i);
        }
    }

    private void initEvent() {
        mStaggeredHomeAdapter.setOnItemClickLitener(new StaggeredHomeAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getActivity(),
                        position + " click", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(getActivity(),
                        position + " long click", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onRefresh() {
        handler.sendEmptyMessageDelayed(1, 5000);
    }
}
