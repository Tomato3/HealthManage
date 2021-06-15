package com.example.healthmanage.ui.activity.temperature.response;

import java.io.Serializable;
import java.util.List;

public class TemperatureResponse {

    /**
     * requestId : null
     * errorLog : null
     * status : 0
     * message : 成功
     * data : [{"id":50,"consultContent":"咨询问题描述1","userId":19,"status":1,"replyContent":"回复信息","type":0,"createTime":"2021-04-22 16:17:09","replyTime":null,"prescriptionStatus":null,"diagnosis":null,"physician":null,"reason":null,"urlList":null,"list":[{"id":null,"healthConsultId":null,"url":"图片url地址1"}],"appDoctorInfo":{"id":107,"systemUserId":19,"name":"测试健康管理师19","idCard":"54269","roleId":9,"departmentId":12,"addr":"北京市北京市东城区","rank":"健康管理师","hospitalId":2,"avatar":"http://192.168.199.235:8080/picture/e8b4b4bdb34705dc46fd0c5f70db8e1b.jpeg","experience":null,"speciality":"从业8年,专注于老年健康问题，服务超好","updateTime":1617939693000,"auditStatus":1,"auditTime":1617268368000,"createTime":null,"frontIdCardUrl":null,"backIdCardUrl":null,"urlList":null,"phone":null},"sysId":19,"drugList":[],"appUser":{"id":19,"userName":"lw12345","nickName":null,"password":null,"sex":0,"phone":null,"avatar":null,"signature":null,"email":null,"emailFlag":null,"isUsed":null,"regTime":null,"updateTime":null,"lastLoginTime":null,"userType":null,"cityNameCn":null,"cityNamePy":null,"birthday":null,"loginCount":null,"regIp":null,"rank":null,"parentId":null,"wxid":null,"provinceCode":null,"cityCode":null,"countyCode":null,"provinceName":null,"cityName":null,"countyName":null,"seerUserId":null,"old":20,"point":null,"appSceneList":null,"token":null,"followStatus":null}}]
     */

    private Object requestId;
    private Object errorLog;
    private int status;
    private String message;
    /**
     * id : 50
     * consultContent : 咨询问题描述1
     * userId : 19
     * status : 1
     * replyContent : 回复信息
     * type : 0
     * createTime : 2021-04-22 16:17:09
     * replyTime : null
     * prescriptionStatus : null
     * diagnosis : null
     * physician : null
     * reason : null
     * urlList : null
     * list : [{"id":null,"healthConsultId":null,"url":"图片url地址1"}]
     * appDoctorInfo : {"id":107,"systemUserId":19,"name":"测试健康管理师19","idCard":"54269","roleId":9,"departmentId":12,"addr":"北京市北京市东城区","rank":"健康管理师","hospitalId":2,"avatar":"http://192.168.199.235:8080/picture/e8b4b4bdb34705dc46fd0c5f70db8e1b.jpeg","experience":null,"speciality":"从业8年,专注于老年健康问题，服务超好","updateTime":1617939693000,"auditStatus":1,"auditTime":1617268368000,"createTime":null,"frontIdCardUrl":null,"backIdCardUrl":null,"urlList":null,"phone":null}
     * sysId : 19
     * drugList : []
     * appUser : {"id":19,"userName":"lw12345","nickName":null,"password":null,"sex":0,"phone":null,"avatar":null,"signature":null,"email":null,"emailFlag":null,"isUsed":null,"regTime":null,"updateTime":null,"lastLoginTime":null,"userType":null,"cityNameCn":null,"cityNamePy":null,"birthday":null,"loginCount":null,"regIp":null,"rank":null,"parentId":null,"wxid":null,"provinceCode":null,"cityCode":null,"countyCode":null,"provinceName":null,"cityName":null,"countyName":null,"seerUserId":null,"old":20,"point":null,"appSceneList":null,"token":null,"followStatus":null}
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
        private String consultContent;
        private int userId;
        private int status;
        private String replyContent;
        private int type;
        private String createTime;
        private String replyTime;
        private int prescriptionStatus;
        private String diagnosis;
        private String physician;
        private String reason;
        private List<String> urlList;
        /**
         * id : 107
         * systemUserId : 19
         * name : 测试健康管理师19
         * idCard : 54269
         * roleId : 9
         * departmentId : 12
         * addr : 北京市北京市东城区
         * rank : 健康管理师
         * hospitalId : 2
         * avatar : http://192.168.199.235:8080/picture/e8b4b4bdb34705dc46fd0c5f70db8e1b.jpeg
         * experience : null
         * speciality : 从业8年,专注于老年健康问题，服务超好
         * updateTime : 1617939693000
         * auditStatus : 1
         * auditTime : 1617268368000
         * createTime : null
         * frontIdCardUrl : null
         * backIdCardUrl : null
         * urlList : null
         * phone : null
         */

        private AppDoctorInfoBean appDoctorInfo;
        private int sysId;
        /**
         * id : 19
         * userName : lw12345
         * nickName : null
         * password : null
         * sex : 0
         * phone : null
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
         * rank : null
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
         * followStatus : null
         */

        private AppUserBean appUser;
        /**
         * id : null
         * healthConsultId : null
         * url : 图片url地址1
         */

        private List<ListBean> list;
        private RecordListBean recordList;
        private List<DrugListBean> drugList;

        public List<DrugListBean> getDrugList() {
            return drugList;
        }

