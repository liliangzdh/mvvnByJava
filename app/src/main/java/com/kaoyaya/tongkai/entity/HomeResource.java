package com.kaoyaya.tongkai.entity;

public class HomeResource {


    private int id;
    private int distributeID;
    private String name;
    private String pictureURL;
    private String resourceType;
    private int resourceID;
    private String resourceName;
    private String resourceURL;
    private HomeResourceChild resource;
    private String remark;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDistributeID() {
        return distributeID;
    }

    public void setDistributeID(int distributeID) {
        this.distributeID = distributeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public int getResourceID() {
        return resourceID;
    }

    public void setResourceID(int resourceID) {
        this.resourceID = resourceID;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getResourceURL() {
        return resourceURL;
    }

    public void setResourceURL(String resourceURL) {
        this.resourceURL = resourceURL;
    }

    public HomeResourceChild getResource() {
        return resource;
    }

    public void setResource(HomeResourceChild resource) {
        this.resource = resource;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
