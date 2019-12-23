package com.kaoyaya.tongkai.ui.live.live_frg.vm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;

import com.kaoyaya.tongkai.BR;
import com.kaoyaya.tongkai.R;
import com.kaoyaya.tongkai.config.Constant;
import com.kaoyaya.tongkai.entity.CourseSampleInfo;
import com.kaoyaya.tongkai.entity.LiveIdAndClassIdResponse;
import com.kaoyaya.tongkai.http.UserApi;
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
import me.tatarka.bindingcollectionadapter2.OnItemBind;

public class LiveFragViewModel extends BaseViewModel {


    public ObservableField<Integer> page = new ObservableField<>();

    public LiveFragViewModel(@NonNull Application application) {
        super(application);
    }

    public BindingCommand goBack = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            finish();
        }
    });


    public BindingCommand filterCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            showDialogEvent.setValue(true);
            if (items.size() == 0) {
                getLiveIdAndClassId();
            }
        }
    });

    //ViewPager切换监听
    public BindingCommand<Integer> onPageSelectedCommand = new BindingCommand<>(new BindingConsumer<Integer>() {
        @Override
        public void call(Integer index) {
            page.set(index);
        }
    });

    public SingleLiveEvent<Boolean> showDialogEvent = new SingleLiveEvent<>();


    //弹窗的数据源 和布局如下
    public ItemBinding<LiveFilterItemViewModel> itemBinding = ItemBinding.of(new OnItemBind<LiveFilterItemViewModel>() {
        @Override
        public void onItemBind(@NonNull ItemBinding itemBinding, int position, LiveFilterItemViewModel item) {
            CourseSampleInfo courseSampleInfo = item.entity.get();
            if (courseSampleInfo != null && ("课程".equals(courseSampleInfo.getTitle()) || "班级".equals(courseSampleInfo.getTitle()))) {
                itemBinding.set(BR.item, R.layout.item_live_back_filter_title);
            } else {
                itemBinding.set(BR.item, R.layout.item_live_back_filter);
            }
        }
    });
    public ObservableArrayList<LiveFilterItemViewModel> items = new ObservableArrayList<>();

    public void changeClassId(CourseSampleInfo info) {
        for (LiveFilterItemViewModel item : items) {
            CourseSampleInfo courseSampleInfo = item.entity.get();
            if (courseSampleInfo != null) {
                courseSampleInfo.setSelect(courseSampleInfo.getId() == info.getId());
                item.entity.notifyChange();
            }
        }

        // 通信 。更新
        Messenger.getDefault().send(info,Constant.LiveBackFilter);
    }


    public void getLiveIdAndClassId() {
        UserApi userApi = RetrofitClient.getInstance().create(UserApi.class);
        Disposable subscribe = userApi.getLiveIdAndClassId()
                .compose(RxUtils.<BaseResponse<LiveIdAndClassIdResponse>>schedulersTransformer())
                .compose(RxUtils.<LiveIdAndClassIdResponse>exceptionTransformerSimple())
                .subscribe(new Consumer<LiveIdAndClassIdResponse>() {
                    @Override
                    public void accept(LiveIdAndClassIdResponse liveIdAndClassIdResponse) throws Exception {
                        items.clear();
                        CourseSampleInfo info = new CourseSampleInfo(0, "全部");
                        info.setSelect(true);
                        items.add(new LiveFilterItemViewModel(LiveFragViewModel.this, info));


                        List<CourseSampleInfo> liveIds = liveIdAndClassIdResponse.getLiveIds();

                        if (liveIds != null && liveIds.size() > 0) {
                            items.add(new LiveFilterItemViewModel(LiveFragViewModel.this, new CourseSampleInfo(0, "课程")));
                        }

                        if (liveIds != null) {
                            for (CourseSampleInfo liveId : liveIds) {
                                liveId.setType(1);
                                items.add(new LiveFilterItemViewModel(LiveFragViewModel.this, liveId));
                            }
                        }


                        List<CourseSampleInfo> classroomIds = liveIdAndClassIdResponse.getClassroomIds();

                        if (classroomIds != null && classroomIds.size() > 0) {
                            items.add(new LiveFilterItemViewModel(LiveFragViewModel.this, new CourseSampleInfo(0, "班级")));
                        }


                        if (classroomIds != null) {
                            for (CourseSampleInfo liveId : classroomIds) {
                                liveId.setType(2);
                                items.add(new LiveFilterItemViewModel(LiveFragViewModel.this, liveId));
                            }
                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });

        addSubscribe(subscribe);
    }
}
