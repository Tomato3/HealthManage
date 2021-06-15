package com.example.healthmanage.ui.activity.qualification.response;

import java.util.List;

public class DepartmentResponse {

    /**
     * requestId : null
     * errorLog : null
     * status : 0
     * message : 查询成功
     * data : [{"id":1,"parentId":0,"name":"内科","isUsed":0,"sort":10,"createTime":null,"updateTime":null,"list":[]},{"id":3,"parentId":0,"name":"儿科","isUsed":0,"sort":20,"createTime":null,"updateTime":null,"list":[{"id":2,"parentId":3,"name":"神经科1","isUsed":1,"sort":20,"createTime":null,"updateTime":1616135179000,"list":null},{"id":4,"parentId":3,"name":"小儿内科","isUsed":0,"sort":10,"createTime":null,"updateTime":null,"list":null}]}]
     */

    private Object requestId;
    private Object errorLog;
    private int status;
    private String message;
    /**
     * id : 1
     * parentId : 0
     * name : 内科
     * isUsed : 0
     * sort : 10
     * createTime : null
     * updateTime : null
     * list : []
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
        private int parentId;
        private String name;
        private int isUsed;
        private int sort;
        private Object createTime;
        private Object updateTime;
        private List<DataBean> list;
        private boolean isSelect;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getParentId() {
            return parentId;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
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

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public Object getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Object createTime) {
            this.createTime = createTime;
        }

        public Object getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Object updateTime) {
            this.updateTime = updateTime;
        }

        public List<DataBean> getList() {
            return list;
        }

        public void setList(List<DataBean> list) {
            this.list = list;
        }

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }
    }
}
