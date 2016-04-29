package com.shijiabao.minsu.ui.view;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.telephony.SmsMessage;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.shijiabao.minsu.R;
import com.shijiabao.minsu.app.MyApplication;
import com.shijiabao.minsu.custom.CustomDialog;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

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
        SMSSDK.initSDK(this,"123c05bcf3380","58292a9bf150cd3f2fb2e7418d48defe");
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        listener();

    }

    @OnClick({R.id.loginBack, R.id.phone_Number, R.id.VerificationCodeBtn, R.id.short_Message, R.id.loginBtn, R.id.loginQQ})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.loginBack:
                finish();
                break;
            case R.id.VerificationCodeBtn:
                String phoneNumber = this.phoneNumber.getText().toString();
                Toast.makeText(this, "获取验证码", Toast.LENGTH_SHORT).show();
                if (!phoneNumber.equals("")){
                    Log.d("LoginActivity", phoneNumber);
                    getVerification("86",phoneNumber);
                    getCountriesList();
                    //获取短信目前支持的国家列表，在监听中返回
                }

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
//                showAlertDialog();
                break;
        }
    }

    private void getCountriesList() {
        SMSSDK.getSupportedCountries();
    }

    private void getVerification(String country, String phone) {
        Log.d("LoginActivity", "getVerificationCode");
        SMSSDK.getVerificationCode(country,phone);
    }

    private void listener() {

        EventHandler eh = new EventHandler(){

            @Override
            public void afterEvent(int event, int result, Object data) {

                if (result == SMSSDK.RESULT_COMPLETE) {
                    //回调完成
                    Log.d("LoginActivity", "回调完成");
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        //提交验证码成功
                    }else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){
                        //获取验证码成功
                        Log.d("LoginActivity", "获取验证码成功");
                        Log.d("LoginActivity", "data:==" + data);

                        Intent intent = new Intent("android.provider.Telephony.SMS_RECEIVED");
                        MyApplication.getContext().sendBroadcast(intent);


                    }else if (event ==SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){
                        //返回支持发送验证码的国家列表
                        Log.d("LoginActivity", "返回支持发送验证码的国家列表");
                        Log.d("LoginActivity", "data:===" + data);
                    }
                }else{
                    ((Throwable)data).printStackTrace();
                }
            }
        };
        SMSSDK.registerEventHandler(eh); //注册短信回调
    }

    public class SMSReceiver extends BroadcastReceiver {

        public static final String SMS_RECEIVED_ACTION = "android.provider.Telephony.SMS_RECEIVED";
        private String TAG = "LoginActivity";

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(SMS_RECEIVED_ACTION)){
                SmsMessage[] messages = getMessagesFromIntent(intent);
                for (SmsMessage message : messages){
                    Log.i(TAG, message.getOriginatingAddress() + " : " +
                            message.getDisplayOriginatingAddress() + " : " +
                            message.getDisplayMessageBody() + " : " +
                            message.getTimestampMillis());
                    String smsContent=message.getDisplayMessageBody();
                    Log.i(TAG, smsContent);
                    //writeFile(smsContent);//将短信内容写入SD卡
                    shortMessage.setText(smsContent);
                }
            }
        }

        public final SmsMessage[] getMessagesFromIntent(Intent intent){
            Object[] messages = (Object[]) intent.getSerializableExtra("pdus");
            byte[][] pduObjs = new byte[messages.length][];
            for (int i = 0; i < messages.length; i++)
            {
                pduObjs[i] = (byte[]) messages[i];
            }
            byte[][] pdus = new byte[pduObjs.length][];
            int pduCount = pdus.length;
            SmsMessage[] msgs = new SmsMessage[pduCount];
            for (int i = 0; i < pduCount; i++)        {
                pdus[i] = pduObjs[i];
                msgs[i] = SmsMessage.createFromPdu(pdus[i]);
            }
            return msgs;
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //SMSSDK.unregisterEventHandler(eh);
    }



}
