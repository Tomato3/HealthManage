package com.example.healthmanage.ui.activity.team.response;

import java.util.List;

public class BusinessDetailResponse {

    /**
     * requestId : null
     * errorLog : null
     * status : 0
     * message : 成功
     * data : {"id":1,"systemUserId":78,"content":"string","userId":111,"date":"string","time":"string","addr":"string","createTime":"2021-05-07 15:42:35","createUserId":78,"status":1,"details":"完成描述","list":[{"id":null,"serviceId":null,"url":"这是一个图片url"},{"id":null,"serviceId":null,"url":"图片地址"}],"appUser":{"id":111,"userName":"15862335733","nickName":"0ULsm4SiDo","password":null,"sex":0,"phone":"15862335733","avatar":null,"signature":null,"email":null,"emailFlag":null,"isUsed":null,"regTime":null,"updateTime":null,"lastLoginTime":null,"userType":null,"cityNameCn":null,"cityNamePy":null,"birthday":null,"loginCount":null,"regIp":null,"rank":2,"parentId":null,"wxid":null,"provinceCode":null,"cityCode":null,"countyCode":null,"provinceName":null,"cityName":null,"countyName":null,"seerUserId":null,"old":null,"point":null,"appSceneList":null,"token":null,"followStatus":null},"appDoctorInfo":{"id":null,"systemUserId":78,"name":"王斌","idCard":null,"roleId":null,"departmentId":null,"addr":null,"rank":"健康管理师","hospitalId":null,"avatar":null,"experience":null,"speciality":null,"updateTime":null,"auditStatus":null,"auditTime":null,"createTime":null,"frontIdCardUrl":null,"backIdCardUrl":null,"urlList":null,"phone":null,"appHospitalDepartment":null,"appSystemUser":null,"appHospital":null},"assignStatus":0,"updateTime":"2021-06-07 17:25:05"}
     */

    private Object requestId;
    private Object errorLog;
    private int status;
    private String message;
    /**
     * id : 1
     * systemUserId : 78
     * content : string
     * userId : 111
     * date : string
     * time : string
     * addr : string
     * createTime : 2021-05-07 15:42:35
     * createUserId : 78
     * status : 1
     * details : 完成描述
     * list : [{"id":null,"serviceId":null,"url":"这是一个图片url"},{"id":null,"serviceId":null,"url":"图片地址"}]
     * appUser : {"id":111,"userName":"15862335733","nickName":"0ULsm4SiDo","password":null,"sex":0,"phone":"15862335733","avatar":null,"signature":null,"email":null,"emailFlag":null,"isUsed":null,"regTime":null,"updateTime":null,"lastLoginTime":null,"userType":null,"cityNameCn":null,"cityNamePy":null,"birthday":null,"loginCount":null,"regIp":null,"rank":2,"parentId":null,"wxid":null,"provinceCode":null,"cityCode":null,"countyCode":null,"provinceName":null,"cityName":null,"countyName":null,"seerUserId":null,"old":null,"point":null,"appSceneList":null,"token":null,"followStatus":null}
     * appDoctorInfo : {"id":null,"systemUserId":78,"name":"王斌","idCard":null,"roleId":null,"departmentId":null,"addr":null,"rank":"健康管理师","hospitalId":null,"avatar":null,"experience":null,"speciality":null,"updateTime":null,"auditStatus":null,"auditTime":null,"createTime":null,"frontIdCardUrl":null,"backIdCardUrl":null,"urlList":null,"phone":null,"appHospitalDepartment":null,"appSystemUser":null,"appHospital":null}
     * assignStatus : 0
     * updateTime : 2021-06-07 17:25:05
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
        private String content;
        private int userId;
        private String date;
        private String time;
        private String addr;
        private String createTime;
        private int createUserId;
        private int status;
        private String details;
        /**
         * id : 111
         * userName : 15862335733
         * nickName : 0ULsm4SiDo
         * password : null
         * sex : 0
         * phone : 15862335733
         * avatar : null
         * signature : null
         * email : null
         * emailFlag : null
         * isUsed : null
         * regTime : null
         * updateTime : null
         * lastLoginTime : null
         * userType : null
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
         * old : null
         * point : null
         * appSceneList : null
         * token : null
         * followStatus : null
         */

