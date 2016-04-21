package com.shijiabao.minsu.ui.travels;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.shijiabao.minsu.R;

public class TitleWriteActivity extends AppCompatActivity {

    private TextView cancel_write;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title_write);

        cancel_write = (TextView) findViewById(R.id.cancel_write);
        cancel_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
