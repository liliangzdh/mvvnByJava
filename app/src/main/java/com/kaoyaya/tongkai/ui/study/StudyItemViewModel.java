package com.kaoyaya.tongkai.ui.study;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.kaoyaya.tongkai.entity.StudyResourceItem;
import com.kaoyaya.tongkai.entity.TiKuExamInfo;
import com.kaoyaya.tongkai.ui.home.HomeViewModel;
import com.li.basemvvm.base.ItemViewModel;
import com.li.basemvvm.binding.command.BindingAction;
import com.li.basemvvm.binding.command.BindingCommand;

public class StudyItemViewModel extends ItemViewModel<StudyViewModel> {

    public ObservableField<StudyResourceItem> entity = new ObservableField<>();

    public StudyItemViewModel(@NonNull StudyViewModel viewModel, StudyResourceItem resource) {
        super(viewModel);
        this.entity.set(resource);
    }


    public BindingCommand click = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            viewModel.changeClass(entity.get());
        }
    });


}
