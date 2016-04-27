package com.shijiabao.minsu.ui.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.shijiabao.minsu.R;
import com.shijiabao.minsu.pototpicker.view.PublishActivity;

public class HomeShareActivity extends Activity {

    private Button home_share_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_fragment_share);
        home_share_cancel = (Button) findViewById(R.id.home_share_cancel);
        home_share_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void sharePhoto(View view) {
        //点击跳转到图片选择器Activity
        Intent intent = new Intent(this, PublishActivity.class);
        startActivity(intent);
    }
}
