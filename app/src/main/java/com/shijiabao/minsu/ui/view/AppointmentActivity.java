package com.shijiabao.minsu.ui.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.shijiabao.minsu.R;

public class AppointmentActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

    }

    public void commit(View view) {
        //点击跳转到订单详情界面
        Intent intent = new Intent(this, OrderDetailsActivity.class);
        startActivity(intent);
    }



}
