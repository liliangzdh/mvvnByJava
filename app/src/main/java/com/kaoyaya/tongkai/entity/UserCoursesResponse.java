package com.kaoyaya.tongkai.entity;

import java.util.List;

public class UserCoursesResponse {


    private List<CourseInfo> courseInfo;
    private List<ExamInfo> topCateList;


    public List<CourseInfo> getCourseInfo() {
        return courseInfo;
    }

    public List<ExamInfo> getTopCateList() {
        return topCateList;
    }
}


