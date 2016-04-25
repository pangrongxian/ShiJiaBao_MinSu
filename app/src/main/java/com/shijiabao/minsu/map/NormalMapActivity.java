package com.shijiabao.minsu.map;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.shijiabao.minsu.R;

/**
 * 普通地图
 */
public class NormalMapActivity extends AppCompatActivity {

    private MapView mMapView;
    private BaiduMap mBaiduMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //初始化SDK中的Context
        SDKInitializer.initialize(getApplicationContext());

        setContentView(R.layout.activity_normal_map);

        //获取地图控件
        mMapView = (MapView) findViewById(R.id.mapView);
        //获取地图对象
        mBaiduMap = mMapView.getMap();

        //设置普通地图
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
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
    }

}
