package com.kaoyaya.tongkai.entity;


import java.util.List;

// 首页分发资源
public class HomeResourseDistribute {


    /**
     * id : 1
     * name : Banner
     * resourceNum : 3
     * resource : [{"id":342,"distributeID":1,"name":"初级高效取证班","pictureURL":"https://img.kaoyaya.com/images%2F2019%2F10%2F41QiDV0GY54CFuyv58i7sE0u31DRK4vX_%E5%88%9D%E7%BA%A7%E9%AB%98%E6%95%88%E5%8F%96%E8%AF%81%E7%8F%AD%E6%89%8B%E6%9C%BAbanner.png","resourceType":"custom","resourceID":0,"resourceName":"","resourceURL":"http://www.kaoyaya.com/knew/home/agent/r/id/931?qudao=368539","resource":{"title":"","subTitle":"","picture":"","peopleNum":0,"teacherUserID":0,"teacherName":""},"remark":""},{"id":308,"distributeID":1,"name":"初级题库","pictureURL":"https://img.kaoyaya.com/images%2F2019%2F10%2FihaSHa9u5Q8NxlcsDpBUJLfhP14Whdxw_%E5%88%9D%E7%BA%A7VIP%E6%99%BA%E8%83%BD%E9%A2%98%E5%BA%93%E6%89%8B%E6%9C%BA_%E6%97%A5%E5%B8%B8.png","resourceType":"custom","resourceID":0,"resourceName":"","resourceURL":"http://www.kaoyaya.com/knew/home/agent/r/id/316?qudao=368539","resource":{"title":"","subTitle":"","picture":"","peopleNum":0,"teacherUserID":0,"teacherName":""},"remark":""},{"id":295,"distributeID":1,"name":"管会报名倒计时","pictureURL":"https://img.kaoyaya.com/images%2F2019%2F10%2F60VUxHpCS3ZcTwTzM7eBYlkrv4prKCir_%E7%AE%A1%E7%90%86%E4%BC%9A%E8%AE%A1%E7%94%B5%E8%84%91banner+%E6%8B%B7%E8%B4%9D.png","resourceType":"custom","resourceID":0,"resourceName":"","resourceURL":"http://kyy1.cn/iQBJbBI","resource":{"title":"","subTitle":"","picture":"","peopleNum":0,"teacherUserID":0,"teacherName":""},"remark":""}]
     */

    private int id;
    private String name;
    private int resourceNum;
    private List<HomeResource> resource;

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

    public int getResourceNum() {
        return resourceNum;
    }

    public void setResourceNum(int resourceNum) {
        this.resourceNum = resourceNum;
    }

    public List<HomeResource> getResource() {
        return resource;
    }

    public void setResource(List<HomeResource> resource) {
        this.resource = resource;
    }

}
