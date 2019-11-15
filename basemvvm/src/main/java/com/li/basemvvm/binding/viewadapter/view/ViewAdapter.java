package com.li.basemvvm.binding.viewadapter.view;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;

import androidx.core.content.ContextCompat;
import androidx.databinding.BindingAdapter;

import com.jakewharton.rxbinding2.view.RxView;
import com.li.basemvvm.binding.command.BindingCommand;

import java.util.concurrent.TimeUnit;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by goldze on 2017/6/16.
 */

public class ViewAdapter {
    //防重复点击间隔(秒)
    private static final int CLICK_INTERVAL = 500;

    /**
     * requireAll 是意思是是否需要绑定全部参数, false为否
     * View的onClick事件绑定
     * onClickCommand 绑定的命令,
     * isThrottleFirst 是否开启防止过快点击
     */
    @BindingAdapter(value = {"onClickCommand", "isThrottleFirst"}, requireAll = false)
    public static void onClickCommand(final View view, final BindingCommand clickCommand, final boolean isThrottleFirst) {
        if (isThrottleFirst) {
            Disposable subscribe  = RxView.clicks(view)
                    .subscribe(new Consumer<Object>() {
                        @Override
                        public void accept(Object object)  {
                            if (clickCommand != null) {
                                clickCommand.execute();
                            }
                        }
                    });
        } else {
            Disposable subscribe = RxView.clicks(view)
                    .throttleFirst(CLICK_INTERVAL, TimeUnit.MILLISECONDS)//1秒钟内只允许点击1次
                    .subscribe(new Consumer<Object>() {
                        @Override
                        public void accept(Object object)  {
                            if (clickCommand != null) {
                                clickCommand.execute();
                            }
                        }
                    });
        }
    }

    /**
     * view的onLongClick事件绑定
     */
    @BindingAdapter(value = {"onLongClickCommand"}, requireAll = false)
    public static void onLongClickCommand(View view, final BindingCommand clickCommand) {
        Disposable subscribe = RxView.longClicks(view)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object object) throws Exception {
                        if (clickCommand != null) {
                            clickCommand.execute();
                        }
                    }
                });
    }

    /**
     * 回调控件本身
     *
     * @param currentView
     * @param bindingCommand
     */
    @BindingAdapter(value = {"currentView"}, requireAll = false)
    public static void replyCurrentView(View currentView, BindingCommand bindingCommand) {
        if (bindingCommand != null) {
            bindingCommand.execute(currentView);
        }
    }

    /**
     * view是否需要获取焦点
     */
    @BindingAdapter({"requestFocus"})
    public static void requestFocusCommand(View view, final Boolean needRequestFocus) {
        if (needRequestFocus) {
            view.setFocusableInTouchMode(true);
            view.requestFocus();
        } else {
            view.clearFocus();
        }
    }

    /**
     * view的焦点发生变化的事件绑定
     */
    @BindingAdapter({"onFocusChangeCommand"})
    public static void onFocusChangeCommand(View view, final BindingCommand<Boolean> onFocusChangeCommand) {
        view.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (onFocusChangeCommand != null) {
                    onFocusChangeCommand.execute(hasFocus);
                }
            }
        });
    }

    /**
     * view的显示隐藏
     */
    @BindingAdapter(value = {"isVisible"}, requireAll = false)
    public static void isVisible(View view, final Boolean visibility) {
        if (visibility) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
    }


    @BindingAdapter(value = {"backgroundColor"}, requireAll = false)
    public static void backgroundColor(View view, String color) {
        view.setBackgroundColor(Color.parseColor(color));
    }

    /** view的显示隐藏
     */
    @BindingAdapter(value = {"background"}, requireAll = false)
    public static void background(View view, int resId) {
        view.setBackgroundResource(resId);
    }
//    @BindingAdapter({"onTouchCommand"})
//    public static void onTouchCommand(View view, final ResponseCommand<MotionEvent, Boolean> onTouchCommand) {
//        view.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (onTouchCommand != null) {
//                    return onTouchCommand.execute(event);
//                }
//                return false;
//            }
//        });
//    }
}
