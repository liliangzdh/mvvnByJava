package com.kaoyaya.tongkai.ui.main;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;

import com.hdl.elog.ELog;
import com.kaoyaya.tongkai.BR;
import com.kaoyaya.tongkai.R;
import com.kaoyaya.tongkai.entity.CourseSampleInfo;
import com.kaoyaya.tongkai.entity.ExamInfo;
import com.kaoyaya.tongkai.entity.ExamTypeInfo;
import com.kaoyaya.tongkai.http.EduApi;
import com.kaoyaya.tongkai.ui.home.HomeViewModel;
import com.li.basemvvm.base.BaseViewModel;
import com.li.basemvvm.binding.command.BindingAction;
import com.li.basemvvm.binding.command.BindingCommand;
import com.li.basemvvm.bus.event.SingleLiveEvent;
import com.li.basemvvm.http.base.BaseResponse;
import com.li.basemvvm.http.base.ErrorConsumer;
import com.li.basemvvm.http.base.ResponseConsumer;
import com.li.basemvvm.http.base.RetrofitClient;
import com.li.basemvvm.utils.RxUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

public class MainViewModel extends BaseViewModel {

    public MainViewModel(@NonNull Application application) {
        super(application);

    }

    public UIChangeObservable uc = new UIChangeObservable();

    public class UIChangeObservable {
        public SingleLiveEvent<Boolean> closeDrawer = new SingleLiveEvent<>();
    }

    public void request() {
        getOemExamTypeList();
    }


    //绑定 action
    public BindingCommand closeCommend = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            uc.closeDrawer.setValue(false);
        }
    });


    // 侧滑菜单的 数据源
    public ObservableList<ExamItemViewModel> examList = new ObservableArrayList<>();
    // 给RecyclerView添加ItemBinding
    public ItemBinding<ExamItemViewModel> examItemBinding = ItemBinding.of(BR.item, R.layout.item_exam);


    @SuppressWarnings("unchecked")
    private void getOemExamTypeList() {
        EduApi eduApi = RetrofitClient.getInstance().create(EduApi.class);
        Disposable subscribe = eduApi.getOemExamTypeList()
                .compose(RxUtils.<BaseResponse<List<ExamTypeInfo>>>schedulersTransformer())
                .compose(RxUtils.exceptionTransformer())
                .subscribe(new Consumer<List<ExamTypeInfo>>() {
                    @Override
                    public void accept(List<ExamTypeInfo> infoList) {
                        if (infoList.size() > 0) {
                            ExamTypeInfo examTypeInfo = infoList.get(0);
                            List<ExamInfo> children = examTypeInfo.getChildren();
                            if (children != null) {
                                examList.clear();
                                for (int i = 0; i < children.size(); i++) {
                                    ExamInfo child = children.get(i);
                                    if (i == 0) {
                                        child.setSelect(true);
                                    }
                                    examList.add(new ExamItemViewModel(MainViewModel.this, child));
                                }
                            }
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        ELog.e("test", throwable.getMessage() + " ");
                    }
                });
        addSubscribe(subscribe);
    }
}
