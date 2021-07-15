package com.example.healthmanage.ui.activity.famousDoctorHall.response;

import java.util.List;

/**
 * desc:
 * date:2021/7/1 10:54
 * author:bWang
 */
public class FamousDoctorListResponse {

    /**
     * requestId : null
     * errorLog : null
     * status : 0
     * message : 成功
     * data : [{"id":130,"systemUserId":79,"name":"吴亦凡","idCard":"320682199201291557","roleId":11,"departmentId":33,"addr":null,"rank":"主任医师","hospitalId":4,"avatar":"http://119.23.187.176:8083/picture/080664505161ba9a7939fd088c343fd8.png","experience":"美利坚合众国","speciality":"妇产科疑难杂症","updateTime":1622710835000,"auditStatus":1,"auditTime":null,"createTime":null,"frontIdCardUrl":null,"backIdCardUrl":null,"urlList":null,"phone":null,"dietitian":null,"doctor":null,"nurse":null,"appHospitalDepartment":{"id":33,"parentId":13,"name":"产科","isUsed":0,"sort":12,"createTime":null,"updateTime":null,"list":null},"appSystemUser":null,"appHospital":{"id":4,"name":"苏州大学附属第一医院(总院)","typeId":1,"createTime":null,"updateTime":null,"avatar":"http://119.23.187.176:8086/YskJudgeSystem/testPicture/20210402140454mkynx.png","addr":"姑苏区平海路899号","phone":"0512-65223637","content":"<p>这是医院简介<\/p>","provinceId":320000,"cityId":320500,"countyId":320508,"appHospitalType":{"id":1,"name":"三甲","isUsed":0,"createTime":null,"updateTime":null}},"consultAmount":0,"followAmount":0}]
     */

    private Object requestId;
    private Object errorLog;
    private int status;
    private String message;
    /**
     * id : 130
     * systemUserId : 79
     * name : 吴亦凡
     * idCard : 320682199201291557
     * roleId : 11
     * departmentId : 33
     * addr : null
     * rank : 主任医师
     * hospitalId : 4
     * avatar : http://119.23.187.176:8083/picture/080664505161ba9a7939fd088c343fd8.png
     * experience : 美利坚合众国
     * speciality : 妇产科疑难杂症
     * updateTime : 1622710835000
     * auditStatus : 1
     * auditTime : null
     * createTime : null
     * frontIdCardUrl : null
     * backIdCardUrl : null
     * urlList : null
     * phone : null
     * dietitian : null
     * doctor : null
     * nurse : null
     * appHospitalDepartment : {"id":33,"parentId":13,"name":"产科","isUsed":0,"sort":12,"createTime":null,"updateTime":null,"list":null}
     * appSystemUser : null
     * appHospital : {"id":4,"name":"苏州大学附属第一医院(总院)","typeId":1,"createTime":null,"updateTime":null,"avatar":"http://119.23.187.176:8086/YskJudgeSystem/testPicture/20210402140454mkynx.png","addr":"姑苏区平海路899号","phone":"0512-65223637","content":"<p>这是医院简介<\/p>","provinceId":320000,"cityId":320500,"countyId":320508,"appHospitalType":{"id":1,"name":"三甲","isUsed":0,"createTime":null,"updateTime":null}}
     * consultAmount : 0
     * followAmount : 0
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
        private String name;
        private String idCard;
        private int roleId;
        private int departmentId;
        private Object addr;
        private String rank;
        private int hospitalId;
        private String avatar;
        private String experience;
        private String speciality;
        private long updateTime;
        private int auditStatus;
        private Object auditTime;
        private Object createTime;
        private Object frontIdCardUrl;
        private Object backIdCardUrl;
        private Object urlList;
        private Object phone;
        private Object dietitian;
        private Object doctor;
        private Object nurse;
        /**
         * id : 33
         * parentId : 13
         * name : 产科
         * isUsed : 0
         * sort : 12
         * createTime : null
         * updateTime : null
         * list : null
         */

        private AppHospitalDepartmentBean appHospitalDepartment;
        private Object appSystemUser;
        /**
         * id : 4
         * name : 苏州大学附属第一医院(总院)
         * typeId : 1
         * createTime : null
         * updateTime : null
         * avatar : http://119.23.187.176:8086/YskJudgeSystem/testPicture/20210402140454mkynx.png
         * addr : 姑苏区平海路899号
         * phone : 0512-65223637
         * content : <p>这是医院简介</p>
         * provinceId : 320000
         * cityId : 320500
         * countyId : 320508
         * appHospitalType : {"id":1,"name":"三甲","isUsed":0,"createTime":null,"updateTime":null}
         */

        private AppHospitalBean appHospital;
        private int consultAmount;
        private int followAmount;

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

        public String getExperience() {
            return experience;
        }

        public void setExperience(String experience) {
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

        public Object getDietitian() {
            return dietitian;
        }

        public void setDietitian(Object dietitian) {
            this.dietitian = dietitian;
        }

        public Object getDoctor() {
            return doctor;
        }

        public void setDoctor(Object doctor) {
            this.doctor = doctor;
        }

        public Object getNurse() {
            return nurse;
        }

        public void setNurse(Object nurse) {
            this.nurse = nurse;
        }

        public AppHospitalDepartmentBean getAppHospitalDepartment() {
            return appHospitalDepartment;
        }

        public void setAppHospitalDepartment(AppHospitalDepartmentBean appHospitalDepartment) {
            this.appHospitalDepartment = appHospitalDepartment;
        }

        public Object getAppSystemUser() {
            return appSystemUser;
        }

        public void setAppSystemUser(Object appSystemUser) {
            this.appSystemUser = appSystemUser;
        }

        public AppHospitalBean getAppHospital() {
            return appHospital;
        }

        public void setAppHospital(AppHospitalBean appHospital) {
            this.appHospital = appHospital;
        }

        public int getConsultAmount() {
            return consultAmount;
        }

        public void setConsultAmount(int consultAmount) {
            this.consultAmount = consultAmount;
        }

        public int getFollowAmount() {
            return followAmount;
        }

        public void setFollowAmount(int followAmount) {
            this.followAmount = followAmount;
        }

        public static class AppHospitalDepartmentBean {
            private int id;
            private int parentId;
            private String name;
            private int isUsed;
            private int sort;
            private Object createTime;
            private Object updateTime;
            private Object list;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getParentId() {
                return parentId;
            }

            public void setParentId(int parentId) {
                this.parentId = parentId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getIsUsed() {
                return isUsed;
            }

            public void setIsUsed(int isUsed) {
                this.isUsed = isUsed;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
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

        public static class AppHospitalBean {
            private int id;
            private String name;
            private int typeId;
            private Object createTime;
            private Object updateTime;
            private String avatar;
            private String addr;
            private String phone;
            private String content;
            private int provinceId;
            private int cityId;
            private int countyId;
            /**
             * id : 1
             * name : 三甲
             * isUsed : 0
             * createTime : null
             * updateTime : null
             */

            private AppHospitalTypeBean appHospitalType;

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

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getAddr() {
                return addr;
            }

            public void setAddr(String addr) {
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

            public AppHospitalTypeBean getAppHospitalType() {
                return appHospitalType;
            }

            public void setAppHospitalType(AppHospitalTypeBean appHospitalType) {
                this.appHospitalType = appHospitalType;
            }

            public static class AppHospitalTypeBean {
                private int id;
                private String name;
                private int isUsed;
                private Object createTime;
                private Object updateTime;

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

                public int getIsUsed() {
                    return isUsed;
                }

                public void setIsUsed(int isUsed) {
                    this.isUsed = isUsed;
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
            }
        }
    }
}
