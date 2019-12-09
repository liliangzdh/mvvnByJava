package com.kaoyaya.tongkai.http;


import androidx.databinding.ObservableField;

import com.kaoyaya.tongkai.entity.CourseSampleInfo;
import com.kaoyaya.tongkai.entity.ExamTypeInfo;
import com.kaoyaya.tongkai.entity.LearnInfoResponse;
import com.kaoyaya.tongkai.entity.LiveInfo;
import com.kaoyaya.tongkai.entity.MyList;
import com.li.basemvvm.http.base.BaseResponse;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface EduApi {


    /**
     * 获取课程信息,课件 同步课程信息
     */
    @POST("api/v1/edu/course/info")
    Observable<BaseResponse<List<CourseSampleInfo>>> getCourseWareInfo(@Body MyList list);


    @POST("api/v1/edu/course/info")
    Observable<BaseResponse<List<CourseSampleInfo>>> getCourseWareInfo2(@Body HashMap<String, List<Integer>> data);


    @FormUrlEncoded
    @POST("api/v1/edu/course/info")
    Observable<BaseResponse<List<CourseSampleInfo>>> getCourseWareInfo3(@FieldMap HashMap<String, List<Integer>> data);


    // 获取 首页 分发资源
    @GET("api/v1/distribute/examType")
    Observable<BaseResponse<List<ExamTypeInfo>>> getOemExamTypeList();


//    https://www.kaoyaya.com/api/v1/edu/class/100/learnInfo?isLiveAll=1

    @GET("api/v1/edu/class/{id}/learnInfo?isLiveAll=1")
    Observable<BaseResponse<LearnInfoResponse>> getLearnInfo(@Path("id") int id);

}
