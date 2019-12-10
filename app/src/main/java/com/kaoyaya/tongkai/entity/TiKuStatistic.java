package com.kaoyaya.tongkai.entity;

public class TiKuStatistic {


    /**
     * totalQuestion : 3442
     * doneQuestion : 9
     * rightQuestion : 3
     * practiceRecord : {"subjectID":219,"practiceType":1,"practiceID":8486,"practiceMode":1,"lastPracticeName":"第一章总论"}
     * tiKuModal : {"isShowChapter":true,"isShowKnowledge":true,"isShowExamMock":true,"isShowTrueQuestion":true,"isShowTopSecret":false,"isExamHomework":true,"isErrorQuestion":true}
     */

    private int totalQuestion;
    private int doneQuestion;
    private int rightQuestion;
    private PracticeRecordBean practiceRecord;
    private TiKuModalBean tiKuModal;

    private String name;

    public int getTotalQuestion() {
        return totalQuestion;
    }

    public void setTotalQuestion(int totalQuestion) {
        this.totalQuestion = totalQuestion;
    }

    public int getDoneQuestion() {
        return doneQuestion;
    }

    public void setDoneQuestion(int doneQuestion) {
        this.doneQuestion = doneQuestion;
    }

    public int getRightQuestion() {
        return rightQuestion;
    }

    public void setRightQuestion(int rightQuestion) {
        this.rightQuestion = rightQuestion;
    }

    public PracticeRecordBean getPracticeRecord() {
        return practiceRecord;
    }

    public void setPracticeRecord(PracticeRecordBean practiceRecord) {
        this.practiceRecord = practiceRecord;
    }

    public TiKuModalBean getTiKuModal() {
        return tiKuModal;
    }

    public void setTiKuModal(TiKuModalBean tiKuModal) {
        this.tiKuModal = tiKuModal;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getRight() {
        if (doneQuestion == 0) {
            return "0";
        }
        return rightQuestion * 100 / doneQuestion + "";
    }

    public String getDone(){
        if (totalQuestion == 0) {
            return "0";
        }
        return doneQuestion *100 / totalQuestion  + "";
    }

    public String getDoneQuestionStr(){
        return "" +doneQuestion;
    }
}
