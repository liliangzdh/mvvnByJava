package com.kaoyaya.tongkai.ui.study.item;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.kaoyaya.tongkai.entity.LearnCourseInfo;
import com.kaoyaya.tongkai.ui.study.StudyViewModel;
import com.li.basemvvm.base.ItemViewModel;
import com.li.basemvvm.binding.command.BindingAction;
import com.li.basemvvm.binding.command.BindingCommand;

public class VideoRecordItemViewModel extends ItemViewModel<StudyViewModel> {

    public ObservableField<LearnCourseInfo> entity = new ObservableField<>();

    public VideoRecordItemViewModel(@NonNull StudyViewModel viewModel, LearnCourseInfo info) {
        super(viewModel);
        entity.set(info);
    }

    public BindingCommand clickCommend = new BindingCommand(new BindingAction() {
        @Override
        public void call() {

        }
    });
}
