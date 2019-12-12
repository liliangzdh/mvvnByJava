package com.li.basemvvm.binding.viewadapter.image;


import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.IdRes;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

/**
 * Created by goldze on 2017/6/18.
 */
public final class ViewAdapter {
    @BindingAdapter(value = {"url", "placeholderRes"}, requireAll = false)
    public static void setImageUri(ImageView imageView, String url, int placeholderRes) {
        if (!TextUtils.isEmpty(url)) {
            //使用Glide框架加载图片
            Glide.with(imageView.getContext())
                    .load(url)
                    .apply(new RequestOptions().placeholder(placeholderRes))
                    .into(imageView);
        } else {
            Glide.with(imageView.getContext()).load(placeholderRes).into(imageView);
        }
    }


    @SuppressLint("ResourceType")
    @BindingAdapter("load_image")
    public static void loadImage(ImageView view, @IdRes int imageId) {
        view.setImageResource(imageId);
    }

    @BindingAdapter("load_image")
    public static void loadImage(ImageView view, Drawable resource) {
        view.setImageDrawable(resource);
    }

    @BindingAdapter("load_image")
    public static void loadImage(ImageView view, Bitmap resource) {
        view.setImageBitmap(resource);
    }

    @BindingAdapter("load_image")
    public static void loadImage(ImageView view, String resource) {

        Log.e("test", "sasas");
    }

}

