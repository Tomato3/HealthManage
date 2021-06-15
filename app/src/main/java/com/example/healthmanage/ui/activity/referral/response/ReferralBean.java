package com.example.healthmanage.ui.activity.referral.response;

import java.util.List;

public class ReferralBean {

    /**
     * patientIllnessDescribe : 患者病情描述
     * token : 19
     * choiseReferral : 选择转诊
     * patientInitialDecide : 患者初步判断
     * referralReasons : 转诊原因说明
     * applyOutHospital : 转出医院
     * applyInHospital : 转入医院
     * referralePatientId : 转出患者
     * appPatientPictures : ["图片地址","图片地址","图片地址","图片地址","图片地址","图片地址","图片地址","图片地址","图片地址"]
     */

    private String patientIllnessDescribe;
    private String token;
    private String choiseReferral;
    private String patientInitialDecide;
    private String referralReasons;
    private String applyOutHospital;
    private String applyInHospital;
    private String referralPatientId;
    private String createTime;
    private List<String> appPatientPictures;

    public String getPatientIllnessDescribe() {
        return patientIllnessDescribe;
    }

    public void setPatientIllnessDescribe(String patientIllnessDescribe) {
        this.patientIllnessDescribe = patientIllnessDescribe;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getChoiseReferral() {
        return choiseReferral;
    }

    public void setChoiseReferral(String choiseReferral) {
        this.choiseReferral = choiseReferral;
    }

    public String getPatientInitialDecide() {
        return patientInitialDecide;
    }

    public void setPatientInitialDecide(String patientInitialDecide) {
        this.patientInitialDecide = patientInitialDecide;
    }

    public String getReferralReasons() {
        return referralReasons;
    }

    public void setReferralReasons(String referralReasons) {
        this.referralReasons = referralReasons;
    }

    public String getApplyOutHospital() {
        return applyOutHospital;
    }

    public void setApplyOutHospital(String applyOutHospital) {
        this.applyOutHospital = applyOutHospital;
    }

    public String getApplyInHospital() {
        return applyInHospital;
    }

    public void setApplyInHospital(String applyInHospital) {
        this.applyInHospital = applyInHospital;
    }

    public String getReferralPatientId() {
        return referralPatientId;
    }

    public void setReferralPatientId(String referralPatientId) {
        this.referralPatientId = referralPatientId;
    }

    public List<String> getAppPatientPictures() {
        return appPatientPictures;
    }

    public void setAppPatientPictures(List<String> appPatientPictures) {
        this.appPatientPictures = appPatientPictures;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
