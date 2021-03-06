package com.kaoyaya.tongkai.ui.login;

import android.app.Application;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.hdl.elog.ELog;
import com.kaoyaya.tongkai.config.Constant;
import com.kaoyaya.tongkai.entity.UserInfo;
import com.kaoyaya.tongkai.http.UserApi;
import com.li.basemvvm.base.BaseViewModel;
import com.li.basemvvm.binding.command.BindingAction;
import com.li.basemvvm.binding.command.BindingCommand;
import com.li.basemvvm.bus.Messenger;
import com.li.basemvvm.bus.event.SingleLiveEvent;
import com.li.basemvvm.http.base.RetrofitClient;
import com.li.basemvvm.utils.RxUtils;
import com.li.basemvvm.utils.ToastUtils;
import com.li.basemvvm.utils.TokenUtils;

import java.util.HashMap;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


public class LoginViewModel extends BaseViewModel {


    public ObservableField<String> userName = new ObservableField<>();

    public ObservableField<String> password = new ObservableField<>();


    public UIChangeObservable uc = new UIChangeObservable();

    public class UIChangeObservable {
        SingleLiveEvent<Boolean> pSwitchEvent = new SingleLiveEvent<>();
    }


    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

    public BindingCommand loginOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            login();
        }
    });

    public BindingCommand backAction = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            finish();
        }
    });

    public BindingCommand passwordShowCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            uc.pSwitchEvent.setValue(uc.pSwitchEvent.getValue() == null || !uc.pSwitchEvent.getValue());
        }
    });


    public BindingCommand getUserInfoCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            getUserInfo();
        }
    });

    private void login() {
        if (TextUtils.isEmpty(userName.get())) {
            ToastUtils.show(getApplication(), "请输入用户名");
            return;
        }

        if (TextUtils.isEmpty(password.get())) {
            ToastUtils.show(getApplication(), "请输入密码");
            return;
        }
        loginByPassword();
    }


    //下面的可以处理 异常。
    @SuppressWarnings("unchecked")
    private void loginByPassword() {
        showDialog("正在登陆...");
        final UserApi appApi = RetrofitClient.getInstance().create(UserApi.class);
        Disposable subscribe = appApi.passWord2(userName.get(), password.get(), true).
                compose(RxUtils.schedulersTransformer()).
                compose(RxUtils.exceptionTransformer()).
                subscribe(new Consumer<HashMap<String, String>>() {
                    @Override
                    public void accept(HashMap<String, String> result) {
                        dismissDialog();
                        if (result != null && result.size() > 0) {
                            String token = result.get("token");
                            if (!TextUtils.isEmpty(token)) {
                                ELog.e("test", "保存token");
                                TokenUtils.getInstance().saveToken(token);
                                Messenger.getDefault().sendNoMsg(Constant.Login);
                                finish();
                            }
                        }

                        getUserInfo();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        dismissDialog();
                        ToastUtils.show(getApplication(), "" + throwable.getMessage());
                    }
                });
        addSubscribe(subscribe);
    }


    @SuppressWarnings("unchecked")
    public void getUserInfo() {
        UserApi userApi = RetrofitClient.getInstance().create(UserApi.class);
        Disposable subscribe = userApi.getUserInfo().
                compose(RxUtils.schedulersTransformer()).
                compose(RxUtils.exceptionTransformer()).
                subscribe(new Consumer<UserInfo>() {
                    @Override
                    public void accept(UserInfo info) {
                        ELog.e("test", "----1-----" + info.getUsername());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        ELog.e("test", throwable.getMessage());
                    }
                });
        addSubscribe(subscribe);
    }

}
