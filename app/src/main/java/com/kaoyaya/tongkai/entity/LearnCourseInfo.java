package com.kaoyaya.tongkai.entity;

import com.kaoyaya.tongkai.R;

public class LearnCourseInfo {
    private int courseNum;
    private int id;
    private int lastLessonID;
    private String lastLessonName;
    private String last_learn_time;
    private int learnNum;
    private String lessonTitle;
    private String picture;
    private String title;
    private int updateNum;


    public int getCourseNum() {
        return courseNum;
    }

    public int getId() {
        return id;
    }

    public int getLastLessonID() {
        return lastLessonID;
    }

    public String getLastLessonName() {
        return lastLessonName;
    }

    public String getLast_learn_time() {
        return last_learn_time;
    }

    public int getLearnNum() {
        return learnNum;
    }

    public String getLessonTitle() {
        return lessonTitle;
    }

    public String getPicture() {
        return picture;
    }

    public String getTitle() {
        return title;
    }

    public int getUpdateNum() {
        return updateNum;
    }


    public String getProgress() {
        int progress = 0;
        if (courseNum == 0) {
            progress = 0;
        } else {
            progress = learnNum * 100 / courseNum;

            if (progress >= 100) {
                progress = 100;
            }
        }

        if (progress == 0) {
            return "未学习";
        }

        return "已学" + progress + "%";
    }

    public String getProgressColor() {
        String progress = getProgress();

        if ("未学习".equals(progress)) {
            return "#b2b2b2";
        }
        return "#5e8ffe";
    }
}
