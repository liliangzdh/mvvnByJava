package com.kaoyaya.tongkai.utils;

import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.kaoyaya.tongkai.R;
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


    public void showLiveBackFilterDialog(Context context) {
        Dialog dialog = new Dialog(context, R.style.dialog);
        View inflate = View.inflate(context, R.layout.dialog_live_back_filter, null);
        dialog.setContentView(inflate);
        setDialogWidth(context, 1.0, dialog);
        dialog.show();
    }

}
