package com.example.healthmanage.ui.activity.academicJournals.response;

import java.util.List;

public class PeriodicalListResponse {

    /**
     * requestId : null
     * errorLog : null
     * status : 0
     * message : 成功
     * data : [{"id":4,"systemUserId":78,"periodical":"投稿期刊","deliveryColumn":"投稿栏目","personalProfile":"个人简介","title":"标题","content":"富文本内容","status":1,"explains":"","deliveryTime":"2021-06-10 16:16:26","auditTime":null,"createTime":"2021-06-10 16:15:46"},{"id":3,"systemUserId":78,"periodical":"投稿期刊","deliveryColumn":"投稿栏目","personalProfile":"个人简介","title":"标题","content":"富文本内容","status":1,"explains":"","deliveryTime":null,"auditTime":null,"createTime":"2021-06-10 16:15:24"}]
     */

    private Object requestId;
    private Object errorLog;
    private int status;
    private String message;
    /**
     * id : 4
     * systemUserId : 78
     * periodical : 投稿期刊
     * deliveryColumn : 投稿栏目
     * personalProfile : 个人简介
     * title : 标题
     * content : 富文本内容
     * status : 1
     * explains :
     * deliveryTime : 2021-06-10 16:16:26
     * auditTime : null
     * createTime : 2021-06-10 16:15:46
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
        private int systemUserId;
        private String periodical;
        private String deliveryColumn;
        private String personalProfile;
        private String title;
        private String content;
        private int status;
        private String explains;
        private String deliveryTime;
        private Object auditTime;
        private String createTime;

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

        public String getPeriodical() {
            return periodical;
        }

        public void setPeriodical(String periodical) {
            this.periodical = periodical;
        }

        public String getDeliveryColumn() {
            return deliveryColumn;
        }

        public void setDeliveryColumn(String deliveryColumn) {
            this.deliveryColumn = deliveryColumn;
        }

        public String getPersonalProfile() {
            return personalProfile;
        }

        public void setPersonalProfile(String personalProfile) {
            this.personalProfile = personalProfile;
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

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getExplains() {
            return explains;
        }

        public void setExplains(String explains) {
            this.explains = explains;
        }

        public String getDeliveryTime() {
            return deliveryTime;
        }

        public void setDeliveryTime(String deliveryTime) {
            this.deliveryTime = deliveryTime;
        }

        public Object getAuditTime() {
            return auditTime;
        }

        public void setAuditTime(Object auditTime) {
            this.auditTime = auditTime;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }
    }
}
