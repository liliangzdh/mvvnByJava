package com.kaoyaya.tongkai.ui.home;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.kaoyaya.tongkai.entity.LiveInfo;
import com.li.basemvvm.base.ItemViewModel;

public class LiveItemViewModel extends ItemViewModel<HomeViewModel> {

    public ObservableField<LiveInfo> entity = new ObservableField<>();


    public LiveItemViewModel(@NonNull HomeViewModel viewModel,LiveInfo liveInfo) {
        super(viewModel);
        entity.set(liveInfo);
    }
}
