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
import com.kaoyaya.tongkai.entity.LearnCourseInfo;
import com.kaoyaya.tongkai.entity.LearnInfoResponse;
import com.kaoyaya.tongkai.entity.LiveBackRequest;
import com.kaoyaya.tongkai.entity.LiveInfo;
import com.kaoyaya.tongkai.entity.StudyResourceItem;
import com.kaoyaya.tongkai.entity.TiKuExamInfo;
import com.kaoyaya.tongkai.entity.TiKuStatistic;
import com.kaoyaya.tongkai.entity.TiKuStudyInfo;
import com.kaoyaya.tongkai.http.EduApi;
import com.kaoyaya.tongkai.http.TiKuApi;
import com.kaoyaya.tongkai.http.UserApi;
import com.kaoyaya.tongkai.ui.home.HomeLiveAdapter;
import com.kaoyaya.tongkai.ui.home.LiveItemViewModel;
import com.kaoyaya.tongkai.ui.live.liveList.LiveListActivity;
import com.kaoyaya.tongkai.ui.live.live_frg.LiveListFragActivity;
import com.kaoyaya.tongkai.ui.live.live_frg.LiveViewPageGroupFragment;
import com.kaoyaya.tongkai.ui.login.LoginActivity;
import com.kaoyaya.tongkai.ui.study.item.StudyTiKuItemViewModel;
import com.kaoyaya.tongkai.ui.study.item.VideoRecordItemViewModel;
import com.kaoyaya.tongkai.utils.SPUtils;
import com.li.basemvvm.BR;
import com.li.basemvvm.base.BaseViewModel;
import com.li.basemvvm.binding.command.BindingAction;
import com.li.basemvvm.binding.command.BindingCommand;
import com.li.basemvvm.binding.command.BindingConsumer;
import com.li.basemvvm.bus.Messenger;
import com.li.basemvvm.bus.event.SingleLiveEvent;
import com.li.basemvvm.http.base.BaseResponse;
import com.li.basemvvm.http.base.RetrofitClient;
import com.li.basemvvm.utils.RxUtils;
import com.li.basemvvm.utils.TokenUtils;

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
    public ItemBinding<LiveItemViewModel> goodLiveItemBinding = ItemBinding.of(BR.item, R.layout.item_home_live);
    public HomeLiveAdapter adapter = new HomeLiveAdapter();

    //是否显示直播暂无标志
    public ObservableField<Boolean> showNoLiveFlag = new ObservableField<>();

    //回放
    public ObservableList<LiveItemViewModel> liveBackList = new ObservableArrayList<>();
    public ItemBinding<LiveItemViewModel> liveBackItemBinding = ItemBinding.of(BR.item, R.layout.item_home_live_back);
    //是否显示回放暂无标志
    public ObservableField<Boolean> showNoLiveBackFlag = new ObservableField<>();

    // 题库   列表
    public ObservableList<StudyTiKuItemViewModel> tiKuList = new ObservableArrayList<>();
    public ItemBinding<StudyTiKuItemViewModel> tiKuItemBinding = ItemBinding.of(BR.item, R.layout.item_study_tiku);

    //题库数据
    public ObservableField<TiKuStatistic> examStatistic = new ObservableField<>();


    public ObservableField<Integer> showType = new ObservableField<>();
    public ObservableField<StudyResourceItem> selectSource = new ObservableField<>();


    //顶部3个按钮
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
            }
        }
    });

    //更多按钮()
    public BindingCommand<Integer> moreCommand = new BindingCommand<>(new BindingConsumer<Integer>() {
        @Override
        public void call(Integer integer) {
            Log.e("test", "  " + integer);
            switch (integer) {
                case 0: //录播课
                    break;
                case 1://题库
                    break;
                case 2://直播
                    startActivity(LiveListActivity.class);
                    break;
                case 3://回放
//                    startContainerActivity(LiveViewPageGroupFragment.class.getCanonicalName());
                    startActivity(LiveListFragActivity.class);
                    break;
            }
        }
    });


    //定位按钮
    public BindingCommand<Integer> headCommand = new BindingCommand<>(new BindingConsumer<Integer>() {
        @Override
        public void call(Integer integer) {
            uc.scrollEvent.setValue(integer);
        }
    });

    //刷新成功按钮
    public BindingCommand refreshCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            StudyResourceItem studyResourceItem = SPUtils.getInstance().getStudyResourceItem();
            if (studyResourceItem != null) {
                changeClass(studyResourceItem);
            } else {

            }
        }
    });


    // 发送到act页面。操作view。
    public class UIChangeObservable {
        public SingleLiveEvent<Integer> scrollEvent = new SingleLiveEvent<>();
        public SingleLiveEvent<Integer> refreshEvent = new SingleLiveEvent<>();
    }


    public UIChangeObservable uc = new UIChangeObservable();

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
        String token = TokenUtils.getInstance().getToken();

        if (!TextUtils.isEmpty(token)) {
            StudyResourceItem studyResourceItem = SPUtils.getInstance().getStudyResourceItem();
            if (studyResourceItem != null) {
                showType.set(2);
                changeClass(studyResourceItem);
            } else {
                showType.set(1);
                getStudyResource();
            }
        } else {
            showType.set(0);
            //退出登录
            resourceList.clear();
            SPUtils.getInstance().clearStudyItem();
        }
    }

    public void changeClass(StudyResourceItem item) {
        SPUtils.getInstance().saveStudyItem(item);
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
            if (resourceList == null || resourceList.size() == 0) {
                getStudyResource();
            }
        }
    });


    public void request() {

    }


    public void getStudyResource() {
        UserApi userApi = RetrofitClient.getInstance().create(UserApi.class);
        Disposable subscribe = userApi.getStudyResource()
                .compose(RxUtils.<BaseResponse<HashMap<String, List<StudyResourceItem>>>>schedulersTransformer())
                .compose(RxUtils.<HashMap<String, List<StudyResourceItem>>>exceptionTransformerSimple())
                .subscribe(new Consumer<HashMap<String, List<StudyResourceItem>>>() {
                    @Override
                    public void accept(HashMap<String, List<StudyResourceItem>> map) throws Exception {
                        resourceList.clear();
                        addResource(map, "class", "系统班级", true);
                        addResource(map, "course", "单项课程", false);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        ELog.e("test", "  " + throwable.getMessage());
                    }
                });

        addSubscribe(subscribe);
    }


    public void addResource(HashMap<String, List<StudyResourceItem>> map, String key, String title, boolean isClass) {
        List<StudyResourceItem> aClass = map.get(key);
        if (aClass != null) {
            if (aClass.size() > 0) {
                resourceList.add(new StudyItemViewModel(StudyViewModel.this, new StudyResourceItem(title, true)));
            }

            for (StudyResourceItem studyResourceItem : aClass) {
                studyResourceItem.setClass(isClass);
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
        showNoLiveFlag.set(false);
        examStatistic.set(null);

        Disposable subscribe = eduApi.getLearnInfo(studyResourceItem.getId())
                .compose(RxUtils.<BaseResponse<LearnInfoResponse>>schedulersTransformer())
                .compose(RxUtils.<LearnInfoResponse>exceptionTransformerSimple())
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
                        showNoLiveFlag.set(live.size() == 0);


                        List<TiKuExamInfo> subjectList = learnInfoResponse.getSubjectList();
                        TiKuStudyInfo exam = learnInfoResponse.getExam();
                        for (TiKuExamInfo tiKuExamInfo : subjectList) {
                            tiKuList.add(new StudyTiKuItemViewModel(StudyViewModel.this, tiKuExamInfo));

                            if (tiKuExamInfo.getId() == exam.getSubjectID()) {
                                //去请求练题情况
                                getSubjectStatistic(tiKuExamInfo.getId(), tiKuExamInfo.getName());
                            }
                        }


                        // 主 recycleView 刷新成功。
                        uc.refreshEvent.setValue(3);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        showNoLiveFlag.set(true);
                        // 主 recycleView 刷新失败。
                        uc.refreshEvent.setValue(3);
                    }
                });


        addSubscribe(subscribe);

    }

    /**
     * 获取直播回放 数据
     */
    public void getLiveBackInfo() {
        liveBackList.clear();
        showNoLiveBackFlag.set(false);
        final StudyResourceItem studyResourceItem = selectSource.get();
        if (studyResourceItem == null) {
            return;
        }

        UserApi userApi = RetrofitClient.getInstance().create(UserApi.class);
        Disposable subscribe = userApi
                .replayLive(new LiveBackRequest(1, 10, 0, studyResourceItem.getId()))
                .compose(RxUtils.<BaseResponse<List<LiveInfo>>>schedulersTransformer())
                .compose(RxUtils.<List<LiveInfo>>exceptionTransformerSimple()).subscribe(new Consumer<List<LiveInfo>>() {
                    @Override
                    public void accept(List<LiveInfo> liveInfoList) throws Exception {
                        for (LiveInfo liveInfo : liveInfoList) {
                            liveBackList.add(new LiveItemViewModel(null, liveInfo));
                        }
                        showNoLiveBackFlag.set(liveInfoList.size() == 0);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("test", "---" + throwable.getMessage());

                        showNoLiveBackFlag.set(true);
                    }
                });

        addSubscribe(subscribe);
    }


    /**
     * 获取练题情况
     */
    private void getSubjectStatistic(int subjectId, final String title) {
        TiKuApi tiKuApi = RetrofitClient.getInstance().create(TiKuApi.class);
        Disposable subscribe = tiKuApi.getSubjectStatistic(subjectId)
                .compose(RxUtils.<BaseResponse<TiKuStatistic>>schedulersTransformer())
                .compose(RxUtils.<TiKuStatistic>exceptionTransformerSimple())
                .subscribe(new Consumer<TiKuStatistic>() {
                    @Override
                    public void accept(TiKuStatistic statistic) throws Exception {
                        statistic.setName(title);
                        examStatistic.set(statistic);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

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
