package com.example.sharetest.share;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.sharetest.R;
import com.example.sharetest.adapter.AppFlexibleItem;
import com.example.sharetest.adapter.AppFlexibleViewHolder;

import java.util.List;

import butterknife.BindView;
import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.IFlexible;

public class ShareDialogItem extends AppFlexibleItem<ShareDialogItem.ShareItemHolder> {

    private AppShareAction appShareAction;

    public ShareDialogItem(String id) {
        super(id);
    }

    public ShareDialogItem(AppShareAction appShareAction) {
        super(appShareAction.aid + "");
        this.appShareAction = appShareAction;
    }

    @Override
    public boolean equals(Object o) {
        return this == o;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.layout_share_item;
    }

    @Override
    public ShareItemHolder createViewHolder(View view, FlexibleAdapter<IFlexible> adapter) {
        return new ShareItemHolder(view, adapter);
    }

    @Override
    public void bindViewHolder(FlexibleAdapter<IFlexible> adapter, ShareItemHolder holder, int position, List<Object> payloads) {
        holder.ivActionIcon.setImageResource(appShareAction.actionIconRes);
        if (!TextUtils.isEmpty(appShareAction.actionName)) {
            holder.tvActionName.setVisibility(View.VISIBLE);
            holder.tvActionName.setText(appShareAction.actionName);
        } else {
            holder.tvActionName.setVisibility(View.GONE);
        }
        holder.itemView.setTag(appShareAction);
    }

    static class ShareItemHolder extends AppFlexibleViewHolder {
        @BindView(R.id.iv_action_icon)
        ImageView ivActionIcon;
        @BindView(R.id.tv_action_name)
        TextView tvActionName;

        public ShareItemHolder(View view, FlexibleAdapter adapter) {
            super(view, adapter);
        }
    }

}