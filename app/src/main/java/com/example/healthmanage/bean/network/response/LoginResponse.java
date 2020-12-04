package com.example.healthmanage.bean.network.response;

public class LoginResponse {

    /**
     * requestId : null
     * errorLog : null
     * status : 200
     * message : 成功
     * data : {"userInfo":{"sysId":19,"sysNickName":"测试健康管理师","sysUserName":"王姓健康管理师","sysInitPwd":"564321","sysPwd":"bbf125918ac66d4ab6fac2c767b213df","token":null,"isLocked":0,"isDeleted":0,"createTime":null,"updateTime":null,"createUserId":null,"lastLoginTime":null,"roleId":9,"groupId":null,"groupName":null,"groupParentId":null,"groupParentPath":null,"groupPath":null,"departmentId":null,"departmentName":null,"phone":"13584869132","invitationCode":null},"expires_in":86400,"token":"3b2bd03a1cc742338714df1f1022232f1598000529772"}
     */

    private Object requestId;
    private Object errorLog;
    private int status;
    private String message;
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
        /**
         * userInfo : {"sysId":19,"sysNickName":"测试健康管理师","sysUserName":"王姓健康管理师","sysInitPwd":"564321","sysPwd":"bbf125918ac66d4ab6fac2c767b213df","token":null,"isLocked":0,"isDeleted":0,"createTime":null,"updateTime":null,"createUserId":null,"lastLoginTime":null,"roleId":9,"groupId":null,"groupName":null,"groupParentId":null,"groupParentPath":null,"groupPath":null,"departmentId":null,"departmentName":null,"phone":"13584869132","invitationCode":null}
         * expires_in : 86400
         * token : 3b2bd03a1cc742338714df1f1022232f1598000529772
         */

        private UserInfoBean userInfo;
        private int expires_in;
        private String token;

        public UserInfoBean getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(UserInfoBean userInfo) {
            this.userInfo = userInfo;
        }

        public int getExpires_in() {
            return expires_in;
        }

        public void setExpires_in(int expires_in) {
            this.expires_in = expires_in;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public static class UserInfoBean {
            /**
             * sysId : 19
             * sysNickName : 测试健康管理师
             * sysUserName : 王姓健康管理师
             * sysInitPwd : 564321
             * sysPwd : bbf125918ac66d4ab6fac2c767b213df
             * token : null
             * isLocked : 0
             * isDeleted : 0
             * createTime : null
             * updateTime : null
             * createUserId : null
             * lastLoginTime : null
             * roleId : 9
             * groupId : null
             * groupName : null
             * groupParentId : null
             * groupParentPath : null
             * groupPath : null
             * departmentId : null
             * departmentName : null
             * phone : 13584869132
             * invitationCode : null
             */

            private int sysId;
            private String sysNickName;
            private String sysUserName;
            private String sysInitPwd;
            private String sysPwd;
            private Object token;
            private int isLocked;
            private int isDeleted;
            private Object createTime;
            private Object updateTime;
            private Object createUserId;
            private Object lastLoginTime;
            private int roleId;
            private Object groupId;
            private Object groupName;
            private Object groupParentId;
            private Object groupParentPath;
            private Object groupPath;
            private Object departmentId;
            private Object departmentName;
            private String phone;
            private String invitationCode;

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

            public int getRoleId() {
                return roleId;
            }

            public void setRoleId(int roleId) {
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

            public String getInvitationCode() {
                return invitationCode;
            }

            public void setInvitationCode(String invitationCode) {
                this.invitationCode = invitationCode;
            }
        }
    }
}
