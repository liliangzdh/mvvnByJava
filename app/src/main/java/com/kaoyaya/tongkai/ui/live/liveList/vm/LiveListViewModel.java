package com.kaoyaya.tongkai.ui.live.liveList.vm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;

import com.kaoyaya.tongkai.BR;
import com.kaoyaya.tongkai.R;
import com.kaoyaya.tongkai.entity.LiveCommand;
import com.li.basemvvm.base.BaseViewModel;
import com.li.basemvvm.binding.command.BindingAction;
import com.li.basemvvm.binding.command.BindingCommand;
import com.li.basemvvm.binding.command.BindingConsumer;
import com.li.basemvvm.bus.event.SingleLiveEvent;

import me.tatarka.bindingcollectionadapter2.BindingViewPagerAdapter;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

public class LiveListViewModel extends BaseViewModel {


    public LiveListViewModel(@NonNull Application application) {
        super(application);
        items.add(new ViewPageItemViewModel(this, true));
        items.add(new ViewPageItemViewModel(this, false));
    }

    //给ViewPager添加ObservableList
    public ObservableList<ViewPageItemViewModel> items = new ObservableArrayList<>();
    //给ViewPager添加ItemBinding
    public ItemBinding<ViewPageItemViewModel> itemBinding = ItemBinding.of(BR.viewModel, R.layout.item_viewpager);

    //给ViewPager添加PageTitle
    public final BindingViewPagerAdapter.PageTitles<ViewPageItemViewModel> pageTitles = new BindingViewPagerAdapter.PageTitles<ViewPageItemViewModel>() {
        @Override
        public CharSequence getPageTitle(int position, ViewPageItemViewModel item) {
            return position == 0 ? " 直播 " : " 回放 ";
        }
    };

    //ViewPager切换监听
    public BindingCommand<Integer> onPageSelectedCommand = new BindingCommand<>(new BindingConsumer<Integer>() {
        @Override
        public void call(Integer index) {

        }
    });

    public BindingCommand goBack = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            finish();
        }
    });


    public SingleLiveEvent<LiveCommand> commandEvent = new SingleLiveEvent<>();

}
