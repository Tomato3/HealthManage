package com.example.healthmanage.bean.network.response;

import java.util.List;

public class ConsultationRecordResponse {

    /**
     * requestId : null
     * errorLog : null
     * status : 0
     * message : 查询成功
     * data : [{"id":2,"questionId":13,"content":null,"fromUid":null,"createTime":1599191871000,"answerStatus":0,"fromName":null},{"id":1,"questionId":13,"content":null,"fromUid":null,"createTime":1599202658000,"answerStatus":1,"fromName":null},{"id":5,"questionId":13,"content":"吃了米饭","fromUid":1,"createTime":1599207657000,"answerStatus":0,"fromName":null},{"id":6,"questionId":13,"content":"吃了米饭","fromUid":1,"createTime":1599208287000,"answerStatus":0,"fromName":null},{"id":7,"questionId":13,"content":"吃了面条","fromUid":92,"createTime":1599208763000,"answerStatus":1,"fromName":null}]
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
         * id : 2
         * questionId : 13
         * content : null
         * fromUid : null
         * createTime : 1599191871000
         * answerStatus : 0
         * fromName : null
         */

        private int id;
        private int questionId;
        private Object content;
        private Object fromUid;
        private long createTime;
        private int answerStatus;
        private Object fromName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getQuestionId() {
            return questionId;
        }

        public void setQuestionId(int questionId) {
            this.questionId = questionId;
        }

        public Object getContent() {
            return content;
        }

        public void setContent(Object content) {
            this.content = content;
        }

        public Object getFromUid() {
            return fromUid;
        }

        public void setFromUid(Object fromUid) {
            this.fromUid = fromUid;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getAnswerStatus() {
            return answerStatus;
        }

        public void setAnswerStatus(int answerStatus) {
            this.answerStatus = answerStatus;
        }

        public Object getFromName() {
            return fromName;
        }

        public void setFromName(Object fromName) {
            this.fromName = fromName;
        }
    }
}
