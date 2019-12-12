package com.kaoyaya.tongkai.entity;

public class StudyResourceItem {

    private int id;

    private String title;

    private boolean isClass;

    private String courseType;
    private int subjectID;

    private boolean isHeader;


    private boolean isSelect;

    public StudyResourceItem(String title, boolean isHeader) {
        this.title = title;
        this.isHeader = isHeader;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public int getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(int subjectID) {
        this.subjectID = subjectID;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public boolean isHeader() {
        return isHeader;
    }

    public void setHeader(boolean header) {
        isHeader = header;
    }

    public boolean showLine() {
        return !"系统班级".equals(title);
    }

    public void setClass(boolean aClass) {
        isClass = aClass;
    }

    public boolean isClass() {
        return isClass;
    }
}
