package com.example.healthmanage.ui.activity.integral.response;

/**
 * desc:
 * date:2021/7/15 17:18
 * author:bWang
 */
public class IntegralResponse {

    /**
     * requestId : null
     * errorLog : null
     * status : 0
     * message : 成功
     * data : {"sysId":78,"sysNickName":"b83aHnkvOp","sysUserName":"13584869132","sysInitPwd":"12345678","sysPwd":"9fb8e854265ced79a4a9cb8bb0f8930e","token":null,"isLocked":0,"isDeleted":0,"createTime":1622710415000,"updateTime":null,"createUserId":null,"lastLoginTime":null,"roleId":null,"groupId":null,"groupName":null,"groupParentId":null,"groupParentPath":null,"groupPath":null,"departmentId":null,"departmentName":null,"phone":"13584869132","invitationCode":null,"appDoctorInfo":null,"point":20}
     */

    private Object requestId;
    private Object errorLog;
    private int status;
    private String message;
    /**
     * sysId : 78
     * sysNickName : b83aHnkvOp
     * sysUserName : 13584869132
     * sysInitPwd : 12345678
     * sysPwd : 9fb8e854265ced79a4a9cb8bb0f8930e
     * token : null
     * isLocked : 0
     * isDeleted : 0
     * createTime : 1622710415000
     * updateTime : null
     * createUserId : null
     * lastLoginTime : null
     * roleId : null
     * groupId : null
     * groupName : null
     * groupParentId : null
     * groupParentPath : null
     * groupPath : null
     * departmentId : null
     * departmentName : null
     * phone : 13584869132
     * invitationCode : null
     * appDoctorInfo : null
     * point : 20
     */

    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private int sysId;
        private String sysNickName;
        private String sysUserName;
        private String sysInitPwd;
        private String sysPwd;
        private Object token;
        private int isLocked;
        private int isDeleted;
        private long createTime;
        private Object updateTime;
        private Object createUserId;
        private Object lastLoginTime;
        private Object roleId;
        private Object groupId;
        private Object groupName;
        private Object groupParentId;
        private Object groupParentPath;
        private Object groupPath;
        private Object departmentId;
        private Object departmentName;
        private String phone;
        private Object invitationCode;
        private Object appDoctorInfo;
        private int point;

        public int getSysId() {
            return sysId;
        }

        public void setSysId(int sysId) {
            this.sysId = sysId;
        }

        public String getSysNickName() {
            return sysNickName;
        }

        public void setSysNickName(String sysNickName) {
            this.sysNickName = sysNickName;
        }

        public String getSysUserName() {
            return sysUserName;
        }

        public void setSysUserName(String sysUserName) {
            this.sysUserName = sysUserName;
        }

        public String getSysInitPwd() {
            return sysInitPwd;
        }

        public void setSysInitPwd(String sysInitPwd) {
            this.sysInitPwd = sysInitPwd;
        }

        public String getSysPwd() {
            return sysPwd;
        }

        public void setSysPwd(String sysPwd) {
            this.sysPwd = sysPwd;
        }

        public Object getToken() {
            return token;
        }

        public void setToken(Object token) {
            this.token = token;
        }

        public int getIsLocked() {
            return isLocked;
        }

        public void setIsLocked(int isLocked) {
            this.isLocked = isLocked;
        }

        public int getIsDeleted() {
            return isDeleted;
        }

        public void setIsDeleted(int isDeleted) {
            this.isDeleted = isDeleted;
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

        public Object getCreateUserId() {
            return createUserId;
        }

        public void setCreateUserId(Object createUserId) {
            this.createUserId = createUserId;
        }

        public Object getLastLoginTime() {
            return lastLoginTime;
        }

        public void setLastLoginTime(Object lastLoginTime) {
            this.lastLoginTime = lastLoginTime;
        }

        public Object getRoleId() {
            return roleId;
        }

        public void setRoleId(Object roleId) {
            this.roleId = roleId;
        }

        public Object getGroupId() {
            return groupId;
        }

        public void setGroupId(Object groupId) {
            this.groupId = groupId;
        }

        public Object getGroupName() {
            return groupName;
        }

        public void setGroupName(Object groupName) {
            this.groupName = groupName;
        }

        public Object getGroupParentId() {
            return groupParentId;
        }

        public void setGroupParentId(Object groupParentId) {
            this.groupParentId = groupParentId;
        }

        public Object getGroupParentPath() {
            return groupParentPath;
        }

        public void setGroupParentPath(Object groupParentPath) {
            this.groupParentPath = groupParentPath;
        }

        public Object getGroupPath() {
            return groupPath;
        }

        public void setGroupPath(Object groupPath) {
            this.groupPath = groupPath;
        }

        public Object getDepartmentId() {
            return departmentId;
        }

        public void setDepartmentId(Object departmentId) {
            this.departmentId = departmentId;
        }

        public Object getDepartmentName() {
            return departmentName;
        }

        public void setDepartmentName(Object departmentName) {
            this.departmentName = departmentName;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public Object getInvitationCode() {
            return invitationCode;
        }

        public void setInvitationCode(Object invitationCode) {
            this.invitationCode = invitationCode;
        }

        public Object getAppDoctorInfo() {
            return appDoctorInfo;
        }

        public void setAppDoctorInfo(Object appDoctorInfo) {
            this.appDoctorInfo = appDoctorInfo;
        }

        public int getPoint() {
            return point;
        }

        public void setPoint(int point) {
            this.point = point;
        }
    }
}
