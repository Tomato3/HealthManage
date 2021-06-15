package com.example.healthmanage.ui.fragment.qualification.bean;

import java.util.List;

public class DoctorInfo {

    /**
     * name : 小马
     * idCard : 411522199602246647
     * roleId : 9
     * departmentId : 1
     * addr : 健康管理师
     * rank : 主任
     * hospitalId : 1
     * avatar : https://image.baidu.com/search/detail?ct=503316480&z=undefined&tn=baiduimagedetail&ipn=d
     * experience : 个人简介
     * speciality : 专业擅长
     * frontIdCardUrl : 身份证国徽面url
     * backIdCardUrl : 身份证人面url
     * phone : 13584869132
     * urlList : ["url 00001","url 00002"]
     */
    private String name;
    private String idCard;
    //职业id
    private int roleId;
    private int departmentId;
    private String addr;
    private String rank;
    private int hospitalId;
    private String avatar;
    private String experience;
    private String speciality;
    private String frontIdCardUrl;
    private String backIdCardUrl;
    private String phone;
    private List<String> urlList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public int getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(int hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getFrontIdCardUrl() {
        return frontIdCardUrl;
    }

    public void setFrontIdCardUrl(String frontIdCardUrl) {
        this.frontIdCardUrl = frontIdCardUrl;
    }

    public String getBackIdCardUrl() {
        return backIdCardUrl;
    }

    public void setBackIdCardUrl(String backIdCardUrl) {
        this.backIdCardUrl = backIdCardUrl;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<String> getUrlList() {
        return urlList;
    }

    public void setUrlList(List<String> urlList) {
        this.urlList = urlList;
    }

    @Override
    public String toString() {
        return "DoctorInfo{" +
                "name='" + name + '\'' +
                ", idCard='" + idCard + '\'' +
                ", roleId=" + roleId +
                ", departmentId=" + departmentId +
                ", addr='" + addr + '\'' +
                ", rank='" + rank + '\'' +
                ", hospitalId=" + hospitalId +
                ", avatar='" + avatar + '\'' +
                ", experience='" + experience + '\'' +
                ", speciality='" + speciality + '\'' +
                ", frontIdCardUrl='" + frontIdCardUrl + '\'' +
                ", backIdCardUrl='" + backIdCardUrl + '\'' +
                ", phone='" + phone + '\'' +
                ", urlList=" + urlList +
                '}';
    }
}
