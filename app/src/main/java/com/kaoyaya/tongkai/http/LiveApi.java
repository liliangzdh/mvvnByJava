package com.kaoyaya.tongkai.http;

import com.kaoyaya.tongkai.entity.LiveInfo;
import com.li.basemvvm.http.base.BaseResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LiveApi {


    //http://www.kaoyaya.com/api/v1/live/newHotPreLive?isAll=1

    // 获取首页的直播数据
    @GET("/api/v1/live/newHotPreLive?isAll=1")
    Observable<BaseResponse<List<LiveInfo>>> getHotPreLive();

}
