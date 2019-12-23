package com.kaoyaya.tongkai.entity;

import java.util.List;

public class LiveIdAndClassIdResponse {

    private List<CourseSampleInfo> classroomIds;

    private List<CourseSampleInfo> liveIds;


    public List<CourseSampleInfo> getClassroomIds() {
        return classroomIds;
    }

    public List<CourseSampleInfo> getLiveIds() {
        return liveIds;
    }
}
