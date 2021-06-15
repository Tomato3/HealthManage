package com.example.healthmanage.bean;

import java.util.List;

/**
 * 职业response
 */
public class ProfessionBeanResponse {

    /**
     * requestId : null
     * errorLog : null
     * status : 200
     * message : 查询成功
     * data : [{"id":9,"roleName":"健康管理师","roleCode":null,"roleType":4,"createTime":null,"isDeleted":0,"displayOrder":null,"remark":null},{"id":10,"roleName":"健康营养师","roleCode":null,"roleType":4,"createTime":null,"isDeleted":0,"displayOrder":null,"remark":null},{"id":11,"roleName":"健康医师","roleCode":null,"roleType":4,"createTime":null,"isDeleted":0,"displayOrder":null,"remark":null}]
     */

    private Object requestId;
    private Object errorLog;
    private int status;
    private String message;
    /**
     * id : 9
     * roleName : 健康管理师
     * roleCode : null
     * roleType : 4
     * createTime : null
     * isDeleted : 0
     * displayOrder : null
     * remark : null
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
        private String roleName;
        private Object roleCode;
        private int roleType;
        private Object createTime;
        private int isDeleted;
        private Object displayOrder;
        private Object remark;
        private boolean isSelect;

        public DataBean(String roleName) {
            this.roleName = roleName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getRoleName() {
            return roleName;
        }

        public void setRoleName(String roleName) {
            this.roleName = roleName;
        }

        public Object getRoleCode() {
            return roleCode;
        }

        public void setRoleCode(Object roleCode) {
            this.roleCode = roleCode;
        }

        public int getRoleType() {
            return roleType;
        }

        public void setRoleType(int roleType) {
            this.roleType = roleType;
        }

        public Object getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Object createTime) {
            this.createTime = createTime;
        }

        public int getIsDeleted() {
            return isDeleted;
        }

        public void setIsDeleted(int isDeleted) {
            this.isDeleted = isDeleted;
        }

        public Object getDisplayOrder() {
            return displayOrder;
        }

        public void setDisplayOrder(Object displayOrder) {
            this.displayOrder = displayOrder;
        }

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }
    }
}
