package com.kaoyaya.tongkai.ui.home;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;

import com.hdl.elog.ELog;
import com.kaoyaya.tongkai.BR;
import com.kaoyaya.tongkai.R;
import com.kaoyaya.tongkai.entity.ExamInfo;
import com.kaoyaya.tongkai.entity.HomeResource;
import com.kaoyaya.tongkai.entity.HomeResourseDistribute;
import com.kaoyaya.tongkai.entity.LiveInfo;
import com.kaoyaya.tongkai.entity.TiKuExamInfo;
import com.kaoyaya.tongkai.entity.TiKuExamResponse;
import com.kaoyaya.tongkai.http.LiveApi;
import com.kaoyaya.tongkai.http.TiKuApi;
import com.kaoyaya.tongkai.http.UserApi;
import com.kaoyaya.tongkai.ui.test.TestAct;
import com.kaoyaya.tongkai.utils.SPUtils;
import com.li.basemvvm.base.BaseViewModel;
import com.li.basemvvm.binding.command.BindingAction;
import com.li.basemvvm.binding.command.BindingCommand;
import com.li.basemvvm.binding.command.BindingConsumer;
import com.li.basemvvm.bus.Messenger;
import com.li.basemvvm.bus.event.SingleLiveEvent;
import com.li.basemvvm.http.base.BaseResponse;
import com.li.basemvvm.http.base.RetrofitClient;
import com.li.basemvvm.utils.RxUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.ObservableSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

public class HomeViewModel extends BaseViewModel {
    public ObservableField<String> title = new ObservableField<>("初级会计");


    public UIChangeObservable uc = new UIChangeObservable();

    // 精选好课的 数据源
    public ObservableList<GoodCourseItemViewModel> goodCourseList = new ObservableArrayList<>();
    // 给RecyclerView添加ItemBinding
    public ItemBinding<GoodCourseItemViewModel> goodCourseItemBinding = ItemBinding.of(BR.item, R.layout.item_good_course);

    // 名师推荐的数据源
    public ObservableList<GoodTeacherItemViewModel> goodTeacherList = new ObservableArrayList<>();
    public ItemBinding<GoodTeacherItemViewModel> goodTeacherItemBinding = ItemBinding.of(BR.item, R.layout.item_good_teacher);


    //题库的
    public ObservableList<GoodTiKuItemViewModel> goodTiKuList = new ObservableArrayList<>();
    public ItemBinding<GoodTiKuItemViewModel> goodTiKuItemBinding = ItemBinding.of(BR.item, R.layout.item_tiku);


    //直播的
    public ObservableList<LiveItemViewModel> goodLiveList = new ObservableArrayList<>();
    public ItemBinding<LiveItemViewModel> goodLiveItemBinding = ItemBinding.of(BR.item, R.layout.item_home_live);


    public HomeLiveAdapter adapter = new HomeLiveAdapter();


    public class UIChangeObservable {
        //获取分发资源
        public SingleLiveEvent<HomeResourseDistribute> finishGetBannerData = new SingleLiveEvent<>();
    }

    public static final String OpenMenuAction = "action_open_menu";
    public static final String RefreshByChangeExam = "refreshByChangeExam";

    public ExamInfo examInfo;

    private void initExamInfo() {
        ExamInfo examInfo = SPUtils.getInstance().getExamInfo();
        if (examInfo != null) {
            title.set(examInfo.getName());
            this.examInfo = examInfo;
        } else {
            // 默认值。
            this.examInfo = new ExamInfo(2, "会计初级职称");
        }
    }

    public HomeViewModel(@NonNull Application application) {
        super(application);
        this.initExamInfo();
        Messenger.getDefault().register(this, RefreshByChangeExam, new BindingAction() {
            @Override
            public void call() {
                initExamInfo();
                getNetResource();
                getTiKuResource();
                getHomeLive();
            }
        });
    }


