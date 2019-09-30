package com.example.sharetest.utils;

import android.support.v4.util.ArrayMap;

import java.util.Map;

/**
 * Created by zx_js
 * <p>
 * for intent put extra
 */
public class FlashBucket {

    private Map<Object, Object> flash = new ArrayMap<Object, Object>();

    public static FlashBucket instance() {
        return FlashBucketLoader.instance;
    }

    /**
     * @param key
     * @param value
     */
    public void put(String key, Object value) {
        if (value == null || key == null) {
            return;
        }
        flash.put(key, value);
    }

    public void put(Integer key, Object value) {
        if (value == null || key == null) {
            return;
        }
        flash.put(key, value);
    }

    public void put(Long key, Object value) {
        if (value == null || key == null) {
            return;
        }
        flash.put(key, value);
    }

    /**
     * 读取并删除，防止存放的数据越来越多
     *
     * @param key
     * @param defaultValue
     * @param <T>
     * @return
     */
    public <T> T get(String key, T defaultValue) {
        Object v = flash.get(key);
        if (v == null) {
            return defaultValue;
        }
        flash.remove(key);
        return (T) v;
    }

    public <T> T get(Integer key, T defaultValue) {
        Object v = flash.get(key);
        if (v == null) {
            return defaultValue;
        }
        flash.remove(key);
        return (T) v;
    }

    public <T> T get(Long key, T defaultValue) {
        Object v = flash.get(key);
        if (v == null) {
            return defaultValue;
        }
        flash.remove(key);
        return (T) v;
    }

    /**
     * @param key
     */
    public void remove(String key) {
        flash.remove(key);
    }

    public void remove(Integer key) {
        flash.remove(key);
    }

    private static class FlashBucketLoader {
        public static final FlashBucket instance = new FlashBucket();
    }
}