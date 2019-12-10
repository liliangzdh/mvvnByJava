package com.kaoyaya.tongkai.entity;

public class PracticeRecordBean {
    /**
     * subjectID : 219
     * practiceType : 1
     * practiceID : 8486
     * practiceMode : 1
     * lastPracticeName : 第一章总论
     */

    private int subjectID;
    private int practiceType;
    private int practiceID;
    private int practiceMode;
    private String lastPracticeName;

    public int getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(int subjectID) {
        this.subjectID = subjectID;
    }

    public int getPracticeType() {
        return practiceType;
    }

    public void setPracticeType(int practiceType) {
        this.practiceType = practiceType;
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

    public String getLastPracticeName() {
        return lastPracticeName;
    }

    public void setLastPracticeName(String lastPracticeName) {
        this.lastPracticeName = lastPracticeName;
    }


}
