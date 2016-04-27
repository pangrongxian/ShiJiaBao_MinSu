package com.shijiabao.minsu.ui.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import com.shijiabao.minsu.R;
import com.shijiabao.minsu.fragment.HomeFragment;
import com.shijiabao.minsu.fragment.MyFragment;
import com.shijiabao.minsu.fragment.SettlementFragment;
import com.shijiabao.minsu.fragment.TravelsFragment;


/**
 * 项目的主Activity，所有的Fragment都嵌入在这里。
 *
 * @author prx
 */
public class MainActivity extends FragmentActivity implements OnClickListener {

    /**
     * 用于展示主页的Fragment
     */
    private HomeFragment homeFragment;

    /**
     * 用于展示聚落的Fragment
     */
    private SettlementFragment settlementFragment;

    /**
     * 用于展示游记的Fragment
     */
    private TravelsFragment travelsFragment;

    /**
     * 用于展示我的的Fragment
     */
    private MyFragment myFragment;

    /**
     * 主页界面布局
     */
    private View homeLayout;

    /**
     * 聚落界面布局
     */
    private View settlementLayout;

    /**
     * 游记界面布局
     */
    private View travelsLayout;

    /**
     * 我的界面布局
     */
    private View myLayout;

    /**
     * 在Tab布局上显示主页图标的控件
     */
    private ImageView homeImage;

    /**
     * 在Tab布局上显示聚落图标的控件
     */
    private ImageView settlementImage;

    /**
     * 在Tab布局上显示游记图标的控件
     */
    private ImageView travelsImage;

    /**
     * 在Tab布局上显示我的图标的控件
     */
    private ImageView myImage;

    /**
     * 在Tab布局上显示主页标题的控件
     */
    private TextView homeText;

    /**
     * 在Tab布局上显示聚落标题的控件
     */
    private TextView settlementText;

    /**
     * 在Tab布局上显示游记标题的控件
     */
    private TextView travelsText;

    /**
     * 在Tab布局上显示我的标题的控件
     */
    private TextView myText;

    /**
     * 用于对Fragment进行管理
     */
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        // 初始化布局元素
        initViews();

        fragmentManager = getSupportFragmentManager();
        // 第一次启动时选中第0个tab
        setTabSelection(0);
    }

    /**
     * 在这里获取到每个需要用到的控件的实例，并给它们设置好必要的点击事件。
     */
    private void initViews() {
        homeLayout = findViewById(R.id.home_layout);
        settlementLayout = findViewById(R.id.settlement_layout);
        travelsLayout = findViewById(R.id.travels_layout);
        myLayout = findViewById(R.id.my_layout);

        homeImage = (ImageView) findViewById(R.id.home_image);
        settlementImage = (ImageView) findViewById(R.id.settlement_image);
        travelsImage = (ImageView) findViewById(R.id.travels_image);
        myImage = (ImageView) findViewById(R.id.my_image);

        homeText = (TextView) findViewById(R.id.home_text);
        settlementText = (TextView) findViewById(R.id.settlement_text);
        travelsText = (TextView) findViewById(R.id.travels_text);
        myText = (TextView) findViewById(R.id.my_text);

        homeLayout.setOnClickListener(this);
        settlementLayout.setOnClickListener(this);
        travelsLayout.setOnClickListener(this);
        myLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_layout:
                // 当点击了主页tab时，选中第1个tab
                setTabSelection(0);
                break;
            case R.id.settlement_layout:
                // 当点击了聚落tab时，选中第2个tab
                setTabSelection(1);
                break;
            case R.id.travels_layout:
                // 当点击了游记tab时，选中第3个tab
                setTabSelection(2);
                break;
            case R.id.my_layout:
                // 当点击了我的tab时，选中第4个tab
                setTabSelection(3);
                break;
            default:
                break;
        }
    }

    /**
     * 根据传入的index参数来设置选中的tab页。
     *
     * @param index 每个tab页对应的下标。0表示主页，1表示聚落，2表示游记，3表示我的。
     */
    private void setTabSelection(int index) {
        // 每次选中之前先清楚掉上次的选中状态
        clearSelection();
        // 开启一个Fragment事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);
        switch (index) {
            case 0:
                // 当点击了主页tab时，改变控件的图片和文字颜色
                homeImage.setImageResource(R.mipmap.tabbar_home_icon_sel);
                homeText.setTextColor(Color.parseColor("#53CAC3"));
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    transaction.add(R.id.content, homeFragment);
                } else {
                    transaction.show(homeFragment);
                }
                break;
            case 1:
                // 当点击了聚落tab时，改变控件的图片和文字颜色
                settlementImage.setImageResource(R.mipmap.tabbar_city_icon_sel);
                settlementText.setTextColor(Color.parseColor("#53CAC3"));
                if (settlementFragment == null) {
                    settlementFragment = new SettlementFragment();
                    transaction.add(R.id.content, settlementFragment);
                } else {
                    transaction.show(settlementFragment);
                }
                break;
            case 2:
                travelsImage.setImageResource(R.mipmap.tabbar_diary_icon_sel);
                travelsText.setTextColor(Color.parseColor("#53CAC3"));
                if (travelsFragment == null) {
                    travelsFragment = new TravelsFragment();
                    transaction.add(R.id.content, travelsFragment);
                } else {
                    transaction.show(travelsFragment);
                }

                break;
            case 3:
            default:
                // 当点击了我的tab时，改变控件的图片和文字颜色
                myImage.setImageResource(R.mipmap.tabbar_my_icon_sel);
                myText.setTextColor(Color.parseColor("#53CAC3"));
                if (myFragment == null) {
                    myFragment = new MyFragment();
                    transaction.add(R.id.content, myFragment);
                } else {
                    transaction.show(myFragment);
                }
                break;
        }
        transaction.commit();
    }

    /**
     * 清除掉所有的选中状态。
     */
    private void clearSelection() {
        homeImage.setImageResource(R.mipmap.tabbar_home_icon_nor);
        homeText.setTextColor(Color.parseColor("#82858b"));
        settlementImage.setImageResource(R.mipmap.tabbar_city_icon_nor);
        settlementText.setTextColor(Color.parseColor("#82858b"));
        travelsImage.setImageResource(R.mipmap.tabbar_diary_icon_nor);
        travelsText.setTextColor(Color.parseColor("#82858b"));
        myImage.setImageResource(R.mipmap.tabbar_my_icon_nor);
        myText.setTextColor(Color.parseColor("#82858b"));
    }

    /**
     * 将所有的Fragment都置为隐藏状态。
     *
     * @param transaction 用于对Fragment执行操作的事务
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (homeFragment != null) {
            transaction.hide(homeFragment);
        }
        if (settlementFragment != null) {
            transaction.hide(settlementFragment);
        }
        if (travelsFragment != null) {
            transaction.hide(travelsFragment);
        }
        if (myFragment != null) {
            transaction.hide(myFragment);
        }
    }
}
