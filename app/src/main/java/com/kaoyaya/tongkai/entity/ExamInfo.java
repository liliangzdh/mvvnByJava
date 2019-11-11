package com.kaoyaya.tongkai.entity;


public class ExamInfo {
    /**
     * id : 2
     * name : 会计初级职称
     */

    private int id;
    private String name;


    private boolean isSelect;

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
