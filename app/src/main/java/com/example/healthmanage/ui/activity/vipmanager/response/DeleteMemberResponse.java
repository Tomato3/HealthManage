package com.example.healthmanage.ui.activity.vipmanager.response;

/**
 * desc:
 * date:2021/6/22 15:50
 * author:bWang
 */
public class DeleteMemberResponse {

    /**
     * requestId : null
     * errorLog : null
     * status : 1
     * message : 失败
     * data : null
     */

    private Object requestId;
    private Object errorLog;
    private int status;
    private String message;
    private Object data;

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
