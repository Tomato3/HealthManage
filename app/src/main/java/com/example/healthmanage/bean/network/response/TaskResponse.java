package com.example.healthmanage.bean.network.response;

import java.util.List;

public class TaskResponse {

    /**
     * requestId : null
     * errorLog : null
     * status : 0
     * message : 当前医生健康异常任务查询成功
     * data : {"total":3,"rows":[{"id":83,"userId":92,"title":"王斌","content":" 蹲踞","managerId":0,"doctorId":19,"doctorReply":null,"status":0,"createTime":"2021-04-12 16:41:50","nursingCreateTime":null,"airCreateTime":null,"transferUser":null,"transferId":null,"processTime":null,"queryTime":null,"appUser":{"id":83,"userName":"王斌","nickName":"王斌","password":null,"sex":0,"phone":"13584869132","avatar":"http://119.23.187.176:8083/picture/893b0933136318b88aef8db6fdb12ec3.jpg","signature":null,"email":null,"emailFlag":null,"isUsed":null,"regTime":null,"updateTime":null,"lastLoginTime":null,"userType":4,"cityNameCn":null,"cityNamePy":null,"birthday":null,"loginCount":null,"regIp":null,"rank":2,"parentId":null,"wxid":null,"provinceCode":null,"cityCode":null,"countyCode":null,"provinceName":null,"cityName":null,"countyName":null,"seerUserId":null,"old":20,"point":null,"appSceneList":null,"token":null,"followStatus":0},"mangerName":null,"doctorName":null},{"id":82,"userId":92,"title":"王斌","content":"附近吧办法","managerId":0,"doctorId":19,"doctorReply":null,"status":0,"createTime":"2021-04-12 16:02:41","nursingCreateTime":null,"airCreateTime":null,"transferUser":null,"transferId":null,"processTime":null,"queryTime":null,"appUser":{"id":82,"userName":"王斌","nickName":"王斌","password":null,"sex":0,"phone":"13584869132","avatar":"http://119.23.187.176:8083/picture/893b0933136318b88aef8db6fdb12ec3.jpg","signature":null,"email":null,"emailFlag":null,"isUsed":null,"regTime":null,"updateTime":null,"lastLoginTime":null,"userType":4,"cityNameCn":null,"cityNamePy":null,"birthday":null,"loginCount":null,"regIp":null,"rank":2,"parentId":null,"wxid":null,"provinceCode":null,"cityCode":null,"countyCode":null,"provinceName":null,"cityName":null,"countyName":null,"seerUserId":null,"old":20,"point":null,"appSceneList":null,"token":null,"followStatus":0},"mangerName":null,"doctorName":null}]}
     */