        private AppUserBean appUser;
        /**
         * id : null
         * systemUserId : 78
         * name : 王斌
         * idCard : null
         * roleId : null
         * departmentId : null
         * addr : null
         * rank : 健康管理师
         * hospitalId : null
         * avatar : null
         * experience : null
         * speciality : null
         * updateTime : null
         * auditStatus : null
         * auditTime : null
         * createTime : null
         * frontIdCardUrl : null
         * backIdCardUrl : null
         * urlList : null
         * phone : null
         * appHospitalDepartment : null
         * appSystemUser : null
         * appHospital : null
         */

        private AppDoctorInfoBean appDoctorInfo;
        private int assignStatus;
        private String updateTime;
        /**
         * id : null
         * serviceId : null
         * url : 这是一个图片url
         */

        private List<ListBean> list;

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

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getCreateUserId() {
            return createUserId;
        }

        public void setCreateUserId(int createUserId) {
            this.createUserId = createUserId;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getDetails() {
            return details;
        }

        public void setDetails(String details) {
            this.details = details;
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

        public int getAssignStatus() {
            return assignStatus;
        }

        public void setAssignStatus(int assignStatus) {
            this.assignStatus = assignStatus;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class AppUserBean {
            private int id;
            private String userName;
            private String nickName;
            private Object password;
            private int sex;
            private String phone;
            private Object avatar;
            private Object signature;
            private Object email;
            private Object emailFlag;
            private Object isUsed;
            private Object regTime;
            private Object updateTime;
            private Object lastLoginTime;
            private Object userType;
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
            private Object old;
            private Object point;
            private Object appSceneList;
            private Object token;
            private Object followStatus;

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

            public Object getAvatar() {
                return avatar;
            }

            public void setAvatar(Object avatar) {
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

            public Object getUserType() {
                return userType;
            }

            public void setUserType(Object userType) {
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

            public Object getOld() {
                return old;
            }

            public void setOld(Object old) {
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

            public Object getFollowStatus() {
                return followStatus;
            }

            public void setFollowStatus(Object followStatus) {
                this.followStatus = followStatus;
            }
        }

        public static class AppDoctorInfoBean {
            private Object id;
            private int systemUserId;
            private String name;
            private Object idCard;
            private Object roleId;
            private Object departmentId;
            private Object addr;
            private String rank;
            private Object hospitalId;
            private Object avatar;
            private Object experience;
            private Object speciality;
            private Object updateTime;
            private Object auditStatus;
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

            public Object getIdCard() {
                return idCard;
            }

            public void setIdCard(Object idCard) {
                this.idCard = idCard;
            }

            public Object getRoleId() {
                return roleId;
            }

            public void setRoleId(Object roleId) {
                this.roleId = roleId;
            }

            public Object getDepartmentId() {
                return departmentId;
            }

            public void setDepartmentId(Object departmentId) {
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

            public Object getHospitalId() {
                return hospitalId;
            }

            public void setHospitalId(Object hospitalId) {
                this.hospitalId = hospitalId;
            }

            public Object getAvatar() {
                return avatar;
            }

            public void setAvatar(Object avatar) {
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

            public Object getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(Object updateTime) {
                this.updateTime = updateTime;
            }

            public Object getAuditStatus() {
                return auditStatus;
            }

            public void setAuditStatus(Object auditStatus) {
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

        public static class ListBean {
            private Object id;
            private Object serviceId;
            private String url;

            public Object getId() {
                return id;
            }

            public void setId(Object id) {
                this.id = id;
            }

            public Object getServiceId() {
                return serviceId;
            }

            public void setServiceId(Object serviceId) {
                this.serviceId = serviceId;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
