package com.kaoyaya.tongkai.ui.study;

import android.app.Application;
import android.text.TextUtils;
import android.util.Log;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;

import com.google.gson.Gson;
import com.hdl.elog.ELog;
import com.kaoyaya.tongkai.R;
import com.kaoyaya.tongkai.config.Constant;
import com.kaoyaya.tongkai.entity.ExamTypeInfo;
import com.kaoyaya.tongkai.entity.LearnCourseInfo;
import com.kaoyaya.tongkai.entity.LearnInfoResponse;
import com.kaoyaya.tongkai.entity.LiveBackRequest;
import com.kaoyaya.tongkai.entity.LiveInfo;
import com.kaoyaya.tongkai.entity.StudyResourceItem;
import com.kaoyaya.tongkai.http.EduApi;
import com.kaoyaya.tongkai.http.UserApi;
import com.kaoyaya.tongkai.test.User;
import com.kaoyaya.tongkai.ui.home.HomeLiveAdapter;
import com.kaoyaya.tongkai.ui.home.LiveItemViewModel;
import com.kaoyaya.tongkai.ui.login.LoginActivity;
import com.kaoyaya.tongkai.ui.study.item.VideoRecordItemViewModel;
import com.kaoyaya.tongkai.ui.test.TestAct;
import com.li.basemvvm.BR;
import com.li.basemvvm.base.BaseViewModel;
import com.li.basemvvm.binding.command.BindingAction;
import com.li.basemvvm.binding.command.BindingCommand;
import com.li.basemvvm.binding.command.BindingConsumer;
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

    // 在学录播课
    public ObservableList<VideoRecordItemViewModel> videoList = new ObservableArrayList<>();
    public ItemBinding<VideoRecordItemViewModel> videoItemBinding = ItemBinding.of(BR.item, R.layout.item_study_video);


    //直播的
    public ObservableList<LiveItemViewModel> goodLiveList = new ObservableArrayList<>();
    public ItemBinding<LiveItemViewModel> goodLiveItemBinding = ItemBinding.of(com.kaoyaya.tongkai.BR.item, R.layout.item_home_live);
    public HomeLiveAdapter adapter = new HomeLiveAdapter();

    //回放
    public ObservableList<LiveItemViewModel> liveBackList = new ObservableArrayList<>();
    public ItemBinding<LiveItemViewModel> liveBackItemBinding = ItemBinding.of(com.kaoyaya.tongkai.BR.item, R.layout.item_home_live_back);


    public ObservableField<Integer> showType = new ObservableField<>();
    public ObservableField<StudyResourceItem> selectSource = new ObservableField<>();

    public BindingCommand<Integer> topCommand = new BindingCommand<>(new BindingConsumer<Integer>() {
        @Override
        public void call(Integer integer) {
            Log.e("test", "  " + integer);
            switch (integer) {
                case 0:
                    break;
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
            }
        }
    });

    public BindingCommand<Integer> moreCommand = new BindingCommand<>(new BindingConsumer<Integer>() {
        @Override
        public void call(Integer integer) {
            Log.e("test", "  " + integer);
            switch (integer) {
                case 0:
                    break;
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
            }
        }
    });


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

    public void changeClass(StudyResourceItem item) {
        selectSource.set(item);
        showType.set(2);
        getLearnInfo();
        getLiveBackInfo();
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
                        addResource(map, "class", "系统班级");
                        addResource(map, "course", "单项课程");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        ELog.e("test", "  " + throwable.getMessage());
                    }
                });

        addSubscribe(subscribe);
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


    public void getLearnInfo() {
        EduApi eduApi = RetrofitClient.getInstance().create(EduApi.class);

        StudyResourceItem studyResourceItem = selectSource.get();
        if (studyResourceItem == null) {
            return;
        }


        videoList.clear();
        goodLiveList.clear();

        Disposable subscribe = eduApi.getLearnInfo(studyResourceItem.getId())
                .compose(RxUtils.<BaseResponse<LearnInfoResponse>>schedulersTransformer())
                .compose(RxUtils.exceptionTransformer())
                .subscribe(new Consumer<LearnInfoResponse>() {
                    @Override
                    public void accept(LearnInfoResponse learnInfoResponse) throws Exception {
                        List<LearnCourseInfo> normal = learnInfoResponse.getNormal();
                        for (LearnCourseInfo learnCourseInfo : normal) {
                            videoList.add(new VideoRecordItemViewModel(StudyViewModel.this, learnCourseInfo));
                        }

                        List<LiveInfo> live = learnInfoResponse.getLive();

                        for (LiveInfo liveInfo : live) {
                            goodLiveList.add(new LiveItemViewModel(null, liveInfo));
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });


        addSubscribe(subscribe);

    }

    public void getLiveBackInfo() {
        liveBackList.clear();
        StudyResourceItem studyResourceItem = selectSource.get();
        if (studyResourceItem == null) {
            return;
        }

        UserApi userApi = RetrofitClient.getInstance().create(UserApi.class);
        Disposable subscribe = userApi
                .replayLive(new LiveBackRequest(1, 10, 0, studyResourceItem.getId()))
                .compose(RxUtils.<BaseResponse<List<LiveInfo>>>schedulersTransformer())
                .compose(RxUtils.exceptionTransformer()).subscribe(new Consumer<List<LiveInfo>>() {
                    @Override
                    public void accept(List<LiveInfo> liveInfoList) throws Exception {
                        Log.e("test", "=====" + liveInfoList.size());

                        for (LiveInfo liveInfo : liveInfoList) {
                            liveBackList.add(new LiveItemViewModel(null,liveInfo));
                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("test","---"+throwable.getMessage());
                    }
                });

        addSubscribe(subscribe);
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        adapter.cancelAllTimers();
    }
}
