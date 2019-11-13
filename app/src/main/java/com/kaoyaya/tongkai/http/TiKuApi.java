package com.kaoyaya.tongkai.http;

import com.kaoyaya.tongkai.entity.ExamTypeInfo;
import com.kaoyaya.tongkai.entity.TiKuExamInfo;
import com.kaoyaya.tongkai.entity.TiKuExamResponse;
import com.li.basemvvm.http.base.BaseResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TiKuApi {

    //http://www.kaoyaya.com/api/v1/distribute/subject?examType=6

    /***
     * 获取分发的题库
     */
    @GET("/api/v1/distribute/subject")
    Observable<BaseResponse<List<TiKuExamInfo>>> getDistributeSubject(@Query("examType") int examType);


    // http://www.kaoyaya.com/api/v1/subjects/8190/subjects
    /**
     * 获取考试类型下的科目
     */
    @GET("/api/v1/subjects/{subjectID}/subjects")
    Observable<BaseResponse<TiKuExamResponse>> getSubjects(@Path("subjectID") int subjectID);


}
