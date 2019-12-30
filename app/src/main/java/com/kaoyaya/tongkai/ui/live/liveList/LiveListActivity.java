package com.kaoyaya.tongkai.ui.live.liveList;

import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.Observer;

import com.google.android.material.tabs.TabLayout;
import com.kaoyaya.tongkai.BR;
import com.kaoyaya.tongkai.R;
import com.kaoyaya.tongkai.databinding.ActivityLiveListBinding;
import com.kaoyaya.tongkai.entity.LiveCommand;
import com.kaoyaya.tongkai.ui.live.liveList.vm.LiveListViewModel;
import com.li.basemvvm.base.BaseActivity;

public class LiveListActivity extends BaseActivity<ActivityLiveListBinding, LiveListViewModel> {


    LiveViewPageAdapter liveViewPageAdapter;

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_live_list;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        super.initData();
        initStatusBar();
        liveViewPageAdapter = new LiveViewPageAdapter();
        binding.tab.setupWithViewPager(binding.viewPager);
        binding.viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.tab));
        binding.setAdapter(liveViewPageAdapter);

        final View inflate = View.inflate(this, R.layout.layout_tab_item, null);

        binding.tab.post(new Runnable() {
            @Override
            public void run() {
                //自定义 头部的布局组件。
                TabLayout.Tab tabAt = binding.tab.getTabAt(0);
                if (tabAt != null) {
                    tabAt.setCustomView(inflate);
                }
            }
        });
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();

        viewModel.commandEvent.observe(this, new Observer<LiveCommand>() {
            @Override
            public void onChanged(LiveCommand liveCommand) {
                //结束刷新。
                liveViewPageAdapter.onFinishFreshAndLoad(liveCommand.type);
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
