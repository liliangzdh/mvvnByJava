package com.li.basemvvm.utils;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.li.basemvvm.R;



public class ToastUtils {


    public static void makeText(Context context, String text) {
        Toast toast = new Toast(context);
        TextView view = new TextView(context);
        view.setBackgroundResource(R.drawable.toast_bg);
        view.setTextColor(Color.WHITE);
        view.setText(text);
        view.setTextSize(20);

        int left = SizeUtils.dp2px(context, 20);
        int top = SizeUtils.dp2px(context, 10);
        view.setPadding(left, top, left, top);
        toast.setView(view);
        toast.show();
    }

    public static void show(Context context,String text) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(layoutInflater == null){
            return;
        }
        final View toastLayout = layoutInflater.inflate(R.layout.layout_toast, null);
        TextView toast_text = toastLayout.findViewById(R.id.toast_text);
        ImageView icon = toastLayout.findViewById(R.id.toast_icon);
        icon.setVisibility(View.GONE);
        toast_text.setText(text);
        Toast toast = new Toast(context);
        toast.setView(toastLayout);
        toast.show();
    }




}
