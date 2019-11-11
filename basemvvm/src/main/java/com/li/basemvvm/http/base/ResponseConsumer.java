package com.li.basemvvm.http.base;

import io.reactivex.functions.Consumer;

public abstract class ResponseConsumer<T> implements Consumer<BaseResponse<T>> {


    public abstract void _accept(T t);

    @Override
    public void accept(BaseResponse<T> response) {
        if (!response.isOk()){
            throw new RuntimeException(!"".equals(response.getCode() + "" + response.getMsg()) ? response.getMsg() : "");
        }
        _accept(response.getResult());
    }
}
