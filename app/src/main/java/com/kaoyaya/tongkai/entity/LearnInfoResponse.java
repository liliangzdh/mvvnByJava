package com.kaoyaya.tongkai.entity;

import java.util.List;

public class LearnInfoResponse {

    private List<LearnCourseInfo> normal;
    private List<LiveInfo> live;


    public List<LearnCourseInfo> getNormal() {
        return normal;
    }

    public List<LiveInfo> getLive() {
        return live;
    }
}
