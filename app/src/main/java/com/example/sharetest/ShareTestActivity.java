package com.example.sharetest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.blankj.utilcode.util.ActivityUtils;
import com.example.sharetest.share.shareto.SecondActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Android分享内容和接收分享内容小小实现
 * https://www.cnblogs.com/daner1257/p/5581443.html
 * <p>
 * Android - 分享内容 - 接收其他APP的内容
 * https://www.cnblogs.com/fengquanwang/archive/2013/06/21/3148689.html
 */
public class ShareTestActivity extends AppCompatActivity {

    @BindView(R.id.bt_share_to)
    Button btShareTo;
    @BindView(R.id.btn_to_share)
    Button btnToShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_test);
        ButterKnife.bind(this);

    }


    @OnClick({R.id.bt_share_to, R.id.btn_to_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_share_to:
                ActivityUtils.startActivity(SecondActivity.class);
                break;
            case R.id.btn_to_share:
                break;
        }
    }
}
