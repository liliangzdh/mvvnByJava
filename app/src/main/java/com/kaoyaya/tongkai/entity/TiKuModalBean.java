package com.kaoyaya.tongkai.entity;

public class TiKuModalBean {
    /**
     * isShowChapter : true
     * isShowKnowledge : true
     * isShowExamMock : true
     * isShowTrueQuestion : true
     * isShowTopSecret : false
     * isExamHomework : true
     * isErrorQuestion : true
     */

    private boolean isShowChapter;
    private boolean isShowKnowledge;
    private boolean isShowExamMock;
    private boolean isShowTrueQuestion;
    private boolean isShowTopSecret;
    private boolean isExamHomework;
    private boolean isErrorQuestion;

    public boolean isIsShowChapter() {
        return isShowChapter;
    }

    public void setIsShowChapter(boolean isShowChapter) {
        this.isShowChapter = isShowChapter;
    }

    public boolean isIsShowKnowledge() {
        return isShowKnowledge;
    }

    public void setIsShowKnowledge(boolean isShowKnowledge) {
        this.isShowKnowledge = isShowKnowledge;
    }

    public boolean isIsShowExamMock() {
        return isShowExamMock;
    }

    public void setIsShowExamMock(boolean isShowExamMock) {
        this.isShowExamMock = isShowExamMock;
    }

    public boolean isIsShowTrueQuestion() {
        return isShowTrueQuestion;
    }

    public void setIsShowTrueQuestion(boolean isShowTrueQuestion) {
        this.isShowTrueQuestion = isShowTrueQuestion;
    }

    public boolean isIsShowTopSecret() {
        return isShowTopSecret;
    }

    public void setIsShowTopSecret(boolean isShowTopSecret) {
        this.isShowTopSecret = isShowTopSecret;
    }

    public boolean isIsExamHomework() {
        return isExamHomework;
    }

    public void setIsExamHomework(boolean isExamHomework) {
        this.isExamHomework = isExamHomework;
    }

    public boolean isIsErrorQuestion() {
        return isErrorQuestion;
    }

    public void setIsErrorQuestion(boolean isErrorQuestion) {
        this.isErrorQuestion = isErrorQuestion;
    }
}
