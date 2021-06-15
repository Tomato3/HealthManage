package com.example.healthmanage.ui.activity.consultation.response;

import java.util.List;

public class DoctorTeamListResponse {

    /**
     * requestId : null
     * errorLog : null
     * status : 0
     * message : 成功
     * data : [{"id":null,"systemUserId":74,"name":"丁妙珠","idCard":"445121199604055622","roleId":11,"departmentId":2,"addr":null,"rank":"主任医师","hospitalId":1,"avatar":"http://192.168.199.235:8080/picture/593b5fb02f10a0ae4df9b4aa90f8ece4.png","experience":null,"speciality":null,"updateTime":1622616278000,"auditStatus":1,"auditTime":null,"createTime":null,"frontIdCardUrl":null,"backIdCardUrl":null,"urlList":null,"phone":null,"appHospitalDepartment":{"id":23,"parentId":null,"name":"儿童内科","isUsed":null,"sort":null,"createTime":null,"updateTime":null,"list":null},"appSystemUser":{"sysId":74,"sysNickName":null,"sysUserName":"13288788605","sysInitPwd":null,"sysPwd":null,"token":null,"isLocked":null,"isDeleted":null,"createTime":null,"updateTime":null,"createUserId":null,"lastLoginTime":null,"roleId":null,"groupId":null,"groupName":null,"groupParentId":null,"groupParentPath":null,"groupPath":null,"departmentId":null,"departmentName":null,"phone":"13288788605","invitationCode":null,"appDoctorInfo":null},"appHospital":{"id":1,"name":"苏州九龙医院","typeId":1,"createTime":1615975651000,"updateTime":null,"avatar":"http://192.168.199.235:8080/picture/593b5fb02f10a0ae4df9b4aa90f8ece4.png","addr":null,"phone":"13288788605","content":"<p>富文本<\/p>","provinceId":320000,"cityId":320500,"countyId":320506}}]
     */

    private Object requestId;
    private Object errorLog;
    private int status;
    private String message;
    /**
     * id : null
     * systemUserId : 74
     * name : 丁妙珠
     * idCard : 445121199604055622
     * roleId : 11
     * departmentId : 2
     * addr : null
     * rank : 主任医师
     * hospitalId : 1
     * avatar : http://192.168.199.235:8080/picture/593b5fb02f10a0ae4df9b4aa90f8ece4.png
     * experience : null
     * speciality : null
     * updateTime : 1622616278000
     * auditStatus : 1
     * auditTime : null
     * createTime : null
     * frontIdCardUrl : null
     * backIdCardUrl : null
     * urlList : null
     * phone : null
     * appHospitalDepartment : {"id":23,"parentId":null,"name":"儿童内科","isUsed":null,"sort":null,"createTime":null,"updateTime":null,"list":null}
     * appSystemUser : {"sysId":74,"sysNickName":null,"sysUserName":"13288788605","sysInitPwd":null,"sysPwd":null,"token":null,"isLocked":null,"isDeleted":null,"createTime":null,"updateTime":null,"createUserId":null,"lastLoginTime":null,"roleId":null,"groupId":null,"groupName":null,"groupParentId":null,"groupParentPath":null,"groupPath":null,"departmentId":null,"departmentName":null,"phone":"13288788605","invitationCode":null,"appDoctorInfo":null}
     * appHospital : {"id":1,"name":"苏州九龙医院","typeId":1,"createTime":1615975651000,"updateTime":null,"avatar":"http://192.168.199.235:8080/picture/593b5fb02f10a0ae4df9b4aa90f8ece4.png","addr":null,"phone":"13288788605","content":"<p>富文本<\/p>","provinceId":320000,"cityId":320500,"countyId":320506}
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
        private Object id;
        private int systemUserId;
        private String name;
        private String idCard;
        private int roleId;
        private int departmentId;
        private Object addr;
        private String rank;
        private int hospitalId;
        private String avatar;
        private Object experience;
        private Object speciality;
        private long updateTime;
        private int auditStatus;
        private Object auditTime;
        private Object createTime;
        private Object frontIdCardUrl;
        private Object backIdCardUrl;
        private Object urlList;
        private Object phone;
        private boolean isSelect;
        /**
         * id : 23
         * parentId : null
         * name : 儿童内科
         * isUsed : null
         * sort : null
         * createTime : null
         * updateTime : null
         * list : null
         */

        private AppHospitalDepartmentBean appHospitalDepartment;
        /**
         * sysId : 74
         * sysNickName : null
         * sysUserName : 13288788605
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
         * phone : 13288788605
         * invitationCode : null
         * appDoctorInfo : null
         */

        private AppSystemUserBean appSystemUser;
        /**
         * id : 1
         * name : 苏州九龙医院
         * typeId : 1
         * createTime : 1615975651000
         * updateTime : null
         * avatar : http://192.168.199.235:8080/picture/593b5fb02f10a0ae4df9b4aa90f8ece4.png
         * addr : null
         * phone : 13288788605
         * content : <p>富文本</p>
         * provinceId : 320000
         * cityId : 320500
         * countyId : 320506
         */

        private AppHospitalBean appHospital;

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

        public Object getId() {
            return id;
        }

        public void setId(Object id) {
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

        public Object getAddr() {
            return addr;
        }

        public void setAddr(Object addr) {
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

        public Object getSpeciality() {
            return speciality;
        }

        public void setSpeciality(Object speciality) {
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

        public Object getAuditTime() {
            return auditTime;
        }

        public void setAuditTime(Object auditTime) {
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

        public AppHospitalBean getAppHospital() {
            return appHospital;
        }

        public void setAppHospital(AppHospitalBean appHospital) {
            this.appHospital = appHospital;
        }

        public static class AppHospitalDepartmentBean {
            private int id;
            private Object parentId;
            private String name;
            private Object isUsed;
            private Object sort;
            private Object createTime;
            private Object updateTime;
            private Object list;

            public int getId() {
                return id;
            }

            public void setId(int id) {
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

            public Object getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(Object updateTime) {
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
            private Object sysNickName;
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

            public Object getSysNickName() {
                return sysNickName;
            }

            public void setSysNickName(Object sysNickName) {
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

        public static class AppHospitalBean {
            private int id;
            private String name;
            private int typeId;
            private long createTime;
            private Object updateTime;
            private String avatar;
            private Object addr;
            private String phone;
            private String content;
            private int provinceId;
            private int cityId;
            private int countyId;

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

            public int getTypeId() {
                return typeId;
            }

            public void setTypeId(int typeId) {
                this.typeId = typeId;
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

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public Object getAddr() {
                return addr;
            }

            public void setAddr(Object addr) {
                this.addr = addr;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getProvinceId() {
                return provinceId;
            }

            public void setProvinceId(int provinceId) {
                this.provinceId = provinceId;
            }

            public int getCityId() {
                return cityId;
            }

            public void setCityId(int cityId) {
                this.cityId = cityId;
            }

            public int getCountyId() {
                return countyId;
            }

            public void setCountyId(int countyId) {
                this.countyId = countyId;
            }
        }
    }
}
