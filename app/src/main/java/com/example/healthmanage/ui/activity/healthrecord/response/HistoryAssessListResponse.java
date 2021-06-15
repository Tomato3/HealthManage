package com.example.healthmanage.ui.activity.healthrecord.response;

import java.util.List;

public class HistoryAssessListResponse {

    /**
     * requestId : null
     * errorLog : null
     * status : 0
     * message : 查询成功
     * data : [{"id":1,"userId":19,"healthAssessId":1,"name":"焦虐自评量表[SAS]","testResult":"无焦虐倾向","content":"继续保持心情放松","createTime":1616724197000}]
     */

    private Object requestId;
    private Object errorLog;
    private int status;
    private String message;
    /**
     * id : 1
     * userId : 19
     * healthAssessId : 1
     * name : 焦虐自评量表[SAS]
     * testResult : 无焦虐倾向
     * content : 继续保持心情放松
     * createTime : 1616724197000
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
        private int healthAssessId;
        private String name;
        private String testResult;
        private String content;
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

        public int getHealthAssessId() {
            return healthAssessId;
        }

        public void setHealthAssessId(int healthAssessId) {
            this.healthAssessId = healthAssessId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTestResult() {
            return testResult;
        }

        public void setTestResult(String testResult) {
            this.testResult = testResult;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }
    }
}
