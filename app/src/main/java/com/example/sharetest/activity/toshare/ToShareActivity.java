package com.example.sharetest.activity.toshare;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.sharetest.R;
import com.example.sharetest.activity.sample.UmengSample1Activity;
import com.example.sharetest.umeng.UmengActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 通过原生分享;
 * 通过第三方分享;
 * <p>
 * 友盟集成分享
 * https://developer.umeng.com/docs/66632/detail/66639
 */
public class ToShareActivity extends AppCompatActivity {

    @BindView(R.id.btn_share1)
    Button btnShare1;
    @BindView(R.id.btn_share2)
    Button btnShare2;
    @BindView(R.id.btn_sample1)
    Button btnSample;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_share);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_share1, R.id.btn_share2, R.id.btn_sample1})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_share1:
                ToastUtils.showShort("todo");
                // 分享文本、图片、链接等;
                break;
            case R.id.btn_share2:
                ActivityUtils.startActivity(UmengActivity.class);
                break;
            case R.id.btn_sample1:
                ActivityUtils.startActivity(UmengSample1Activity.class);
                break;
        }
    }

}
