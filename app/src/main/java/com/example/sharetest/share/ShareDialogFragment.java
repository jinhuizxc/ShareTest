package com.example.sharetest.share;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;


import com.example.sharetest.R;
import com.example.sharetest.adapter.AppFlexibleAdapter;
import com.example.sharetest.adapter.AppFlexibleItem;
import com.example.sharetest.base.AppDialogFragment;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.common.SmoothScrollGridLayoutManager;


public class ShareDialogFragment extends AppDialogFragment {

    @BindView(R.id.tv_share_dialog_title)
    TextView tvShareDialogTitle;
    //    @BindView(R.id.empty_view)
//    View emptyView;
    private List<AppFlexibleItem> items;
    private List<AppShareAction> appShareActions;

    private int spanCount = 4;
    private RecyclerView rcvShare;
    private boolean showTitle;
    private AppFlexibleAdapter shareDialogAdapter;
    private Activity activity;
    private SmoothScrollGridLayoutManager smoothScrollGridLayoutManager;
    private FlexibleAdapter.OnItemClickListener itemClickListener;

    public static ShareDialogFragment getInstance() {
        ShareDialogFragment dialogFragment = new ShareDialogFragment();
        dialogFragment.setCanceledBack(true);
        dialogFragment.setCanceledOnTouchOutside(true);
        dialogFragment.setGravity(Gravity.BOTTOM);
        dialogFragment.setWidth(1f);
        return dialogFragment;
    }

    public void setSpanCount(int spanCount) {
        this.spanCount = spanCount;
    }

    public void showTitle(boolean showTitle) {
        this.showTitle = showTitle;
    }

    public void setOrUpdateData(List<AppShareAction> shareActions) {
        this.appShareActions = shareActions;
        items = new ArrayList<>();
        for (int i = 0; i < this.appShareActions.size(); i++) {
            AppShareAction appShareAction = this.appShareActions.get(i);
            ShareDialogItem item = new ShareDialogItem(appShareAction);
            items.add(item);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (Activity) context;
    }

    @Override
    public int getDialogFragmentLayoutId() {
        return R.layout.layout_dialog_share;
    }

    @Override
    public void init() {
        View view = getView();
        rcvShare = view.findViewById(R.id.rcv_dialog_share);
        if (shareDialogAdapter == null) {
            shareDialogAdapter = new AppFlexibleAdapter(items);
        }
        tvShareDialogTitle.setVisibility(showTitle ? View.VISIBLE : View.GONE);
        smoothScrollGridLayoutManager = new SmoothScrollGridLayoutManager(activity, spanCount);
        rcvShare.setLayoutManager(smoothScrollGridLayoutManager);
        rcvShare.setHasFixedSize(true);
        rcvShare.setAdapter(shareDialogAdapter);
        itemClickListener = new FlexibleAdapter.OnItemClickListener() {
            @Override
            public boolean onItemClick(View view, int position) {
                Object obj = view.getTag();
                if (obj == null) {
                    return false;
                }
                AppShareAction appShareAction = (AppShareAction) obj;
                EventBus.getDefault().post(new AppShareEvent(appShareAction));
                dismiss();
                return true;
            }
        };
        shareDialogAdapter.addListener(itemClickListener);
        shareDialogAdapter.updateDataSet(items);
//        boolean navVisible = BarUtils.isNavBarVisible(activity);
//        if (navVisible) {
//            int navgateHeight = BarUtils.getNavBarHeight();
//            emptyView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, navgateHeight));
//        }
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        try {
            //在每个add事务前增加一个remove事务，防止连续的add
            manager.beginTransaction().remove(this).commit();
            super.show(manager, tag);
        } catch (Exception e) {
            //同一实例使用不同的tag会异常,这里捕获一下
            e.printStackTrace();
        }
    }
    
    @Override
    public void onDestroyView() {
        if (rcvShare != null) {
            rcvShare.setItemAnimator(null);
            rcvShare.setAdapter(null);
            rcvShare = null;
        }
        shareDialogAdapter = null;
        itemClickListener = null;
        smoothScrollGridLayoutManager = null;
        if (items != null) {
            items.clear();
            items = null;
        }
        appShareActions = null;
        super.onDestroyView();
    }

    public class AppShareEvent {

        private AppShareAction appShareAction;

        public AppShareEvent(AppShareAction appShareAction) {
            this.appShareAction = appShareAction;
        }

        public AppShareAction getAppShareAction() {
            return appShareAction;
        }
    }

}
