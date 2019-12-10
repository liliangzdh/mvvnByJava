package com.kaoyaya.tongkai.entity;

public class TiKuStudyInfo {

    private String lastPracticeName;
    private int practiceID;
    private int practiceMode;
    private int practiceType;
    private int subjectID;


    private String subjectTitle;

    public String getLastPracticeName() {
        return lastPracticeName;
    }

    public void setLastPracticeName(String lastPracticeName) {
        this.lastPracticeName = lastPracticeName;
    }

    public int getPracticeID() {
        return practiceID;
    }

    public void setPracticeID(int practiceID) {
        this.practiceID = practiceID;
    }

    public int getPracticeMode() {
        return practiceMode;
    }

    public void setPracticeMode(int practiceMode) {
        this.practiceMode = practiceMode;
    }

    public int getPracticeType() {
        return practiceType;
    }

    public void setPracticeType(int practiceType) {
        this.practiceType = practiceType;
    }

    public int getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(int subjectID) {
        this.subjectID = subjectID;
    }

    public String getSubjectTitle() {

        return subjectTitle;
    }

    public void setSubjectTitle(String subjectTitle) {
        this.subjectTitle = subjectTitle;
    }
}
