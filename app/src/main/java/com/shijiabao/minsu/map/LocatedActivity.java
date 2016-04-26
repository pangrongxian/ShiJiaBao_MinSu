package com.shijiabao.minsu.map;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

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

import butterknife.Bind;
import butterknife.ButterKnife;

public class LocatedActivity extends AppCompatActivity {

    @Bind(R.id.city)
    TextView cityText;
    @Bind(R.id.district)
    TextView districtText;
    private MapView mMapView;
    private BaiduMap mBaiduMap;

    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();
    private boolean isFirstLocation = true;
    private String city=null;
    private String district=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化SDK中的Context
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_located);
        ButterKnife.bind(this);

        //获取地图控件
        mMapView = (MapView) findViewById(R.id.mapViewLocate);
        //获取地图对象
        mBaiduMap = mMapView.getMap();

        //设置普通地图
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);

        //2.开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        mLocationClient = new LocationClient(getApplicationContext());     //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);    //注册监听函数
        setLocationOption();
        mLocationClient.start();

    }

    /**
     * 定位SDK监听函数
     */

    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // map view 销毁后不在处理新接收的位置
            if (location == null || mBaiduMap == null)
                return;
             city = location.getCity();
             district = location.getDistrict();
            Log.d("MyLocationListener==", city);
            Log.d("MyLocationListener===", district);

            cityText.setText(city);
            districtText.setText(district);

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
    private void setLocationOption(){
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
