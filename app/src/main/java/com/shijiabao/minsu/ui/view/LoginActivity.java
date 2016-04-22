package com.shijiabao.minsu.ui.view;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.shijiabao.minsu.R;
import com.shijiabao.minsu.custom.CustomDialog;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends Activity {

    @Bind(R.id.loginBack)
    ImageView loginBack;
    @Bind(R.id.phone_Number)
    EditText phoneNumber;
    @Bind(R.id.VerificationCodeBtn)
    Button VerificationCodeBtn;
    @Bind(R.id.short_Message)
    EditText shortMessage;
    @Bind(R.id.loginBtn)
    Button loginBtn;
    @Bind(R.id.loginQQ)
    ImageView loginQQ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.loginBack, R.id.phone_Number, R.id.VerificationCodeBtn, R.id.short_Message, R.id.loginBtn, R.id.loginQQ})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.loginBack:
                finish();
                break;
            case R.id.phone_Number:
                Toast.makeText(this, "phone_Number", Toast.LENGTH_SHORT).show();
                break;
            case R.id.VerificationCodeBtn:
                Toast.makeText(this, "VerificationCodeBtn", Toast.LENGTH_SHORT).show();
                break;
            case R.id.short_Message:
                Toast.makeText(this, "short_Message", Toast.LENGTH_SHORT).show();
                break;
            case R.id.loginBtn:
                Toast.makeText(this, "loginBtn", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this,PerfectInformationActivity.class);
                startActivity(intent);
                break;
            case R.id.loginQQ:
                Toast.makeText(this, "loginQQ", Toast.LENGTH_SHORT).show();
//                Intent intentQQ = new Intent(this,TimePickerActivity.class);
//                startActivity(intentQQ);
                showAlertDialog();
                break;
        }
    }

    /**
     * 对话框
     */
    public void showAlertDialog() {
        CustomDialog.Builder builder = new CustomDialog.Builder(this);
        builder.setTitle("标题");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                //设置你的操作事项
                Toast.makeText(LoginActivity.this, "设置你的操作事项", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("取消",
                new android.content.DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        //setMessage

        builder.create().show();
    }

}