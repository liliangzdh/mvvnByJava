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
import me.tatarka.bindingcollectionadapter2.OnItemBind;

public class StudyViewModel extends BaseViewModel {


    public ObservableList<StudyItemViewModel> resourceList = new ObservableArrayList<>();
    //RecyclerView多布局添加ItemBinding
    public ItemBinding<StudyItemViewModel> studyItemBinding = ItemBinding.of(new OnItemBind<StudyItemViewModel>() {
        @Override
        public void onItemBind(@NonNull ItemBinding itemBinding, int position, StudyItemViewModel item) {
            //通过item的类型, 动态设置Item加载的布局
            StudyResourceItem studyResourceItem = item.entity.get();
            if (studyResourceItem != null && studyResourceItem.isHeader()) {
                itemBinding.set(BR.item, R.layout.item_study_resource_header);
            } else {
                itemBinding.set(BR.item, R.layout.item_study_resource);
            }
        }
    });


    public ObservableField<Integer> showType = new ObservableField<>();
    public ObservableField<StudyResourceItem> selectSource = new ObservableField<>();


    public StudyViewModel(@NonNull Application application) {
        super(application);
        initState();
        Messenger.getDefault().register(this, Constant.Login, new BindingAction() {
            @Override
            public void call() {
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
                        resourceList.clear();
                        addResource(map,"class","系统班级");
                        addResource(map,"course","单项课程");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        ELog.e("test", "  " + throwable.getMessage());
                    }
                });
    }


    public void addResource(HashMap<String, List<StudyResourceItem>> map, String key, String title) {
        List<StudyResourceItem> aClass = map.get(key);
        if (aClass != null) {
            if (aClass.size() > 0) {
                resourceList.add(new StudyItemViewModel(StudyViewModel.this, new StudyResourceItem(title, true)));
            }

            for (StudyResourceItem studyResourceItem : aClass) {
                resourceList.add(new StudyItemViewModel(StudyViewModel.this, studyResourceItem));
            }
        }
    }
}
