package com.kaoyaya.tongkai.http;


import com.kaoyaya.tongkai.entity.CourseSampleInfo;
import com.kaoyaya.tongkai.entity.ExamTypeInfo;
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

public interface EduApi {


    /**
     * 获取课程信息,课件 同步课程信息
     */
    @POST("api/v1/edu/course/info")
    Observable<BaseResponse<List<CourseSampleInfo>>> getCourseWareInfo(@Body MyList list);


    @POST("api/v1/edu/course/info")
    Observable<BaseResponse<List<CourseSampleInfo>>> getCourseWareInfo2(@Body HashMap<String,List<Integer>> data);


    @FormUrlEncoded
    @POST("api/v1/edu/course/info")
    Observable<BaseResponse<List<CourseSampleInfo>>> getCourseWareInfo3(@FieldMap HashMap<String,List<Integer>> data);


    @GET("api/v1/distribute/examType")
    Observable<BaseResponse<List<ExamTypeInfo>>> getOemExamTypeList();

}
