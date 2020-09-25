package com.fenixinnovation.api.ui.objects;

public class Case {
    private  String name;
    private String nickname;
    private String birthday;
    private String city;
    private String province;
    private String status;
    private  String createdAt;

    public Case() {
    }

    public Case(String name, String nickname, String birthday, String city, String province, String status, String createdAt) {
        this.name = name;
        this.nickname = nickname;
        this.birthday = birthday;
        this.city = city;
        this.province = province;
        this.status = status;
        this.createdAt = createdAt;
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

}
