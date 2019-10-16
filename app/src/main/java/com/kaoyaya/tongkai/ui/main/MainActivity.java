package com.kaoyaya.tongkai.ui.main;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;

import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.hdl.elog.ELog;
import com.kaoyaya.tongkai.BR;
import com.kaoyaya.tongkai.R;
import com.kaoyaya.tongkai.databinding.ActMainBinding;
import com.kaoyaya.tongkai.entity.TabItem;
import com.kaoyaya.tongkai.ui.home.HomeFragment;
import com.kaoyaya.tongkai.ui.home.HomeViewModel;
import com.kaoyaya.tongkai.ui.study.StudyFragment;
import com.kaoyaya.tongkai.ui.user.UserCenterFragment;
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
        initFragment();
        initBottomTab();
        initOpenDrawerListener();
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

    private void initFragment() {
        mFragments = new ArrayList<>();
        mFragments.add(new HomeFragment());
        mFragments.add(new StudyFragment());
        mFragments.add(new UserCenterFragment());
        //默认选中第一个
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.frameLayout, mFragments.get(0));
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
                transaction.replace(R.id.frameLayout, mFragments.get(index));
                transaction.commitAllowingStateLoss();
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
