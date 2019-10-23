package com.kaoyaya.tongkai.ui.home;

import android.app.Application;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;

import com.google.gson.Gson;
import com.hdl.elog.ELog;
import com.kaoyaya.tongkai.BR;
import com.kaoyaya.tongkai.R;
import com.kaoyaya.tongkai.entity.HomeResource;
import com.kaoyaya.tongkai.entity.HomeResourseDistribute;
import com.kaoyaya.tongkai.http.UserApi;
import com.li.basemvvm.base.BaseViewModel;
import com.li.basemvvm.binding.command.BindingAction;
import com.li.basemvvm.binding.command.BindingCommand;
import com.li.basemvvm.bus.Messenger;
import com.li.basemvvm.bus.event.SingleLiveEvent;
import com.li.basemvvm.http.base.BaseResponse;
import com.li.basemvvm.http.base.RetrofitClient;
import com.li.basemvvm.utils.RxUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

public class HomeViewModel extends BaseViewModel {
    public ObservableField<String> title = new ObservableField<>("初级会计");


    public UIChangeObservable uc = new UIChangeObservable();

    // 精选好课的 数据源
    public ObservableList<GoodCourseItemViewModel> goodCourseList = new ObservableArrayList<>();
    // 给RecyclerView添加ItemBinding
    public ItemBinding<GoodCourseItemViewModel> goodCourseItemBinding = ItemBinding.of(BR.item, R.layout.item_good_course);


    public class UIChangeObservable {
        //获取分发资源
        public SingleLiveEvent<HomeResourseDistribute> finishGetBannerData = new SingleLiveEvent<>();
    }

    public static final String OpenMenuAction = "action_open_menu";

    public HomeViewModel(@NonNull Application application) {
        super(application);

    }


    public BindingCommand openMenuCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            Messenger.getDefault().sendNoMsg(OpenMenuAction);
        }
    });

    public BindingCommand goodLessonMoreCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            Log.e("test", "点击事件");
        }
    });


    public BindingCommand goodLessonCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            Log.e("test", "点击事件 精选好课");
        }
    });


    // 发起网络请求。获取分发资源
//    @SuppressWarnings("unchecked")
    public void getNetResource() {
        UserApi userApi = RetrofitClient.getInstance().create(UserApi.class);
        Disposable subscribe = userApi.getUserDistribute().
                compose(RxUtils.<BaseResponse<ArrayList<HomeResourseDistribute>>>schedulersTransformer())
                .compose(RxUtils.exceptionTransformer())
                .subscribe(new Consumer<ArrayList<HomeResourseDistribute> >() {
                    @Override
                    public void accept(ArrayList<HomeResourseDistribute> list) throws Exception {
                        for (HomeResourseDistribute homeResourseDistribute : list) {
                            Log.e("test", homeResourseDistribute.getName());
                            if ("Banner".equals(homeResourseDistribute.getName())) {
                                //发送 banner 数据到 页面上。更新数据源
                                uc.finishGetBannerData.setValue(homeResourseDistribute);
                            } else if ("精品体验课".equals(homeResourseDistribute.getName())) {
                                List<HomeResource> goodCourseResource = homeResourseDistribute.getResource();
                                for (HomeResource homeResource : goodCourseResource) {
                                    GoodCourseItemViewModel itemViewModel = new GoodCourseItemViewModel(HomeViewModel.this, homeResource);
                                    goodCourseList.add(itemViewModel);
                                }
                            }
                        }
                    }
                });

        addSubscribe(subscribe);
    }


    /**
     * 获取条目下标
     */
    public int getItemPosition(GoodCourseItemViewModel itemViewModel) {
        ELog.e("test", "huoqu---->" + goodCourseList.size());
        return goodCourseList.indexOf(itemViewModel);
    }

}
