package com.kaoyaya.tongkai.ui.study;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.gyf.immersionbar.ImmersionBar;
import com.kaoyaya.tongkai.BR;
import com.kaoyaya.tongkai.R;
import com.kaoyaya.tongkai.databinding.FragmentStudyBinding;
import com.li.basemvvm.base.BaseFragment;

public class StudyFragment extends BaseFragment<FragmentStudyBinding, StudyViewModel> {


    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_study;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        super.initData();
        ImmersionBar.with(this).
                fitsSystemWindows(true).
                statusBarColor(R.color.colorPrimaryDark).
                init();
    }
}
