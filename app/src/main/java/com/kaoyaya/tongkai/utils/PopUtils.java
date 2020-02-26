package com.kaoyaya.tongkai.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayList;

import com.kaoyaya.tongkai.BR;
import com.kaoyaya.tongkai.R;
import com.kaoyaya.tongkai.databinding.DialogLiveBackFilterBinding;
import com.kaoyaya.tongkai.databinding.DialogRecordFilterBinding;
import com.kaoyaya.tongkai.ui.live.live_frg.vm.LiveFilterItemViewModel;
import com.kaoyaya.tongkai.ui.record.item.ItemRecordFilterDialogModel;

import me.tatarka.bindingcollectionadapter2.ItemBinding;

public class PopUtils {

    private static PopUtils instance;

    private PopUtils() {
    }

    public static PopUtils getInstance() {
        if (instance == null) {
            instance = new PopUtils();
        }
        return instance;
    }


    // 直播课过滤
    public PopupWindow showLiveBackFilterDialog(Context context, View headView, ObservableArrayList<LiveFilterItemViewModel> items, ItemBinding<LiveFilterItemViewModel> itemBinding) {

        DialogLiveBackFilterBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_live_back_filter, null, false);
//        View view = View.inflate(context, R.layout.dialog_live_back_filter, null);
        final PopupWindow popupBigPhoto = new PopupWindow(binding.getRoot(), ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupBigPhoto.setOutsideTouchable(true);


        binding.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupBigPhoto.dismiss();
            }
        });


        binding.setItemBinding(itemBinding);
        binding.setVariable(BR.items, items);

        popupBigPhoto.showAsDropDown(headView);

        return popupBigPhoto;
    }


    // 录播课过滤
    public PopupWindow showRecordFilterDialog(Context context, View headView, ObservableArrayList<ItemRecordFilterDialogModel> items, ItemBinding<ItemRecordFilterDialogModel> itemBinding) {

        DialogRecordFilterBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_record_filter, null, false);

        binding.setItemBinding(itemBinding);
        binding.setVariable(BR.items, items);


        final PopupWindow popupBigPhoto = new PopupWindow(binding.getRoot(), ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupBigPhoto.setOutsideTouchable(true);


        binding.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupBigPhoto.dismiss();
            }
        });
        popupBigPhoto.showAsDropDown(headView);

        return popupBigPhoto;
    }

}