        public void setDrugList(List<DrugListBean> drugList) {
            this.drugList = drugList;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getConsultContent() {
            return consultContent;
        }

        public void setConsultContent(String consultContent) {
            this.consultContent = consultContent;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getReplyContent() {
            return replyContent;
        }

        public void setReplyContent(String replyContent) {
            this.replyContent = replyContent;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getReplyTime() {
            return replyTime;
        }

        public void setReplyTime(String replyTime) {
            this.replyTime = replyTime;
        }

        public int getPrescriptionStatus() {
            return prescriptionStatus;
        }

        public void setPrescriptionStatus(int prescriptionStatus) {
            this.prescriptionStatus = prescriptionStatus;
        }

        public String getDiagnosis() {
            return diagnosis;
        }

        public void setDiagnosis(String diagnosis) {
            this.diagnosis = diagnosis;
        }

        public String getPhysician() {
            return physician;
        }

        public void setPhysician(String physician) {
            this.physician = physician;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public List<String> getUrlList() {
            return urlList;
        }

        public void setUrlList(List<String> urlList) {
            this.urlList = urlList;
        }

        public AppDoctorInfoBean getAppDoctorInfo() {
            return appDoctorInfo;
        }

        public void setAppDoctorInfo(AppDoctorInfoBean appDoctorInfo) {
            this.appDoctorInfo = appDoctorInfo;
        }

        public int getSysId() {
            return sysId;
        }

        public void setSysId(int sysId) {
            this.sysId = sysId;
        }

        public AppUserBean getAppUser() {
            return appUser;
        }

        public void setAppUser(AppUserBean appUser) {
            this.appUser = appUser;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public RecordListBean getRecordList() {
            return recordList;
        }

        public void setRecordList(RecordListBean recordList) {
            this.recordList = recordList;
        }

        public static class AppDoctorInfoBean implements Serializable{
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
            private String  experience;
            private String speciality;
            private long updateTime;
            private int auditStatus;
            private long auditTime;
            private String createTime;
            private String frontIdCardUrl;
            private String backIdCardUrl;
            private Object urlList;
            private String phone;

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

            public String getExperience() {
                return experience;
            }

            public void setExperience(String experience) {
                this.experience = experience;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
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

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public Object getUrlList() {
                return urlList;
            }

            public void setUrlList(Object urlList) {
                this.urlList = urlList;
            }

            public String getPhone() {
                return phone;
            }
        }

        public static class AppUserBean implements Serializable{
            private int id;
            private String userName;
            private String nickName;
            private Object password;
            private int sex;
            private Object phone;
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
            private Object rank;
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

            public Object getPhone() {
                return phone;
            }

            public void setPhone(Object phone) {
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

            public Object getRank() {
                return rank;
            }

            public void setRank(Object rank) {
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

            public Object getFollowStatus() {
                return followStatus;
            }

            public void setFollowStatus(Object followStatus) {
                this.followStatus = followStatus;
            }
        }

        public static class ListBean implements Serializable{
            private int id;
            private int healthConsultId;
            private String url;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getHealthConsultId() {
                return healthConsultId;
            }

            public void setHealthConsultId(int healthConsultId) {
                this.healthConsultId = healthConsultId;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }

    public static class RecordListBean implements Serializable{
        private int id;
        private Object healthConsultId;
        private int sysId;
        private int systemUserId;
        private int transferStatus;
        private String transferSysNickName;
        private String transferRank;
        private String transferTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Object getHealthConsultId() {
            return healthConsultId;
        }

        public void setHealthConsultId(Object healthConsultId) {
            this.healthConsultId = healthConsultId;
        }

        public int getSysId() {
            return sysId;
        }

        public void setSysId(int sysId) {
            this.sysId = sysId;
        }

        public int getSystemUserId() {
            return systemUserId;
        }

        public void setSystemUserId(int systemUserId) {
            this.systemUserId = systemUserId;
        }

        public int getTransferStatus() {
            return transferStatus;
        }

        public void setTransferStatus(int transferStatus) {
            this.transferStatus = transferStatus;
        }

        public String getTransferSysNickName() {
            return transferSysNickName;
        }

        public void setTransferSysNickName(String transferSysNickName) {
            this.transferSysNickName = transferSysNickName;
        }

        public String getTransferRank() {
            return transferRank;
        }

        public void setTransferRank(String transferRank) {
            this.transferRank = transferRank;
        }

        public String getTransferTime() {
            return transferTime;
        }

        public void setTransferTime(String transferTime) {
            this.transferTime = transferTime;
        }
    }
    public static class DrugListBean implements Serializable{
        private int id;
        private String name;
        private int number;
        private String unit;
        private String useMode;
        private String useTime;
        private String useFrequency;
        private int healthConsultId;

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

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public String getUseMode() {
            return useMode;
        }

        public void setUseMode(String useMode) {
            this.useMode = useMode;
        }

        public String getUseTime() {
            return useTime;
        }

        public void setUseTime(String useTime) {
            this.useTime = useTime;
        }

        public String getUseFrequency() {
            return useFrequency;
        }

        public void setUseFrequency(String useFrequency) {
            this.useFrequency = useFrequency;
        }

        public int getHealthConsultId() {
            return healthConsultId;
        }

        public void setHealthConsultId(int healthConsultId) {
            this.healthConsultId = healthConsultId;
        }
    }
}
