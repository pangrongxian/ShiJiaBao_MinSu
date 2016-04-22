package com.shijiabao.minsu.ui.settlement;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shijiabao.minsu.R;
import com.shijiabao.minsu.ui.view.DatePickerActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettlementItemActivity extends AppCompatActivity {

    @Bind(R.id.searchBtn)
    LinearLayout searchBtn;
    @Bind(R.id.checkDay)
    TextView checkDay;
    @Bind(R.id.leaveDate)
    TextView leaveDate;
    @Bind(R.id.datePickerLinear)
    LinearLayout datePickerLinear;
    @Bind(R.id.settlement_item_lv)
    ListView settlementItemLv;
    @Bind(R.id.choiceTab)
    RelativeLayout choiceTab;
    @Bind(R.id.positionTab)
    RelativeLayout positionTab;
    @Bind(R.id.priceTab)
    RelativeLayout priceTab;
    @Bind(R.id.sortTab)
    RelativeLayout sortTab;

    private SharedPreferences sharedPreferences;
    String inday, outday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settlement_item);
        ButterKnife.bind(this);
        sharedPreferences = getSharedPreferences("date", Context.MODE_PRIVATE);
    }

    @OnClick(R.id.datePickerLinear)
    public void onClick() {
        Intent intent = new Intent(this, DatePickerActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        inday = sharedPreferences.getString("dateIn", "");
        outday = sharedPreferences.getString("dateOut", "");
        if (!"".equals(inday)) {
            checkDay.setText(inday);
        }
        if (!"".equals(outday)) {
            leaveDate.setText(outday);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //退出就清空 sharedPreferences
       // sharedPreferences.edit().clear().commit();
    }
}
