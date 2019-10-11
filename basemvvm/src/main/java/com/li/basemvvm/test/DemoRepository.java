package com.li.basemvvm.test;

import com.li.basemvvm.base.BaseModel;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

public class DemoRepository extends BaseModel {

    public Observable<Object> login() {
        return Observable.just(new Object()).delay(3, TimeUnit.SECONDS);
    }

}