    private Object requestId;
    private Object errorLog;
    private int status;
    private String message;
    /**
     * total : 3
     * rows : [{"id":83,"userId":92,"title":"王斌","content":" 蹲踞","managerId":0,"doctorId":19,"doctorReply":null,"status":0,"createTime":"2021-04-12 16:41:50","nursingCreateTime":null,"airCreateTime":null,"transferUser":null,"transferId":null,"processTime":null,"queryTime":null,"appUser":{"id":83,"userName":"王斌","nickName":"王斌","password":null,"sex":0,"phone":"13584869132","avatar":"http://119.23.187.176:8083/picture/893b0933136318b88aef8db6fdb12ec3.jpg","signature":null,"email":null,"emailFlag":null,"isUsed":null,"regTime":null,"updateTime":null,"lastLoginTime":null,"userType":4,"cityNameCn":null,"cityNamePy":null,"birthday":null,"loginCount":null,"regIp":null,"rank":2,"parentId":null,"wxid":null,"provinceCode":null,"cityCode":null,"countyCode":null,"provinceName":null,"cityName":null,"countyName":null,"seerUserId":null,"old":20,"point":null,"appSceneList":null,"token":null,"followStatus":0},"mangerName":null,"doctorName":null},{"id":82,"userId":92,"title":"王斌","content":"附近吧办法","managerId":0,"doctorId":19,"doctorReply":null,"status":0,"createTime":"2021-04-12 16:02:41","nursingCreateTime":null,"airCreateTime":null,"transferUser":null,"transferId":null,"processTime":null,"queryTime":null,"appUser":{"id":82,"userName":"王斌","nickName":"王斌","password":null,"sex":0,"phone":"13584869132","avatar":"http://119.23.187.176:8083/picture/893b0933136318b88aef8db6fdb12ec3.jpg","signature":null,"email":null,"emailFlag":null,"isUsed":null,"regTime":null,"updateTime":null,"lastLoginTime":null,"userType":4,"cityNameCn":null,"cityNamePy":null,"birthday":null,"loginCount":null,"regIp":null,"rank":2,"parentId":null,"wxid":null,"provinceCode":null,"cityCode":null,"countyCode":null,"provinceName":null,"cityName":null,"countyName":null,"seerUserId":null,"old":20,"point":null,"appSceneList":null,"token":null,"followStatus":0},"mangerName":null,"doctorName":null}]
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
        private int total;
        /**
         * id : 83
         * userId : 92
         * title : 王斌
         * content :  蹲踞
         * managerId : 0
         * doctorId : 19
         * doctorReply : null
         * status : 0
         * createTime : 2021-04-12 16:41:50
         * nursingCreateTime : null
         * airCreateTime : null
         * transferUser : null
         * transferId : null
         * processTime : null
         * queryTime : null
         * appUser : {"id":83,"userName":"王斌","nickName":"王斌","password":null,"sex":0,"phone":"13584869132","avatar":"http://119.23.187.176:8083/picture/893b0933136318b88aef8db6fdb12ec3.jpg","signature":null,"email":null,"emailFlag":null,"isUsed":null,"regTime":null,"updateTime":null,"lastLoginTime":null,"userType":4,"cityNameCn":null,"cityNamePy":null,"birthday":null,"loginCount":null,"regIp":null,"rank":2,"parentId":null,"wxid":null,"provinceCode":null,"cityCode":null,"countyCode":null,"provinceName":null,"cityName":null,"countyName":null,"seerUserId":null,"old":20,"point":null,"appSceneList":null,"token":null,"followStatus":0}
         * mangerName : null
         * doctorName : null
         */

        private List<RowsBean> rows;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<RowsBean> getRows() {
            return rows;
        }

        public void setRows(List<RowsBean> rows) {
            this.rows = rows;
        }

        public static class RowsBean {
            private int id;
            private int userId;
            private String title;
            private String content;
            private int managerId;
            private int doctorId;
            private String doctorReply;
            private int status;
            private String createTime;
            private Object nursingCreateTime;
            private Object airCreateTime;
            private Object transferUser;
            private Object transferId;
            private Object processTime;
            private Object queryTime;
            /**
             * id : 83
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
             * userType : 4
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
             * followStatus : 0
             */

            private AppUserBean appUser;
            private Object mangerName;
            private Object doctorName;

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

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getManagerId() {
                return managerId;
            }

            public void setManagerId(int managerId) {
                this.managerId = managerId;
            }

            public int getDoctorId() {
                return doctorId;
            }

            public void setDoctorId(int doctorId) {
                this.doctorId = doctorId;
            }

            public String getDoctorReply() {
                return doctorReply;
            }

            public void setDoctorReply(String doctorReply) {
                this.doctorReply = doctorReply;
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

            public Object getNursingCreateTime() {
                return nursingCreateTime;
            }

            public void setNursingCreateTime(Object nursingCreateTime) {
                this.nursingCreateTime = nursingCreateTime;
            }

            public Object getAirCreateTime() {
                return airCreateTime;
            }

            public void setAirCreateTime(Object airCreateTime) {
                this.airCreateTime = airCreateTime;
            }

            public Object getTransferUser() {
                return transferUser;
            }

            public void setTransferUser(Object transferUser) {
                this.transferUser = transferUser;
            }

            public Object getTransferId() {
                return transferId;
            }

            public void setTransferId(Object transferId) {
                this.transferId = transferId;
            }

            public Object getProcessTime() {
                return processTime;
            }

            public void setProcessTime(Object processTime) {
                this.processTime = processTime;
            }

            public Object getQueryTime() {
                return queryTime;
            }

            public void setQueryTime(Object queryTime) {
                this.queryTime = queryTime;
            }

            public AppUserBean getAppUser() {
                return appUser;
            }

            public void setAppUser(AppUserBean appUser) {
                this.appUser = appUser;
            }

            public Object getMangerName() {
                return mangerName;
            }

            public void setMangerName(Object mangerName) {
                this.mangerName = mangerName;
            }

            public Object getDoctorName() {
                return doctorName;
            }

            public void setDoctorName(Object doctorName) {
                this.doctorName = doctorName;
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
        }
    }
}
