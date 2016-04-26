package com.shijiabao.minsu.ui.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.shijiabao.minsu.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AppointmentActivity extends AppCompatActivity {

    @Bind(R.id.commitBtn)
    Button commitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.commitBtn)
    public void onClick() {
        //点击跳转到订单详情界面
        Intent intent = new Intent(this, OrderDetailsActivity.class);
        startActivity(intent);
    }
}
