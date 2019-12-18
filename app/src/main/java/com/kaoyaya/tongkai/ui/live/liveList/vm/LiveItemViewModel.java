package com.kaoyaya.tongkai.ui.live.liveList.vm;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.kaoyaya.tongkai.entity.LiveInfo;
import com.li.basemvvm.base.ItemPageViewModel;
import com.li.basemvvm.binding.command.BindingAction;
import com.li.basemvvm.binding.command.BindingCommand;

public class LiveItemViewModel extends ItemPageViewModel<ViewPageItemViewModel> {

    public ObservableField<LiveInfo> entity = new ObservableField<>();


    public LiveItemViewModel(@NonNull ViewPageItemViewModel viewModel,LiveInfo info) {
        super(viewModel);
        entity.set(info);
    }

    public BindingCommand onClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {

        }
    });
}
