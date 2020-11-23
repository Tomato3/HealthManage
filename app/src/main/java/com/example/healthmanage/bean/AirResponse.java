package com.example.healthmanage.bean;

import java.util.List;

public class AirResponse {

    /**
     * requestId : null
     * errorLog : null
     * status : 0
     * message : 成功
     * data : [{"id":1425184,"deviceId":"11102435CC152AD7","productId":null,"productType":null,"temperature":"21.5","humidity":"54.6","pm25":"38.0","co2":"719","hcho":"0.04","waterT":null,"jfwd":null,"negOxygenIons":"0","o2":"20.8","o3":"1.00","tvoc":"55.00","co":"0.0","pm03":"18.0","loadStatus":null,"runModel":null,"deviceType":"2","createTime":"2020-08-21 16:19:22","sceneName":"卧室"}]
     */

    private Object requestId;
    private Object errorLog;
    private int status;
    private String message;
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

    public static class DataBean {
        /**
         * id : 1425184
         * deviceId : 11102435CC152AD7
         * productId : null
         * productType : null
         * temperature : 21.5
         * humidity : 54.6
         * pm25 : 38.0
         * co2 : 719
         * hcho : 0.04
         * waterT : null
         * jfwd : null
         * negOxygenIons : 0
         * o2 : 20.8
         * o3 : 1.00
         * tvoc : 55.00
         * co : 0.0
         * pm03 : 18.0
         * loadStatus : null
         * runModel : null
         * deviceType : 2
         * createTime : 2020-08-21 16:19:22
         * sceneName : 卧室
         */

        private int id;
        private String deviceId;
        private Object productId;
        private Object productType;
        private String temperature;
        private String humidity;
        private String pm25;
        private String co2;
        private String hcho;
        private Object waterT;
        private Object jfwd;
        private String negOxygenIons;
        private String o2;
        private String o3;
        private String tvoc;
        private String co;
        private String pm03;
        private Object loadStatus;
        private Object runModel;
        private String deviceType;
        private String createTime;
        private String sceneName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }

        public Object getProductId() {
            return productId;
        }

        public void setProductId(Object productId) {
            this.productId = productId;
        }

        public Object getProductType() {
            return productType;
        }

        public void setProductType(Object productType) {
            this.productType = productType;
        }

        public String getTemperature() {
            return temperature;
        }

        public void setTemperature(String temperature) {
            this.temperature = temperature;
        }

        public String getHumidity() {
            return humidity;
        }

        public void setHumidity(String humidity) {
            this.humidity = humidity;
        }

        public String getPm25() {
            return pm25;
        }

        public void setPm25(String pm25) {
            this.pm25 = pm25;
        }

        public String getCo2() {
            return co2;
        }

        public void setCo2(String co2) {
            this.co2 = co2;
        }

        public String getHcho() {
            return hcho;
        }

        public void setHcho(String hcho) {
            this.hcho = hcho;
        }

        public Object getWaterT() {
            return waterT;
        }

        public void setWaterT(Object waterT) {
            this.waterT = waterT;
        }

        public Object getJfwd() {
            return jfwd;
        }

        public void setJfwd(Object jfwd) {
            this.jfwd = jfwd;
        }

        public String getNegOxygenIons() {
            return negOxygenIons;
        }

        public void setNegOxygenIons(String negOxygenIons) {
            this.negOxygenIons = negOxygenIons;
        }

        public String getO2() {
            return o2;
        }

        public void setO2(String o2) {
            this.o2 = o2;
        }

        public String getO3() {
            return o3;
        }

        public void setO3(String o3) {
            this.o3 = o3;
        }

        public String getTvoc() {
            return tvoc;
        }

        public void setTvoc(String tvoc) {
            this.tvoc = tvoc;
        }

        public String getCo() {
            return co;
        }

        public void setCo(String co) {
            this.co = co;
        }

        public String getPm03() {
            return pm03;
        }

        public void setPm03(String pm03) {
            this.pm03 = pm03;
        }

        public Object getLoadStatus() {
            return loadStatus;
        }

        public void setLoadStatus(Object loadStatus) {
            this.loadStatus = loadStatus;
        }

        public Object getRunModel() {
            return runModel;
        }

        public void setRunModel(Object runModel) {
            this.runModel = runModel;
        }

        public String getDeviceType() {
            return deviceType;
        }

        public void setDeviceType(String deviceType) {
            this.deviceType = deviceType;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getSceneName() {
            return sceneName;
        }

        public void setSceneName(String sceneName) {
            this.sceneName = sceneName;
        }
    }
}
