package com.kaoyaya.tongkai.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.kaoyaya.tongkai.entity.HomeResource;
import com.youth.banner.loader.ImageLoader;

public class GlideImageLoader  extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        if(path instanceof Integer){
            Glide.with(context).load(path).into(imageView);
        }else if(path instanceof HomeResource){
            HomeResource child = (HomeResource) path;
            Glide.with(context).load(child.getPictureURL()).into(imageView);
        }
    }
}
