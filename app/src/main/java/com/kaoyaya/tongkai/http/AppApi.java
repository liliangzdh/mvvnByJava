package com.kaoyaya.tongkai.http;

import com.kaoyaya.tongkai.entity.AppVersion;
import com.li.basemvvm.http.base.BaseResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface AppApi {

    @GET("api/v1/app/latest")
    Observable<BaseResponse<AppVersion>> getLastVersion();
    // @GET注解的作用:采用Get方法发送网络请求
    // getCall() = 接收网络请求数据的方法
    // 其中返回类型为Call<*>，*是接收数据的类（即上面定义的Translation类）
    // 如果想直接获得Responsebody中的内容，可以定义网络请求返回值为Call<ResponseBody>
}