    public BindingCommand openMenuCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            Messenger.getDefault().sendNoMsg(OpenMenuAction);
        }
    });

    public BindingCommand<Integer> moreCommand = new BindingCommand<>(new BindingConsumer<Integer>() {
        @Override
        public void call(Integer integer) {
            Log.e("test","ppp:"+integer);
        }
    });


    public BindingCommand<Integer> goodLessonCommand = new BindingCommand<>(new BindingConsumer<Integer>() {
        @Override
        public void call(Integer integer) {
            Log.e("test","  "+integer);
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
            startActivity(TestAct.class);
        }
    });


    // 发起网络请求。获取分发资源
    @SuppressWarnings("unchecked")
    public void getNetResource() {
        UserApi userApi = RetrofitClient.getInstance().create(UserApi.class);
        Disposable subscribe = userApi.getUserDistribute(examInfo.getId()).
                compose(RxUtils.<BaseResponse<ArrayList<HomeResourseDistribute>>>schedulersTransformer())
                .compose(RxUtils.exceptionTransformer())
                .subscribe(new Consumer<ArrayList<HomeResourseDistribute>>() {
                    @Override
                    public void accept(ArrayList<HomeResourseDistribute> list) throws Exception {
                        for (HomeResourseDistribute homeResourseDistribute : list) {
                            if ("Banner".equals(homeResourseDistribute.getName())) {
                                //发送 banner 数据到 页面上。更新数据源
                                uc.finishGetBannerData.setValue(homeResourseDistribute);
                            } else if ("精品体验课".equals(homeResourseDistribute.getName())) {
                                List<HomeResource> goodCourseResource = homeResourseDistribute.getResource();
                                goodCourseList.clear();
                                for (HomeResource homeResource : goodCourseResource) {
                                    GoodCourseItemViewModel itemViewModel = new GoodCourseItemViewModel(HomeViewModel.this, homeResource);
                                    goodCourseList.add(itemViewModel);
                                }
                            } else if ("名师推荐".equals(homeResourseDistribute.getName())) {
                                goodTeacherList.clear();
                                List<HomeResource> goodTeacheList = homeResourseDistribute.getResource();
                                for (HomeResource homeResource : goodTeacheList) {
                                    GoodTeacherItemViewModel itemViewModel = new GoodTeacherItemViewModel(HomeViewModel.this, homeResource);
                                    goodTeacherList.add(itemViewModel);
                                }
                            }
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });

        addSubscribe(subscribe);
        getTiKuResource();
        getHomeLive();
    }


    // 获取题库资源
    @SuppressWarnings("unchecked")
    public void getTiKuResource() {
        // 执行网络嵌套。
        final TiKuApi tiKuApi = RetrofitClient.getInstance().create(TiKuApi.class);
        Disposable subscribe2 = tiKuApi.getDistributeSubject(examInfo.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<BaseResponse<List<TiKuExamInfo>>>() {
                    @Override
                    public void accept(BaseResponse<List<TiKuExamInfo>> listBaseResponse) throws Exception {
                        //第一次请求成功。

                    }
                })
                .subscribeOn(Schedulers.io())
                .flatMap(new Function<BaseResponse<List<TiKuExamInfo>>, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(BaseResponse<List<TiKuExamInfo>> listBaseResponse) throws Exception {
                        List<TiKuExamInfo> list = listBaseResponse.getResult();
                        if (listBaseResponse.getCode() == 200 && list != null && list.size() > 0) {
                            int id = list.get(0).getId();
                            return tiKuApi.getSubjects(id);
                        }
                        return null;
                    }
                })
                .compose(RxUtils.schedulersTransformer())
                .compose(RxUtils.exceptionTransformer())
                .subscribe(new Consumer<TiKuExamResponse>() {
                    @Override
                    public void accept(TiKuExamResponse response) throws Exception {
                        List<TiKuExamInfo> list = response.getSubjects();
                        goodTiKuList.clear();
                        if (list != null) {
                            for (TiKuExamInfo tiKuExamInfo : list) {
                                goodTiKuList.add(new GoodTiKuItemViewModel(HomeViewModel.this, tiKuExamInfo));
                            }
                        }
                        Log.e("test", "题库 " + goodTiKuList.size());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("test", "190:" + throwable.getMessage());
                    }
                });

        addSubscribe(subscribe2);
    }


    /**
     * 获取 首页 直播
     **/
    @SuppressWarnings("unchecked")
    public void getHomeLive() {
        LiveApi liveApi = RetrofitClient.getInstance().create(LiveApi.class);

        Disposable subscribe = liveApi.getHotPreLive()
                .compose(RxUtils.<BaseResponse<List<LiveInfo>>>schedulersTransformer())
                .compose(RxUtils.exceptionTransformer())
                .subscribe(new Consumer<List<LiveInfo>>() {
                    @Override
                    public void accept(List<LiveInfo> list) throws Exception {
                        goodLiveList.clear();
                        for (LiveInfo info : list) {
                            goodLiveList.add(new LiveItemViewModel(HomeViewModel.this, info));
                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("test", "   " + throwable.getMessage());
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


    @Override
    protected void onCleared() {
        super.onCleared();
        adapter.cancelAllTimers();
    }
}
