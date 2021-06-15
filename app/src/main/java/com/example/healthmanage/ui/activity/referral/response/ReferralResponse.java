package com.example.healthmanage.ui.activity.referral.response;

import java.io.Serializable;
import java.util.List;

public class ReferralResponse {


    /**
     * requestId : null
     * errorLog : null
     * status : 0
     * message : 成功
     * data : [{"id":94,"patientIllnessDescribe":"患者病情描述","referralTargetId":null,"referralTime":null,"patientInitialDecide":"患者初步判断","referralReasons":"转诊原因说明","applyOutHospital":"转出医院","applyInHospital":"转入医院","choiseReferral":"选择转诊","status":1,"referralPatientId":"24","doctorId":19,"createTime":"2021-01-23 08:12:02.0","referralePatientName":"test | 0","token":null,"appPatientPictures":null,"appNinePictures":[{"id":535,"patientReferralId":94,"patientReferralPic":"图片地址"},{"id":536,"patientReferralId":94,"patientReferralPic":"图片地址"},{"id":537,"patientReferralId":94,"patientReferralPic":"图片地址"},{"id":538,"patientReferralId":94,"patientReferralPic":"图片地址"},{"id":539,"patientReferralId":94,"patientReferralPic":"图片地址"},{"id":540,"patientReferralId":94,"patientReferralPic":"图片地址"},{"id":541,"patientReferralId":94,"patientReferralPic":"图片地址"},{"id":542,"patientReferralId":94,"patientReferralPic":"图片地址"},{"id":543,"patientReferralId":94,"patientReferralPic":"图片地址"}]}]
     */

    private Object requestId;
    private Object errorLog;
    private int status;
    private String message;
    /**
     * id : 94
     * patientIllnessDescribe : 患者病情描述
     * referralTargetId : null
     * referralTime : null
     * patientInitialDecide : 患者初步判断
     * referralReasons : 转诊原因说明
     * applyOutHospital : 转出医院
     * applyInHospital : 转入医院
     * choiseReferral : 选择转诊
     * status : 1
     * referralPatientId : 24
     * doctorId : 19
     * createTime : 2021-01-23 08:12:02.0
     * referralePatientName : test | 0
     * token : null
     * appPatientPictures : null
     * appNinePictures : [{"id":535,"patientReferralId":94,"patientReferralPic":"图片地址"},{"id":536,"patientReferralId":94,"patientReferralPic":"图片地址"},{"id":537,"patientReferralId":94,"patientReferralPic":"图片地址"},{"id":538,"patientReferralId":94,"patientReferralPic":"图片地址"},{"id":539,"patientReferralId":94,"patientReferralPic":"图片地址"},{"id":540,"patientReferralId":94,"patientReferralPic":"图片地址"},{"id":541,"patientReferralId":94,"patientReferralPic":"图片地址"},{"id":542,"patientReferralId":94,"patientReferralPic":"图片地址"},{"id":543,"patientReferralId":94,"patientReferralPic":"图片地址"}]
     */

    private List<DataBean> data;

    public Object getRequestId() {
        return requestId;
    }

    public void setRequestId(Object requestId) {
        this.requestId = requestId;
    }

    public Object getErrorLog() {
        return errorLog;
    }

    public void setErrorLog(Object errorLog) {
        this.errorLog = errorLog;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        private int id;
        private String patientIllnessDescribe;
        private Object referralTargetId;
        private String referralTime;
        private String patientInitialDecide;
        private String referralReasons;
        private String applyOutHospital;
        private String applyInHospital;
        private String choiseReferral;
        private int status;
        private String referralPatientId;
        private int doctorId;
        private String createTime;
        private String referralPatientName;
        private Object token;
        private Object appPatientPictures;
        /**
         * id : 535
         * patientReferralId : 94
         * patientReferralPic : 图片地址
         */

        private List<AppNinePicturesBean> appNinePictures;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPatientIllnessDescribe() {
            return patientIllnessDescribe;
        }

        public void setPatientIllnessDescribe(String patientIllnessDescribe) {
            this.patientIllnessDescribe = patientIllnessDescribe;
        }

        public Object getReferralTargetId() {
            return referralTargetId;
        }

        public void setReferralTargetId(Object referralTargetId) {
            this.referralTargetId = referralTargetId;
        }

        public String getReferralTime() {
            return referralTime;
        }

        public void setReferralTime(String referralTime) {
            this.referralTime = referralTime;
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

        public String getChoiseReferral() {
            return choiseReferral;
        }

        public void setChoiseReferral(String choiseReferral) {
            this.choiseReferral = choiseReferral;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getReferralPatientId() {
            return referralPatientId;
        }

        public void setReferralPatientId(String referralPatientId) {
            this.referralPatientId = referralPatientId;
        }

        public int getDoctorId() {
            return doctorId;
        }

        public void setDoctorId(int doctorId) {
            this.doctorId = doctorId;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getReferralPatientName() {
            return referralPatientName;
        }

        public void setReferralPatientName(String referralPatientName) {
            this.referralPatientName = referralPatientName;
        }

        public Object getToken() {
            return token;
        }

        public void setToken(Object token) {
            this.token = token;
        }

        public Object getAppPatientPictures() {
            return appPatientPictures;
        }

        public void setAppPatientPictures(Object appPatientPictures) {
            this.appPatientPictures = appPatientPictures;
        }

        public List<AppNinePicturesBean> getAppNinePictures() {
            return appNinePictures;
        }

        public void setAppNinePictures(List<AppNinePicturesBean> appNinePictures) {
            this.appNinePictures = appNinePictures;
        }

        public static class AppNinePicturesBean implements Serializable{
            private int id;
            private int patientReferralId;
            private String patientReferralPic;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getPatientReferralId() {
                return patientReferralId;
            }

            public void setPatientReferralId(int patientReferralId) {
                this.patientReferralId = patientReferralId;
            }

            public String getPatientReferralPic() {
                return patientReferralPic;
            }

            public void setPatientReferralPic(String patientReferralPic) {
                this.patientReferralPic = patientReferralPic;
            }
        }
    }
}
