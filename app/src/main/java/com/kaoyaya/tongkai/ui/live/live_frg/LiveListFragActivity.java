package com.kaoyaya.tongkai.ui.live.live_frg;

import android.os.Bundle;
import android.util.Log;
import android.widget.PopupWindow;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.google.android.material.tabs.TabLayout;
import com.kaoyaya.tongkai.BR;
import com.kaoyaya.tongkai.R;
import com.kaoyaya.tongkai.databinding.ActivityLiveListFrgBinding;
import com.kaoyaya.tongkai.entity.LiveIdAndClassIdResponse;
import com.kaoyaya.tongkai.ui.base.adapter.BaseFragmentPagerAdapter;
import com.kaoyaya.tongkai.ui.live.live_frg.fragment.LiveBackPageFragment;
import com.kaoyaya.tongkai.ui.live.live_frg.fragment.LivePageFragment;
import com.kaoyaya.tongkai.ui.live.live_frg.fragment.TabBar2Fragment;
import com.kaoyaya.tongkai.ui.live.live_frg.vm.LiveFragViewModel;
import com.kaoyaya.tongkai.utils.PopUtils;
import com.li.basemvvm.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class LiveListFragActivity extends BaseActivity<ActivityLiveListFrgBinding, LiveFragViewModel> {

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_live_list_frg;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override

    public void initData() {
        initStatusBar();
        List<Fragment> list = new ArrayList<>();
        list.add(new LivePageFragment());
        list.add(new TabBar2Fragment());
        list.add(new LiveBackPageFragment());

        List<String> titlePager = new ArrayList<>();
        titlePager.add("直播");
        titlePager.add("测试复用");
        titlePager.add("回放");

        //设置Adapter
        BaseFragmentPagerAdapter pagerAdapter = new BaseFragmentPagerAdapter(getSupportFragmentManager(), list, titlePager);
        binding.viewPager.setAdapter(pagerAdapter);
        binding.tabs.setupWithViewPager(binding.viewPager);
        binding.viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.tabs));
    }


    private PopupWindow popupWindow;

    @Override
    public void initViewObservable() {
        super.initViewObservable();
        viewModel.showDialogEvent.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    if (popupWindow != null) {
                        popupWindow.showAsDropDown(binding.headView);
                        return;
                    }
                    popupWindow = PopUtils.getInstance().showLiveBackFilterDialog(LiveListFragActivity.this, binding.headView, viewModel.items, viewModel.itemBinding);
                } else {
                    if (popupWindow != null) {
                        popupWindow.dismiss();
                    }
                }
            }
        });
    }


    public void showFilterDialog() {

    }
}
