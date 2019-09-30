package com.example.sharetest.adapter;

import android.content.Context;
import android.view.View;

import butterknife.ButterKnife;
import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.viewholders.FlexibleViewHolder;

public abstract class AppFlexibleViewHolder extends FlexibleViewHolder {

    Context context;

    public AppFlexibleViewHolder(View view, FlexibleAdapter adapter) {
        super(view, adapter);
        ButterKnife.bind(this, view);

        context = view.getContext();
    }

    public AppFlexibleViewHolder(View view, FlexibleAdapter adapter, boolean b) {
        super(view, adapter, b);
        ButterKnife.bind(this, view);

        context = view.getContext();
    }

    public Context getContext() {
        return context;
    }
}