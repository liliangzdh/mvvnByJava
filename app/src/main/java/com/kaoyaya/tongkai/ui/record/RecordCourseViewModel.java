package com.kaoyaya.tongkai.ui.record;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;

import com.hdl.elog.ELog;
import com.kaoyaya.tongkai.BR;
import com.kaoyaya.tongkai.R;
import com.kaoyaya.tongkai.entity.CourseInfo;
import com.kaoyaya.tongkai.entity.ExamInfo;
import com.kaoyaya.tongkai.entity.UserCoursesResponse;
import com.kaoyaya.tongkai.http.UserApi;
import com.kaoyaya.tongkai.ui.record.item.ItemRecordFilterDialogModel;
import com.kaoyaya.tongkai.ui.record.item.ItemRecordViewModel;
import com.li.basemvvm.base.BaseViewModel;
import com.li.basemvvm.binding.command.BindingAction;
import com.li.basemvvm.binding.command.BindingCommand;
import com.li.basemvvm.bus.event.SingleLiveEvent;
import com.li.basemvvm.http.base.BaseResponse;
import com.li.basemvvm.http.base.RetrofitClient;
import com.li.basemvvm.utils.RxUtils;

import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

public class RecordCourseViewModel extends BaseViewModel {


    public BindingCommand backCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            finish();
        }
    });

    private int requestId = 0;


    public BindingCommand filterCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            showFilterEvent.setValue(true);
        }
    });

    public BindingCommand refreshCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            request();
        }
    });

    public RecordCourseViewModel(@NonNull Application application) {
        super(application);
    }

    public SingleLiveEvent<Boolean> showFilterEvent = new SingleLiveEvent<>();
    public SingleLiveEvent<Boolean> refreshEvent = new SingleLiveEvent<>();

    //
    public ItemBinding<ItemRecordViewModel> itemBinding = ItemBinding.of(BR.item, R.layout.item_record_course);
    public ObservableArrayList<ItemRecordViewModel> itemArray = new ObservableArrayList<>();


    public ItemBinding<ItemRecordFilterDialogModel> itemBindingFilterDialog = ItemBinding.of(BR.item, R.layout.item_record_filter_dialog);
    public ObservableArrayList<ItemRecordFilterDialogModel> itemArrayFilterDialog = new ObservableArrayList<>();


    public void requestAll() {
        request();
    }

    private void request() {
        UserApi userApi = RetrofitClient.getInstance().create(UserApi.class);
        showDialog("正在加载...");
        Disposable subscribe = userApi.getUserCourses(requestId)
                .compose(RxUtils.<BaseResponse<UserCoursesResponse>>schedulersTransformer())
                .compose(RxUtils.<UserCoursesResponse>exceptionTransformerSimple())
                .subscribe(new Consumer<UserCoursesResponse>() {
                    @Override
                    public void accept(UserCoursesResponse userCoursesResponse) throws Exception {
                        try {
                            List<CourseInfo> courseInfoList = userCoursesResponse.getCourseInfo();
                            itemArray.clear();
                            for (CourseInfo info : courseInfoList) {
                                itemArray.add(new ItemRecordViewModel(RecordCourseViewModel.this, info));
                            }


                            List<ExamInfo> topCateList = userCoursesResponse.getTopCateList();

                            itemArrayFilterDialog.clear();

                            // 默认选中 全部
                            ExamInfo info = new ExamInfo(0, "全部");
                            info.setSelect(requestId == 0);
                            itemArrayFilterDialog.add(new ItemRecordFilterDialogModel(RecordCourseViewModel.this, info));


                            for (ExamInfo examInfo : topCateList) {
                                examInfo.setSelect(requestId == examInfo.getId());
                                itemArrayFilterDialog.add(new ItemRecordFilterDialogModel(RecordCourseViewModel.this, examInfo));
                            }


                        } catch (Exception e) {
                            ELog.e("test", e.getMessage());
                        }


                        dismissDialog();
                        refreshEvent.setValue(true);


                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        ELog.e("test", throwable.getMessage() + " :e");
                        dismissDialog();
                        refreshEvent.setValue(true);
                    }
                });
        addSubscribe(subscribe);
    }

    // 开启过滤。
    public void startFilter(ExamInfo examInfo) {
        for (ItemRecordFilterDialogModel itemRecordFilterDialogModel : itemArrayFilterDialog) {
            ExamInfo info = itemRecordFilterDialogModel.entity.get();
            if (info != null) {
                info.setSelect(examInfo.getId() == info.getId());
                itemRecordFilterDialogModel.entity.notifyChange();
            }
        }
        showFilterEvent.setValue(false);
        requestId = examInfo.getId();
        request();
    }
}
