package com.kaoyaya.tongkai.ui.live.liveList;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;

import com.kaoyaya.tongkai.databinding.ItemViewpagerBinding;
import com.kaoyaya.tongkai.ui.live.liveList.vm.ViewPageItemViewModel;

import java.util.HashMap;

import me.tatarka.bindingcollectionadapter2.BindingViewPagerAdapter;

public class LiveViewPageAdapter extends BindingViewPagerAdapter<ViewPageItemViewModel> {


    private HashMap<Integer, ItemViewpagerBinding> map = new HashMap<>();


    @Override
    public void onBindBinding(@NonNull ViewDataBinding binding, int variableId, int layoutRes, int position, ViewPageItemViewModel item) {
        super.onBindBinding(binding, variableId, layoutRes, position, item);
        //这里可以强转成ViewPagerItemViewModel对应的ViewDataBinding，
        ItemViewpagerBinding bind = (ItemViewpagerBinding) binding;
        map.put(position, bind);
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.destroyItem(container, position, object);
    }


    public void onFinishFreshAndLoad(int pos) {
        ItemViewpagerBinding itemViewpagerBinding = map.get(pos);
        if (itemViewpagerBinding != null) {
            itemViewpagerBinding.springView.onFinishFreshAndLoad();
        }
    }
}
