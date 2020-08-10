package com.example.healthmanage.bean;

import java.util.List;

public class MyMemberResponse {


    /**
     * requestId : null
     * errorLog : null
     * status : 0
     * message : 会员刷新成功
     * data : [{"id":68,"userName":"bb_123","nickName":null,"password":"472edc8f204c61fd235e9fdafbd9f8af","sex":0,"phone":"18668967663","avatar":null,"signature":null,"email":null,"emailFlag":0,"isUsed":0,"regTime":null,"updateTime":null,"lastLoginTime":null,"userType":0,"cityNameCn":null,"cityNamePy":null,"birthday":null,"loginCount":0,"regIp":null,"rank":0,"parentId":0,"wxid":null,"provinceCode":null,"cityCode":null,"countyCode":null,"provinceName":null,"cityName":null,"countyName":null,"point":"0","token":null,"followStatus":0},{"id":69,"userName":"mmdd_000","nickName":null,"password":"92c7dbde8afbf5ba804dbd3425d293e9","sex":0,"phone":"15165175008","avatar":null,"signature":null,"email":null,"emailFlag":0,"isUsed":0,"regTime":null,"updateTime":null,"lastLoginTime":null,"userType":0,"cityNameCn":null,"cityNamePy":null,"birthday":null,"loginCount":0,"regIp":null,"rank":0,"parentId":0,"wxid":null,"provinceCode":null,"cityCode":null,"countyCode":null,"provinceName":null,"cityName":null,"countyName":null,"point":"0","token":null,"followStatus":0},{"id":87,"userName":"马涛","nickName":"艾欧尼亚","password":"b484521fc8b5627675505768cef14930","sex":0,"phone":"18951108098","avatar":null,"signature":null,"email":null,"emailFlag":0,"isUsed":0,"regTime":null,"updateTime":null,"lastLoginTime":null,"userType":0,"cityNameCn":null,"cityNamePy":null,"birthday":null,"loginCount":0,"regIp":null,"rank":1,"parentId":0,"wxid":null,"provinceCode":null,"cityCode":null,"countyCode":null,"provinceName":null,"cityName":null,"countyName":null,"point":"0","token":null,"followStatus":0},{"id":88,"userName":"马良","nickName":"德玛西亚","password":"2e5c95de8d402d6d989fb1741b94967d","sex":1,"phone":"15738276815","avatar":null,"signature":null,"email":"799127234@qq.com","emailFlag":0,"isUsed":0,"regTime":null,"updateTime":null,"lastLoginTime":null,"userType":0,"cityNameCn":null,"cityNamePy":null,"birthday":null,"loginCount":0,"regIp":null,"rank":2,"parentId":0,"wxid":null,"provinceCode":null,"cityCode":null,"countyCode":null,"provinceName":null,"cityName":null,"countyName":null,"point":"0","token":null,"followStatus":0}]
     */

    private Object requestId;
    private Object errorLog;
    private int status;
    private String message;
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
        /**
         * id : 68
         * userName : bb_123
         * nickName : null
         * password : 472edc8f204c61fd235e9fdafbd9f8af
         * sex : 0
         * phone : 18668967663
         * avatar : null
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
         * point : 0
         * token : null
         * followStatus : 0
         */

        private int id;
        private String userName;
        private String nickName;
        private String password;
        private int sex;
        private String phone;
        private Object avatar;
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
        private String point;
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

        public String getPoint() {
            return point;
        }

        public void setPoint(String point) {
            this.point = point;
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
