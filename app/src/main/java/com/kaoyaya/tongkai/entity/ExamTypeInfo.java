package com.kaoyaya.tongkai.entity;

import java.util.List;

public class ExamTypeInfo {


    /**
     * id : 1
     * name : 会计课堂
     * children : [{"id":2,"name":"会计初级职称","children":[]},{"id":3,"name":"会计中级职称","children":[]},{"id":4,"name":"注册会计师","children":[]},{"id":5,"name":"管理会计师","children":[]},{"id":6,"name":"税务师","children":[]},{"id":7,"name":"实操VIP会员","children":[]}]
     */

    private int id;
    private String name;
    private List<ExamInfo> children;

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

    public List<ExamInfo> getChildren() {
        return children;
    }

    public void setChildren(List<ExamInfo> children) {
        this.children = children;
    }


}
