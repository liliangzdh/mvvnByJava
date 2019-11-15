package com.kaoyaya.tongkai.ui.home;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.kaoyaya.tongkai.R;
import com.kaoyaya.tongkai.entity.LiveInfo;
import com.li.basemvvm.base.ItemViewModel;
import com.li.basemvvm.binding.command.BindingAction;
import com.li.basemvvm.binding.command.BindingCommand;

public class LiveItemViewModel extends ItemViewModel<HomeViewModel> {

    public ObservableField<LiveInfo> entity = new ObservableField<>();


    public LiveItemViewModel(@NonNull HomeViewModel viewModel,LiveInfo liveInfo) {
        super(viewModel);
        entity.set(liveInfo);
    }



    public BindingCommand onClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {


        }
    });
}
