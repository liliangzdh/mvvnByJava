package com.kaoyaya.tongkai.ui.login;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.kaoyaya.tongkai.BR;
import com.kaoyaya.tongkai.R;
import com.kaoyaya.tongkai.databinding.ActivityLoginBinding;
import com.li.basemvvm.base.BaseActivity;
import com.kaoyaya.tongkai.app.AppViewModelFactory;

public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel> {
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_login;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public LoginViewModel initViewModel() {
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
//        return ViewModelProviders.of(this, factory).get(LoginViewModel.class);
        return new ViewModelProvider(getViewModelStore(), factory).get(LoginViewModel.class);
//        return  AppViewModelFactory.getInstance(getApplication()).create(LoginViewModel.class);
    }

    @Override
    public void initViewObservable() {
        viewModel.uc.pSwitchEvent.observe(LoginActivity.this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    binding.ivShowPsw.setImageResource(R.mipmap.show_psw);
                    binding.editPassWord.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    binding.ivShowPsw.setImageResource(R.mipmap.show_psw_press);
                    binding.editPassWord.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                binding.editPassWord.setSelection(binding.editPassWord.getText().length());
            }
        });
    }

    @Override
    public void initParam() {
        super.initParam();
    }

    @Override
    public void initData() {
        super.initData();
    }

}
