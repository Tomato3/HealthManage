package com.example.healthmanage.bean.network.response;

/**
 * 不返回数据通用Response
 */
public class GeneralResponse {

    /**
     * requestId : null
     * errorLog : null
     * status : 9999
     * message : 密码输入错误请重新输入。
     * data : null
     */

    private Object requestId;
    private Object errorLog;
    private int status;
    private String message;
    private String data;

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
