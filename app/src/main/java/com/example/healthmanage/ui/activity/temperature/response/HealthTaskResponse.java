package com.example.healthmanage.ui.activity.temperature.response;

import java.io.Serializable;
import java.util.List;

public class HealthTaskResponse {


    /**
     * requestId : null
     * errorLog : null
     * status : 0
     * message : 成功
     * data : [{"id":107,"userId":94,"systemUserId":73,"content":"有病","status":0,"proposal":null,"processTime":null,"createTime":"2021-06-02 15:53:26","transferSysId":null,"transferStatus":null,"appUser":{"id":107,"userName":"83","nickName":"陈明","password":null,"sex":0,"phone":"15862335733","avatar":"http://119.23.187.176:8083/picture/c4be29fae18b857e8851dc587841d5ec.jpg","signature":null,"email":null,"emailFlag":null,"isUsed":null,"regTime":null,"updateTime":null,"lastLoginTime":null,"userType":3,"cityNameCn":null,"cityNamePy":null,"birthday":null,"loginCount":null,"regIp":null,"rank":2,"parentId":null,"wxid":null,"provinceCode":null,"cityCode":null,"countyCode":null,"provinceName":null,"cityName":null,"countyName":null,"seerUserId":null,"old":20,"point":null,"appSceneList":null,"token":null,"followStatus":1},"appDoctorInfo":null,"list":[{"id":null,"url":"http://192.168.199.235:8080/picture/a5462a6c143a9c08e0f50bf62b2d2893.jpeg","healthTaskId":null},{"id":null,"url":"http://192.168.199.235:8080/picture/b4b425757cd1f7484200e61462a3e5b1.jpeg","healthTaskId":null},{"id":null,"url":"http://192.168.199.235:8080/picture/95a9a9559ade3437fd9ece52b0cbb448.jpeg","healthTaskId":null}]}]
     */

    private Object requestId;
    private Object errorLog;
    private int status;
    private String message;
    /**
     * id : 107
     * userId : 94
     * systemUserId : 73
     * content : 有病
     * status : 0
     * proposal : null
     * processTime : null
     * createTime : 2021-06-02 15:53:26
     * transferSysId : null
     * transferStatus : null
     * appUser : {"id":107,"userName":"83","nickName":"陈明","password":null,"sex":0,"phone":"15862335733","avatar":"http://119.23.187.176:8083/picture/c4be29fae18b857e8851dc587841d5ec.jpg","signature":null,"email":null,"emailFlag":null,"isUsed":null,"regTime":null,"updateTime":null,"lastLoginTime":null,"userType":3,"cityNameCn":null,"cityNamePy":null,"birthday":null,"loginCount":null,"regIp":null,"rank":2,"parentId":null,"wxid":null,"provinceCode":null,"cityCode":null,"countyCode":null,"provinceName":null,"cityName":null,"countyName":null,"seerUserId":null,"old":20,"point":null,"appSceneList":null,"token":null,"followStatus":1}
     * appDoctorInfo : null
     * list : [{"id":null,"url":"http://192.168.199.235:8080/picture/a5462a6c143a9c08e0f50bf62b2d2893.jpeg","healthTaskId":null},{"id":null,"url":"http://192.168.199.235:8080/picture/b4b425757cd1f7484200e61462a3e5b1.jpeg","healthTaskId":null},{"id":null,"url":"http://192.168.199.235:8080/picture/95a9a9559ade3437fd9ece52b0cbb448.jpeg","healthTaskId":null}]
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

    public static class DataBean implements Serializable {
        private int id;
        private int userId;
        private int systemUserId;
        private String content;
        private int status;
        private String proposal;
        private Object processTime;
        private String createTime;
        private int transferSysId;
        private int transferStatus;
        /**
         * id : 107
         * userName : 83
         * nickName : 陈明
         * password : null
         * sex : 0
         * phone : 15862335733
         * avatar : http://119.23.187.176:8083/picture/c4be29fae18b857e8851dc587841d5ec.jpg
         * signature : null
         * email : null
         * emailFlag : null
         * isUsed : null
         * regTime : null
         * updateTime : null
         * lastLoginTime : null
         * userType : 3
         * cityNameCn : null
         * cityNamePy : null
         * birthday : null
         * loginCount : null
         * regIp : null
         * rank : 2
         * parentId : null
         * wxid : null
         * provinceCode : null
         * cityCode : null
         * countyCode : null
         * provinceName : null
         * cityName : null
         * countyName : null
         * seerUserId : null
         * old : 20
         * point : null
         * appSceneList : null
         * token : null
         * followStatus : 1
         */

