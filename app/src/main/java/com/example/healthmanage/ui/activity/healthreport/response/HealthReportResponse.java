package com.example.healthmanage.ui.activity.healthreport.response;

import java.util.List;

public class HealthReportResponse {

    /**
     * requestId : null
     * errorLog : null
     * status : 0
     * message : 查询成功
     * data : [{"id":1,"userId":92,"name":"这是一个健康报告","bloodPressure":"血压","bloodSugar":"血糖","bloodOxygen":"血氧","temperature":"体温","sport":"运动","sleep":"睡眠","startTime":"2021-03-27","endTime":"2021-03-29","status":1,"createTime":1616998807000}]
     */

    private Object requestId;
    private Object errorLog;
    private int status;
    private String message;
    /**
     * id : 1
     * userId : 92
     * name : 这是一个健康报告
     * bloodPressure : 血压
     * bloodSugar : 血糖
     * bloodOxygen : 血氧
     * temperature : 体温
     * sport : 运动
     * sleep : 睡眠
     * startTime : 2021-03-27
     * endTime : 2021-03-29
     * status : 1
     * createTime : 1616998807000
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

    public static class DataBean {
        private int id;
        private int userId;
        private String name;
        private String bloodPressure;
        private String bloodSugar;
        private String bloodOxygen;
        private String temperature;
        private String sport;
        private String sleep;
        private String startTime;
        private String endTime;
        private int status;
        private long createTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getBloodPressure() {
            return bloodPressure;
        }

        public void setBloodPressure(String bloodPressure) {
            this.bloodPressure = bloodPressure;
        }

        public String getBloodSugar() {
            return bloodSugar;
        }

        public void setBloodSugar(String bloodSugar) {
            this.bloodSugar = bloodSugar;
        }

        public String getBloodOxygen() {
            return bloodOxygen;
        }

        public void setBloodOxygen(String bloodOxygen) {
            this.bloodOxygen = bloodOxygen;
        }

        public String getTemperature() {
            return temperature;
        }

        public void setTemperature(String temperature) {
            this.temperature = temperature;
        }

        public String getSport() {
            return sport;
        }

        public void setSport(String sport) {
            this.sport = sport;
        }

        public String getSleep() {
            return sleep;
        }

        public void setSleep(String sleep) {
            this.sleep = sleep;
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

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }
    }
}
