package com.kaoyaya.tongkai.entity;

public class TiKuExamInfo {

//    id: 8191
//    isSprintOpen: 1
//    name: "税法（Ⅰ）"
//    parentID: 8190


    private int id;

    private int isSprintOpen;

    private String name;

    private int parentID;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIsSprintOpen() {
        return isSprintOpen;
    }

    public void setIsSprintOpen(int isSprintOpen) {
        this.isSprintOpen = isSprintOpen;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParentID() {
        return parentID;
    }

    public void setParentID(int parentID) {
        this.parentID = parentID;
    }
}
