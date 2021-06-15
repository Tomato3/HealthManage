package com.example.healthmanage.ui.activity.memberdetail.response;

public class HealthDataResponse {

    /**
     * requestId : null
     * errorLog : null
     * status : 0
     * message : 查询成功
     * data : {"saturation":97,"lowPressure":69,"heartRate":0,"distance":166.8,"PPG":29,"ECG":0,"temperature":28.6,"calorie":517,"highPressure":110,"HRV":0,"steps":54555}
     */

    private Object requestId;
    private Object errorLog;
    private int status;
    private String message;
    /**
     * saturation : 97
     * lowPressure : 69
     * heartRate : 0
     * distance : 166.8
     * PPG : 29
     * ECG : 0
     * temperature : 28.6
     * calorie : 517.0
     * highPressure : 110
     * HRV : 0
     * steps : 54555
     */

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
        /**血氧饱和度*/
        private int saturation;
        /**低压*/
        private int lowPressure;
        /**心率*/
        private int heartRate;
        /**路程*/
        private double distance;
        //创建时间
        private String createTime;
        /**血糖*/
        private int PPG;
        /**心电图*/
        private int ECG;
        /**体温*/
        private double temperature;
        /**卡路里*/
        private double calorie;
        /**高压*/
        private int highPressure;
        /**心率变异性*/
        private int HRV;
        /**步数*/
        private int steps;

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getSaturation() {
            return saturation;
        }

        public void setSaturation(int saturation) {
            this.saturation = saturation;
        }

        public int getLowPressure() {
            return lowPressure;
        }

        public void setLowPressure(int lowPressure) {
            this.lowPressure = lowPressure;
        }

        public int getHeartRate() {
            return heartRate;
        }

        public void setHeartRate(int heartRate) {
            this.heartRate = heartRate;
        }

        public double getDistance() {
            return distance;
        }

        public void setDistance(double distance) {
            this.distance = distance;
        }

        public int getPPG() {
            return PPG;
        }

        public void setPPG(int PPG) {
            this.PPG = PPG;
        }

        public int getECG() {
            return ECG;
        }

        public void setECG(int ECG) {
            this.ECG = ECG;
        }

        public double getTemperature() {
            return temperature;
        }

        public void setTemperature(double temperature) {
            this.temperature = temperature;
        }

        public double getCalorie() {
            return calorie;
        }

        public void setCalorie(double calorie) {
            this.calorie = calorie;
        }

        public int getHighPressure() {
            return highPressure;
        }

        public void setHighPressure(int highPressure) {
            this.highPressure = highPressure;
        }

        public int getHRV() {
            return HRV;
        }

        public void setHRV(int HRV) {
            this.HRV = HRV;
        }

        public int getSteps() {
            return steps;
        }

        public void setSteps(int steps) {
            this.steps = steps;
        }
    }
}
