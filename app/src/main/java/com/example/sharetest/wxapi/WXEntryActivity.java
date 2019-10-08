package com.example.sharetest.wxapi;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.blankj.utilcode.util.ToastUtils;
import com.example.sharetest.base.WXBaseActivity;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * 测试下原生微信分享模块;
 * <p>
 * IWXAPIEventHandler
 * <p>
 * Android 微信分享与QQ分享功能（原生实现）
 * https://blog.csdn.net/YiRanAiNi_/article/details/79454719
 * <p>
 * 微信分享及收藏目前支持文字、图片、音乐、视频、网页共五种类型，可以分享至微信好友会话、朋友圈或添加到微信收藏
 * <p>
 * 分享或收藏的目标场景，通过修改scene场景值实现。
 * 发送到聊天界面——WXSceneSession
 * 发送到朋友圈——WXSceneTimeline
 * 添加到微信收藏——WXSceneFavorite
 */
public class WXEntryActivity extends WXBaseActivity {


//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_wxentry);
//    }



}

