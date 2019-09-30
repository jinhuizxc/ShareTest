package com.example.sharetest.activity.sample;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.sharetest.base.AppShareActivity;
import com.example.sharetest.R;
import com.umeng.socialize.bean.SHARE_MEDIA;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UmengSample1Activity extends AppShareActivity {

    @BindView(R.id.root_layout)
    LinearLayout rootLayout;
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_right_action)
    ImageView ivRightAction;

    @Override
    public void shareTo(SHARE_MEDIA platForm) {
//        String thumb = Constant.getUploadImageUrlPrefix() + mArticle.news_head;
//        if (TextUtils.isEmpty(mArticle.news_head)) {
//            thumb = Constant.Share.THUMB;
//        }
        String thumb = "https://avatars0.githubusercontent.com/u/24784194?s=460&v=4";
        String share_url = "https://github.com/jinhuizxc?tab=repositories";
        String news_title = "jinhuizxc";
        String desc = "描述信息";
        if (platForm == SHARE_MEDIA.SINA) {
            boolean netActive = NetworkUtils.isConnected();
            if (!netActive) {
                ToastUtils.showShort("网络不给力，请检查网络连接");
                return;
            }
            createWebShare(share_url, news_title, news_title + getString(R.string.app_name), thumb, platForm);
        } else {
//            StringBuilder descSb = new StringBuilder("");
//            if (TextUtils.isEmpty(news_subtitle)) {
//                descSb.append(getString(R.string.hint_share_abstract));
//            } else {
//                String subTitleR = news_subtitle.replace("\n", "");
//                descSb.append(subTitleR);
//            }
            createWebShare(share_url, news_title, desc, thumb, platForm);
        }
    }

    @Override
    public int getActivityLayoutId() {
        return R.layout.activity_umeng_sample;
    }

    @Override
    public void init() {
        super.init();

        BarUtils.addMarginTopEqualStatusBarHeight(rootLayout);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.ll_back, R.id.iv_right_action})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.iv_right_action:
                share(0, initActions2(), false);
                break;
        }
    }
}
