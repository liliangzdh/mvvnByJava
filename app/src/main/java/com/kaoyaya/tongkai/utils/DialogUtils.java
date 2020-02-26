package com.kaoyaya.tongkai.utils;

import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayList;

import com.kaoyaya.tongkai.BR;
import com.kaoyaya.tongkai.R;
import com.kaoyaya.tongkai.databinding.DialogRecordFilterBinding;
import com.kaoyaya.tongkai.ui.record.item.ItemRecordFilterDialogModel;
import com.li.basemvvm.base.BaseActivity;

import me.tatarka.bindingcollectionadapter2.ItemBinding;

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





}
