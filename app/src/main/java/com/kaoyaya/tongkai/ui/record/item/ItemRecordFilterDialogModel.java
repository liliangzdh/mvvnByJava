package com.kaoyaya.tongkai.ui.record.item;

import android.service.autofill.OnClickAction;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.kaoyaya.tongkai.entity.ExamInfo;
import com.kaoyaya.tongkai.ui.record.RecordCourseViewModel;
import com.li.basemvvm.base.ItemViewModel;
import com.li.basemvvm.binding.command.BindingAction;
import com.li.basemvvm.binding.command.BindingCommand;

public class ItemRecordFilterDialogModel extends ItemViewModel<RecordCourseViewModel> {


    public ObservableField<ExamInfo> entity = new ObservableField<>();

    public ItemRecordFilterDialogModel(@NonNull RecordCourseViewModel viewModel, ExamInfo info) {
        super(viewModel);
        entity.set(info);
    }


    // 单个点击。
    public BindingCommand clickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            ExamInfo examInfo = entity.get();
            if(examInfo != null){
                viewModel.startFilter(examInfo);
            }
        }
    });
}
