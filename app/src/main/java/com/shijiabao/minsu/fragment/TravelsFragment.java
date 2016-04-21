package com.shijiabao.minsu.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shijiabao.minsu.R;
import com.shijiabao.minsu.adapter.ViewPagerFragmentAdapter;
import com.shijiabao.minsu.fragment.travels.FollowFragment;
import com.shijiabao.minsu.fragment.travels.HotFragment;
import com.shijiabao.minsu.fragment.travels.NearbyFragment;
import com.shijiabao.minsu.fragment.travels.NewestFragment;
import com.shijiabao.minsu.ui.travels.TitleWriteActivity;

import java.util.ArrayList;
import java.util.List;


public class TravelsFragment extends Fragment implements View.OnClickListener {

    private List<Fragment> fragmnets;
    private ViewPager viewPager;
    private TextView title1, title2, title3, title4;
    private ArrayList<TextView> titleList;
    private TextView titleLine1, titleLine2, titleLine3, titleLine4;
    private ArrayList<TextView> titleLineList;
    private View view;
    private ViewPagerFragmentAdapter adapter;
    private View newsLayout, hotLayout, nearbyLayout, followLayout;
    private ImageView write_travels;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_travels, container,
                false);

        initView(view);

        initColor();

        initViewPager(view);

        initPager();

        initAdapter();

        bindAdapter();

        setListener();

        return view;
    }

    private void initColor() {
        titleList.get(0).setTextColor(Color.parseColor("#8AD6D1"));
        titleLineList.get(0).setBackgroundColor(Color.parseColor("#8AD6D1"));
    }

    private void initViewPager(View view) {
        viewPager = (ViewPager) view.findViewById(R.id.travels_view_pager);
    }

    private void initPager() {
        fragmnets = new ArrayList<>();
        NewestFragment newestFragment = new NewestFragment();
        HotFragment hotFragment = new HotFragment();
        NearbyFragment nearbyFragment = new NearbyFragment();
        FollowFragment followFragment = new FollowFragment();
        viewPager.setOffscreenPageLimit(4);//缓存Fragment数据

        fragmnets.add(newestFragment);
        fragmnets.add(hotFragment);
        fragmnets.add(nearbyFragment);
        fragmnets.add(followFragment);
    }

    private void initAdapter() {
        adapter = new ViewPagerFragmentAdapter(getActivity().getSupportFragmentManager(), fragmnets);
    }

    private void bindAdapter() {
        viewPager.setAdapter(adapter);
    }

    private void setListener() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(final int position) {

                if (position < 4) {
                    for (int i = 0; i < 4; i++) {
                        titleList.get(i).setTextColor(Color.GRAY);
                        titleLineList.get(i).setBackgroundColor(Color.WHITE);
                    }
                    titleLineList.get(position).setBackgroundColor(Color.parseColor("#8AD6D1"));
                    titleList.get(position).setTextColor(Color.parseColor("#8AD6D1"));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //写游记
        write_travels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TitleWriteActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initView(View view) {

        write_travels = (ImageView) view.findViewById(R.id.write_travels);


        title1 = (TextView) view.findViewById(R.id.title1);
        title2 = (TextView) view.findViewById(R.id.title2);
        title3 = (TextView) view.findViewById(R.id.title3);
        title4 = (TextView) view.findViewById(R.id.title4);

        titleList = new ArrayList<>();
        titleList.add(title1);
        titleList.add(title2);
        titleList.add(title3);
        titleList.add(title4);

        titleLine1 = (TextView) view.findViewById(R.id.underLine1);
        titleLine2 = (TextView) view.findViewById(R.id.underLine2);
        titleLine3 = (TextView) view.findViewById(R.id.underLine3);
        titleLine4 = (TextView) view.findViewById(R.id.underLine4);

        titleLineList = new ArrayList<>();
        titleLineList.add(titleLine1);
        titleLineList.add(titleLine2);
        titleLineList.add(titleLine3);
        titleLineList.add(titleLine4);

        newsLayout = view.findViewById(R.id.newsLayout);
        hotLayout = view.findViewById(R.id.hotLayout);
        nearbyLayout = view.findViewById(R.id.nearbyLayout);
        followLayout = view.findViewById(R.id.followLayout);

        newsLayout.setOnClickListener(this);
        hotLayout.setOnClickListener(this);
        nearbyLayout.setOnClickListener(this);
        followLayout.setOnClickListener(this);
    }

    private int currentIndex = 0;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.newsLayout:
                currentIndex = 0;
                break;
            case R.id.hotLayout:
                currentIndex = 1;
                break;
            case R.id.nearbyLayout:
                currentIndex = 2;
                break;
            case R.id.followLayout:
                currentIndex = 3;
                break;
            default:
        }
        changeTitle(currentIndex);
    }

    private void changeTitle(int index) {
        for (int i = 0; i < 4; i++) {
            titleList.get(i).setTextColor(Color.GRAY);
            titleLineList.get(i).setBackgroundColor(Color.WHITE);
        }
        titleList.get(index).setTextColor(Color.parseColor("#8AD6D1"));
        titleLineList.get(index).setBackgroundColor(Color.parseColor("#8AD6D1"));
        viewPager.setCurrentItem(index);
    }
}
