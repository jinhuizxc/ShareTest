package com.example.sharetest.adapter;

import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;
import eu.davidea.viewholders.FlexibleViewHolder;

public abstract class AppFlexibleItem<VH extends FlexibleViewHolder>
        extends AbstractFlexibleItem<VH> {

    protected String id;

    public AppFlexibleItem(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object inObject) {
        if (inObject instanceof AppFlexibleItem) {
            AppFlexibleItem inItem = (AppFlexibleItem) inObject;
            return this.id.equals(inItem.id);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


//    public void loadImageByTag(Context context, String url, ImageView imageView) {
//        Object object = imageView.getTag(R.id.image_load_tag);
//        if (object == null || !object.equals(url)) {
//            imageView.setTag(R.id.image_load_tag, url);
//            GlideAppUtils.load(context, url, imageView);
//        }
//    }
//    public void loadCenterCropImageByTag(Context context, String url, ImageView imageView) {
//        Object object = imageView.getTag(R.id.image_load_tag);
//        if (object == null || !object.equals(url)) {
//            imageView.setTag(R.id.image_load_tag, url);
//            GlideAppUtils.loadCenterCrop(context, url, imageView);
//        }
//    }
//    public void loadRoundedCornersImageByTag(Context context, String url, ImageView imageView) {
//        Object object = imageView.getTag(R.id.image_load_tag);
//        if (object == null || !object.equals(url)) {
//            imageView.setTag(R.id.image_load_tag, url);
//            GlideAppUtils.loadRoundedCorners(context, url, imageView);
//        }
//    }
//    public void loadCircleCropImageByTag(Context context, String url, ImageView imageView) {
//        Object object = imageView.getTag(R.id.image_load_tag);
//        if (object == null || !object.equals(url)) {
//            imageView.setTag(R.id.image_load_tag, url);
//            GlideAppUtils.loadCircleCrop(context, url, imageView);
//        }
//    }


}
