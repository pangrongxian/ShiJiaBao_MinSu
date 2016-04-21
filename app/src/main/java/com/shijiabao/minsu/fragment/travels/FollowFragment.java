package com.shijiabao.minsu.fragment.travels;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shijiabao.minsu.R;


public class FollowFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View newsLayout = inflater.inflate(R.layout.fragment_follow, container,
				false);
		return newsLayout;
	}

}
