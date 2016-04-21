package com.shijiabao.minsu.fragment.travels;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shijiabao.minsu.R;


public class NearbyFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View newsLayout = inflater.inflate(R.layout.fragment_nearby, container,
				false);
		return newsLayout;
	}

}
