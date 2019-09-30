package com.example.sharetest.base;

import android.content.Intent;
import android.content.res.TypedArray;

import com.blankj.utilcode.util.ToastUtils;
import com.example.sharetest.Constant;
import com.example.sharetest.R;
import com.example.sharetest.share.AppShareAction;
import com.example.sharetest.share.ShareDialogFragment;
import com.example.sharetest.share.ShareDialogLandscape;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMEmoji;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMMin;
import com.umeng.socialize.media.UMVideo;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.media.UMusic;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;


/**
 * 分享
 */
public abstract class AppShareActivity extends AppActivity {

    private ShareAction shareAction;
    private ShareDialogFragment shareDialog;
    private ShareDialogLandscape shareDialogLandscape;

    private UMShareListener umShareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

            if (platform == SHARE_MEDIA.WEIXIN) {
                ToastUtils.showShort("分享到微信好友...");
            } else if (platform == SHARE_MEDIA.WEIXIN_CIRCLE) {
                ToastUtils.showShort("分享到微信朋友圈...");
            } else if (platform == SHARE_MEDIA.SINA) {
                ToastUtils.showShort("分享到新浪微博...");
            } else if (platform == SHARE_MEDIA.QQ) {
                ToastUtils.showShort("分享到QQ...");
            } else if (platform == SHARE_MEDIA.QZONE) {
                ToastUtils.showShort("分享到QQ空间...");
            }

        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            if (platform != SHARE_MEDIA.WEIXIN && platform != SHARE_MEDIA.WEIXIN_CIRCLE) {
                ToastUtils.showShort("分享成功");
//                int shareType = flashBucket.get(Constant.ShareAction.SHARE_TYPE, Constant.ShareAction.ONE_LISTENER);
//                if (shareType == Constant.ShareAction.ONE_LISTENER){
//                    EventBus.getDefault().postSticky(new ShareActionEvent(shareType));
//                }else if (shareType == Constant.ShareAction.VIDEO){
//                    EventBus.getDefault().postSticky(new ShareActionEvent(shareType));
//                }else if (shareType == Constant.ShareAction.GOOD_COURSE){
//                    EventBus.getDefault().postSticky(new ShareActionEvent(shareType));
//                }
            }
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            String msg = t.getMessage();
            if (msg.contains("2008")) {
                if (platform == SHARE_MEDIA.WEIXIN || platform == SHARE_MEDIA.WEIXIN_CIRCLE) {
                    ToastUtils.showShort("微信未安装");
                } else if (platform == SHARE_MEDIA.QQ || platform == SHARE_MEDIA.QZONE) {
                    ToastUtils.showShort("QQ未安装");
                } else if (platform == SHARE_MEDIA.SINA) {
                    ToastUtils.showShort("微博未安装");
                }
            }

        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            ToastUtils.showShort("取消分享");
        }
    };



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //QQ
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    public void createTextShare(String text, SHARE_MEDIA platForm) {
        shareAction = new ShareAction(AppShareActivity.this)
                .withText(text)
                .setPlatform(platForm)
//                .setDisplayList(SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.SINA)
                .setCallback(umShareListener);
        shareAction.share();
    }

    public void createWebShare(String url, String title, String desc, String thumb, SHARE_MEDIA platForm) {
        UMWeb web = new UMWeb(url);
        web.setTitle(title);
        web.setDescription(desc);
        web.setThumb(new UMImage(this, thumb));

        shareAction = new ShareAction(AppShareActivity.this)
                .withMedia(web)
                .setPlatform(platForm)
//                .setDisplayList(SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.SINA)
                .setCallback(umShareListener);
        shareAction.share();
    }

    public void createImageShare(String imageUrl, String thumb, SHARE_MEDIA platForm) {
        UMImage umImage = new UMImage(this, imageUrl);
        umImage.setThumb(new UMImage(this, thumb));

        shareAction = new ShareAction(AppShareActivity.this)
                .withMedia(umImage)
                .setPlatform(platForm)
//                .setDisplayList(SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.SINA)
                .setCallback(umShareListener);
        shareAction.share();
    }

    public void createImagesShare(String imageUrl, String imageUrl2, String imageUrl3, SHARE_MEDIA platForm) {
        UMImage umImage1 = new UMImage(this, imageUrl);
        UMImage umImage2 = new UMImage(this, imageUrl2);
        UMImage umImage3 = new UMImage(this, imageUrl3);

        shareAction = new ShareAction(AppShareActivity.this)
                .withMedias(umImage1, umImage2, umImage3)
                .setPlatform(platForm)
//                .setDisplayList(SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.SINA)
                .setCallback(umShareListener);
        shareAction.share();
    }

    public void createVideoShare(String videoUrl, String title, String desc, String thumb, String h5Url, SHARE_MEDIA platForm) {
        UMVideo video = new UMVideo(videoUrl);
        video.setThumb(new UMImage(this, thumb));
        video.setTitle(title);
        video.setDescription(desc);
        video.setH5Url(h5Url);
        shareAction = new ShareAction(AppShareActivity.this)
                .withMedia(video)
                .setPlatform(platForm)
//                .setDisplayList(SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.SINA)
                .setCallback(umShareListener);
        shareAction.share();
    }

    public void createMusicShare(String musicUrl, String targetUrl, String title, String desc, String thumb, SHARE_MEDIA platForm) {
        UMusic music = new UMusic(musicUrl);
        music.setThumb(new UMImage(this, thumb));
        music.setTitle(title);
        music.setDescription(desc);
        music.setmTargetUrl(targetUrl);
        shareAction = new ShareAction(AppShareActivity.this)
                .withMedia(music)
                .setPlatform(platForm)
//                .setDisplayList(SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.SINA)
                .setCallback(umShareListener);
        shareAction.share();
    }

    public void createEmojiShare(String emojiUrl, String thumb, SHARE_MEDIA platForm) {
        UMEmoji emoji = new UMEmoji(this, emojiUrl);
        emoji.setThumb(new UMImage(this, thumb));
        shareAction = new ShareAction(AppShareActivity.this)
                .withMedia(emoji)
                .setPlatform(platForm)
//                .setDisplayList(SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.SINA)
                .setCallback(umShareListener);
        shareAction.share();
    }

    public void createMinShare(String minUrl, String path, String userName, String title, String desc, String thumb, SHARE_MEDIA platForm) {

        UMMin umMin = new UMMin(minUrl);
        umMin.setThumb(new UMImage(this, thumb));
        umMin.setTitle(title);
        umMin.setDescription(desc);
        umMin.setPath(path);
        umMin.setUserName(userName);

        shareAction = new ShareAction(AppShareActivity.this)
                .withMedia(umMin)
                .setPlatform(platForm)
//                .setDisplayList(SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.SINA)
                .setCallback(umShareListener);
        shareAction.share();
    }

    public void share(int spanCount, List<AppShareAction> actions, boolean showTitle) {
        if (shareDialog == null) {
            shareDialog = ShareDialogFragment.getInstance();
        }
        List<AppShareAction> appShareActions = actions;
        shareDialog.setOrUpdateData(appShareActions);
        shareDialog.showTitle(showTitle);
        shareDialog.setSpanCount(spanCount == 0 ? Constant.SpanCount.DEFAULT : spanCount);
        shareDialog.show(getSupportFragmentManager(), "umeng_share");
    }


    public void shareLandscape(int spanCount, List<AppShareAction> actions) {
        if (shareDialogLandscape == null) {
            shareDialogLandscape = ShareDialogLandscape.getInstance();
        }
        List<AppShareAction> appShareActions = actions;
        shareDialogLandscape.setOrUpdateData(appShareActions);
        shareDialogLandscape.setSpanCount(spanCount == 0 ? Constant.SpanCount.DEFAULT : spanCount);
        shareDialogLandscape.show(getSupportFragmentManager(), "umeng_share");
    }

    public void dismissShareDialogLandscape() {
        if (shareDialogLandscape != null && shareDialogLandscape.isVisible()) {
            shareDialogLandscape.dismiss();
            shareDialogLandscape = null;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onAppShareEvent(ShareDialogFragment.AppShareEvent event) {
        AppShareAction appShareAction = event.getAppShareAction();
        if (appShareAction.aid == R.id.share_to_weixin_circle) {
            shareTo(SHARE_MEDIA.WEIXIN_CIRCLE);
        } else if (appShareAction.aid == R.id.share_to_weixin) {
            shareTo(SHARE_MEDIA.WEIXIN);
        } else if (appShareAction.aid == R.id.share_to_qq) {
            shareTo(SHARE_MEDIA.QQ);
        } else if (appShareAction.aid == R.id.share_to_qzone) {
            shareTo(SHARE_MEDIA.QZONE);
        } else if (appShareAction.aid == R.id.share_to_sina) {
            shareTo(SHARE_MEDIA.SINA);
        } else {
            other(appShareAction.aid);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onAppShareLandscapeEvent(ShareDialogLandscape.AppShareEvent event) {
        AppShareAction appShareAction = event.getAppShareAction();
        if (appShareAction.aid == R.id.share_to_weixin_circle) {
            shareTo(SHARE_MEDIA.WEIXIN_CIRCLE);
        } else if (appShareAction.aid == R.id.share_to_weixin) {
            shareTo(SHARE_MEDIA.WEIXIN);
        } else if (appShareAction.aid == R.id.share_to_qq) {
            shareTo(SHARE_MEDIA.QQ);
        } else if (appShareAction.aid == R.id.share_to_qzone) {
            shareTo(SHARE_MEDIA.QZONE);
        } else if (appShareAction.aid == R.id.share_to_sina) {
            shareTo(SHARE_MEDIA.SINA);
        } else {
            other(appShareAction.aid);
        }
    }

    /**
     * other action
     */
    public void other(int aid) {

    }

    public abstract void shareTo(SHARE_MEDIA share_media);

    public List<AppShareAction> initActions() {
        List<AppShareAction> appShareActions = new ArrayList<>();
        TypedArray actionArray = getResources().obtainTypedArray(R.array.short_video_app_share_ids);
        int parentLen = actionArray.length();
        int[] parentIds = new int[parentLen];
        for (int i = 0; i < parentLen; i++) {
            parentIds[i] = actionArray.getResourceId(i, 0);
        }
        actionArray.recycle();
        String[] actionLabels = getResources().getStringArray(R.array.short_video_app_share_names);
        //drawable array
        TypedArray array = getResources().obtainTypedArray(R.array.short_video_app_share_icons);
        int len = array.length();
        int[] item_icon = new int[len];
        for (int i = 0; i < len; i++) {
            item_icon[i] = array.getResourceId(i, 0);
        }
        array.recycle();
        for (int i = 0; i < parentIds.length; i++) {
            AppShareAction action = new AppShareAction();
            action.aid = parentIds[i];
            action.actionIconRes = item_icon[i];
            action.actionName = actionLabels[i];
            appShareActions.add(action);
        }
        return appShareActions;
    }

    public List<AppShareAction> initActions2() {
        List<AppShareAction> appShareActions = new ArrayList<>();
        TypedArray actionArray = getResources().obtainTypedArray(R.array.app_share_ids_2);
        int parentLen = actionArray.length();
        int[] parentIds = new int[parentLen];
        for (int i = 0; i < parentLen; i++) {
            parentIds[i] = actionArray.getResourceId(i, 0);
        }
        actionArray.recycle();
        String[] actionLabels = getResources().getStringArray(R.array.app_share_names_2);
        //drawable array
        TypedArray array = getResources().obtainTypedArray(R.array.app_share_icons_2);
        int len = array.length();
        int[] item_icon = new int[len];
        for (int i = 0; i < len; i++) {
            item_icon[i] = array.getResourceId(i, 0);
        }
        array.recycle();
        for (int i = 0; i < parentIds.length; i++) {
            AppShareAction action = new AppShareAction();
            action.aid = parentIds[i];
            action.actionIconRes = item_icon[i];
            action.actionName = actionLabels[i];
            appShareActions.add(action);
        }
        return appShareActions;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (shareAction != null) {
            shareAction.close();
        }
        UMShareAPI.get(this).release();
        umShareListener = null;
        shareDialog = null;
        shareDialogLandscape = null;
    }
}
