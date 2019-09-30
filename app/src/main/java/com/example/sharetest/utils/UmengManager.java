package com.example.sharetest.utils;

import android.app.Notification;
import android.content.Context;
import android.util.Log;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.Utils;

import com.meituan.android.walle.WalleChannelReader;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;

import com.umeng.message.IUmengCallback;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.MsgConstant;
import com.umeng.message.PushAgent;
import com.umeng.message.UTrack;
import com.umeng.message.UmengMessageHandler;
import com.umeng.message.UmengNotificationClickHandler;
import com.umeng.message.entity.UMessage;
import com.umeng.socialize.UMShareAPI;


public class UmengManager {

//    private final static String ALIAS_TYPE = "UMENGTEST";
//    private final static String ALIAS_PRE = "mingyu";

    private final static String APP_KEY = "59892f08310c9307b60023d0";
    private PushAgent mPushAgent;

    public final static UmengManager get() {
        return UmengManager.LazyHolder.INSTANCE;
    }

    public void init() {

        MobclickAgent.setScenarioType(Utils.getApp(), MobclickAgent.EScenarioType.E_UM_NORMAL);
        //设置LOG开关，默认为false
        UMConfigure.setLogEnabled(true);
        // 美团打包
//        String channel = WalleChannelReader.getChannel(Utils.getApp(), Market.MINGYU.getChannel());
        //common
//        UMConfigure.init(Utils.getApp(), APP_KEY, channel, UMConfigure.DEVICE_TYPE_PHONE, "edb6f754fff5e13c90a4e694fbf55afd");
        UMConfigure.init(Utils.getApp(), APP_KEY, "Umeng", UMConfigure.DEVICE_TYPE_PHONE, "");
        //share
        UMShareAPI.get(Utils.getApp());
        //push
        mPushAgent = PushAgent.getInstance(Utils.getApp());
        //注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {
            @Override
            public void onSuccess(String deviceToken) {
                //注册成功会返回device token
                Log.e("TAG", "deviceToken:" + deviceToken);
//                bindUserId(SessionManager.get().getUserId());
            }

            @Override
            public void onFailure(String s, String s1) {
//                Logger.d("onFailure :" + s + "," + s1);
            }
        });

        //默认情况下，同一台设备在1分钟内收到同一个应用的多条通知时，不会重复提醒，同时在通知栏里新的通知会替换掉旧的通知。可以通过如下方法来设置冷却时间
        mPushAgent.setMuteDurationSeconds(10);
        //设置通知栏最多显示两条通知（当通知栏已经有两条通知，此时若第三条通知到达，则会把第一条通知隐藏）：
        mPushAgent.setDisplayNotificationNumber(0);

        mPushAgent.setNotificationPlaySound(MsgConstant.NOTIFICATION_PLAY_SERVER); //声音
        mPushAgent.setNotificationPlayLights(MsgConstant.NOTIFICATION_PLAY_SERVER);//呼吸灯
        mPushAgent.setNotificationPlayVibrate(MsgConstant.NOTIFICATION_PLAY_SERVER);//振动

        UmengMessageHandler messageHandler = new UmengMessageHandler() {

            @Override
            public Notification getNotification(Context context, UMessage uMessage) {
                return super.getNotification(context, uMessage);
            }

            /**
             * 通知的回调方法
             * @param context
             * @param msg
             */
            @Override
            public void dealWithNotificationMessage(Context context, UMessage msg) {
                //调用super则会走通知展示流程，不调用super则不展示通知
//                EventBus.getDefault().post(new UmengPushMsgEvent(msg));
                super.dealWithNotificationMessage(context, msg);
            }

        };

        UmengNotificationClickHandler notificationClickHandler = new UmengNotificationClickHandler() {
            @Override
            public void dealWithCustomAction(Context context, UMessage uMessage) {
                super.dealWithCustomAction(context, uMessage);
            }

            @Override
            public void launchApp(Context context, UMessage uMessage) {
                super.launchApp(context, uMessage);
//                SPUtils.getInstance(Constant.Sp.SP_1_NAME).put(Constant.Sp.HAS_UMENG_MSG, true);
//                FlashBucket.instance().put(MainActivityNew2.KEY_UMESSAGE, uMessage);
//                Logger.d("Umeng Msg launchApp start:" + System.currentTimeMillis());
//                ActivityUtils.startActivity(MainActivityNew2.class);
            }
        };

        mPushAgent.setNotificationClickHandler(notificationClickHandler);
        mPushAgent.setMessageHandler(messageHandler);
    }

    public void bindUserId(final long newUserId) {
        mPushAgent.register(new IUmengRegisterCallback() {
            @Override
            public void onSuccess(String deviceToken) {
                //注册成功会返回device token
//                mPushAgent.setAlias(ALIAS_PRE + newUserId, ALIAS_TYPE, new UTrack.ICallBack() {
//                    @Override
//                    public void onMessage(boolean b, String s) {
//                        Logger.d("bindUserId,b:" + b + ",s" + s);
//                        if (b) {
//                            boolean msgRemind = SPUtils.getInstance(Constant.Sp.SP_1_NAME).getBoolean(Constant.RemindAction.REMIND_MSG, true);
//                            if (msgRemind) {
//                                UmengManager.get().open();
//                            } else {
//                                UmengManager.get().close();
//                            }
//                        }
//                    }
//                });
            }

            @Override
            public void onFailure(String s, String s1) {
//                Logger.d("onFailure :" + s + "," + s1);
            }
        });
    }

//    public void unBindUserId(final long newUserId) {
//        mPushAgent.deleteAlias(ALIAS_PRE + newUserId, ALIAS_TYPE, new UTrack.ICallBack() {
//            @Override
//            public void onMessage(boolean b, String s) {
////                Logger.d("unBindUserId,b:" + b + ",s" + s);
//            }
//        });
//    }

    public String getDeviceToken() {
        return mPushAgent.getRegistrationId();
    }

    public void close() {
        mPushAgent.disable(new IUmengCallback() {
            @Override
            public void onSuccess() {
//                Logger.d("close success");
            }

            @Override
            public void onFailure(String s, String s1) {
//                Logger.d("close error");
            }
        });
    }

    public void open() {
        mPushAgent.enable(new IUmengCallback() {
            @Override
            public void onSuccess() {
//                Logger.d("open success");
            }

            @Override
            public void onFailure(String s, String s1) {
//                Logger.d("open error");
            }
        });
    }

    public void foreground(boolean b) {
        if (mPushAgent != null) {
            mPushAgent.setNotificaitonOnForeground(b);
        }
    }

    private static class LazyHolder {
        private static final UmengManager INSTANCE = new UmengManager();
    }


}
