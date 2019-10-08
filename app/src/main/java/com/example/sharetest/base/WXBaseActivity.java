package com.example.sharetest.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.blankj.utilcode.util.ToastUtils;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class WXBaseActivity extends Activity implements IWXAPIEventHandler {

    private String APP_ID = "wxdc1e388c3822c80b";   // 微信注册的appid
    private static final String TAG = "WXBaseActivity";

    public IWXAPI iwxapi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        iwxapi = WXAPIFactory.createWXAPI(this, APP_ID, false);
        iwxapi.handleIntent(getIntent(), this);
        iwxapi.registerApp(APP_ID);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        iwxapi.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }


    @Override
    public void onResp(BaseResp baseResp) {
        Log.e(TAG, "微信调用返回参数: onResp: " + baseResp.errCode);
        // 则为
        if (baseResp.getType() == 1){  // 微信登录

        }else if (baseResp.getType() == 0){  // 微信分享
            String result;
            switch (baseResp.errCode) {
                case BaseResp.ErrCode.ERR_OK:
                    result = "分享成功";
                    break;
                case BaseResp.ErrCode.ERR_USER_CANCEL:
                    result = "取消分享";
                    break;
                case BaseResp.ErrCode.ERR_AUTH_DENIED:
                    result = "分享被拒绝";
                    break;
                default:
                    result = "发送返回";
                    break;
            }
            ToastUtils.showShort(result);
        }
        finish();
    }


    public enum SHARE_TYPE {Type_WXSceneSession, Type_WXSceneTimeline,
        Type_WXSceneFavorite, TYPE_WXSceneSpecifiedContact}


}
