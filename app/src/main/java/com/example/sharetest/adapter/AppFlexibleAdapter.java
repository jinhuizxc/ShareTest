package com.example.sharetest.adapter;

import android.support.annotation.Nullable;

import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;

public class AppFlexibleAdapter extends FlexibleAdapter<AppFlexibleItem> {
    public AppFlexibleAdapter(@Nullable List<AppFlexibleItem> items) {
        super(items);
    }

    @Override
    public void updateDataSet(@Nullable List<AppFlexibleItem> items, boolean animate) {
        super.updateDataSet(items, animate);
    }
}
