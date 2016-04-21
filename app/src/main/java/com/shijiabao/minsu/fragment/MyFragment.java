package com.shijiabao.minsu.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.shijiabao.minsu.R;
import com.shijiabao.minsu.ui.my.MyCollectionActivity;
import com.shijiabao.minsu.ui.my.MyCompletedActivity;
import com.shijiabao.minsu.ui.my.MyConsultationActivity;
import com.shijiabao.minsu.ui.my.MyEvaluateActivity;
import com.shijiabao.minsu.ui.my.MyFocusActivity;
import com.shijiabao.minsu.ui.my.MyHeadActivity;
import com.shijiabao.minsu.ui.my.MyIntegralActivity;
import com.shijiabao.minsu.ui.my.MyMessageActivity;
import com.shijiabao.minsu.ui.my.MyMinSuActivity;
import com.shijiabao.minsu.ui.my.MyOpenMinSuActivity;
import com.shijiabao.minsu.ui.my.MyOperationActivity;
import com.shijiabao.minsu.ui.my.MyOrderActivity;
import com.shijiabao.minsu.ui.my.MySettingActivity;
import com.shijiabao.minsu.ui.my.MyWalletActivity;


public class MyFragment extends Fragment implements View.OnClickListener {

	private ImageView my_news_image, my_head_image,
			my_setting_image, my_collection_image,
			my_integral_image, my_focus_image,
			my_operation_image, my_complete_image, my_evaluate_image,
			my_open_image, my_order_image, my_consultation_image,
			my_wallet_iamge, my_minsu_image;



	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_my,
				container, false);
		initView(view);

		listener();

		return view;
	}



	private void listener() {
		my_news_image.setOnClickListener(this);
		my_head_image.setOnClickListener(this);
		my_setting_image.setOnClickListener(this);
		my_collection_image.setOnClickListener(this);
		my_integral_image.setOnClickListener(this);
		my_focus_image.setOnClickListener(this);
		my_operation_image.setOnClickListener(this);
		my_complete_image.setOnClickListener(this);
		my_evaluate_image.setOnClickListener(this);
		my_open_image.setOnClickListener(this);
		my_order_image.setOnClickListener(this);
		my_consultation_image.setOnClickListener(this);
		my_wallet_iamge.setOnClickListener(this);
		my_minsu_image.setOnClickListener(this);

	}

	private void initView(View view) {
		my_news_image = (ImageView) view.findViewById(R.id.my_news_image);
		my_head_image = (ImageView) view.findViewById(R.id.my_head_image);
		my_setting_image = (ImageView) view.findViewById(R.id.my_setting_image);
		my_collection_image = (ImageView) view.findViewById(R.id.my_collection_image);
		my_integral_image = (ImageView) view.findViewById(R.id.my_integral_image);
		my_focus_image = (ImageView) view.findViewById(R.id.my_focus_image);
		my_operation_image = (ImageView) view.findViewById(R.id.my_operation_image);
		my_complete_image = (ImageView) view.findViewById(R.id.my_complete_image);
		my_evaluate_image = (ImageView) view.findViewById(R.id.my_evaluate_image);
		my_open_image = (ImageView) view.findViewById(R.id.my_open_image);
		my_order_image = (ImageView) view.findViewById(R.id.my_order_image);
		my_consultation_image = (ImageView) view.findViewById(R.id.my_consultation_image);
		my_wallet_iamge = (ImageView) view.findViewById(R.id.my_wallet_iamge);
		my_minsu_image = (ImageView) view.findViewById(R.id.my_minsu_image);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.my_news_image :
				Intent intent1 = new Intent(getActivity(), MyMessageActivity.class);
				startActivity(intent1);
				break;
			case R.id.my_head_image :
				Intent intent2 = new Intent(getActivity(), MyHeadActivity.class);
				startActivity(intent2);
				break;
			case R.id.my_setting_image :
				Intent intent = new Intent(getActivity(), MySettingActivity.class);
				startActivity(intent);
				break;
			case R.id.my_collection_image :
				Intent intent3 = new Intent(getActivity(), MyCollectionActivity.class);
				startActivity(intent3);
				break;
			case R.id.my_integral_image :
				Intent intent4 = new Intent(getActivity(), MyIntegralActivity.class);
				startActivity(intent4);
				break;
			case R.id.my_focus_image :
				Intent intent5 = new Intent(getActivity(), MyFocusActivity.class);
				startActivity(intent5);
				break;
			case R.id.my_operation_image :
				Intent intent6 = new Intent(getActivity(), MyOperationActivity.class);
				startActivity(intent6);
				break;
			case R.id.my_complete_image :
				Intent intent7 = new Intent(getActivity(), MyCompletedActivity.class);
				startActivity(intent7);
				break;
			case R.id.my_evaluate_image :
				Intent intent8 = new Intent(getActivity(), MyEvaluateActivity.class);
				startActivity(intent8);
				break;
			case R.id.my_open_image :
				Intent intent9 = new Intent(getActivity(), MyOpenMinSuActivity.class);
				startActivity(intent9);
				break;
			case R.id.my_order_image :
				Intent intent10 = new Intent(getActivity(), MyOrderActivity.class);
				startActivity(intent10);
				break;
			case R.id.my_consultation_image :
				Intent intent11 = new Intent(getActivity(), MyConsultationActivity.class);
				startActivity(intent11);
				break;
			case R.id.my_wallet_iamge :
				Intent intent12 = new Intent(getActivity(), MyWalletActivity.class);
				startActivity(intent12);
				break;
			case R.id.my_minsu_image :
				Intent intent13 = new Intent(getActivity(), MyMinSuActivity.class);
				startActivity(intent13);
				break;
		}

	}
}
