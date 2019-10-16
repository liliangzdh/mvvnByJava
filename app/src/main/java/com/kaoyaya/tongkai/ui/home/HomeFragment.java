package com.kaoyaya.tongkai.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.gyf.immersionbar.ImmersionBar;
import com.kaoyaya.tongkai.R;
import com.kaoyaya.tongkai.databinding.FragmentHomeBinding;
import com.kaoyaya.tongkai.entity.HomeResourseDistribute;
import com.kaoyaya.tongkai.utils.GlideImageLoader;
import com.li.basemvvm.BR;
import com.li.basemvvm.base.BaseFragment;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;

public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeViewModel> {
    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_home;
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
                statusBarColor(R.color.white).
                statusBarDarkFont(true, 0.2f).
                init();
        initBanner();

        // 发起获取分发资源请求
        viewModel.getNetResource();
    }


    @Override
    public void initViewObservable() {
        super.initViewObservable();
        updateBanner();
    }

    //更新banner 数据
    private void updateBanner() {
        viewModel.uc.finishGetBannerData.observe(this, new Observer<HomeResourseDistribute>() {
            @Override
            public void onChanged(HomeResourseDistribute homeResourseDistribute) {
                binding.banner.update(homeResourseDistribute.getResource());
            }
        });
    }

    private void initBanner() {
        binding.banner.setImageLoader(new GlideImageLoader());
        ArrayList<Integer> imgArr = new ArrayList<>();
        imgArr.add(R.mipmap.banner);
        binding.banner.setImages(imgArr);
        binding.banner.isAutoPlay(true);
        binding.banner.setIndicatorGravity(BannerConfig.CENTER);
        binding.banner.start();
    }

}
