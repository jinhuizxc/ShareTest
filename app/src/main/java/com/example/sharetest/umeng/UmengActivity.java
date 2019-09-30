package com.example.sharetest.umeng;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.ActivityUtils;
import com.example.sharetest.R;
import com.example.sharetest.umeng.base.BaseActivity;

/**
 * U-Share集成文档
 * 该文档适用于Android组件化分享SDK6.9.0及以上版本。
 * https://developer.umeng.com/docs/66632/detail/66639
 * <p>
 * 友盟多功能Android Demo
 * https://github.com/umeng/MultiFunctionAndroidDemo
 * <p>
 * 权限配置
 * 如果需要使用QQ纯图分享或避免其它平台纯图分享的时候图片不被压缩，可以增加以下权限：
 * <p>
 * 代码:
 * 复制代码到剪切板
 * <p>
 * <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
 * <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
 */
public class UmengActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("分享");
        setBackVisibily();
        findViewById(R.id.social_share).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityUtils.startActivity(SharePlatformActivity.class);
            }
        });
        findViewById(R.id.social_board).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityUtils.startActivity(ShareBoardActivity.class);
            }
        });
        findViewById(R.id.social_auth).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityUtils.startActivity(AuthActivity.class);
            }
        });
        findViewById(R.id.social_info).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityUtils.startActivity(InfoActivity.class);
            }
        });

    }

    @Override
    public int getLayout() {
        return R.layout.activity_umeng;
    }
}
