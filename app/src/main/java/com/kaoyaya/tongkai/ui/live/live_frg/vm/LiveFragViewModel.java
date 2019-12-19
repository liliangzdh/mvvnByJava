package com.kaoyaya.tongkai.ui.live.live_frg.vm;

import android.app.Application;

import androidx.annotation.NonNull;

import com.li.basemvvm.base.BaseViewModel;
import com.li.basemvvm.binding.command.BindingAction;
import com.li.basemvvm.binding.command.BindingCommand;

public class LiveFragViewModel extends BaseViewModel {


    public LiveFragViewModel(@NonNull Application application) {
        super(application);
    }

    public BindingCommand goBack = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            finish();
        }
    });
}
