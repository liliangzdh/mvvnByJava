package com.kaoyaya.tongkai.ui.live.live_frg.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.kaoyaya.tongkai.BR;
import com.kaoyaya.tongkai.R;
import com.kaoyaya.tongkai.databinding.FragmentPageLiveBackBinding;
import com.kaoyaya.tongkai.entity.LiveCommand;
import com.kaoyaya.tongkai.ui.live.live_frg.vm.LiveBackPageViewModel;
import com.li.basemvvm.base.BaseFragment;


/**
 * Created by goldze on 2018/7/18.
 */

public class LiveBackPageFragment extends BaseFragment<FragmentPageLiveBackBinding, LiveBackPageViewModel> {
    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_page_live_back;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        super.initData();
        Log.e("test", "init 3:");
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
        viewModel.commandEvent.observe(this, new Observer<LiveCommand>() {
            @Override
            public void onChanged(LiveCommand liveCommand) {
                binding.springView.onFinishFreshAndLoad();

                //
                binding.springView.setEnableFooter(liveCommand.hasMore);
            }
        });

    }
}
