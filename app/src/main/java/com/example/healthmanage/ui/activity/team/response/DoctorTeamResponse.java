package com.example.healthmanage.ui.activity.team.response;

public class DoctorTeamResponse {

    /**
     * requestId : null
     * errorLog : null
     * status : 0
     * message : 查询成功
     * data : {"id":null,"systemUserId":34,"name":"邱琦雯","idCard":"54269","roleId":9,"departmentId":11,"addr":"北京市北京市东城区","rank":"健康管理师","hospitalId":1,"avatar":"http://192.168.199.235:8080/picture/e8b4b4bdb34705dc46fd0c5f70db8e1b.jpeg","experience":null,"speciality":"从业8年,专注于老年健康问题，服务超好","updateTime":1617939693000,"auditStatus":1,"auditTime":1618628986000,"createTime":null,"frontIdCardUrl":null,"backIdCardUrl":null,"urlList":null,"phone":null,"appHospitalDepartment":{"id":null,"parentId":null,"name":"外科","isUsed":null,"sort":null,"createTime":null,"updateTime":1617939693000,"list":null},"appSystemUser":{"sysId":34,"sysNickName":"178556","sysUserName":"18852407703","sysInitPwd":null,"sysPwd":null,"token":null,"isLocked":null,"isDeleted":null,"createTime":null,"updateTime":null,"createUserId":null,"lastLoginTime":null,"roleId":null,"groupId":null,"groupName":null,"groupParentId":null,"groupParentPath":null,"groupPath":null,"departmentId":null,"departmentName":null,"phone":"18852407703","invitationCode":null,"appDoctorInfo":null}}
     */

    private Object requestId;
    private Object errorLog;
    private int status;
    private String message;
    /**
     * id : null
     * systemUserId : 34
     * name : 邱琦雯
     * idCard : 54269
     * roleId : 9
     * departmentId : 11
     * addr : 北京市北京市东城区
     * rank : 健康管理师
     * hospitalId : 1
     * avatar : http://192.168.199.235:8080/picture/e8b4b4bdb34705dc46fd0c5f70db8e1b.jpeg
     * experience : null
     * speciality : 从业8年,专注于老年健康问题，服务超好
     * updateTime : 1617939693000
     * auditStatus : 1
     * auditTime : 1618628986000
     * createTime : null
     * frontIdCardUrl : null
     * backIdCardUrl : null
     * urlList : null
     * phone : null
     * appHospitalDepartment : {"id":null,"parentId":null,"name":"外科","isUsed":null,"sort":null,"createTime":null,"updateTime":1617939693000,"list":null}
     * appSystemUser : {"sysId":34,"sysNickName":"178556","sysUserName":"18852407703","sysInitPwd":null,"sysPwd":null,"token":null,"isLocked":null,"isDeleted":null,"createTime":null,"updateTime":null,"createUserId":null,"lastLoginTime":null,"roleId":null,"groupId":null,"groupName":null,"groupParentId":null,"groupParentPath":null,"groupPath":null,"departmentId":null,"departmentName":null,"phone":"18852407703","invitationCode":null,"appDoctorInfo":null}
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
        private int id;
        private int systemUserId;
        private String name;
        private String idCard;
        private int roleId;
        private int departmentId;
        private String addr;
        private String rank;
        private int hospitalId;
        private String avatar;
        private Object experience;
        private String speciality;
        private long updateTime;
        private int auditStatus;
        private long auditTime;
        private Object createTime;
        private Object frontIdCardUrl;
        private Object backIdCardUrl;
        private Object urlList;
        private Object phone;
        /**
         * id : null
         * parentId : null
         * name : 外科
         * isUsed : null
         * sort : null
         * createTime : null
         * updateTime : 1617939693000
         * list : null
         */

        private AppHospitalDepartmentBean appHospitalDepartment;
        /**
         * sysId : 34
         * sysNickName : 178556
         * sysUserName : 18852407703
         * sysInitPwd : null
         * sysPwd : null
         * token : null
         * isLocked : null
         * isDeleted : null
         * createTime : null
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
         * phone : 18852407703
         * invitationCode : null
         * appDoctorInfo : null
         */

        private AppSystemUserBean appSystemUser;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getSystemUserId() {
            return systemUserId;
        }

