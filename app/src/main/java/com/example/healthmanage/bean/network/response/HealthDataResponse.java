package com.example.healthmanage.bean.network.response;

import java.util.List;

public class HealthDataResponse {


    /**
     * requestId : null
     * errorLog : null
     * status : 200
     * message : 成功
     * data : {"sleepList":null,"sportList":null,"vitalSignsList":null,"bloodSugarSendList":[{"imei":"866118040106539","bigBloodSugar":"5.18","smallBloodSugar":"5.18","superBloodSugar":0,"bigBloodHighPressure":"117","smallBloodHighPressure":"117","superBloodHighPressure":0,"bigBloodLowPressure":"67","smallBloodLowPressure":"67","superBloodLowPressure":0,"heartRate":74,"bigHeartRate":"74","smallHeartRate":"74","superHeartRate":0,"createTime":"2020-07-25 09:30:59"}]}
     */

    private Object requestId;
    private Object errorLog;
    private int status;
    private String message;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * sleepList : null
         * sportList : null
         * vitalSignsList : null
         * bloodSugarSendList : [{"imei":"866118040106539","bigBloodSugar":"5.18","smallBloodSugar":"5.18","superBloodSugar":0,"bigBloodHighPressure":"117","smallBloodHighPressure":"117","superBloodHighPressure":0,"bigBloodLowPressure":"67","smallBloodLowPressure":"67","superBloodLowPressure":0,"heartRate":74,"bigHeartRate":"74","smallHeartRate":"74","superHeartRate":0,"createTime":"2020-07-25 09:30:59"}]
         */

        private Object sleepList;
        private Object sportList;
        private Object vitalSignsList;
        private List<BloodSugarSendListBean> bloodSugarSendList;

        public Object getSleepList() {
            return sleepList;
        }

        public void setSleepList(Object sleepList) {
            this.sleepList = sleepList;
        }

        public Object getSportList() {
            return sportList;
        }

        public void setSportList(Object sportList) {
            this.sportList = sportList;
        }

        public Object getVitalSignsList() {
            return vitalSignsList;
        }

        public void setVitalSignsList(Object vitalSignsList) {
            this.vitalSignsList = vitalSignsList;
        }

        public List<BloodSugarSendListBean> getBloodSugarSendList() {
            return bloodSugarSendList;
        }

        public void setBloodSugarSendList(List<BloodSugarSendListBean> bloodSugarSendList) {
            this.bloodSugarSendList = bloodSugarSendList;
        }

        public static class BloodSugarSendListBean {
            /**
             * imei : 866118040106539
             * bigBloodSugar : 5.18
             * smallBloodSugar : 5.18
             * superBloodSugar : 0
             * bigBloodHighPressure : 117
             * smallBloodHighPressure : 117
             * superBloodHighPressure : 0
             * bigBloodLowPressure : 67
             * smallBloodLowPressure : 67
             * superBloodLowPressure : 0
             * heartRate : 74
             * bigHeartRate : 74
             * smallHeartRate : 74
             * superHeartRate : 0
             * createTime : 2020-07-25 09:30:59
             */

            private String imei;
            private String bigBloodSugar;
            private String smallBloodSugar;
            private int superBloodSugar;
            private String bigBloodHighPressure;
            private String smallBloodHighPressure;
            private int superBloodHighPressure;
            private String bigBloodLowPressure;
            private String smallBloodLowPressure;
            private int superBloodLowPressure;
            private int heartRate;
            private String bigHeartRate;
            private String smallHeartRate;
            private int superHeartRate;
            private String createTime;

            public String getImei() {
                return imei;
            }

            public void setImei(String imei) {
                this.imei = imei;
            }

            public String getBigBloodSugar() {
                return bigBloodSugar;
            }

            public void setBigBloodSugar(String bigBloodSugar) {
                this.bigBloodSugar = bigBloodSugar;
            }

            public String getSmallBloodSugar() {
                return smallBloodSugar;
            }

            public void setSmallBloodSugar(String smallBloodSugar) {
                this.smallBloodSugar = smallBloodSugar;
            }

            public int getSuperBloodSugar() {
                return superBloodSugar;
            }

            public void setSuperBloodSugar(int superBloodSugar) {
                this.superBloodSugar = superBloodSugar;
            }

            public String getBigBloodHighPressure() {
                return bigBloodHighPressure;
            }

            public void setBigBloodHighPressure(String bigBloodHighPressure) {
                this.bigBloodHighPressure = bigBloodHighPressure;
            }

            public String getSmallBloodHighPressure() {
                return smallBloodHighPressure;
            }

            public void setSmallBloodHighPressure(String smallBloodHighPressure) {
                this.smallBloodHighPressure = smallBloodHighPressure;
            }

            public int getSuperBloodHighPressure() {
                return superBloodHighPressure;
            }

            public void setSuperBloodHighPressure(int superBloodHighPressure) {
                this.superBloodHighPressure = superBloodHighPressure;
            }

            public String getBigBloodLowPressure() {
                return bigBloodLowPressure;
            }

            public void setBigBloodLowPressure(String bigBloodLowPressure) {
                this.bigBloodLowPressure = bigBloodLowPressure;
            }

            public String getSmallBloodLowPressure() {
                return smallBloodLowPressure;
            }

            public void setSmallBloodLowPressure(String smallBloodLowPressure) {
                this.smallBloodLowPressure = smallBloodLowPressure;
            }

            public int getSuperBloodLowPressure() {
                return superBloodLowPressure;
            }

            public void setSuperBloodLowPressure(int superBloodLowPressure) {
                this.superBloodLowPressure = superBloodLowPressure;
            }

            public int getHeartRate() {
                return heartRate;
            }

            public void setHeartRate(int heartRate) {
                this.heartRate = heartRate;
            }

            public String getBigHeartRate() {
                return bigHeartRate;
            }

            public void setBigHeartRate(String bigHeartRate) {
                this.bigHeartRate = bigHeartRate;
            }

            public String getSmallHeartRate() {
                return smallHeartRate;
            }

            public void setSmallHeartRate(String smallHeartRate) {
                this.smallHeartRate = smallHeartRate;
            }

            public int getSuperHeartRate() {
                return superHeartRate;
            }

            public void setSuperHeartRate(int superHeartRate) {
                this.superHeartRate = superHeartRate;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }
        }
    }
}
