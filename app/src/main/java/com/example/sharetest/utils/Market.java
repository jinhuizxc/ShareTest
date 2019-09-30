package com.example.sharetest.utils;

public enum Market {

//    MINGYU("mingyu", "com.mykj.mingyu"),
    TENCENT("tencent", "com.tencent.android.qqdownloader"),
    QIHOO360("qihoo360", "com.qihoo.appstore"),
    BAIDU("baidu", "com.baidu.appsearch"),
    XIAOMI("xiaomi", "com.xiaomi.market"),
    HUAWEI("huawei", "com.huawei.appmarket"),
    OPPO("oppo", "com.oppo.market"),
    VIVO("vivo", "com.bbk.appstore"),
    MEIZU("meizu", "com.meizu.mstore"),
    GOAPK("goapk", "cn.goapk.market"),
    CHUIZI("chuizi", "com.smartisan.appstore"),
    JINLI("jinli", "com.gionee.aora.market"),
    ALI("ali", "com.pp.assistant"),
    SANXING("sanxing", "com.sec.android.app.samsungapps"),
    MUMAYI("mumayi", "com.mumayi.market.ui");

    private String channel;
    private String packageName;

    Market(String channel, String packageName) {
        this.channel = channel;
        this.packageName = packageName;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getChannel() {
        return channel;
    }
}
