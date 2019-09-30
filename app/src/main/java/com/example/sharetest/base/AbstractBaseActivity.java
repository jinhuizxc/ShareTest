package com.example.sharetest.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.example.sharetest.utils.FlashBucket;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by ad on 2019/3/15
 */

public abstract class AbstractBaseActivity extends AppCompatActivity {

    public FlashBucket flashBucket;
    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (flashBucket == null) {
            flashBucket = FlashBucket.instance();
        }

        ScreenUtils.setPortrait(this);

        BarUtils.setStatusBarVisibility(this, true);
        setContentView(getActivityLayoutId());
        unbinder = ButterKnife.bind(this);
        init();

    }


    @Override
    protected void onDestroy() {
//        ScreenUtils.cancelAdaptScreen(this);
        resetToast();
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }

    }


    public abstract int getActivityLayoutId();

    public abstract void init();

//    @ColorRes
//    public abstract int getStatusBarColor();

//    public abstract boolean enableImmersionBar();

    /**
     * 重置吐司的样式
     */
    public void resetToast() {
        //do nothing
    }
}
