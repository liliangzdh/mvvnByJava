package com.kaoyaya.tongkai.ui.study;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

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
        initStatusBar();

        viewModel.request();
    }

    public void initStatusBar() {
        ImmersionBar.with(this)
                .statusBarDarkFont(false)
                .titleBar(binding.toolbar)
                .init();
    }


    @Override
    public void initViewObservable() {
        super.initViewObservable();

        viewModel.uc.scrollEvent.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                switch (integer) {
                    case 0:
                        binding.scrollView.scrollTo(0,binding.llTop1.getRoot().getTop());
                        break;
                    case 1:
                        binding.scrollView.scrollTo(0,binding.llTop2.getRoot().getTop());
                        break;
                    case 2:
                        binding.scrollView.scrollTo(0,binding.llTop3.getRoot().getTop());
                        break;
                    case 3:
                        binding.scrollView.scrollTo(0,binding.llTop4.getRoot().getTop());
                        break;
                }
            }
        });
    }
}
