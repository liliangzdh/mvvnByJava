package com.kaoyaya.tongkai.ui.live.live_frg.vm;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;

import com.kaoyaya.tongkai.BR;
import com.kaoyaya.tongkai.R;
import com.kaoyaya.tongkai.config.Constant;
import com.kaoyaya.tongkai.entity.CourseSampleInfo;
import com.kaoyaya.tongkai.entity.LiveBackRequest;
import com.kaoyaya.tongkai.entity.LiveCommand;
import com.kaoyaya.tongkai.entity.LiveIdAndClassIdResponse;
import com.kaoyaya.tongkai.entity.LiveInfo;
import com.kaoyaya.tongkai.http.UserApi;
import com.kaoyaya.tongkai.ui.live.liveList.vm.LiveItemViewModel;
import com.li.basemvvm.base.BaseViewModel;
import com.li.basemvvm.binding.command.BindingAction;
import com.li.basemvvm.binding.command.BindingCommand;
import com.li.basemvvm.binding.command.BindingConsumer;
import com.li.basemvvm.bus.Messenger;
import com.li.basemvvm.bus.event.SingleLiveEvent;
import com.li.basemvvm.http.base.BaseResponse;
import com.li.basemvvm.http.base.RetrofitClient;
import com.li.basemvvm.utils.RxUtils;

import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

public class LiveBackPageViewModel extends BaseViewModel {

    public int classId = 0;
    public int courseId = 0;

    public LiveBackPageViewModel(@NonNull Application application) {
        super(application);
        request();


        // 注册 点击 筛选监听
        Messenger.getDefault().register(this, Constant.LiveBackFilter, CourseSampleInfo.class, new BindingConsumer<CourseSampleInfo>() {
            @Override
            public void call(CourseSampleInfo info) {
                if (info.getType() == 1) {
                    courseId = info.getId();
                    classId = 0;
                } else {
                    //班级
                    classId = info.getId();
                    courseId = 0;
                }
                getLiveBackList(true);
            }
        });
    }

    public void request() {
        getLiveBackList(true);
    }


    public ItemBinding<LiveItemViewModel> itemBinding = ItemBinding.of(BR.item, R.layout.item_live_back);


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


    public SingleLiveEvent<LiveCommand> commandEvent = new SingleLiveEvent<>();


    public void sendRefreshEnd(boolean hasMore) {
        // 只能发生给 act处理
        commandEvent.setValue(new LiveCommand(1, 0,hasMore));
    }


    private int page = 1;

    public void getLiveBackList(boolean isFirst) {
        if (isFirst) {
            page = 1;
        } else {
            page++;
        }
        UserApi userApi = RetrofitClient.getInstance().create(UserApi.class);
        Disposable subscribe = userApi.replayLive(new LiveBackRequest(this.page, 15, courseId, classId))
                .compose(RxUtils.<BaseResponse<List<LiveInfo>>>schedulersTransformer())
                .compose(RxUtils.<List<LiveInfo>>exceptionTransformerSimple())
                .subscribe(new Consumer<List<LiveInfo>>() {
                    @Override
                    public void accept(List<LiveInfo> liveInfos) throws Exception {
                        if (page == 1) {
                            items.clear();
                        }
                        for (LiveInfo liveInfo : liveInfos) {
                            items.add(new LiveItemViewModel(null, liveInfo));
                        }
                        sendRefreshEnd(liveInfos.size() == 15);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        sendRefreshEnd(true);
                    }
                });

        addSubscribe(subscribe);
    }


}
