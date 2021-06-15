package com.example.healthmanage.ui.activity.team.response;

import com.example.healthmanage.bean.network.response.MyMemberResponse;

import java.util.List;

public class TeamResponse {
    private Object requestId;
    private Object errorLog;
    private int status;
    private String message;
    private List<DataBean> data;

    public TeamResponse(List<DataBean> data) {
        this.data = data;
    }

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
        private String avar;
        private String nickName;
        private String phone;

        public DataBean(String avar, String nickName, String phone) {
            this.avar = avar;
            this.nickName = nickName;
            this.phone = phone;
        }

        public String getAvar() {
            return avar;
        }

        public String getNickName() {
            return nickName;
        }

        public String getPhone() {
            return phone;
        }
    }
}
