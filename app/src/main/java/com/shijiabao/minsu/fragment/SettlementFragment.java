package com.shijiabao.minsu.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.integration.volley.VolleyUrlLoader;
import com.bumptech.glide.load.model.GlideUrl;
import com.shijiabao.minsu.R;
import com.shijiabao.minsu.adapter.HomeAdapter;
import com.shijiabao.minsu.common.MemoryCache;
import com.shijiabao.minsu.resource.Resource;
import com.shijiabao.minsu.ui.settlement.SettlementItemActivity;
import com.shijiabao.minsu.ui.view.CityPickerActivity;


import java.io.InputStream;

public class SettlementFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

	private RequestQueue mQueue;
	private ImageLoader mLoader;
	private String[] IMAGE_URLS = Resource.IMAGE_URLS;
	private ListView settlement_lv;
	private SwipeRefreshLayout swipeRefreshLayout;
	private ImageView settlement_head_image;

	Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			swipeRefreshLayout.setRefreshing(false);
		}
	};
	private HomeAdapter adapter;
	private Button all_city;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_settlement, container, false);
		View headerView =  inflater.inflate(R.layout.item_settlement_lv_header,container,false);

		initView(view,headerView);

		initAdapter();

		request();

		bindAdapter();

		listener();

		return view;
	}


	private void initView(View view ,View headerView) {
		settlement_lv = (ListView) view.findViewById(R.id.settlement_lv);
		settlement_head_image = (ImageView) headerView.findViewById(R.id.settlement_head_image);
		all_city = (Button) headerView.findViewById(R.id.all_city);

		swipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.SwipeRefresh);

		AbsListView.LayoutParams params = new AbsListView.LayoutParams(
				AbsListView.LayoutParams.MATCH_PARENT,AbsListView.LayoutParams.WRAP_CONTENT);
		headerView.setLayoutParams(params);

		settlement_lv.addHeaderView(headerView);

	}

	private void initAdapter() {
		adapter = new HomeAdapter(getActivity(),IMAGE_URLS);
	}

	private void bindAdapter() {
		settlement_lv.setAdapter(adapter);
	}

	private void request() {
		//新建一个请求
		mQueue = Volley.newRequestQueue(getActivity());
		//注册Volley,和Glide结合使用
		Glide.get(getActivity()).register(GlideUrl.class, InputStream.class, new VolleyUrlLoader.Factory(mQueue));
		//初始化ImageLoader
		mLoader = new ImageLoader(mQueue, new MemoryCache());
	}

	private void listener() {

		settlement_head_image.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {//跳转到SettlementItemActivity
				Intent intent = new Intent(getActivity(), SettlementItemActivity.class);
				startActivity(intent);
			}
		});

		all_city.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//跳转到所有城市
				Intent intent = new Intent(getActivity(), CityPickerActivity.class);
				startActivity(intent);
			}
		});


		//刷新监听
		swipeRefreshLayout.setOnRefreshListener(this);
		//设置刷新颜色条
		swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,R.color.colorAccent,R.color.comment,R.color.fraction);

	}

	@Override
	public void onRefresh() {//刷新监听
		handler.sendEmptyMessageDelayed(1, 3000);
	}
}
