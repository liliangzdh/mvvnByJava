package com.kaoyaya.tongkai.ui.user;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.gyf.immersionbar.ImmersionBar;
import com.kaoyaya.tongkai.BR;
import com.kaoyaya.tongkai.R;
import com.kaoyaya.tongkai.databinding.FragmentUserCenterBinding;
import com.li.basemvvm.base.BaseFragment;

public class UserCenterFragment extends BaseFragment<FragmentUserCenterBinding, UserCenterViewModel> {





    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_user_center;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    public void initData() {
        super.initData();
        initStatusBar();
    }

    public void initStatusBar() {
        ImmersionBar.with(this)
                .statusBarDarkFont(false)
                .titleBar(binding.toolbar)
                .init();
    }
}
