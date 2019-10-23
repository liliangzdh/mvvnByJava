package com.kaoyaya.tongkai.ui.home;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.kaoyaya.tongkai.entity.HomeResource;
import com.li.basemvvm.base.ItemViewModel;

public class GoodCourseItemViewModel extends ItemViewModel<HomeViewModel> {

   public ObservableField<HomeResource>  entity = new ObservableField<>();

    public GoodCourseItemViewModel(@NonNull HomeViewModel viewModel,HomeResource resource) {
        super(viewModel);
        this.entity.set(resource);
    }

    /**
     * 获取position的方式有很多种,indexOf是其中一种，常见的还有在Adapter中、ItemBinding.of回调里
     *
     * @return
     */
    public int getPosition() {
        return viewModel.getItemPosition(this);
    }


}
