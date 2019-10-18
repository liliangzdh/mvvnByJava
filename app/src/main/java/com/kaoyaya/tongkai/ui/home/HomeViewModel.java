package com.kaoyaya.tongkai.ui.home;

import android.app.Application;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.databinding.ObservableField;

import com.kaoyaya.tongkai.R;
import com.kaoyaya.tongkai.entity.HomeResourseDistribute;
import com.kaoyaya.tongkai.http.UserApi;
import com.li.basemvvm.base.BaseViewModel;
import com.li.basemvvm.binding.command.BindingAction;
import com.li.basemvvm.binding.command.BindingCommand;
import com.li.basemvvm.bus.Messenger;
import com.li.basemvvm.bus.event.SingleLiveEvent;
import com.li.basemvvm.http.base.RetrofitClient;
import com.li.basemvvm.utils.RxUtils;

import java.util.ArrayList;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class HomeViewModel extends BaseViewModel {
    public ObservableField<String> title = new ObservableField<>("初级会计");



    public UIChangeObservable uc = new UIChangeObservable();



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
    @SuppressWarnings("unchecked")
    public void getNetResource() {
        UserApi userApi = RetrofitClient.getInstance().create(UserApi.class);
        Disposable subscribe = userApi.getUserDistribute().
                compose(RxUtils.schedulersTransformer()).
                compose(RxUtils.exceptionTransformer()).
                subscribe(new Consumer<ArrayList<HomeResourseDistribute>>() {
                    @Override
                    public void accept(ArrayList<HomeResourseDistribute> list) throws Exception {
                        for (HomeResourseDistribute homeResourseDistribute : list) {
                            Log.e("test", homeResourseDistribute.getName());
                            if ("Banner".equals(homeResourseDistribute.getName())) {
                                //发送 banner 数据到 页面上。更新数据源
                                uc.finishGetBannerData.setValue(homeResourseDistribute);
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
