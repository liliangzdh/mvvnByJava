package com.kaoyaya.tongkai.ui.main;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.kaoyaya.tongkai.entity.ExamInfo;
import com.li.basemvvm.base.ItemViewModel;

public class ExamItemViewModel extends ItemViewModel<MainViewModel> {


    public ObservableField<ExamInfo> entity = new ObservableField<>();

    public ExamItemViewModel(@NonNull MainViewModel viewModel,ExamInfo info) {
        super(viewModel);
        entity.set(info);
    }
}
