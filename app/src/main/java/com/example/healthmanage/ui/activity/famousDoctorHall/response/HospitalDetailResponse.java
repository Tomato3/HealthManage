package com.example.healthmanage.ui.activity.famousDoctorHall.response;

/**
 * desc:
 * date:2021/7/2 16:57
 * author:bWang
 */
public class HospitalDetailResponse {

    /**
     * requestId : null
     * errorLog : null
     * status : 0
     * message : 成功
     * data : {"id":4,"name":"苏州大学附属第一医院(总院)","typeId":1,"createTime":1617343532000,"updateTime":null,"avatar":"http://119.23.187.176:8086/YskJudgeSystem/testPicture/20210402140454mkynx.png","addr":"姑苏区平海路899号","phone":"0512-65223637","content":"<p>这是医院简介<\/p>","provinceId":320000,"cityId":320500,"countyId":320508,"appHospitalType":{"id":1,"name":"三甲","isUsed":0,"createTime":null,"updateTime":null}}
     */

    private Object requestId;
    private Object errorLog;
    private int status;
    private String message;
    /**
     * id : 4
     * name : 苏州大学附属第一医院(总院)
     * typeId : 1
     * createTime : 1617343532000
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
        private String name;
        private int typeId;
        private long createTime;
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
