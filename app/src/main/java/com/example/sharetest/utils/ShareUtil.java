//package com.example.sharetest.utils;
//
//import android.app.Activity;
//import android.content.Context;
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.net.Uri;
//import android.os.Bundle;
//
//import com.tencent.connect.share.QQShare;
//import com.tencent.connect.share.QzoneShare;
//import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
//import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
//import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
//import com.tencent.mm.opensdk.openapi.IWXAPI;
//import com.tencent.mm.opensdk.openapi.WXAPIFactory;
//import com.tencent.tauth.IUiListener;
//import com.tencent.tauth.Tencent;
//
//import java.util.ArrayList;
//
///**
// * @author _H_JY
// * 2015-8-27下午4:26:35
// * <p>
// * 分享工具类：可以分享到微信好友、微信收藏、微信朋友圈、QQ好友、QQ空间、短信
// *
// *
// *
// * //接下来你就可以在你的Activity中使用该类简单实现分享了。这部分代码根据自己需要进行参数修改，使用方法如下：
// *         ShareUtil shareUtil = new ShareUtil();
// *         shareUtil.registerToWX(context);//向微信终端注册appID
// *         shareUtil.registerToQQ(context);//向QQ终端注册appID
// *         shareUtil.shareToSinaWeiBo(getActivity(), mShareTitle, description,TEST_IMAGE);
// *         shareUtil.shareToWXSceneSession(url, mShareTitle, description);
// *         shareUtil.shareToWXSceneFavorite(url, mShareTitle, description);
// *         shareUtil.shareToWXSceneTimeline(url, mShareTitle, description, TEST_IMAGE);
// *         shareUtil.shareToQQ(getActivity(), url, mShareTitle, description, new BaseUiListener());
// *         shareUtil.shareToQzone(getActivity(), url, TEST_IMAGE, mShareTitle, description, new BaseUiListener());
// *         startActivity(shareUtil.sendSMS(description));
// *
// */
//public class ShareUtil {
//
//    public static final String WX_APP_ID = "wxa44a945e29e96f94";//改成你在微信开放平台审核通过的appID
//    public static final String QQ_APP_ID = "1101799954";//改成你在QQ开放平台审核通过的appID
//
//    private IWXAPI iwxapi;
//    private Tencent tencent;
//
//
//    public ShareUtil() {
//        super();
//    }
//
//    /**
//     * 要分享必须先注册(微信)
//     */
//    public void registerToWX(Context context) {
//        iwxapi = WXAPIFactory.createWXAPI(context, WX_APP_ID, true);
//        iwxapi.registerApp(WX_APP_ID);
//
//    }
//
//    /**
//     * 要分享必须先注册(QQ)
//     */
//    public void registerToQQ(Context context) {
//        tencent = Tencent.createInstance(QQ_APP_ID, context);
//    }
//
//
//    public IWXAPI getIwxapi() {
//        return iwxapi;
//    }
//
//    public void setIwxapi(IWXAPI iwxapi) {
//        this.iwxapi = iwxapi;
//    }
//
//    public Tencent getTencent() {
//        return tencent;
//    }
//
//    public void setTencent(Tencent tencent) {
//        this.tencent = tencent;
//    }
//
//    public String getWxAppId() {
//        return WX_APP_ID;
//    }
//
//    public String getQqAppId() {
//        return QQ_APP_ID;
//    }
//
//
//    /**
//     * 分享到短信
//     */
//    public Intent sendSMS(String description) {
//        Uri smsToUri = Uri.parse("smsto:");
//        Intent sendIntent = new Intent(Intent.ACTION_VIEW, smsToUri);
//        //sendIntent.putExtra("address", "123456"); // 电话号码，这行去掉的话，默认就没有电话
//        sendIntent.putExtra("sms_body", description);
//        sendIntent.setType("vnd.android-dir/mms-sms");
//
//        return sendIntent;
//
//    }
//
//
//    /**
//     * 分享到微信好友
//     */
//    public void shareToWXSceneSession(String url, String shareTitle, String description) {
//        WXWebpageObject webpageObject = new WXWebpageObject();
//        webpageObject.webpageUrl = url;
//        WXMediaMessage mWxMediaMessage = new WXMediaMessage(webpageObject);
//        mWxMediaMessage.title = shareTitle;
//        mWxMediaMessage.description = description;
//        SendMessageToWX.Req req = new SendMessageToWX.Req();
//        req.transaction = String.valueOf(System.currentTimeMillis());//transaction字段用于唯一标识一个请求
//        req.message = mWxMediaMessage;
//        req.scene = SendMessageToWX.Req.WXSceneSession;
//        iwxapi.sendReq(req);
//
//    }
//
//
//    /**
//     * 分享到微信收藏
//     */
//    public void shareToWXSceneFavorite(String url, String shareTitle, String description) {
//        WXWebpageObject webpageObject = new WXWebpageObject();
//        webpageObject.webpageUrl = url;
//        WXMediaMessage wxMediaMessage = new WXMediaMessage(webpageObject);
//        wxMediaMessage.title = shareTitle;
//        wxMediaMessage.description = description;
//        SendMessageToWX.Req req = new SendMessageToWX.Req();
//        req.transaction = String.valueOf(System.currentTimeMillis());
//        req.message = wxMediaMessage;
//        req.scene = SendMessageToWX.Req.WXSceneFavorite;
//        iwxapi.sendReq(req);
//    }
//
//
//    /**
//     * 分享到微信朋友圈
//     */
//    public void shareToWXSceneTimeline(String url, String shareTitle, String description, String imageUrl) {
//        WXWebpageObject webpageObject = new WXWebpageObject();
//        webpageObject.webpageUrl = url;
//        WXMediaMessage mediaMessage = new WXMediaMessage(webpageObject);
//        mediaMessage.title = shareTitle;
//        mediaMessage.description = description;
//        Bitmap bitmap = BitmapFactory.decodeFile(imageUrl);
//        Bitmap thumBmp = Bitmap.createScaledBitmap(bitmap, 150, 150, true);//图片大小有限制，太大分享不了
////        mediaMessage.thumbData = Tools.getBitmapByte(thumBmp);
//        SendMessageToWX.Req req = new SendMessageToWX.Req();
//        req.transaction = String.valueOf(System.currentTimeMillis());
//        req.message = mediaMessage;
//        req.scene = SendMessageToWX.Req.WXSceneTimeline;
//        iwxapi.sendReq(req);
//    }
//
//
//    /**
//     * 分享到QQ好友
//     */
//    public void shareToQQ(Activity activity, String url, String shareTitle, String description, IUiListener uiListener) {
//        Bundle qqParams = new Bundle();
//        qqParams.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
//        qqParams.putString(QQShare.SHARE_TO_QQ_TITLE, shareTitle);
//        qqParams.putString(QQShare.SHARE_TO_QQ_SUMMARY, description);
//        qqParams.putString(QQShare.SHARE_TO_QQ_TARGET_URL, url);
//        //qqParams.putString(QQShare.SHARE_TO_QQ_APP_NAME,  "APP名称");
//        tencent.shareToQQ(activity, qqParams, uiListener);
//
//    }
//
//
//    /**
//     * 分享到QQ空间
//     */
//    public void shareToQzone(Activity activity, String url, String imageUrl, String shareTitle, String description, IUiListener uiListener) {
//        Bundle qzoneParams = new Bundle();
//        qzoneParams.putInt(QzoneShare.SHARE_TO_QZONE_KEY_TYPE, QzoneShare.SHARE_TO_QZONE_TYPE_IMAGE_TEXT);
//        qzoneParams.putString(QzoneShare.SHARE_TO_QQ_TITLE, shareTitle);//必填
//        qzoneParams.putString(QzoneShare.SHARE_TO_QQ_SUMMARY, description);
//        qzoneParams.putString(QzoneShare.SHARE_TO_QQ_TARGET_URL, url);//必填
//        ArrayList<String> imageUrlList = new ArrayList<String>();
//        imageUrlList.add(imageUrl);
//        qzoneParams.putStringArrayList(QzoneShare.SHARE_TO_QQ_IMAGE_URL, imageUrlList);
//        tencent.shareToQzone(activity, qzoneParams, uiListener);
//
//    }
//}
