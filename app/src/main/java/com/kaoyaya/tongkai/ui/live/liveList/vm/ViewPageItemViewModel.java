package com.kaoyaya.tongkai.ui.live.liveList.vm;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;

import com.kaoyaya.tongkai.BR;
import com.kaoyaya.tongkai.R;
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

public class ViewPageItemViewModel extends ItemViewModel<LiveListViewModel> {

    public String text;

    public boolean isLiveType;//是否是直播。

    private LiveListViewModel viewModel;

    public ViewPageItemViewModel(@NonNull LiveListViewModel viewModel, boolean isLiveType) {
        super(viewModel);
        this.isLiveType = isLiveType;
        this.viewModel = viewModel;
        text = "ddd:" + (isLiveType ? "直播" : "回放");


        if(isLiveType){
            getPreLiveList();
        }
    }

    public BindingCommand onItemClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            //点击之后将逻辑转到activity中处理
//            viewModel.itemClickEvent.setValue(text);
        }
    });


    public ItemBinding<LiveItemViewModel> itemBinding = ItemBinding.of(BR.item, R.layout.item_live);

    public ObservableArrayList<LiveItemViewModel> items = new ObservableArrayList<>();


    public void getPreLiveList() {
        Log.e("test","getPreLiveList");
        UserApi userApi = RetrofitClient.getInstance().create(UserApi.class);
        Disposable subscribe = userApi.GetPreLive()
                .compose(RxUtils.<BaseResponse<List<LiveInfo>>>schedulersTransformer())
                .compose(RxUtils.<List<LiveInfo>>exceptionTransformerSimple())
                .subscribe(new Consumer<List<LiveInfo>>() {
                    @Override
                    public void accept(List<LiveInfo> liveInfos) throws Exception {
                        Log.e("test", ":" + liveInfos.size());
                        items.clear();
                        for (LiveInfo liveInfo : liveInfos) {
                            items.add(new LiveItemViewModel(ViewPageItemViewModel.this, liveInfo));
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("test","-----:"+throwable.getMessage());
                    }
                });

        viewModel.addSubscribe(subscribe);
    }
}
