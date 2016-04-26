package com.shijiabao.minsu.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.shijiabao.minsu.R;
import com.shijiabao.minsu.adapter.HomeItemRecyclerViewAdapter;
import com.shijiabao.minsu.common.DividerGridItemDecoration;
import com.shijiabao.minsu.map.LocatedActivity;
import com.shijiabao.minsu.ui.view.AppointmentActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class HomeItemLocateActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    private HomeItemRecyclerViewAdapter mAdapter;

    private MapView mMapView;
    private BaiduMap mBaiduMap;

    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();
    private boolean isFirstLocation = true;

    private Button appointmentBtn,seeMapBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化SDK中的Context
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_home_item2);
        ButterKnife.bind(this);

        initView();
        initData();
        bindAdapter();

        //获取地图控件
        mMapView = (MapView) findViewById(R.id.mapViewLocation);
        //获取地图对象
        mBaiduMap = mMapView.getMap();

        //设置普通地图
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        //设置地图大小
        mBaiduMap.setMaxAndMinZoomLevel(18,15);

        //2.开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        mLocationClient = new LocationClient(getApplicationContext());     //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);    //注册监听函数
        setLocationOption();
        mLocationClient.start();

    }


    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.home_item_recyclerView);
        appointmentBtn = (Button) findViewById(R.id.appointmentBtn);
        seeMapBtn = (Button) findViewById(R.id.seeMapBtn);
        appointmentBtn.setOnClickListener(this);
        seeMapBtn.setOnClickListener(this);
    }

    private void initData() {
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++) {
            mDatas.add("" + (char) i);
        }
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.appointmentBtn:
                Intent intent2 = new Intent(this, AppointmentActivity.class);
                startActivity(intent2);
                break;
            //查看全屏地图
            case R.id.seeMapBtn:
                Intent intent = new Intent(this, LocatedActivity.class);
                startActivity(intent);
                break;
        }
    }

//    @OnClick({R.id.seeMap, R.id.appointmentBtn})
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.seeMap:
//                //查看全屏地图
//                Intent intent = new Intent(this, LocatedActivity.class);
//                startActivity(intent);
//                break;
//            case R.id.appointmentBtn:
//                Intent intent2 = new Intent(this, AppointmentActivity.class);
//                startActivity(intent2);
//                break;
//        }
//    }

    /**
     * 定位SDK监听函数
     */

    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // map view 销毁后不在处理新接收的位置
            if (location == null || mBaiduMap == null)
                return;
            String city = location.getCity();
            String district = location.getDistrict();
            Log.d("MyLocationListener==", city);
            Log.d("MyLocationListener===", district);

            MyLocationData locationData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    .direction(100).latitude(location.getLatitude())
                    .longitude(location.getLongitude())
                    .build();


            //4.设置定位数据
            mBaiduMap.setMyLocationData(locationData);

            //判断是否是第一次定位
            if (isFirstLocation) {
                isFirstLocation = false;
                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

                MapStatusUpdate up = MapStatusUpdateFactory.newLatLng(latLng);
                mBaiduMap.animateMapStatus(up);
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mLocationClient.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.stop();
        mBaiduMap.setMyLocationEnabled(false);
    }

    //设置相关参数
    private void setLocationOption() {
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true);
        option.setCoorType("bd09ll");
        option.setScanSpan(1000);

        //添加以下参数才能获取到城市和市区的信息
        option.setServiceName("com.baidu.location.service_v2.9");
        option.setAddrType("all");
        option.setPriority(LocationClientOption.NetWorkFirst);
        option.setPriority(LocationClientOption.GpsFirst);       //gps
        option.disableCache(true);
        mLocationClient.setLocOption(option);
    }

}
