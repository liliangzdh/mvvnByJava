package com.kaoyaya.tongkai.ui.live.liveList.vm;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;

import com.kaoyaya.tongkai.BR;
import com.kaoyaya.tongkai.R;
import com.kaoyaya.tongkai.entity.LiveBackRequest;
import com.kaoyaya.tongkai.entity.LiveCommand;
import com.kaoyaya.tongkai.entity.LiveInfo;
import com.kaoyaya.tongkai.http.UserApi;
import com.kaoyaya.tongkai.test.User;
import com.li.basemvvm.base.ItemViewModel;
import com.li.basemvvm.binding.command.BindingAction;
import com.li.basemvvm.binding.command.BindingCommand;
import com.li.basemvvm.http.base.BaseResponse;
import com.li.basemvvm.http.base.RetrofitClient;
import com.li.basemvvm.utils.RxUtils;

import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import me.tatarka.bindingcollectionadapter2.ItemBinding;
import me.tatarka.bindingcollectionadapter2.OnItemBind;

public class ViewPageItemViewModel extends ItemViewModel<LiveListViewModel> {

    public String text;

    public boolean isLiveType;//是否是直播。

    private LiveListViewModel viewModel;

    public ViewPageItemViewModel(@NonNull LiveListViewModel viewModel, boolean isLiveType) {
        super(viewModel);
        this.isLiveType = isLiveType;
        this.viewModel = viewModel;
        text = "ddd:" + (isLiveType ? "直播" : "回放");
        request();
    }

    public void request() {
        if (isLiveType) {
            getPreLiveList();
        } else {
            getLiveBackList(true);
        }
    }


    //    public ItemBinding<LiveItemViewModel> itemBinding = ItemBinding.of(BR.item, R.layout.item_live);
    public ItemBinding<LiveItemViewModel> itemBinding = ItemBinding.of(new OnItemBind<LiveItemViewModel>() {
        @Override
        public void onItemBind(@NonNull ItemBinding itemBinding, int position, LiveItemViewModel item) {
            if (isLiveType) {
                itemBinding.set(BR.item, R.layout.item_live);
            } else {
                itemBinding.set(BR.item, R.layout.item_live_back);
            }
        }
    });

    public ObservableArrayList<LiveItemViewModel> items = new ObservableArrayList<>();

    // 刷新。
    public BindingCommand refreshCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            request();
        }
    });

    // 加载更多、
    public BindingCommand loadMoreCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            getLiveBackList(false);
        }
    });

    public void sendRefreshEnd() {
        // 只能发生给 act处理
        viewModel.commandEvent.setValue(new LiveCommand(isLiveType ? 0 : 1, 0));
    }

    public void getPreLiveList() {
        UserApi userApi = RetrofitClient.getInstance().create(UserApi.class);
        Disposable subscribe = userApi.GetPreLive()
                .compose(RxUtils.<BaseResponse<List<LiveInfo>>>schedulersTransformer())
                .compose(RxUtils.<List<LiveInfo>>exceptionTransformerSimple())
                .subscribe(new Consumer<List<LiveInfo>>() {
                    @Override
                    public void accept(List<LiveInfo> liveInfos) throws Exception {
                        items.clear();
                        for (LiveInfo liveInfo : liveInfos) {
                            items.add(new LiveItemViewModel(ViewPageItemViewModel.this, liveInfo));
                        }
                        sendRefreshEnd();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        sendRefreshEnd();
                    }
                });

        viewModel.addSubscribe(subscribe);
    }

    private int page = 1;

    public void getLiveBackList(boolean isFirst) {
        if (isFirst) {
            page = 1;
        } else {
            page++;
        }
        UserApi userApi = RetrofitClient.getInstance().create(UserApi.class);
        Disposable subscribe = userApi.replayLive(new LiveBackRequest(this.page, 15, 0, 0))
                .compose(RxUtils.<BaseResponse<List<LiveInfo>>>schedulersTransformer())
                .compose(RxUtils.<List<LiveInfo>>exceptionTransformerSimple())
                .subscribe(new Consumer<List<LiveInfo>>() {
                    @Override
                    public void accept(List<LiveInfo> liveInfos) throws Exception {
                        if (page == 1) {
                            items.clear();
                        }
                        for (LiveInfo liveInfo : liveInfos) {
                            items.add(new LiveItemViewModel(ViewPageItemViewModel.this, liveInfo));
                        }
                        sendRefreshEnd();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        sendRefreshEnd();
                    }
                });

        viewModel.addSubscribe(subscribe);
    }
}
