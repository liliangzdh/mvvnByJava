package com.kaoyaya.tongkai.entity;

import androidx.databinding.BaseObservable;

import com.kaoyaya.tongkai.utils.TimeUtils;

public class LiveInfo {


    /**
     * access : 0
     * courseId : 99543
     * courseTitle : 管理会计直播课-加菲猫
     * free : 0
     * category :
     * expiryDay : 365
     * needLogin : 1
     * mediaId : 3275
     * lessonTitle : 《成本管理》1
     * end_time : 2019-11-13 21:30:00
     * start_time : 2019-11-13 20:00:00
     * picture : https://img.kaoyaya.com/Ntsl/www/10/1571370910.png
     * nickname : 加菲猫老师
     * livePlatform : 1
     * subscribeNum : 0
     * subscribeStatus : false
     */

    private int access;
    private int courseId;
    private String courseTitle;
    private int free;
    private String category;
    private int expiryDay;
    private int needLogin;
    private int mediaId;
    private String lessonTitle;
    private String end_time;
    private String start_time;
    private String picture;
    private String nickname;
    private int livePlatform;
    private int subscribeNum;
    private boolean subscribeStatus;

    public boolean isStart;

    public String fromStartTimeStr;

    public int getAccess() {
        return access;
    }

    public void setAccess(int access) {
        this.access = access;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public int getFree() {
        return free;
    }

    public void setFree(int free) {
        this.free = free;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getExpiryDay() {
        return expiryDay;
    }

    public void setExpiryDay(int expiryDay) {
        this.expiryDay = expiryDay;
    }

    public int getNeedLogin() {
        return needLogin;
    }

    public void setNeedLogin(int needLogin) {
        this.needLogin = needLogin;
    }

    public int getMediaId() {
        return mediaId;
    }

    public void setMediaId(int mediaId) {
        this.mediaId = mediaId;
    }

    public String getLessonTitle() {
        return lessonTitle;
    }

    public void setLessonTitle(String lessonTitle) {
        this.lessonTitle = lessonTitle;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getLivePlatform() {
        return livePlatform;
    }

    public void setLivePlatform(int livePlatform) {
        this.livePlatform = livePlatform;
    }

    public int getSubscribeNum() {
        return subscribeNum;
    }

    public void setSubscribeNum(int subscribeNum) {
        this.subscribeNum = subscribeNum;
    }

    public boolean isSubscribeStatus() {
        return subscribeStatus;
    }

    public void setSubscribeStatus(boolean subscribeStatus) {
        this.subscribeStatus = subscribeStatus;
    }

    public boolean isStart() {
        return isStart;
    }

    public void setStart(boolean start) {
        isStart = start;
    }

    public String getFromStartTimeStr() {
        return fromStartTimeStr;
    }

    public void setFromStartTimeStr(String fromStartTimeStr) {
        this.fromStartTimeStr = fromStartTimeStr;
    }


    public String getStartAndEndTime(){
        return TimeUtils.getInstance().formatTime(getStart_time()) + "-" + TimeUtils.getInstance().formatTime(getEnd_time());
    }
}
