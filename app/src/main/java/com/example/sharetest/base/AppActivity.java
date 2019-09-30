package com.example.sharetest.base;

import android.support.v4.content.ContextCompat;

import com.example.sharetest.R;
import com.example.sharetest.base.AbstractBaseActivity;
import com.example.sharetest.event.EmptyEvent;
import com.example.sharetest.utils.AppBarUtils;
import com.umeng.analytics.MobclickAgent;
import com.umeng.message.PushAgent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


/**
 * Created by Administrator on 2018/3/15.
 * EventBus 监听 在onPaused 时候销毁
 */
public abstract class AppActivity extends AbstractBaseActivity {

    @Override
    public void init() {
        // umeng push
        PushAgent.getInstance(this).onAppStart();
        AppBarUtils.setStatusBarColor(this, ContextCompat.getColor(this, R.color.color_white), 0);
        AppBarUtils.setLightMode(this, true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
        MobclickAgent.onPause(this);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEmptyEvent(EmptyEvent emptyEvent) {

    }

}
