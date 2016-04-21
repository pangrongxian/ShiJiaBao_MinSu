//package com.shijiabao.minsu.ui.view;
//
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//
//import com.amap.api.maps.AMap;
//import com.amap.api.maps.MapView;
//import com.shijiabao.minsu.R;
//
//public class MapActivity extends AppCompatActivity {
//
//    //private AMapLocationClient mLocationClient;
//
//    private MapView mapView;
//    private AMap aMap;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_map);
//        mapView = (MapView) findViewById(R.id.map);
//        mapView.onCreate(savedInstanceState);// 此方法必须重写
//
//        init();
//
//    }
//
//    /**
//     * 初始化AMap对象
//     */
//    private void init() {
//        if (aMap == null) {
//            aMap = mapView.getMap();
//        }
//    }
//
//    /**
//     * 方法必须重写
//     */
//    @Override
//    protected void onResume() {
//        super.onResume();
//        mapView.onResume();
//    }
//
//    /**
//     * 方法必须重写
//     */
//    @Override
//    protected void onPause() {
//        super.onPause();
//        mapView.onPause();
//    }
//
//    /**
//     * 方法必须重写
//     */
//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        mapView.onSaveInstanceState(outState);
//    }
//
//    /**
//     * 方法必须重写
//     */
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        mapView.onDestroy();
//    }
//
//}
