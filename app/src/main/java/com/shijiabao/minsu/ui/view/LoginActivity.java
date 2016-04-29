package com.shijiabao.minsu.ui.view;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.telephony.SmsMessage;
import android.text.TextUtils;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.mob.tools.utils.UIHandler;
import com.shijiabao.minsu.R;
import com.shijiabao.minsu.custom.CustomDialog;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class LoginActivity extends Activity implements PlatformActionListener, Handler.Callback {

    private static final int MSG_USERID_FOUND = 1;
    private static final int MSG_LOGIN = 2;
    private static final int MSG_AUTH_CANCEL = 3;
    private static final int MSG_AUTH_ERROR = 4;
    private static final int MSG_AUTH_COMPLETE = 5;

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
    private EventHandler eh;
    private Platform platform;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ShareSDK.initSDK(this);
        SMSSDK.initSDK(this, "123c05bcf3380", "58292a9bf150cd3f2fb2e7418d48defe");
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mTiemTimeCount = new TimeCount(60000, 1000);//初始化计时器
        listener();//监听短信验证码回调
    }


    @OnClick({R.id.loginBack, R.id.phone_Number, R.id.VerificationCodeBtn, R.id.short_Message, R.id.loginBtn, R.id.loginQQ})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.loginBack:
                finish();
                break;
            case R.id.VerificationCodeBtn:
                String number = this.phoneNumber.getText().toString();
                Toast.makeText(this, "获取验证码", Toast.LENGTH_SHORT).show();
                if (!number.equals("")) {
                    sendSms();//点击初始化广播监听
                    getVerification("86", number);//86代表中国
                    getCountriesList();
                    //获取短信目前支持的国家列表，在监听中返回
                }
                break;
            case R.id.short_Message:
                Toast.makeText(this, "short_Message", Toast.LENGTH_SHORT).show();
                break;
            case R.id.loginBtn:
                Toast.makeText(this, "loginBtn", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, PerfectInformationActivity.class);
                startActivity(intent);
                break;
            case R.id.loginQQ:
                Toast.makeText(this, "loginQQ", Toast.LENGTH_SHORT).show();
                platform = ShareSDK.getPlatform(this, QQ.NAME);
                authorize(platform);//授权QQ登录
                break;
        }
    }

    @Override
    protected void onDestroy() {
        SMSSDK.unregisterEventHandler(eh);//取消短信验证码监听
        ShareSDK.stopSDK(this);//停止使用ShareSDK
        platform.removeAccount();//移除授权
        try {
            unregisterReceiver(smsReceiver);//接触广播接收区的绑定
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }


    /**
     * 第三方登录
     */
    private void authorize(Platform plat) {
        if(plat.isValid()) {
            String userId = plat.getDb().getUserId();
            if (!TextUtils.isEmpty(userId)) {
                UIHandler.sendEmptyMessage(MSG_USERID_FOUND, this);
                login(plat.getName(), userId, null);
                return;
            }
        }
        plat.setPlatformActionListener(this);
       //SSO授权方式，简单来说就是使用目标平台客户端来完成授权。
        plat.SSOSetting(false);//设置false表示使用SSO授权方式
        plat.showUser(null);
    }

    private void login(String plat, String userId, HashMap<String, Object> userInfo) {

        Log.d("LoginActivity="+"userId :", userId);
        Log.d("LoginActivity=", "userInfo:" + userInfo);

        Message msg = new Message();
        msg.what = MSG_LOGIN;
        msg.obj = plat;
        UIHandler.sendMessage(msg, this);

//        Intent intent = new Intent(this,MainActivity.class);
//        startActivity(intent);
    }

    /**
     * PlatformActionListener
     * @param platform
     * @param action
     * @param hashMap
     */
    @Override
    public void onComplete(Platform platform, int action, HashMap<String, Object> hashMap) {
        if (action == Platform.ACTION_USER_INFOR) {
            UIHandler.sendEmptyMessage(MSG_AUTH_COMPLETE, this);

            login(platform.getName(), platform.getDb().getUserId(), hashMap);
        }
        Log.d("LoginActivity", "hashMap:" + hashMap);
        Log.d("LoginActivity", platform.getDb().getUserName());
        Log.d("LoginActivity", platform.getDb().getUserId());
    }

    @Override
    public void onError(Platform platform, int action, Throwable throwable) {
        if (action == Platform.ACTION_USER_INFOR) {
            UIHandler.sendEmptyMessage(MSG_AUTH_ERROR, this);
        }
        throwable.printStackTrace();
    }

    @Override
    public void onCancel(Platform platform, int action) {
        if (action == Platform.ACTION_USER_INFOR) {
            UIHandler.sendEmptyMessage(MSG_AUTH_CANCEL, this);
        }
    }

    /**
     * Handler.Callback
     * @param msg
     * @return
     */
    @Override
    public boolean handleMessage(Message msg) {
        switch(msg.what) {
            case MSG_USERID_FOUND: {
                Toast.makeText(this, R.string.userid_found, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
            }
            break;
            case MSG_LOGIN: {
                String text = getString(R.string.logining, msg.obj);
                Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
            }
            break;
            case MSG_AUTH_CANCEL: {
                Toast.makeText(this, R.string.auth_cancel, Toast.LENGTH_SHORT).show();
            }
            break;
            case MSG_AUTH_ERROR: {
                Toast.makeText(this, R.string.auth_error, Toast.LENGTH_SHORT).show();
            }
            break;
            case MSG_AUTH_COMPLETE: {
                Toast.makeText(this, R.string.auth_complete, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
            }
            break;
        }
        return false;
    }


    /**
     *短信验证码
     */
    private void getVerification(String country, String phone) {//获取短信验证码方法
        Log.d("LoginActivity", "getVerificationCode");
        SMSSDK.getVerificationCode(country, phone);
    }

    private void getCountriesList() {//获取支持城市代码列表
        SMSSDK.getSupportedCountries();
    }

    private void listener() {//监听短信验证码回调
        eh = new EventHandler() {
            @Override
            public void afterEvent(int event, int result, Object data) {

                if (result == SMSSDK.RESULT_COMPLETE) {
                    //回调完成
                    Log.d("LoginActivity", "回调完成");
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        //提交验证码成功
                    } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        //获取验证码成功
                        Log.d("LoginActivity", "获取验证码成功");
                        Log.d("LoginActivity", "data:==" + data);
                    } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                        //返回支持发送验证码的国家列表
                        Log.d("LoginActivity", "返回支持发送验证码的国家列表");
                        Log.d("LoginActivity", "data:===" + data);
                    }
                } else {
                    ((Throwable) data).printStackTrace();
                }
            }
        };
        SMSSDK.registerEventHandler(eh); //注册短信回调
    }

    private TimeCount mTiemTimeCount;

    //计时重发
    private class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            VerificationCodeBtn.setClickable(false);
            VerificationCodeBtn.setTextSize(14);
            VerificationCodeBtn.setText(millisUntilFinished / 1000 + "秒后重新发送");
        }

        @Override
        public void onFinish() {
            VerificationCodeBtn.setTextSize(16);
            VerificationCodeBtn.setText("获取验证码");
            VerificationCodeBtn.setClickable(true);
        }
    }

    //短信验证码内容 验证码是6位数字的格式
    private String strContent;
    private String patternCoder = "(?<!\\d)\\d{6}(?!\\d)";//使用正则表达式验证是否是6位数字的验证码

    //更新界面
    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            Log.d("LoginActivity", strContent);
            if (strContent != null) {
                phoneNumber.setText(strContent);
            }
        }

    };

    //监听短信广播
    private BroadcastReceiver smsReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            Object[] objs = (Object[]) intent.getExtras().get("pdus");
            for (Object obj : objs) {
                byte[] pdu = (byte[]) obj;
                SmsMessage sms = SmsMessage.createFromPdu(pdu);
                // 短信的内容
                String message = sms.getMessageBody();
                String from = sms.getOriginatingAddress();

                Time time = new Time();
                time.set(sms.getTimestampMillis());
                strContent = from + "   " + message;
                //mHandler.sendEmptyMessage(1);
                if (!TextUtils.isEmpty(from)) {
                    String code = patternCode(message);

                    Log.d("LoginActivity" + "code:", code);

                    if (!TextUtils.isEmpty(code)) {
                        strContent = code;
                        mHandler.sendEmptyMessage(1);
                    }
                }
                return;
            }

        }
    };

    /**
     * 匹配短信中间的6个数字（验证码等）
     *
     * @param patternContent
     * @return
     */
    private String patternCode(String patternContent) {
        if (TextUtils.isEmpty(patternContent)) {
            return null;
        }
        Pattern p = Pattern.compile(patternCoder);
        Matcher matcher = p.matcher(patternContent);
        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }

    public void sendSms() {//点击发送验证码
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.provider.Telephony.SMS_RECEIVED");
        filter.setPriority(Integer.MAX_VALUE);
        registerReceiver(smsReceiver, filter);//动态注册广播
        mTiemTimeCount.start();
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
