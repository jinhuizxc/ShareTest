package com.example.sharetest;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

/**
 * Android分享内容和接收分享内容小小实现
 * https://www.cnblogs.com/daner1257/p/5581443.html
 * <p>
 * Android - 分享内容 - 接收其他APP的内容
 * https://www.cnblogs.com/fengquanwang/archive/2013/06/21/3148689.html
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.bt_go).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });



    }


}
