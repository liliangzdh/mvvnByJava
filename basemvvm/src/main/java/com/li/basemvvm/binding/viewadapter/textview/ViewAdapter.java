package com.li.basemvvm.binding.viewadapter.textview;

import android.graphics.Color;
import android.util.Log;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

public class ViewAdapter {

    @BindingAdapter({"textColor"})
    public static void textColor(TextView textView, final String color) {
        Log.d("test","asas"+color);
        textView.setTextColor(Color.parseColor(color));
    }
}
