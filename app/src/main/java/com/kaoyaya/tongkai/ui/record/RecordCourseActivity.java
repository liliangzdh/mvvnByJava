package com.kaoyaya.tongkai.ui.record;

import android.os.Bundle;
import android.widget.PopupWindow;

import androidx.lifecycle.Observer;

import com.kaoyaya.tongkai.BR;
import com.kaoyaya.tongkai.R;
import com.kaoyaya.tongkai.databinding.ActivityRecordCourseBinding;
import com.kaoyaya.tongkai.utils.PopUtils;
import com.li.basemvvm.base.BaseActivity;

public class RecordCourseActivity extends BaseActivity<ActivityRecordCourseBinding, RecordCourseViewModel> {
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_record_course;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }


    @Override
    public void initData() {
        super.initData();
        initStatusBar();
        viewModel.requestAll();
    }

    PopupWindow popupWindow;

    @Override
    public void initViewObservable() {
        super.initViewObservable();
        viewModel.showFilterEvent.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    if (popupWindow != null) {
                        popupWindow.showAsDropDown(binding.headView);
                        return;
                    }
                    popupWindow = PopUtils.getInstance().showRecordFilterDialog(RecordCourseActivity.this, binding.headView, viewModel.itemArrayFilterDialog, viewModel.itemBindingFilterDialog);
                } else {
                    if (popupWindow != null) {
                        popupWindow.dismiss();
                    }
                }
            }
        });


        viewModel.refreshEvent.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                binding.springView.onFinishFreshAndLoad();
            }
        });
    }
}
