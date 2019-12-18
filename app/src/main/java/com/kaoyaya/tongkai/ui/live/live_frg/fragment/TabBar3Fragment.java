package com.kaoyaya.tongkai.ui.live.live_frg.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.kaoyaya.tongkai.BR;
import com.kaoyaya.tongkai.R;
import com.li.basemvvm.base.BaseFragment;


/**
 * Created by goldze on 2018/7/18.
 */

public class TabBar3Fragment extends BaseFragment {
    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_tab_bar_2;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        super.initData();
        Log.e("test","init 3:" );
    }

}
