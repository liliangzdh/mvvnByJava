package com.kaoyaya.tongkai.entity;

public class UserInfo {
    /**
     * uid : 1123761
     * username : 13714996607
     * name :
     * nickname : 正式
     * avatar : http://thirdwx.qlogo.cn/mmopen/kNiakvQjaCpn22ibRVb7lPzqiafJvaOy5nibu5HpvYo0tmSSESnXC2K1d9Q8NjfBEsBLiajPtIm8ZjplVvttlmvT9we7C61j4Dn47/132
     * sex : 0
     * qq :
     * mobile : 13714996607
     * email :
     * idCard :
     * regID : 0
     * currentExamType : 2
     * status : true
     */

    private int uid;
    private String username;
    private String name;
    private String nickname;
    private String avatar;
    private int sex;
    private String qq;
    private String mobile;
    private String email;
    private String idCard;
    private boolean status;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
