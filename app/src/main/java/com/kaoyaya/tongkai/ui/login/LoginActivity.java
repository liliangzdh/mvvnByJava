package com.kaoyaya.tongkai.ui.login;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

import androidx.lifecycle.Observer;

import com.kaoyaya.tongkai.BR;
import com.kaoyaya.tongkai.R;
import com.kaoyaya.tongkai.databinding.ActivityLoginBinding;
import com.li.basemvvm.base.BaseActivity;


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
    public void initData() {
        super.initData();
        initStatusBar();

    }


    private void anim(View view) {
        if (view.getVisibility() == View.GONE) {
            //相对于自己的高度往下平移
//            TranslateAnimation translate = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
//                    Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
//                    1.0f, Animation.RELATIVE_TO_SELF, 0.0f);

            TranslateAnimation translate = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 1.0f,
                    Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                    0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
            translate.setDuration(300);//动画时间500毫秒
            translate.setFillAfter(false);//动画出来控件可以点击
            view.startAnimation(translate);//开始动画
            view.setVisibility(View.VISIBLE);//设置可见


        } else {

            //相对于自己的高度往上平移
//            TranslateAnimation translate = new TranslateAnimation(Animation.RELATIVE_TO_SELF,
//                    0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
//                    Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
//                    1.0f);

            TranslateAnimation translate = new TranslateAnimation(Animation.RELATIVE_TO_SELF,
                    0.0f, Animation.RELATIVE_TO_SELF, 1.0f,
                    Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                    0.0f);
            translate.setDuration(300);
            translate.setFillAfter(false);//设置动画结束后控件不可点击
            view.startAnimation(translate);
            view.setVisibility(View.GONE);//隐藏不占位置

        }
    }

}