        private AppUserBean appUser;
        private AppDoctorInfoBean appDoctorInfo;
        /**
         * id : null
         * url : http://192.168.199.235:8080/picture/a5462a6c143a9c08e0f50bf62b2d2893.jpeg
         * healthTaskId : null
         */

        private List<ListBean> list;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getSystemUserId() {
            return systemUserId;
        }

        public void setSystemUserId(int systemUserId) {
            this.systemUserId = systemUserId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getProposal() {
            return proposal;
        }

        public void setProposal(String proposal) {
            this.proposal = proposal;
        }

        public Object getProcessTime() {
            return processTime;
        }

        public void setProcessTime(Object processTime) {
            this.processTime = processTime;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getTransferSysId() {
            return transferSysId;
        }

        public void setTransferSysId(int transferSysId) {
            this.transferSysId = transferSysId;
        }

        public int getTransferStatus() {
            return transferStatus;
        }

        public void setTransferStatus(int transferStatus) {
            this.transferStatus = transferStatus;
        }

        public AppUserBean getAppUser() {
            return appUser;
        }

        public void setAppUser(AppUserBean appUser) {
            this.appUser = appUser;
        }

        public AppDoctorInfoBean getAppDoctorInfo() {
            return appDoctorInfo;
        }

        public void setAppDoctorInfo(AppDoctorInfoBean appDoctorInfo) {
            this.appDoctorInfo = appDoctorInfo;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class AppDoctorInfoBean implements Serializable{
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
            private Object appHospitalDepartment;
            private Object appSystemUser;
            private Object appHospital;

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

            public Object getAppHospitalDepartment() {
                return appHospitalDepartment;
            }

            public void setAppHospitalDepartment(Object appHospitalDepartment) {
                this.appHospitalDepartment = appHospitalDepartment;
            }

            public Object getAppSystemUser() {
                return appSystemUser;
            }

            public void setAppSystemUser(Object appSystemUser) {
                this.appSystemUser = appSystemUser;
            }

            public Object getAppHospital() {
                return appHospital;
            }

            public void setAppHospital(Object appHospital) {
                this.appHospital = appHospital;
            }
        }

        public static class AppUserBean implements Serializable{
            private int id;
            private String userName;
            private String nickName;
            private Object password;
            private int sex;
            private String phone;
            private String avatar;
            private Object signature;
            private Object email;
            private Object emailFlag;
            private Object isUsed;
            private Object regTime;
            private Object updateTime;
            private Object lastLoginTime;
            private int userType;
            private Object cityNameCn;
            private Object cityNamePy;
            private Object birthday;
            private Object loginCount;
            private Object regIp;
            private int rank;
            private Object parentId;
            private Object wxid;
            private Object provinceCode;
            private Object cityCode;
            private Object countyCode;
            private Object provinceName;
            private Object cityName;
            private Object countyName;
            private Object seerUserId;
            private int old;
            private Object point;
            private Object appSceneList;
            private Object token;
            private int followStatus;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }

            public Object getPassword() {
                return password;
            }

            public void setPassword(Object password) {
                this.password = password;
            }

            public int getSex() {
                return sex;
            }

            public void setSex(int sex) {
                this.sex = sex;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public Object getSignature() {
                return signature;
            }

            public void setSignature(Object signature) {
                this.signature = signature;
            }

            public Object getEmail() {
                return email;
            }

            public void setEmail(Object email) {
                this.email = email;
            }

            public Object getEmailFlag() {
                return emailFlag;
            }

            public void setEmailFlag(Object emailFlag) {
                this.emailFlag = emailFlag;
            }

            public Object getIsUsed() {
                return isUsed;
            }

            public void setIsUsed(Object isUsed) {
                this.isUsed = isUsed;
            }

            public Object getRegTime() {
                return regTime;
            }

            public void setRegTime(Object regTime) {
                this.regTime = regTime;
            }

            public Object getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(Object updateTime) {
                this.updateTime = updateTime;
            }

            public Object getLastLoginTime() {
                return lastLoginTime;
            }

            public void setLastLoginTime(Object lastLoginTime) {
                this.lastLoginTime = lastLoginTime;
            }

            public int getUserType() {
                return userType;
            }

            public void setUserType(int userType) {
                this.userType = userType;
            }

            public Object getCityNameCn() {
                return cityNameCn;
            }

            public void setCityNameCn(Object cityNameCn) {
                this.cityNameCn = cityNameCn;
            }

            public Object getCityNamePy() {
                return cityNamePy;
            }

            public void setCityNamePy(Object cityNamePy) {
                this.cityNamePy = cityNamePy;
            }

            public Object getBirthday() {
                return birthday;
            }

            public void setBirthday(Object birthday) {
                this.birthday = birthday;
            }

            public Object getLoginCount() {
                return loginCount;
            }

            public void setLoginCount(Object loginCount) {
                this.loginCount = loginCount;
            }

            public Object getRegIp() {
                return regIp;
            }

            public void setRegIp(Object regIp) {
                this.regIp = regIp;
            }

            public int getRank() {
                return rank;
            }

            public void setRank(int rank) {
                this.rank = rank;
            }

            public Object getParentId() {
                return parentId;
            }

            public void setParentId(Object parentId) {
                this.parentId = parentId;
            }

            public Object getWxid() {
                return wxid;
            }

            public void setWxid(Object wxid) {
                this.wxid = wxid;
            }

            public Object getProvinceCode() {
                return provinceCode;
            }

            public void setProvinceCode(Object provinceCode) {
                this.provinceCode = provinceCode;
            }

            public Object getCityCode() {
                return cityCode;
            }

            public void setCityCode(Object cityCode) {
                this.cityCode = cityCode;
            }

            public Object getCountyCode() {
                return countyCode;
            }

            public void setCountyCode(Object countyCode) {
                this.countyCode = countyCode;
            }

            public Object getProvinceName() {
                return provinceName;
            }

            public void setProvinceName(Object provinceName) {
                this.provinceName = provinceName;
            }

            public Object getCityName() {
                return cityName;
            }

            public void setCityName(Object cityName) {
                this.cityName = cityName;
            }

            public Object getCountyName() {
                return countyName;
            }

            public void setCountyName(Object countyName) {
                this.countyName = countyName;
            }

            public Object getSeerUserId() {
                return seerUserId;
            }

            public void setSeerUserId(Object seerUserId) {
                this.seerUserId = seerUserId;
            }

            public int getOld() {
                return old;
            }

            public void setOld(int old) {
                this.old = old;
            }

            public Object getPoint() {
                return point;
            }

            public void setPoint(Object point) {
                this.point = point;
            }

            public Object getAppSceneList() {
                return appSceneList;
            }

            public void setAppSceneList(Object appSceneList) {
                this.appSceneList = appSceneList;
            }

            public Object getToken() {
                return token;
            }

            public void setToken(Object token) {
                this.token = token;
            }

            public int getFollowStatus() {
                return followStatus;
            }

            public void setFollowStatus(int followStatus) {
                this.followStatus = followStatus;
            }
        }

        public static class ListBean implements Serializable{
            private Object id;
            private String url;
            private Object healthTaskId;

            public Object getId() {
                return id;
            }

            public void setId(Object id) {
                this.id = id;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public Object getHealthTaskId() {
                return healthTaskId;
            }

            public void setHealthTaskId(Object healthTaskId) {
                this.healthTaskId = healthTaskId;
            }
        }
    }
}
