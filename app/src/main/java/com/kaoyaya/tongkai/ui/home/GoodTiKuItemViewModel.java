package com.kaoyaya.tongkai.ui.home;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.kaoyaya.tongkai.entity.HomeResource;
import com.kaoyaya.tongkai.entity.TiKuExamInfo;
import com.li.basemvvm.base.ItemViewModel;

public class GoodTiKuItemViewModel extends ItemViewModel<HomeViewModel> {

   public ObservableField<TiKuExamInfo>  entity = new ObservableField<>();

    public GoodTiKuItemViewModel(@NonNull HomeViewModel viewModel, TiKuExamInfo resource) {
        super(viewModel);
        this.entity.set(resource);
    }



}
