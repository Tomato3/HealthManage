package com.example.healthmanage.ui.activity.personalRequest.response;

public class ReplyResponse {

    /**
     * requestId : null
     * errorLog : null
     * status : 0
     * message : 成功
     * data : {"id":2,"systemUserId":78,"type":"生活","content":"这里是请求内容","status":"1","createTime":"2021-06-08 16:01:21","replyTime":"2021-06-08 16:02:58","phone":"15738276815","replyContent":"预留字段（回复内容）"}
     */

    private Object requestId;
    private Object errorLog;
    private int status;
    private String message;
    /**
     * id : 2
     * systemUserId : 78
     * type : 生活
     * content : 这里是请求内容
     * status : 1
     * createTime : 2021-06-08 16:01:21
     * replyTime : 2021-06-08 16:02:58
     * phone : 15738276815
     * replyContent : 预留字段（回复内容）
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
        private int id;
        private int systemUserId;
        private String type;
        private String content;
        private String status;
        private String createTime;
        private String replyTime;
        private String phone;
        private String replyContent;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getSystemUserId() {
            return systemUserId;
        }

        public void setSystemUserId(int systemUserId) {
            this.systemUserId = systemUserId;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getReplyTime() {
            return replyTime;
        }

        public void setReplyTime(String replyTime) {
            this.replyTime = replyTime;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getReplyContent() {
            return replyContent;
        }

        public void setReplyContent(String replyContent) {
            this.replyContent = replyContent;
        }
    }
}
