package com.kaoyaya.tongkai.ui.study;

import android.app.Application;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;

import com.hdl.elog.ELog;
import com.kaoyaya.tongkai.R;
import com.kaoyaya.tongkai.config.Constant;
import com.kaoyaya.tongkai.entity.StudyResourceItem;
import com.kaoyaya.tongkai.http.UserApi;
import com.kaoyaya.tongkai.ui.login.LoginActivity;
import com.li.basemvvm.BR;
import com.li.basemvvm.base.BaseViewModel;
import com.li.basemvvm.binding.command.BindingAction;
import com.li.basemvvm.binding.command.BindingCommand;
import com.li.basemvvm.bus.Messenger;
import com.li.basemvvm.http.base.BaseResponse;
import com.li.basemvvm.http.base.RetrofitClient;
import com.li.basemvvm.utils.RxUtils;
import com.li.basemvvm.utils.SPUtils;

import java.util.HashMap;
import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

public class StudyViewModel extends BaseViewModel {


    public ItemBinding<StudyItemViewModel> studyItemBinding = ItemBinding.of(BR.item, R.layout.item_study_resource);
    public ObservableList<StudyItemViewModel> resourceList = new ObservableArrayList<>();


    public ObservableField<Integer> showType = new ObservableField<>();

    public StudyViewModel(@NonNull Application application) {
        super(application);
        initState();
        Messenger.getDefault().register(this, Constant.Login, new BindingAction() {
            @Override
            public void call() {
                Log.e("test", "监听到退出或者登录");
                initState();
            }
        });
    }

    private void initState() {
        String token = SPUtils.getInstance().getToken();
        showType.set(TextUtils.isEmpty(token) ? 0 : 1);
        if (!TextUtils.isEmpty(token)) {
            getStudyResource();
        } else {
            //退出登录
            resourceList.clear();
        }
    }


    public BindingCommand goLoginAction = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(LoginActivity.class);
        }
    });
    public BindingCommand changeExamAction = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
           showType.set(1);
        }
    });


    public void request() {

    }


    public void getStudyResource() {
        UserApi userApi = RetrofitClient.getInstance().create(UserApi.class);
        Disposable subscribe = userApi.getStudyResource()
                .compose(RxUtils.<BaseResponse<HashMap<String, List<StudyResourceItem>>>>schedulersTransformer())
                .compose(RxUtils.exceptionTransformer())
                .subscribe(new Consumer<HashMap<String, List<StudyResourceItem>>>() {
                    @Override
                    public void accept(HashMap<String, List<StudyResourceItem>> map) throws Exception {
                        List<StudyResourceItem> aClass = map.get("class");

                        Log.e("test", "===" + aClass.size());

                        resourceList.clear();
                        for (StudyResourceItem studyResourceItem : aClass) {
                            resourceList.add(new StudyItemViewModel(StudyViewModel.this, studyResourceItem));
                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        ELog.e("test", "  " + throwable.getMessage());
                    }
                });
    }
}
