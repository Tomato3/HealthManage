package com.example.healthmanage.bean.network.response;

public class MemberInfoResponse {

    /**
     * requestId : null
     * errorLog : null
     * status : 0
     * message :
     * data : {"id":92,"userName":null,"nickName":"王斌","password":"2e5c95de8d402d6d989fb1741b94967d","sex":0,"phone":"13584869132","avatar":"http://119.23.187.176:8083/picture/4440e81137718e4279070970f888317e.jpg","signature":null,"email":null,"emailFlag":0,"isUsed":0,"regTime":null,"updateTime":null,"lastLoginTime":null,"userType":0,"cityNameCn":null,"cityNamePy":null,"birthday":null,"loginCount":0,"regIp":null,"rank":0,"parentId":0,"wxid":null,"provinceCode":null,"cityCode":null,"countyCode":null,"provinceName":null,"cityName":null,"countyName":null,"seerUserId":"57b18fdae2474d34a0159e9e3d8badd5","point":"0","appSceneList":null,"token":null,"followStatus":1}
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
         * id : 92
         * userName : null
         * nickName : 王斌
         * password : 2e5c95de8d402d6d989fb1741b94967d
         * sex : 0
         * phone : 13584869132
         * avatar : http://119.23.187.176:8083/picture/4440e81137718e4279070970f888317e.jpg
         * signature : null
         * email : null
         * emailFlag : 0
         * isUsed : 0
         * regTime : null
         * updateTime : null
         * lastLoginTime : null
         * userType : 0
         * cityNameCn : null
         * cityNamePy : null
         * birthday : null
         * loginCount : 0
         * regIp : null
         * rank : 0
         * parentId : 0
         * wxid : null
         * provinceCode : null
         * cityCode : null
         * countyCode : null
         * provinceName : null
         * cityName : null
         * countyName : null
         * seerUserId : 57b18fdae2474d34a0159e9e3d8badd5
         * point : 0
         * appSceneList : null
         * token : null
         * followStatus : 1
         */

        private int id;
        private Object userName;
        private String nickName;
        private String password;
        private int sex;
        private String phone;
        private String avatar;
        private Object signature;
        private Object email;
        private int emailFlag;
        private int isUsed;
        private Object regTime;
        private Object updateTime;
        private Object lastLoginTime;
        private int userType;
        private Object cityNameCn;
        private Object cityNamePy;
        private Object birthday;
        private int loginCount;
        private Object regIp;
        private int rank;
        private int parentId;
        private Object wxid;
        private Object provinceCode;
        private Object cityCode;
        private Object countyCode;
        private Object provinceName;
        private Object cityName;
        private Object countyName;
        private String seerUserId;
        private String point;
        private Object appSceneList;
        private Object token;
        private int followStatus;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Object getUserName() {
            return userName;
        }

        public void setUserName(Object userName) {
            this.userName = userName;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
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

        public int getEmailFlag() {
            return emailFlag;
        }

        public void setEmailFlag(int emailFlag) {
            this.emailFlag = emailFlag;
        }

        public int getIsUsed() {
            return isUsed;
        }

        public void setIsUsed(int isUsed) {
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

        public int getLoginCount() {
            return loginCount;
        }

        public void setLoginCount(int loginCount) {
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

        public int getParentId() {
            return parentId;
        }

        public void setParentId(int parentId) {
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

        public String getSeerUserId() {
            return seerUserId;
        }

        public void setSeerUserId(String seerUserId) {
            this.seerUserId = seerUserId;
        }

        public String getPoint() {
            return point;
        }

        public void setPoint(String point) {
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
}
