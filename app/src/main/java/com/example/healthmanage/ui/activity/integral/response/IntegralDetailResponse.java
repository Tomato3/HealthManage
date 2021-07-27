package com.example.healthmanage.ui.activity.integral.response;

import java.util.List;

/**
 * desc:积分明细response
 * date:2021/7/16 17:13
 * author:bWang
 */
public class IntegralDetailResponse {

    /**
     * requestId : null
     * errorLog : null
     * status : 0
     * message : 成功
     * data : [{"id":47,"userId":78,"changeReason":"完成资格认证","type":0,"value":10,"createTime":"2021-07-16 10:05:40","delFlag":0},{"id":42,"userId":78,"changeReason":"登录APP","type":0,"value":10,"createTime":"2021-07-16 09:25:18","delFlag":0},{"id":38,"userId":78,"changeReason":"登录APP","type":0,"value":10,"createTime":"2021-07-15 09:59:56","delFlag":0},{"id":35,"userId":78,"changeReason":"登录APP","type":0,"value":10,"createTime":"2021-07-14 16:11:47","delFlag":0}]
     */

    private Object requestId;
    private Object errorLog;
    private int status;
    private String message;
    /**
     * id : 47
     * userId : 78
     * changeReason : 完成资格认证
     * type : 0
     * value : 10
     * createTime : 2021-07-16 10:05:40
     * delFlag : 0
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
        private String changeReason;
        private int type;
        private int value;
        private String createTime;
        private int delFlag;

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

        public String getChangeReason() {
            return changeReason;
        }

        public void setChangeReason(String changeReason) {
            this.changeReason = changeReason;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(int delFlag) {
            this.delFlag = delFlag;
        }
    }
}
