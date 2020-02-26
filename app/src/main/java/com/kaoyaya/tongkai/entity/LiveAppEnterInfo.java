package com.kaoyaya.tongkai.entity;

import androidx.annotation.NonNull;

public class LiveAppEnterInfo {


    /**
     * ccId : E6214CDB2F09EB92
     * desc :
     * liveId : test
     * liveStartTime : 2020-01-16 19:30:00
     * playPass :
     * receivingInformation :
     * receivingIntro :
     * recordId : 2B940F71397797AB
     * roomId : 7FB0FBBB8D6994969C33DC5901307461
     * templateType : 4
     * title : CPA经济法预科02讲
     * viewName : 正式12_1123761
     */

    private String ccId;
    private String desc;
    private String liveId;
    private String liveStartTime;
    private String playPass;
    private String receivingInformation;
    private String receivingIntro;
    private String recordId;
    private String roomId;
    private int templateType;
    private String title;
    private String viewName;

    public String getCcId() {
        return ccId;
    }

    public void setCcId(String ccId) {
        this.ccId = ccId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getLiveId() {
        return liveId;
    }

    public void setLiveId(String liveId) {
        this.liveId = liveId;
    }

    public String getLiveStartTime() {
        return liveStartTime;
    }

    public void setLiveStartTime(String liveStartTime) {
        this.liveStartTime = liveStartTime;
    }

    public String getPlayPass() {
        return playPass;
    }

    public void setPlayPass(String playPass) {
        this.playPass = playPass;
    }

    public String getReceivingInformation() {
        return receivingInformation;
    }

    public void setReceivingInformation(String receivingInformation) {
        this.receivingInformation = receivingInformation;
    }

    public String getReceivingIntro() {
        return receivingIntro;
    }

    public void setReceivingIntro(String receivingIntro) {
        this.receivingIntro = receivingIntro;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public int getTemplateType() {
        return templateType;
    }

    public void setTemplateType(int templateType) {
        this.templateType = templateType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    @Override
    public String toString() {
        return "LiveAppEnterInfo{" +
                "ccId='" + ccId + '\'' +
                ", desc='" + desc + '\'' +
                ", liveId='" + liveId + '\'' +
                ", liveStartTime='" + liveStartTime + '\'' +
                ", playPass='" + playPass + '\'' +
                ", receivingInformation='" + receivingInformation + '\'' +
                ", receivingIntro='" + receivingIntro + '\'' +
                ", recordId='" + recordId + '\'' +
                ", roomId='" + roomId + '\'' +
                ", templateType=" + templateType +
                ", title='" + title + '\'' +
                ", viewName='" + viewName + '\'' +
                '}';
    }
}
