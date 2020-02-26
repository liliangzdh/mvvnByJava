package com.kaoyaya.tongkai.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CourseInfo {


    /**
     * picture : https://img.kaoyaya.com/Ntsl/www/09/1535939669.png
     * title : 2018税务师考试《涉税服务相关法律》精讲视频
     * id : 91220
     * type : normal
     * url :
     * is_activate : 1
     * activation_time : 2019-02-01 22:51:50
     * deadline : 2022-01-31 22:51:50
     * categoryId : 6958
     * expiryDay : 365
     * hitNum : 2107
     * studentNum : 2472
     * memberStart : 1580
     * lessonNum : 35
     * last_learn_lesson : 0
     * last_learn_time : 0001-01-01 00:00:00
     * mediaId : 0
     * lessonTitle :
     * topId : 541
     * progress : 0
     * updateNum : 0
     */

    private String picture;
    private String title;
    private int id;
    private String type;
    private String url;
    private int is_activate;
    private String activation_time;
    private String deadline;
    private int categoryId;
    private int expiryDay;
    private int hitNum;
    private int studentNum;
    private int memberStart;
    private int lessonNum;
    private int last_learn_lesson;
    private String last_learn_time;
    private int mediaId;
    private String lessonTitle;
    private int topId;
    private float progress;
    private int updateNum;

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getIs_activate() {
        return is_activate;
    }

    public void setIs_activate(int is_activate) {
        this.is_activate = is_activate;
    }

    public String getActivation_time() {
        return activation_time;
    }

    public void setActivation_time(String activation_time) {
        this.activation_time = activation_time;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getExpiryDay() {
        return expiryDay;
    }

    public void setExpiryDay(int expiryDay) {
        this.expiryDay = expiryDay;
    }

    public int getHitNum() {
        return hitNum;
    }

    public void setHitNum(int hitNum) {
        this.hitNum = hitNum;
    }

    public int getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(int studentNum) {
        this.studentNum = studentNum;
    }

    public int getMemberStart() {
        return memberStart;
    }

    public void setMemberStart(int memberStart) {
        this.memberStart = memberStart;
    }

    public int getLessonNum() {
        return lessonNum;
    }

    public void setLessonNum(int lessonNum) {
        this.lessonNum = lessonNum;
    }

    public int getLast_learn_lesson() {
        return last_learn_lesson;
    }

    public void setLast_learn_lesson(int last_learn_lesson) {
        this.last_learn_lesson = last_learn_lesson;
    }

    public String getLast_learn_time() {
        return last_learn_time;
    }

    public void setLast_learn_time(String last_learn_time) {
        this.last_learn_time = last_learn_time;
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

    public int getTopId() {
        return topId;
    }

    public void setTopId(int topId) {
        this.topId = topId;
    }

    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
    }

    public int getUpdateNum() {
        return updateNum;
    }

    public void setUpdateNum(int updateNum) {
        this.updateNum = updateNum;
    }

    public String getStudyProgress() {
        return "已学" + ((int) progress) + "%";
    }

    public String getPeriodTime() {


        return format(activation_time) + " - "+ format(deadline);
    }


    public String format(String time) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date parse = formatter.parse(time);
            formatter = new SimpleDateFormat("yyyy-MM-dd");
            return formatter.format(parse);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return time;
    }
}
