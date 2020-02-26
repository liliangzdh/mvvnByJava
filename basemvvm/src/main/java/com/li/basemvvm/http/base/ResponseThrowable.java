package com.li.basemvvm.http.base;

import android.text.TextUtils;

import androidx.annotation.Nullable;

/**
 * Created by goldze on 2017/5/11.
 */

public class ResponseThrowable extends Exception {
    public int code;
    public String message;

    public ResponseThrowable(Throwable throwable, int code) {
        super(throwable);
        this.code = code;
    }


    @Nullable
    @Override
    public String getMessage() {
        if(TextUtils.isEmpty(message)){
            return super.getMessage();
        }
        return message;
    }
}
