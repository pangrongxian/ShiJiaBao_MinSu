package com.shijiabao.minsu.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.shijiabao.minsu.R;
import com.shijiabao.minsu.adapter.HomeFragmentAdapter;
import com.shijiabao.minsu.map.LocatedActivity;
import com.shijiabao.minsu.resource.Resource;
import com.shijiabao.minsu.ui.home.HomeItemActivity;
import com.shijiabao.minsu.ui.home.HomeItemLocateActivity;
import com.shijiabao.minsu.ui.home.HomeShareActivity;

public class HomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

	private RollPagerView mRollViewPager;
	private ListView home_lv;
	private SwipeRefreshLayout swipeRefreshLayout;
	private HomeFragmentAdapter adapter;

	Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			swipeRefreshLayout.setRefreshing(false);
		}
	};

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_home,
				container, false);

		View headerView =  inflater.inflate(R.layout.item_fragment_home_header,container,false);

		initView(view,headerView);

		initAdapter();

		bindAdapter();

		listener();

		return view;
	}

	private void initView(View view,View headerView) {

		home_lv = (ListView) view.findViewById(R.id.home_lv);//ListView

		swipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.SwipeRefresh);//刷新控件

		mRollViewPager = (RollPagerView) headerView.findViewById(R.id.roll_view_pager);//广告ViewPager
		Button home_search_btn = (Button) headerView.findViewById(R.id.home_search_btn);
		ImageView home_hot_image = (ImageView) headerView.findViewById(R.id.home_hot_image);
		ImageView home_active_image = (ImageView) headerView.findViewById(R.id.home_active_image);
		ImageView home_travel_image = (ImageView) headerView.findViewById(R.id.home_travel_image);
		ImageView home_share_image = (ImageView) headerView.findViewById(R.id.home_share_image);

		home_share_image.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), HomeShareActivity.class);
				startActivity(intent);
			}
		});


		AbsListView.LayoutParams params = new AbsListView.LayoutParams(
				AbsListView.LayoutParams.MATCH_PARENT,AbsListView.LayoutParams.WRAP_CONTENT);
		headerView.setLayoutParams(params);//设置HeaderView参数

		home_lv.addHeaderView(headerView);//添加ListView的头布局
	}



	private void initAdapter() {
		adapter = new HomeFragmentAdapter(getActivity(), Resource.IMAGE_URLS);
	}

	private void bindAdapter() {
		mRollViewPager.setPlayDelay(2500);
		mRollViewPager.setAnimationDurtion(300);
		mRollViewPager.setHintView(new ColorPointHintView(getActivity(), Color.WHITE,Color.GRAY));//滑动圆点设置
		mRollViewPager.setAdapter(new TestLoopAdapter(mRollViewPager));
		home_lv.setAdapter(adapter);
	}

	private void listener() {
		swipeRefreshLayout.setOnRefreshListener(this);//刷新监听

		swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,R.color.colorAccent,R.color.comment,R.color.fraction);

	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		home_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				//点击跳转到HomeItemActivity
				Intent intent = new Intent(getActivity(), HomeItemLocateActivity.class);
				startActivity(intent);
			}
		});
	}

	@Override
	public void onRefresh() {//刷新监听事件
		handler.sendEmptyMessageDelayed(1, 5000);
	}

	private class TestLoopAdapter extends LoopPagerAdapter {//广告栏的adapter
		private int[] imgs = {//广告图片数据源
				R.drawable.img1,
				R.drawable.img2,
				R.drawable.img3,
		};

		public TestLoopAdapter(RollPagerView viewPager) {
			super(viewPager);
		}

		@Override
		public View getView(ViewGroup container, final int position) {
			ImageView view = new ImageView(container.getContext());
			view.setImageResource(imgs[position]);
			view.setScaleType(ImageView.ScaleType.CENTER_CROP);
			view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
			//广告图片的item点击事件
			view.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					switch (position){
						case 0:
							Toast.makeText(getActivity(),"position="+position,Toast.LENGTH_SHORT).show();
							break;
						case 1:
							Toast.makeText(getActivity(),"position==="+position,Toast.LENGTH_SHORT).show();
							break;
						case 2:
							Toast.makeText(getActivity(),"position==="+position,Toast.LENGTH_SHORT).show();
							break;
					}

				}
			});

			return view;
		}

		@Override
		public int getRealCount() {
			return imgs.length;
		}

	}

}
