package com.example.sharetest.umeng;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.example.sharetest.R;
import com.example.sharetest.offical.AuthAdapter;
import com.example.sharetest.umeng.base.BaseActivity;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.shareboard.SnsPlatform;

import java.util.ArrayList;

public class AuthActivity extends BaseActivity {

    private ListView listView;
    private AuthAdapter shareAdapter;

    public ArrayList<SnsPlatform> platforms = new ArrayList<SnsPlatform>();

    private SHARE_MEDIA[] list = {SHARE_MEDIA.QQ, SHARE_MEDIA.SINA, SHARE_MEDIA.WEIXIN,
            SHARE_MEDIA.FACEBOOK, SHARE_MEDIA.TWITTER, SHARE_MEDIA.LINKEDIN, SHARE_MEDIA.DOUBAN, SHARE_MEDIA.RENREN, SHARE_MEDIA.KAKAO,
            SHARE_MEDIA.VKONTAKTE, SHARE_MEDIA.DROPBOX};

    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("授权");
        setBackVisibily();
        initViews();
    }
    private void initViews(){
        dialog = new ProgressDialog(this);
        listView = (ListView) findViewById(R.id.list);
        initPlatforms();
        shareAdapter = new AuthAdapter(this, platforms);
        listView.setAdapter(shareAdapter);
    }
    @Override
    public int getLayout() {
        return R.layout.activity_auth;
    }
    private void initPlatforms() {
        platforms.clear();
        for (SHARE_MEDIA e : list) {
            if (!e.toString().equals(SHARE_MEDIA.GENERIC.toString())) {
                platforms.add(e.toSnsPlatform());
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UMShareAPI.get(this).release();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        UMShareAPI.get(this).onSaveInstanceState(outState);
    }
}

