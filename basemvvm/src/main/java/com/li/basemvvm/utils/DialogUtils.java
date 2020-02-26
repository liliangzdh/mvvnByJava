package com.li.basemvvm.utils;

import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.li.basemvvm.R;
import com.li.basemvvm.base.BaseActivity;

public class DialogUtils {

    private static DialogUtils instance;

    private DialogUtils() {
    }

    public static DialogUtils getInstance() {
        if (instance == null) {
            instance = new DialogUtils();
        }
        return instance;
    }

    /**
     * 设置dialog屏幕宽度
     **/
    public void setDialogWidth(Context context, double scale, Dialog dialog) {

        Window dialogWindow = dialog.getWindow();
        DisplayMetrics dm = new DisplayMetrics();

        if (context instanceof BaseActivity) {
            BaseActivity activity = (BaseActivity) context;
            activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        }
        int iWidth = (int) (dm.widthPixels * scale);
        if (dialogWindow != null) {
            WindowManager.LayoutParams p = dialogWindow.getAttributes();
            p.width = iWidth;
            dialogWindow.setAttributes(p);
        }
    }

    public void setDialogWidth(Context context, int width, Dialog dialog) {

        Window dialogWindow = dialog.getWindow();
        DisplayMetrics dm = new DisplayMetrics();

        if (context instanceof BaseActivity) {
            BaseActivity activity = (BaseActivity) context;
            activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        }

        if (dialogWindow != null) {
            WindowManager.LayoutParams p = dialogWindow.getAttributes();
            p.width = width;
            dialogWindow.setAttributes(p);
        }
    }

    public void setDialogHeight(Context context, int high, Dialog dialog) {

        Window dialogWindow = dialog.getWindow();
        DisplayMetrics dm = new DisplayMetrics();

        if (context instanceof BaseActivity) {
            BaseActivity activity = (BaseActivity) context;
            activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        }

        if (dialogWindow != null) {
            WindowManager.LayoutParams p = dialogWindow.getAttributes();
            p.height = high;
            dialogWindow.setAttributes(p);
        }
    }


    public Dialog showLoading(Context context) {
        Dialog dialog = new Dialog(context, R.style.dialog);
        View inflate = View.inflate(context, R.layout.layout_loading, null);
        dialog.setContentView(inflate);
        setDialogWidth(context, SizeUtils.dp2px(context, 100), dialog);
        setDialogHeight(context, SizeUtils.dp2px(context, 100), dialog);
        Window window = dialog.getWindow();
        dialog.setCancelable(true);
        View viewById = inflate.findViewById(R.id.linear);
        viewById.getBackground().setAlpha(180);
        if(window != null){
            window.setDimAmount(0f);
        }
        return dialog;
    }

}
