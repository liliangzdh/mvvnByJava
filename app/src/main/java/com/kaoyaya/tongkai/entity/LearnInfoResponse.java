package com.kaoyaya.tongkai.entity;

import java.util.List;

public class LearnInfoResponse {

    private List<LearnCourseInfo> normal;
    private List<LiveInfo> live;

    private List<TiKuExamInfo> subjectList;

    private TiKuStudyInfo exam;

    public List<LearnCourseInfo> getNormal() {
        return normal;
    }

    public List<LiveInfo> getLive() {
        return live;
    }


    public List<TiKuExamInfo> getSubjectList() {
        return subjectList;
    }


    public TiKuStudyInfo getExam() {
        return exam;
    }
}
