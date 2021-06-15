package com.example.healthmanage.ui.activity.workplan.response;

import java.util.List;

public class WorkPlanListResponse {


    /**
     * requestId : null
     * errorLog : null
     * status : 0
     * message : 成功
     * data : [{"id":1,"workText":"今天的工作计划1","imgUrl":"http://localhost:8080/picture/06cbd500cac1ebad543c3661cfb34771.JPG","status":"已完成","createTime":1617174627000,"updateTime":1617685951000,"title":null,"userId":null,"doctorId":19},{"id":2,"workText":"今天的工作计划2","imgUrl":"http://localhost:8080/picture/087f6318b172219d048e947efd1cbbf1.JPG","status":"待完成","createTime":1617174627000,"updateTime":null,"title":null,"userId":null,"doctorId":19},{"id":16,"workText":"今天的工作计划3","imgUrl":"http://localhost:8080/picture/087f6318b172219d048e947efd1cbbf1.JPG","status":"待完成","createTime":1617174627000,"updateTime":null,"title":null,"userId":null,"doctorId":19},{"id":17,"workText":"今天的工作计划4","imgUrl":"http://localhost:8080/picture/087f6318b172219d048e947efd1cbbf1.JPG","status":"待完成","createTime":1617174627000,"updateTime":null,"title":null,"userId":null,"doctorId":19},{"id":18,"workText":"今天的工作计划5","imgUrl":"http://localhost:8080/picture/087f6318b172219d048e947efd1cbbf1.JPG","status":"待完成","createTime":1617174627000,"updateTime":null,"title":null,"userId":null,"doctorId":19},{"id":19,"workText":"今天的工作计划","imgUrl":"http://localhost:8080/picture/087f6318b172219d048e947efd1cbbf1.JPG","status":"待完成","createTime":1617174627000,"updateTime":null,"title":null,"userId":null,"doctorId":19},{"id":20,"workText":"今天的工作计划","imgUrl":"http://localhost:8080/picture/087f6318b172219d048e947efd1cbbf1.JPG","status":"待完成","createTime":1617174627000,"updateTime":null,"title":null,"userId":null,"doctorId":19},{"id":21,"workText":"今天的工作计划","imgUrl":"http://localhost:8080/picture/087f6318b172219d048e947efd1cbbf1.JPG","status":"待完成","createTime":1617174627000,"updateTime":null,"title":null,"userId":null,"doctorId":19},{"id":22,"workText":"今天的工作计划","imgUrl":"http://localhost:8080/picture/087f6318b172219d048e947efd1cbbf1.JPG","status":"待完成","createTime":1617174627000,"updateTime":null,"title":null,"userId":null,"doctorId":19}]
     */

    private Object requestId;
    private Object errorLog;
    private int status;
    private String message;
    /**
     * id : 1
     * workText : 今天的工作计划1
     * imgUrl : http://localhost:8080/picture/06cbd500cac1ebad543c3661cfb34771.JPG
     * status : 已完成
     * createTime : 1617174627000
     * updateTime : 1617685951000
     * title : null
     * userId : null
     * doctorId : 19
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
        private String workText;
        private String imgUrl;
        private String status;
        private long createTime;
        private long updateTime;
        private Object title;
        private Object userId;
        private int doctorId;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getWorkText() {
            return workText;
        }

        public void setWorkText(String workText) {
            this.workText = workText;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public Object getTitle() {
            return title;
        }

        public void setTitle(Object title) {
            this.title = title;
        }

        public Object getUserId() {
            return userId;
        }

        public void setUserId(Object userId) {
            this.userId = userId;
        }

        public int getDoctorId() {
            return doctorId;
        }

        public void setDoctorId(int doctorId) {
            this.doctorId = doctorId;
        }
    }
}
