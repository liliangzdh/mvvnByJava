package com.kaoyaya.tongkai.ui.main;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;

import com.gyf.immersionbar.ImmersionBar;
import com.kaoyaya.tongkai.BR;
import com.kaoyaya.tongkai.R;
import com.kaoyaya.tongkai.databinding.ActMainBinding;
import com.kaoyaya.tongkai.entity.TabItem;
import com.kaoyaya.tongkai.ui.home.HomeFragment;
import com.kaoyaya.tongkai.ui.home.HomeViewModel;
import com.kaoyaya.tongkai.ui.study.StudyFragment;
import com.kaoyaya.tongkai.ui.user.UserCenterFragment;
import com.kaoyaya.tongkai.utils.SPUtils;
import com.kaoyaya.tongkai.utils.SizeUtils;
import com.li.basemvvm.base.BaseActivity;
import com.li.basemvvm.binding.command.BindingAction;
import com.li.basemvvm.bus.Messenger;

import java.util.ArrayList;
import java.util.List;

import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.PageNavigationView;
import me.majiajie.pagerbottomtabstrip.item.BaseTabItem;
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectedListener;

public class MainActivity extends BaseActivity<ActMainBinding, MainViewModel> {

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.act_main;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) binding.drawerTop.getLayoutParams();
        params.setMargins(0, ImmersionBar.getStatusBarHeight(this), 0, 0);
        binding.drawerTop.setLayoutParams(params);
        initFragment();
        initBottomTab();
        initOpenDrawerListener();
        viewModel.request();

        if (SPUtils.getInstance().isEmptyExamInfo()) {
            // 没有选择。要选择一个。
            binding.drawerLayout.openDrawer(GravityCompat.START);
        }
        setLeftDrawer(SPUtils.getInstance().isEmptyExamInfo());
    }


    public void setLeftDrawer(boolean isFullWidth) {
        ViewGroup.LayoutParams layoutParams = binding.leftDrawer.getLayoutParams();
        int tempWidth;
        if (isFullWidth) {
            tempWidth = SizeUtils.getScreenWidth(this);
        } else {
            tempWidth = SizeUtils.dp2px(this, 245);
        }
        if (tempWidth == layoutParams.width) {
            return;
        }
        layoutParams.width = tempWidth;
        binding.leftDrawer.setLayoutParams(layoutParams);
    }


    @Override
    public void initViewObservable() {
        super.initViewObservable();

        //关闭侧滑菜单。
        viewModel.uc.closeDrawer.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (!aBoolean) {
                    binding.drawerLayout.closeDrawers();
                }
            }
        });

        // 切换栏目、
        viewModel.uc.examChange.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                binding.leftDrawer.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        binding.drawerLayout.closeDrawers();
                        setLeftDrawer(SPUtils.getInstance().isEmptyExamInfo());
                        // 通知 homeViewModel。
                        Messenger.getDefault().sendNoMsg(HomeViewModel.RefreshByChangeExam);
                    }
                }, 300);
            }
        });
    }

    //监听打开菜单
    private void initOpenDrawerListener() {
        Messenger.getDefault().register(this, HomeViewModel.OpenMenuAction, new BindingAction() {
            @Override
            public void call() {
                binding.drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }


    private List<Fragment> mFragments;

    private List<Fragment> cacheFragments;

    private void initFragment() {
        mFragments = new ArrayList<>();
        cacheFragments = new ArrayList<>();
        mFragments.add(new HomeFragment());
        mFragments.add(new StudyFragment());
        mFragments.add(new UserCenterFragment());
        //默认选中第一个
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.frameLayout, mFragments.get(0));
        cacheFragments.add(mFragments.get(0));
        transaction.commitAllowingStateLoss();
    }

    private void initBottomTab() {
        PageNavigationView pageBottomTabLayout = findViewById(R.id.pager_bottom_tab);
        NavigationController controller = pageBottomTabLayout.custom().
                addItem(newItem(R.mipmap.home_xx, R.mipmap.home_jy, "首页")).
                addItem(newItem(R.mipmap.lean_xx, R.mipmap.learn_jy, "学习")).
                addItem(newItem(R.mipmap.my_xx, R.mipmap.my_jy, "我的")).
                build();
        controller.addTabItemSelectedListener(new OnTabItemSelectedListener() {
            @Override
            public void onSelected(int index, int old) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                Fragment oldFragment = mFragments.get(old);
                Fragment newFragment = mFragments.get(index);
                transaction.hide(oldFragment);

                if (!cacheFragments.contains(newFragment)) {
                    cacheFragments.add(newFragment);
                    transaction.add(R.id.frameLayout, newFragment);
                } else {
                    transaction.show(newFragment);

                    if (newFragment instanceof HomeFragment) {
                        ((HomeFragment) newFragment).initStatusBar();
                    }else if(newFragment instanceof UserCenterFragment){
                        ((UserCenterFragment)newFragment).initStatusBar();
                    }else if(newFragment instanceof StudyFragment){

                    }
                }
                transaction.commit();
            }

            @Override
            public void onRepeat(int index) {

            }
        });
    }

    //创建一个Item
    private BaseTabItem newItem(int drawable, int checkedDrawable, String text) {
        TabItem normalItemView = new TabItem(this);
        normalItemView.initialize(drawable, checkedDrawable, text);
        normalItemView.setTextDefaultColor(Color.GRAY);
        normalItemView.setTextCheckedColor(getResources().getColor(R.color.colorPrimary));
        return normalItemView;
    }

}
