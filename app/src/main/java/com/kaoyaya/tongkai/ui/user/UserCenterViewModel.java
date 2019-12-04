package com.kaoyaya.tongkai.ui.user;

import android.app.Application;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.kaoyaya.tongkai.config.Constant;
import com.kaoyaya.tongkai.ui.login.LoginActivity;
import com.kaoyaya.tongkai.ui.set.SetActivity;
import com.li.basemvvm.base.BaseViewModel;
import com.li.basemvvm.binding.command.BindingAction;
import com.li.basemvvm.binding.command.BindingCommand;
import com.li.basemvvm.binding.command.BindingConsumer;
import com.li.basemvvm.bus.Messenger;
import com.li.basemvvm.utils.SPUtils;

public class UserCenterViewModel extends BaseViewModel {


    public UserCenterViewModel(@NonNull Application application) {
        super(application);
        initLoginState();
        Messenger.getDefault().register(this, Constant.Login, new BindingAction() {
            @Override
            public void call() {
                initLoginState();
            }
        });
    }

    private void initLoginState() {
        String token = SPUtils.getInstance().getToken();
        isLogin.set(!TextUtils.isEmpty(token));
    }

    public ObservableField<Boolean> isLogin = new ObservableField<>();


    public BindingCommand<Integer> action = new BindingCommand<>(new BindingConsumer<Integer>() {
        @Override
        public void call(Integer integer) {
            switch (integer) {
                case 0:
                    startActivity(LoginActivity.class);
                    break;
                case 1:
                    startActivity(SetActivity.class);
                    break;
                case 2:
                    Toast.makeText(getApplication(),"调整个人中心",Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    });
}
