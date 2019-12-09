package com.kaoyaya.tongkai.entity;

public class LiveBackRequest {

    private int page;
    private int pageSize;
    private int sortCourse;
    private int sortClassroom;
    private int isAllLive = 1;


    public LiveBackRequest(int page, int pageSize, int sortCourse, int sortClassroom) {
        this.page = page;
        this.pageSize = pageSize;
        this.sortCourse = sortCourse;
        this.sortClassroom = sortClassroom;
    }
}
