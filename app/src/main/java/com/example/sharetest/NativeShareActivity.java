package com.example.sharetest;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.ToastUtils;
import com.example.sharetest.base.WXBaseActivity;
import com.tencent.connect.share.QQShare;
import com.tencent.connect.share.QzoneShare;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;


import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.sharetest.base.WXBaseActivity.SHARE_TYPE.TYPE_WXSceneSpecifiedContact;
import static com.example.sharetest.base.WXBaseActivity.SHARE_TYPE.Type_WXSceneFavorite;
import static com.example.sharetest.base.WXBaseActivity.SHARE_TYPE.Type_WXSceneSession;
import static com.example.sharetest.base.WXBaseActivity.SHARE_TYPE.Type_WXSceneTimeline;
import static com.tencent.mm.opensdk.modelmsg.SendMessageToWX.Req.WXSceneFavorite;
import static com.tencent.mm.opensdk.modelmsg.SendMessageToWX.Req.WXSceneSession;
import static com.tencent.mm.opensdk.modelmsg.SendMessageToWX.Req.WXSceneTimeline;

/**
 * 原生分享: 微信，qq
 * <p>
 * 遇到的冲突:
 * Error: Program type already present: com.tencent.connect.common.AssistActivity$1
 * <p>
 * umeng的jar包与原生jar包产生冲突;
 */
public class NativeShareActivity extends WXBaseActivity implements IUiListener {

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


    // qq分享
    private String shareTitle = "Hi,叶应是叶";
    private String description = "description";
    private String url = "http://blog.csdn.net/new_one_object";
    private String imageUrl = "http://avatar.csdn.net/B/0/1/1_new_one_object.jpg";
    private String app_name = "HiTips";

    private Tencent mTencent;
    private String APP_ID = "100424468";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native_share);
        ButterKnife.bind(this);

        mTencent = Tencent.createInstance(APP_ID, getApplicationContext());

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
                shareToQQ(view);
                break;
            case R.id.btn_qqzone:
                shareToQZone(view);
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
//        finish();
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


    public void shareToQQ(View view) {
        Bundle qqParams = new Bundle();
        qqParams.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
        qqParams.putString(QQShare.SHARE_TO_QQ_TITLE, shareTitle);
        qqParams.putString(QQShare.SHARE_TO_QQ_SUMMARY, description);
        qqParams.putString(QQShare.SHARE_TO_QQ_TARGET_URL, url);
        qqParams.putString(QQShare.SHARE_TO_QQ_APP_NAME, "APP名称");
        qqParams.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, "http://avatar.csdn.net/B/0/1/1_new_one_object.jpg");// 网络图片地址　url　
        // 分享操作要在主线程中完成
        mTencent.shareToQQ(this, qqParams, this);
    }


    public void shareToQZone(View view) {
        Bundle params = new Bundle();
        params.putInt(QzoneShare.SHARE_TO_QZONE_KEY_TYPE, QzoneShare.SHARE_TO_QZONE_TYPE_IMAGE_TEXT);
        params.putString(QzoneShare.SHARE_TO_QQ_TITLE, "我是标题");  // 必填
        params.putString(QzoneShare.SHARE_TO_QQ_SUMMARY, "欢迎访问我的博客");
        params.putString(QzoneShare.SHARE_TO_QQ_TARGET_URL, "http://blog.csdn.net/new_one_object");  // 必填
//        params.putString(QzoneShare.SHARE_TO_QQ_IMAGE_URL, "http://avatar.csdn.net/B/0/1/1_new_one_object.jpg");  // 格式错误!
        ArrayList<String> imgUrlList = new ArrayList<>();
        imgUrlList.add("http://f.hiphotos.baidu.com/image/h%3D200/sign=6f05c5f929738bd4db21b531918a876c/6a600c338744ebf8affdde1bdef9d72a6059a702.jpg");
        params.putStringArrayList(QzoneShare.SHARE_TO_QQ_IMAGE_URL, imgUrlList);// 图片地址
        // 分享操作要在主线程中完成
        params.putString(QzoneShare.SHARE_TO_QQ_APP_NAME, "HiTips");
        // 分享操作要在主线程中完成
        mTencent.shareToQzone(this, params, this);

    }

    @Override
    public void onComplete(Object o) {
        ToastUtils.showShort(o.toString());
    }

    @Override
    public void onError(UiError uiError) {
        ToastUtils.showShort(uiError.errorMessage + "--" + uiError.errorCode + "---" + uiError.errorDetail);
    }

    @Override
    public void onCancel() {
        ToastUtils.showShort("取消");
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (mTencent != null) {
            Tencent.onActivityResultData(requestCode, resultCode, data, this);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


}
