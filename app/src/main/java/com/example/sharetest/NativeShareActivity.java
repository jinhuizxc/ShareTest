package com.example.sharetest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.sharetest.base.WXBaseActivity;
import com.example.sharetest.wxapi.WXEntryActivity;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;

import java.io.ByteArrayOutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.sharetest.base.WXBaseActivity.SHARE_TYPE.TYPE_WXSceneSpecifiedContact;
import static com.example.sharetest.base.WXBaseActivity.SHARE_TYPE.Type_WXSceneFavorite;
import static com.example.sharetest.base.WXBaseActivity.SHARE_TYPE.Type_WXSceneSession;
import static com.example.sharetest.base.WXBaseActivity.SHARE_TYPE.Type_WXSceneTimeline;
import static com.tencent.mm.opensdk.modelmsg.SendMessageToWX.Req.WXSceneFavorite;
import static com.tencent.mm.opensdk.modelmsg.SendMessageToWX.Req.WXSceneSession;
import static com.tencent.mm.opensdk.modelmsg.SendMessageToWX.Req.WXSceneSpecifiedContact;
import static com.tencent.mm.opensdk.modelmsg.SendMessageToWX.Req.WXSceneTimeline;

/**
 * 原生分享: 微信，qq
 */
public class NativeShareActivity extends WXBaseActivity {

    @BindView(R.id.btn_wxchat)
    Button btnWxchat;
    @BindView(R.id.btn_wxfriend)
    Button btnWxfriend;
    @BindView(R.id.btn_qqchat)
    Button btnQqchat;
    @BindView(R.id.btn_qqzone)
    Button btnQqzone;
    @BindView(R.id.activity_share_entry)
    LinearLayout activityShareEntry;
    @BindView(R.id.btn_wxFavorite)
    Button btnWxFavorite;
    @BindView(R.id.btn_wxSpecifiedContact)
    Button btnWxSpecifiedContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native_share);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_wxchat, R.id.btn_wxfriend, R.id.btn_wxFavorite,
            R.id.btn_wxSpecifiedContact, R.id.btn_qqchat, R.id.btn_qqzone})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_wxchat:
                share(Type_WXSceneSession);
                break;
            case R.id.btn_wxfriend:
                share(Type_WXSceneTimeline);
                break;
            case R.id.btn_wxFavorite:
                share(Type_WXSceneFavorite);
                break;
            case R.id.btn_wxSpecifiedContact:
                share(TYPE_WXSceneSpecifiedContact);
                break;
            case R.id.btn_qqchat:
                break;
            case R.id.btn_qqzone:
                break;
        }
    }


//    public void shareWXSceneSession(View view) {
//        share(Type_WXSceneSession);
//    }
//
//    public void shareWXSceneTimeline(View view) {
//        share(Type_WXSceneTimeline);
//    }

    /**
     * 分享的url、title、desc、pic
     *
     * @param type
     */
    private void share(SHARE_TYPE type) {
        WXWebpageObject webpageObject = new WXWebpageObject();
        // URL
        webpageObject.webpageUrl = "https://www.wanandroid.com/";
        WXMediaMessage msg = new WXMediaMessage(webpageObject);
        msg.title = "这是title";
        msg.description = "这是一个wanAndroid网站";
        Bitmap thumb = BitmapFactory.decodeResource(getResources(), R.drawable.send);
        msg.thumbData = bmpToByteArray(thumb, true);
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("Req");
        req.message = msg;
        switch (type) {
            case Type_WXSceneSession:
                req.scene = WXSceneSession;
                break;
            case Type_WXSceneTimeline:
                req.scene = WXSceneTimeline;
                break;
            case Type_WXSceneFavorite:
                req.scene = WXSceneFavorite;
                break;
//            case TYPE_WXSceneSpecifiedContact:
//                req.scene = WXSceneSpecifiedContact;
//                break;
        }
        iwxapi.sendReq(req);
        finish();
    }

    public static byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, output);
        if (needRecycle) {
            bmp.recycle();
        }
        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }


}
