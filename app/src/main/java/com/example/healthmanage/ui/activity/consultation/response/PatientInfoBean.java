package com.example.healthmanage.ui.activity.consultation.response;

import java.util.List;

public class PatientInfoBean {

    /**
     * briefHistory : 简要病史
     * createUserId : 创建人ID
     * examineId : 会诊人员ID
     * patientInitialDecide : 患者初步判断
     * examineGoalRequest : 会诊目的和要求
     * startTime : 2020-1-1 00:00:00
     * endTime : 2020-1-1 00:00:00
     * token :
     * appPatientPictures : ["图片地址","图片地址","图片地址","图片地址","图片地址","图片地址","图片地址","图片地址","图片地址"]
     */

    private String briefHistory;
    private String createUserId;
    private String examineId;
    private String patientInitialDecide;
    private String examineGoalRequest;
    private String startTime;
    private String endTime;
    private String token;
    private List<String> appPatientPictures;

    public String getBriefHistory() {
        return briefHistory;
    }

    public void setBriefHistory(String briefHistory) {
        this.briefHistory = briefHistory;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getExamineId() {
        return examineId;
    }

    public void setExamineId(String examineId) {
        this.examineId = examineId;
    }

    public String getPatientInitialDecide() {
        return patientInitialDecide;
    }

    public void setPatientInitialDecide(String patientInitialDecide) {
        this.patientInitialDecide = patientInitialDecide;
    }

    public String getExamineGoalRequest() {
        return examineGoalRequest;
    }

    public void setExamineGoalRequest(String examineGoalRequest) {
        this.examineGoalRequest = examineGoalRequest;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<String> getAppPatientPictures() {
        return appPatientPictures;
    }

    public void setAppPatientPictures(List<String> appPatientPictures) {
        this.appPatientPictures = appPatientPictures;
    }
}
