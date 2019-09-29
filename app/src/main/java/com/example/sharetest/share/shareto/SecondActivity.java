package com.example.sharetest.share.shareto;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.util.Log;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sharetest.R;

import java.util.List;
import java.util.regex.Matcher;

public class SecondActivity extends AppCompatActivity {

    private static final String TAG = "ShareTestContent";
    private static TextView tv_content;
    private static Button bt_test;
    private static TextView tv_text;

    private SecondActivity context;
    AlertDialog alertDialog;

    private String content = "习近平给福建省寿宁县下党乡乡亲们的回信http://t.zijieimg.com/DM2aoF/";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        context = this;

        bt_test = findViewById(R.id.bt_test);
        tv_content = findViewById(R.id.tv_content);
        tv_text = findViewById(R.id.tv_text);

        bt_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(content);
            }
        });

        dealIntent(this);

        tv_text.setText("为文字设置超链接");
        SpannableString spannableString = new SpannableString(tv_text.getText().toString());
        URLSpan urlSpan = new URLSpan("http://www.jianshu.com/users/dbae9ac95c78");
        spannableString.setSpan(urlSpan, 5, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tv_text.setMovementMethod(LinkMovementMethod.getInstance());
        tv_text.setHighlightColor(Color.parseColor("#36969696"));
        tv_text.setText(spannableString);

        // 获取字符串中的url
        String data = "#在抖音，记录美好生活#这大概就是冰雪美人吧…… https://www.douyin.com/ 复制此链接，打开【抖音短视频】，直接观看视频！";
        Matcher matcher = Patterns.WEB_URL.matcher(data);
        if (matcher.find()){
            System.out.println(matcher.group());
//            Log.e(TAG, "url: " + matcher.group());
            // E/ShareTestContent: url: http://v.douyin.com/eUWYth
        }


    }

    public void dealIntent(Activity activity) {
        // 获得 intent, action 和 MIME type
        Intent intent = activity.getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        if (Intent.ACTION_SEND.equals(action) && type != null) {
            if ("text/plain".equals(type)) {
                handleSendText(intent); // 处理发送来的文字
            } else if (type.startsWith("image/")) {
                handleSendImage(intent); // 处理发送来的图片
            }
        } else if (Intent.ACTION_SEND_MULTIPLE.equals(action) && type != null) {
            if (type.startsWith("image/")) {
                handleSendMultipleImages(intent); // 处理发送来的多张图片
            }
        } else {
            // 处理其他intents，比如由主屏启动
        }
    }

    public void handleSendText(Intent intent) {
        String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
        if (sharedText != null) {
            // 根据分享的文字更新UI
            Log.e(TAG, "handleSendText: " + sharedText);
            // E/ShareTestContent: handleSendText: 习近平给福建省寿宁县下党乡乡亲们的回信http://t.zijieimg.com/DM2aoF/
            // 触发弹框
            showDialog(sharedText);
//            editText.setText(sharedText);
        }
    }

    /**
     * 提示对话框
     *
     * @param
     */
    private void showDialog(final String sharedText) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final AlertDialog dialog = builder.create();
        View dialogView = View.inflate(context, R.layout.item_share, null);
        //设置对话框布局
        dialog.setView(dialogView);
        dialog.show();

        // 设置内容
        TextView tv_text = dialogView.findViewById(R.id.tv_text);
        TextView tv_cancel = dialogView.findViewById(R.id.tv_cancel);
        TextView tv_ok = dialogView.findViewById(R.id.tv_ok);
        tv_text.setText(sharedText);
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                tv_content.setText(sharedText);

                // 设置样式
                SpannableString spannableString = new SpannableString(tv_content.getText().toString());
                Matcher matcher = Patterns.WEB_URL.matcher(tv_content.getText().toString());
                if (matcher.find()){
                    System.out.println(matcher.group());
                    Log.e(TAG, "url: " + matcher.group());
                    // E/ShareTestContent: url: http://v.douyin.com/eUWYth
                }
                Log.e(TAG, "url地址: " + matcher.group());  // url地址: http://t.zijieimg.com/DM2aoF/
                URLSpan urlSpan = new URLSpan(matcher.group());
                // 下标位置
                spannableString.setSpan(urlSpan, spannableString.length() - matcher.group().length(),
                        spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                tv_content.setMovementMethod(LinkMovementMethod.getInstance());
                tv_content.setHighlightColor(Color.parseColor("#36969696"));
                tv_content.setText(spannableString);


            }
        });



    }

    private static void handleSendImage(Intent intent) {
        Uri imageUri = (Uri) intent.getParcelableExtra(Intent.EXTRA_STREAM);
        if (imageUri != null) {
            // 根据分享的图片更新UI
            Log.e(TAG, "handleSendText: " + imageUri);
        }
    }

    private static void handleSendMultipleImages(Intent intent) {
        List<Uri> imageUris = intent.getParcelableArrayListExtra(Intent.EXTRA_STREAM);
        if (imageUris != null) {
            // 根据分享的多张图片更新UI
            Log.e(TAG, "handleSendText: " + imageUris);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            startActivity(new Intent(this, ShareToActivity.class));
        }
        return super.onKeyDown(keyCode, event);
    }
}
