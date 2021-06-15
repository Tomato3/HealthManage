package com.example.healthmanage.bean.network.response;

public class LoginResponse {


    /**
     * requestId : null
     * errorLog : null
     * status : 200
     * message : 成功
     * data : {"userInfo":{"sysId":19,"sysNickName":"测试健康管理师19","sysUserName":"13584869132","sysInitPwd":"222222","sysPwd":"7b9f5fa1302e8b08ca78e35669d08589","token":null,"isLocked":0,"isDeleted":0,"createTime":1617348476000,"updateTime":null,"createUserId":null,"lastLoginTime":null,"roleId":13,"groupId":null,"groupName":null,"groupParentId":null,"groupParentPath":null,"groupPath":null,"departmentId":null,"departmentName":null,"phone":"13584869132","invitationCode":"692016","appDoctorInfo":{"id":48,"systemUserId":19,"name":"题目","idCard":"54269","roleId":9,"departmentId":0,"addr":"北京市北京市东城区","rank":"健康管理师","hospitalId":0,"avatar":"http://192.168.199.235:8080/picture/e8b4b4bdb34705dc46fd0c5f70db8e1b.jpeg","experience":null,"speciality":"从业8年,专注于老年健康问题，服务超好","updateTime":null,"auditStatus":1,"auditTime":1617268368000,"createTime":1617268021000,"frontIdCardUrl":"http://192.168.199.235:8080/picture/8e55935a6aa7a0b778625ee660ed4332.jpeg","backIdCardUrl":"http://192.168.199.235:8080/picture/8378e4cd4c744a825fd93b87ebdd4b41.jpeg","urlList":null,"phone":null}},"expires_in":86400,"token":"fdc6402ea8e248dab38e7ad6f51f26ae1617788596580"}
     */

    private Object requestId;
    private Object errorLog;
    private int status;
    private String message;
    /**
     * userInfo : {"sysId":19,"sysNickName":"测试健康管理师19","sysUserName":"13584869132","sysInitPwd":"222222","sysPwd":"7b9f5fa1302e8b08ca78e35669d08589","token":null,"isLocked":0,"isDeleted":0,"createTime":1617348476000,"updateTime":null,"createUserId":null,"lastLoginTime":null,"roleId":13,"groupId":null,"groupName":null,"groupParentId":null,"groupParentPath":null,"groupPath":null,"departmentId":null,"departmentName":null,"phone":"13584869132","invitationCode":"692016","appDoctorInfo":{"id":48,"systemUserId":19,"name":"题目","idCard":"54269","roleId":9,"departmentId":0,"addr":"北京市北京市东城区","rank":"健康管理师","hospitalId":0,"avatar":"http://192.168.199.235:8080/picture/e8b4b4bdb34705dc46fd0c5f70db8e1b.jpeg","experience":null,"speciality":"从业8年,专注于老年健康问题，服务超好","updateTime":null,"auditStatus":1,"auditTime":1617268368000,"createTime":1617268021000,"frontIdCardUrl":"http://192.168.199.235:8080/picture/8e55935a6aa7a0b778625ee660ed4332.jpeg","backIdCardUrl":"http://192.168.199.235:8080/picture/8378e4cd4c744a825fd93b87ebdd4b41.jpeg","urlList":null,"phone":null}}
     * expires_in : 86400
     * token : fdc6402ea8e248dab38e7ad6f51f26ae1617788596580
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
        /**
         * sysId : 19
         * sysNickName : 测试健康管理师19
         * sysUserName : 13584869132
         * sysInitPwd : 222222
         * sysPwd : 7b9f5fa1302e8b08ca78e35669d08589
         * token : null
         * isLocked : 0
         * isDeleted : 0
         * createTime : 1617348476000
         * updateTime : null
         * createUserId : null
         * lastLoginTime : null
         * roleId : 13
         * groupId : null
         * groupName : null
         * groupParentId : null
         * groupParentPath : null
         * groupPath : null
         * departmentId : null
         * departmentName : null
         * phone : 13584869132
         * invitationCode : 692016
         * appDoctorInfo : {"id":48,"systemUserId":19,"name":"题目","idCard":"54269","roleId":9,"departmentId":0,"addr":"北京市北京市东城区","rank":"健康管理师","hospitalId":0,"avatar":"http://192.168.199.235:8080/picture/e8b4b4bdb34705dc46fd0c5f70db8e1b.jpeg","experience":null,"speciality":"从业8年,专注于老年健康问题，服务超好","updateTime":null,"auditStatus":1,"auditTime":1617268368000,"createTime":1617268021000,"frontIdCardUrl":"http://192.168.199.235:8080/picture/8e55935a6aa7a0b778625ee660ed4332.jpeg","backIdCardUrl":"http://192.168.199.235:8080/picture/8378e4cd4c744a825fd93b87ebdd4b41.jpeg","urlList":null,"phone":null}
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
            /**
             * id : 48
             * systemUserId : 19
             * name : 题目
             * idCard : 54269
             * roleId : 9
             * departmentId : 0
             * addr : 北京市北京市东城区
             * rank : 健康管理师
             * hospitalId : 0
             * avatar : http://192.168.199.235:8080/picture/e8b4b4bdb34705dc46fd0c5f70db8e1b.jpeg
             * experience : null
             * speciality : 从业8年,专注于老年健康问题，服务超好
             * updateTime : null
             * auditStatus : 1
             * auditTime : 1617268368000
             * createTime : 1617268021000
             * frontIdCardUrl : http://192.168.199.235:8080/picture/8e55935a6aa7a0b778625ee660ed4332.jpeg
             * backIdCardUrl : http://192.168.199.235:8080/picture/8378e4cd4c744a825fd93b87ebdd4b41.jpeg
             * urlList : null
             * phone : null
             */

            private AppDoctorInfoBean appDoctorInfo;

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

            public AppDoctorInfoBean getAppDoctorInfo() {
                return appDoctorInfo;
            }

            public void setAppDoctorInfo(AppDoctorInfoBean appDoctorInfo) {
                this.appDoctorInfo = appDoctorInfo;
            }

            public static class AppDoctorInfoBean {
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
                private Object updateTime;
                private int auditStatus;
                private long auditTime;
                private long createTime;
                private String frontIdCardUrl;
                private String backIdCardUrl;
                private Object urlList;
                private Object phone;

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

                public Object getUpdateTime() {
                    return updateTime;
                }

                public void setUpdateTime(Object updateTime) {
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

                public long getCreateTime() {
                    return createTime;
                }

                public void setCreateTime(long createTime) {
                    this.createTime = createTime;
                }

                public String getFrontIdCardUrl() {
                    return frontIdCardUrl;
                }

                public void setFrontIdCardUrl(String frontIdCardUrl) {
                    this.frontIdCardUrl = frontIdCardUrl;
                }

                public String getBackIdCardUrl() {
                    return backIdCardUrl;
                }

                public void setBackIdCardUrl(String backIdCardUrl) {
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
            }
        }
    }
}
