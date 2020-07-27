package com.example.healthmanage.bean;

public class TaskDetailResponse {

    /**
     * requestId : null
     * errorLog : null
     * status : 0
     * message : 查询成功
     * data : {"id":7,"userId":69,"title":"gg思密达","content":"呼呼呼","managerId":19,"doctorId":null,"doctorReply":null,"status":1,"createTime":"2020-07-14 11:21:15"}
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
         * id : 7
         * userId : 69
         * title : gg思密达
         * content : 呼呼呼
         * managerId : 19
         * doctorId : null
         * doctorReply : null
         * status : 1
         * createTime : 2020-07-14 11:21:15
         */

        private int id;
        private int userId;
        private String title;
        private String content;
        private int managerId;
        private Object doctorId;
        private Object doctorReply;
        private int status;
        private String createTime;

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

        public Object getDoctorId() {
            return doctorId;
        }

        public void setDoctorId(Object doctorId) {
            this.doctorId = doctorId;
        }

        public Object getDoctorReply() {
            return doctorReply;
        }

        public void setDoctorReply(Object doctorReply) {
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
    }
}
