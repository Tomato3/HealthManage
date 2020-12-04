package com.example.healthmanage.bean.network.response;

public class TaskDetailResponse {


    /**
     * requestId : null
     * errorLog : null
     * status : 0
     * message : 查询成功
     * data : {"id":18,"userId":86,"title":"卡莉斯塔","content":"这是管理师填写内容","managerId":19,"doctorId":20,"doctorReply":"add","status":1,"createTime":"2020-07-31 15:49:58","doctorCreateTime":"2020-08-03 17:31:35","healthCreateTime":null,"mangerName":"于健康管理师","doctorName":"测试医生"}
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
         * id : 18
         * userId : 86
         * title : 卡莉斯塔
         * content : 这是管理师填写内容
         * managerId : 19
         * doctorId : 20
         * doctorReply : add
         * status : 1
         * createTime : 2020-07-31 15:49:58
         * doctorCreateTime : 2020-08-03 17:31:35
         * healthCreateTime : null
         * mangerName : 于健康管理师
         * doctorName : 测试医生
         */

        private int id;
        private int userId;
        private String title;
        private String content;
        private int managerId;
        private int doctorId;
        private String doctorReply;
        private int status;
        private String createTime;
        private String doctorCreateTime;
        private Object healthCreateTime;
        private String mangerName;
        private String doctorName;

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

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getManagerId() {
            return managerId;
        }

        public void setManagerId(int managerId) {
            this.managerId = managerId;
        }

        public int getDoctorId() {
            return doctorId;
        }

        public void setDoctorId(int doctorId) {
            this.doctorId = doctorId;
        }

        public String getDoctorReply() {
            return doctorReply;
        }

        public void setDoctorReply(String doctorReply) {
            this.doctorReply = doctorReply;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getDoctorCreateTime() {
            return doctorCreateTime;
        }

        public void setDoctorCreateTime(String doctorCreateTime) {
            this.doctorCreateTime = doctorCreateTime;
        }

        public Object getHealthCreateTime() {
            return healthCreateTime;
        }

        public void setHealthCreateTime(Object healthCreateTime) {
            this.healthCreateTime = healthCreateTime;
        }

        public String getMangerName() {
            return mangerName;
        }

        public void setMangerName(String mangerName) {
            this.mangerName = mangerName;
        }

        public String getDoctorName() {
            return doctorName;
        }

        public void setDoctorName(String doctorName) {
            this.doctorName = doctorName;
        }
    }
}
