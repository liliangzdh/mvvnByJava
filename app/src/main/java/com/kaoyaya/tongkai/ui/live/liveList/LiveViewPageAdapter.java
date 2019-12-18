package com.kaoyaya.tongkai.ui.live.liveList;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;

import com.kaoyaya.tongkai.ui.live.liveList.vm.ViewPageItemViewModel;

import me.tatarka.bindingcollectionadapter2.BindingViewPagerAdapter;

public class LiveViewPageAdapter extends BindingViewPagerAdapter<ViewPageItemViewModel> {


    @Override
    public void onBindBinding(@NonNull ViewDataBinding binding, int variableId, int layoutRes, int position, ViewPageItemViewModel item) {
        super.onBindBinding(binding, variableId, layoutRes, position, item);
        //这里可以强转成ViewPagerItemViewModel对应的ViewDataBinding，
//        ItemViewpagerBinding _binding = (ItemViewpagerBinding) binding;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.destroyItem(container, position, object);
    }
}
