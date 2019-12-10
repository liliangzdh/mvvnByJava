package com.kaoyaya.tongkai.ui.study.item;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.kaoyaya.tongkai.entity.TiKuExamInfo;
import com.kaoyaya.tongkai.ui.study.StudyViewModel;
import com.li.basemvvm.base.ItemViewModel;

public class StudyTiKuItemViewModel extends ItemViewModel<StudyViewModel> {

    public ObservableField<TiKuExamInfo> entity = new ObservableField<>();

    public StudyTiKuItemViewModel(@NonNull StudyViewModel viewModel,TiKuExamInfo examInfo) {
        super(viewModel);
        entity.set(examInfo);
    }
}
