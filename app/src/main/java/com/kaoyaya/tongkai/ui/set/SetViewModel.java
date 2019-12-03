package com.kaoyaya.tongkai.ui.set;

import android.app.Application;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.kaoyaya.tongkai.config.Constant;
import com.li.basemvvm.base.BaseViewModel;
import com.li.basemvvm.binding.command.BindingAction;
import com.li.basemvvm.binding.command.BindingCommand;
import com.li.basemvvm.bus.Messenger;
import com.li.basemvvm.utils.SPUtils;

public class SetViewModel extends BaseViewModel {

    public ObservableField<Boolean> isLogin = new ObservableField<>();


    public SetViewModel(@NonNull Application application) {
        super(application);
        isLogin.set(!TextUtils.isEmpty(SPUtils.getInstance().getToken()));
    }

    public BindingCommand backAction = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            finish();
        }
    });

    public BindingCommand logoutAction = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            SPUtils.getInstance().clearToken();
            Messenger.getDefault().sendNoMsg(Constant.Login);
            isLogin.set(!TextUtils.isEmpty(SPUtils.getInstance().getToken()));
            finish();
        }
    });
}
