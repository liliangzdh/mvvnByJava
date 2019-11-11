package com.li.basemvvm.http.base;

import io.reactivex.functions.Consumer;

public abstract class ErrorConsumer<T> implements Consumer<Throwable> {


    public abstract void accept(String msg);


    @Override
    public void accept(Throwable throwable) {


        ResponseThrowable responseThrowable = ExceptionHandle.handleException(throwable);
//        accept(responseThrowable.message);
    }
}
