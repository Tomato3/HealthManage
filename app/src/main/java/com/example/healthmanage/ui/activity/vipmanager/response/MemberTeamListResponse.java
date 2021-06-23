package com.example.healthmanage.ui.activity.vipmanager.response;

import java.util.List;

/**
 * desc:
 * date:2021/6/22 13:36
 * author:bWang
 */
public class MemberTeamListResponse {

    /**
     * requestId : null
     * errorLog : null
     * status : 0
     * message : 成功
     * data : [{"id":1,"systemUserId":78,"userId":92,"rank":0,"status":1,"createTime":"2021-06-21 17:13:48","appUser":{"id":92,"userName":"王斌","nickName":"王斌","password":null,"sex":0,"phone":"13584869132","avatar":"http://119.23.187.176:8083/picture/893b0933136318b88aef8db6fdb12ec3.jpg","signature":null,"email":null,"emailFlag":null,"isUsed":null,"regTime":null,"updateTime":null,"lastLoginTime":null,"userType":0,"cityNameCn":null,"cityNamePy":null,"birthday":null,"loginCount":null,"regIp":null,"rank":null,"parentId":null,"wxid":null,"provinceCode":null,"cityCode":null,"countyCode":null,"provinceName":null,"cityName":null,"countyName":null,"seerUserId":null,"old":20,"point":null,"appSceneList":null,"token":null,"followStatus":null}}]
     */

    private Object requestId;
    private Object errorLog;
    private int status;
    private String message;
    /**
     * id : 1
     * systemUserId : 78
     * userId : 92
     * rank : 0
     * status : 1
     * createTime : 2021-06-21 17:13:48
     * appUser : {"id":92,"userName":"王斌","nickName":"王斌","password":null,"sex":0,"phone":"13584869132","avatar":"http://119.23.187.176:8083/picture/893b0933136318b88aef8db6fdb12ec3.jpg","signature":null,"email":null,"emailFlag":null,"isUsed":null,"regTime":null,"updateTime":null,"lastLoginTime":null,"userType":0,"cityNameCn":null,"cityNamePy":null,"birthday":null,"loginCount":null,"regIp":null,"rank":null,"parentId":null,"wxid":null,"provinceCode":null,"cityCode":null,"countyCode":null,"provinceName":null,"cityName":null,"countyName":null,"seerUserId":null,"old":20,"point":null,"appSceneList":null,"token":null,"followStatus":null}
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
        private int id;
        private int systemUserId;
        private int userId;
        private int rank;
        private int status;
        private String createTime;
        private boolean isSelect;
        /**
         * id : 92
         * userName : 王斌
         * nickName : 王斌
         * password : null
         * sex : 0
         * phone : 13584869132
         * avatar : http://119.23.187.176:8083/picture/893b0933136318b88aef8db6fdb12ec3.jpg
         * signature : null
         * email : null
         * emailFlag : null
         * isUsed : null
         * regTime : null
         * updateTime : null
         * lastLoginTime : null
         * userType : 0
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

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

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

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getRank() {
            return rank;
        }

        public void setRank(int rank) {
            this.rank = rank;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public AppUserBean getAppUser() {
            return appUser;
        }

        public void setAppUser(AppUserBean appUser) {
            this.appUser = appUser;
        }

        public static class AppUserBean {
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

            public int getFollowStatus() {
                return followStatus;
            }

            public void setFollowStatus(int followStatus) {
                this.followStatus = followStatus;
            }
        }
    }
}
