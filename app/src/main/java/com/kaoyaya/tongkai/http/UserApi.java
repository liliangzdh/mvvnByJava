package com.kaoyaya.tongkai.http;

import com.kaoyaya.tongkai.entity.UserInfo;
import com.li.basemvvm.http.base.BaseResponse;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserApi {

    @GET("api/v1/users/info")
    Observable<BaseResponse<UserInfo>> getUserInfo();

    //
    @POST("api/v1/login/password")
    Observable<BaseResponse<HashMap<String, String>>> passWord(@Body HashMap<String, String> body);

    @FormUrlEncoded
    @POST("api/v1/login/password")
    Observable<BaseResponse<HashMap<String, String>>> passWord2(@Field("username") String username, @Field("password") String password,@Field("json") boolean json);

}
