package com.example.healthmanage.ui.activity.famousDoctorHall.response;

import java.util.List;

/**
 * desc:
 * date:2021/7/1 11:15
 * author:bWang
 */
public class HospitalTypeResponse {

    /**
     * requestId : null
     * errorLog : null
     * status : 0
     * message : 成功
     * data : [{"id":1,"name":"三甲","isUsed":0,"createTime":1615975392000,"updateTime":null},{"id":3,"name":"三级","isUsed":0,"createTime":1617343408000,"updateTime":null},{"id":4,"name":"二甲","isUsed":0,"createTime":1617343413000,"updateTime":null},{"id":5,"name":"二级","isUsed":0,"createTime":1617343418000,"updateTime":null},{"id":6,"name":"一甲","isUsed":0,"createTime":1617343428000,"updateTime":null},{"id":7,"name":"其它","isUsed":0,"createTime":1617343438000,"updateTime":null}]
     */

    private Object requestId;
    private Object errorLog;
    private int status;
    private String message;
    /**
     * id : 1
     * name : 三甲
     * isUsed : 0
     * createTime : 1615975392000
     * updateTime : null
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
        private String name;
        private int isUsed;
        private long createTime;
        private Object updateTime;
        private boolean isSelect;

        public DataBean(String name) {
            this.name = name;
        }

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getIsUsed() {
            return isUsed;
        }

        public void setIsUsed(int isUsed) {
            this.isUsed = isUsed;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public Object getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Object updateTime) {
            this.updateTime = updateTime;
        }
    }
}
