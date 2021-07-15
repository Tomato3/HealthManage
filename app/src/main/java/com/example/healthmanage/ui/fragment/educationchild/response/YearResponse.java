package com.example.healthmanage.ui.fragment.educationchild.response;

import java.util.List;

/**
 * desc:
 * date:2021/7/14 10:19
 * author:bWang
 */
public class YearResponse {

    /**
     * requestId : null
     * errorLog : null
     * status : 200
     * message : 成功
     * data : ["2021","2020","2018"]
     */

    private Object requestId;
    private Object errorLog;
    private int status;
    private String message;
    private List<String> data;

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

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
