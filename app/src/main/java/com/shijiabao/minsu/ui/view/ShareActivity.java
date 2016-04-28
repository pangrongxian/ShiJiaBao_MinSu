package com.shijiabao.minsu.ui.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.shijiabao.minsu.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

public class ShareActivity extends AppCompatActivity {

    @Bind(R.id.button)
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button)
    public void onClick() {
        //一键分享
        showShare();
    }

    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // title标题：微信、QQ（新浪微博不需要标题）
        oks.setTitle("庞荣先独家分享");  //最多30个字符

        // text是分享文本：所有平台都需要这个字段
        oks.setText("我是分享文本，点开有惊喜哦~!!!,啦啦啦~");  //最多40个字符

        // imagePath是图片的本地路径：除Linked-In以外的平台都支持此参数
        //oks.setImagePath(Environment.getExternalStorageDirectory() + "/meinv.jpg");//确保SDcard下面存在此张图片

        //网络图片的url：所有平台
        //oks.setImageUrl("https://ss0.baidu.com/-Po3dSag_xI4khGko9WTAnF6hhy/image/h%3D360/sign=9f6e24765adf8db1a32e7a623922dddb/0ff41bd5ad6eddc492d491153ddbb6fd52663328.jpg");//网络图片rul

        // url：仅在微信（包括好友和朋友圈）中使用
        //oks.setUrl("http://e.hiphotos.baidu.com/image/h%3D360/sign=85c7a9d07fec54e75eec1c18893a9bfd/314e251f95cad1c8037ed8c97b3e6709c83d5112.jpg");   //网友点进链接后，可以看到分享的详情

        // Url：仅在QQ空间使用
        oks.setTitleUrl("https://ss0.baidu.com/-Po3dSag_xI4khGko9WTAnF6hhy/image/h%3D360/sign=9f6e24765adf8db1a32e7a623922dddb/0ff41bd5ad6eddc492d491153ddbb6fd52663328.jpg");  //网友点进链接后，可以看到分享的详情

        // 启动分享GUI
        oks.show(this);
    }

}
