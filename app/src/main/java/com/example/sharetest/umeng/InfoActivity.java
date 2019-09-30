package com.example.sharetest.umeng;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.sharetest.R;
import com.example.sharetest.umeng.base.BaseActivity;
import com.example.sharetest.umeng.views.Item;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.common.ResContainer;
import com.umeng.socialize.shareboard.SnsPlatform;

import java.util.ArrayList;

public class InfoActivity extends BaseActivity {

    private LinearLayout container;
    public ArrayList<SnsPlatform> platforms = new ArrayList<SnsPlatform>();

    private SHARE_MEDIA[] list = {SHARE_MEDIA.QQ,SHARE_MEDIA.SINA,SHARE_MEDIA.WEIXIN,
            SHARE_MEDIA.FACEBOOK,SHARE_MEDIA.LINKEDIN,SHARE_MEDIA.KAKAO,SHARE_MEDIA.VKONTAKTE,SHARE_MEDIA.DROPBOX};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("获取用户资料");
        setBackVisibily();
        initViews();
    }
    private void initViews(){
        LinearLayout container = (LinearLayout)findViewById(R.id.platform_container);
        initPlatforms();
        for (final SnsPlatform platform:platforms){
            Item item = new Item(this);
            item.setIcon(ResContainer.getResourceId(this,"drawable",platform.mIcon));
            item.setName(platform.mShowWord);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,getResources().getDimensionPixelOffset(R.dimen.item_height));
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(InfoActivity.this,InfoDetailActivity.class);
                    intent.putExtra("platform",platform.mPlatform);
                    InfoActivity.this.startActivity(intent);
                }
            });
            container.addView(item,lp);
        }


    }
    @Override
    public int getLayout() {
        return R.layout.activity_ushareplatform;
    }
    private void initPlatforms() {
        platforms.clear();
        for (SHARE_MEDIA e : list) {
            if (!e.toString().equals(SHARE_MEDIA.GENERIC.toString())) {
                platforms.add(e.toSnsPlatform());
            }
        }
    }


}
