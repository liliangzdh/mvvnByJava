package com.kaoyaya.tongkai.ui.home;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.kaoyaya.tongkai.entity.HomeResource;
import com.li.basemvvm.base.ItemViewModel;

public class GoodTeacherItemViewModel extends ItemViewModel<HomeViewModel> {

   public ObservableField<HomeResource>  entity = new ObservableField<>();

    public GoodTeacherItemViewModel(@NonNull HomeViewModel viewModel, HomeResource resource) {
        super(viewModel);
        this.entity.set(resource);
    }



}
