package com.kaoyaya.tongkai.http;

import com.kaoyaya.tongkai.entity.CCPage;
import com.kaoyaya.tongkai.entity.LiveAppEnterInfo;
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


    // app App进去CC直播间
    @GET("/api/v1/live/appEnterLiveRoom")
    Observable<BaseResponse<LiveAppEnterInfo>> appEnterLiveRoom(@Query("id") int id);

//    https://view.csslcloud.net/api/view/replay/v2/info?recordid=417AA3B3F066ABAA&userid=E6214CDB2F09EB92&liveid=test&roomid=EE841107396A68959C33DC5901307461

    @GET("/api/view/replay/v2/info")
    Observable<CCPage> ccInfo(@Query("recordid") String recordid,@Query("userid") String userid,@Query("liveid")String liveid,@Query("roomid") String roomid);


}