        public void setSystemUserId(int systemUserId) {
            this.systemUserId = systemUserId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIdCard() {
            return idCard;
        }

        public void setIdCard(String idCard) {
            this.idCard = idCard;
        }

        public int getRoleId() {
            return roleId;
        }

        public void setRoleId(int roleId) {
            this.roleId = roleId;
        }

        public int getDepartmentId() {
            return departmentId;
        }

        public void setDepartmentId(int departmentId) {
            this.departmentId = departmentId;
        }

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public String getRank() {
            return rank;
        }

        public void setRank(String rank) {
            this.rank = rank;
        }

        public int getHospitalId() {
            return hospitalId;
        }

        public void setHospitalId(int hospitalId) {
            this.hospitalId = hospitalId;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public Object getExperience() {
            return experience;
        }

        public void setExperience(Object experience) {
            this.experience = experience;
        }

        public String getSpeciality() {
            return speciality;
        }

        public void setSpeciality(String speciality) {
            this.speciality = speciality;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public int getAuditStatus() {
            return auditStatus;
        }

        public void setAuditStatus(int auditStatus) {
            this.auditStatus = auditStatus;
        }

        public long getAuditTime() {
            return auditTime;
        }

        public void setAuditTime(long auditTime) {
            this.auditTime = auditTime;
        }

        public Object getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Object createTime) {
            this.createTime = createTime;
        }

        public Object getFrontIdCardUrl() {
            return frontIdCardUrl;
        }

        public void setFrontIdCardUrl(Object frontIdCardUrl) {
            this.frontIdCardUrl = frontIdCardUrl;
        }

        public Object getBackIdCardUrl() {
            return backIdCardUrl;
        }

        public void setBackIdCardUrl(Object backIdCardUrl) {
            this.backIdCardUrl = backIdCardUrl;
        }

        public Object getUrlList() {
            return urlList;
        }

        public void setUrlList(Object urlList) {
            this.urlList = urlList;
        }

        public Object getPhone() {
            return phone;
        }

        public void setPhone(Object phone) {
            this.phone = phone;
        }

        public AppHospitalDepartmentBean getAppHospitalDepartment() {
            return appHospitalDepartment;
        }

        public void setAppHospitalDepartment(AppHospitalDepartmentBean appHospitalDepartment) {
            this.appHospitalDepartment = appHospitalDepartment;
        }

        public AppSystemUserBean getAppSystemUser() {
            return appSystemUser;
        }

        public void setAppSystemUser(AppSystemUserBean appSystemUser) {
            this.appSystemUser = appSystemUser;
        }

        public static class AppHospitalDepartmentBean {
            private Object id;
            private Object parentId;
            private String name;
            private Object isUsed;
            private Object sort;
            private Object createTime;
            private long updateTime;
            private Object list;

            public Object getId() {
                return id;
            }

            public void setId(Object id) {
                this.id = id;
            }

            public Object getParentId() {
                return parentId;
            }

            public void setParentId(Object parentId) {
                this.parentId = parentId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public Object getIsUsed() {
                return isUsed;
            }

            public void setIsUsed(Object isUsed) {
                this.isUsed = isUsed;
            }

            public Object getSort() {
                return sort;
            }

            public void setSort(Object sort) {
                this.sort = sort;
            }

            public Object getCreateTime() {
                return createTime;
            }

            public void setCreateTime(Object createTime) {
                this.createTime = createTime;
            }

            public long getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(long updateTime) {
                this.updateTime = updateTime;
            }

            public Object getList() {
                return list;
            }

            public void setList(Object list) {
                this.list = list;
            }
        }

        public static class AppSystemUserBean {
            private int sysId;
            private String sysNickName;
            private String sysUserName;
            private Object sysInitPwd;
            private Object sysPwd;
            private Object token;
            private Object isLocked;
            private Object isDeleted;
            private Object createTime;
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

            public Object getSysInitPwd() {
                return sysInitPwd;
            }

            public void setSysInitPwd(Object sysInitPwd) {
                this.sysInitPwd = sysInitPwd;
            }

            public Object getSysPwd() {
                return sysPwd;
            }

            public void setSysPwd(Object sysPwd) {
                this.sysPwd = sysPwd;
            }

            public Object getToken() {
                return token;
            }

            public void setToken(Object token) {
                this.token = token;
            }

            public Object getIsLocked() {
                return isLocked;
            }

            public void setIsLocked(Object isLocked) {
                this.isLocked = isLocked;
            }

            public Object getIsDeleted() {
                return isDeleted;
            }

            public void setIsDeleted(Object isDeleted) {
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
        }
    }
}
