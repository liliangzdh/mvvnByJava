package com.kaoyaya.tongkai.ui.set;

import android.os.Bundle;

import com.kaoyaya.tongkai.BR;
import com.kaoyaya.tongkai.R;
import com.kaoyaya.tongkai.databinding.ActSetBinding;
import com.li.basemvvm.base.BaseActivity;

public class SetActivity extends BaseActivity<ActSetBinding, SetViewModel> {
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.act_set;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }


    @Override
    public void initData() {
        initStatusBar();
    }
}
