package com.example.healthmanage.ui.activity.famousDoctorHall.response;

import java.util.List;

/**
 * desc:
 * date:2021/7/1 11:11
 * author:bWang
 */
public class HospitalListResponse {

    /**
     * requestId : null
     * errorLog : null
     * status : 0
     * message : 成功
     * data : [{"id":4,"name":"苏州大学附属第一医院(总院)","typeId":1,"createTime":1617343532000,"updateTime":null,"avatar":"http://119.23.187.176:8086/YskJudgeSystem/testPicture/20210402140454mkynx.png","addr":"姑苏区平海路899号","phone":"0512-65223637","content":"<p>这是医院简介<\/p>","provinceId":320000,"cityId":320500,"countyId":320508,"appHospitalType":{"id":1,"name":"三甲","isUsed":0,"createTime":null,"updateTime":null}},{"id":1,"name":"苏州九龙医院","typeId":1,"createTime":1615975651000,"updateTime":1617343395000,"avatar":"https://dss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2519824424,1132423651&fm=26&gp=0.jpg","addr":"苏州xxxx","phone":"18801234567","content":"<p>富文本<\/p>","provinceId":320000,"cityId":320500,"countyId":320506,"appHospitalType":{"id":1,"name":"三甲","isUsed":0,"createTime":null,"updateTime":null}},{"id":2,"name":"苏州独墅湖医院","typeId":1,"createTime":1616036179000,"updateTime":1617343373000,"avatar":"https://dss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2519824424,1132423651&fm=26&gp=0.jpg","addr":"测试地址","phone":"18812349874","content":"<p><img src=\"http://localhost:8088/YskJudgeSystem/testPicture/202103181056178yeqs.png\" style=\"max-width: 100%;\"/>测试<\/p>","provinceId":110000,"cityId":110100,"countyId":110101,"appHospitalType":{"id":1,"name":"三甲","isUsed":0,"createTime":null,"updateTime":null}},{"id":3,"name":"苏州市立医院","typeId":1,"createTime":1616036371000,"updateTime":1617343364000,"avatar":"https://dss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2519824424,1132423651&fm=26&gp=0.jpg","addr":"测试地址11","phone":"123456789000","content":"<p>苏州市里医院<\/p>","provinceId":320000,"cityId":320500,"countyId":320505,"appHospitalType":{"id":1,"name":"三甲","isUsed":0,"createTime":null,"updateTime":null}},{"id":5,"name":"苏州大学附属医院","typeId":1,"createTime":1617775048000,"updateTime":null,"avatar":"","addr":"姑苏区178号","phone":"052-00880088","content":"<p>这是输入医院简介的地方。<\/p>","provinceId":320000,"cityId":320500,"countyId":320508,"appHospitalType":{"id":1,"name":"三甲","isUsed":0,"createTime":null,"updateTime":null}},{"id":11,"name":"武汉金银潭医院","typeId":1,"createTime":1619421614000,"updateTime":1619434863000,"avatar":"http://localhost:8886/profile/avatar/2021/04/26/b70975ec-de90-4182-beb2-d7fabb293086.png","addr":"武汉","phone":"052-1112211","content":"<p>厉害哦111<\/p>","provinceId":null,"cityId":null,"countyId":null,"appHospitalType":{"id":1,"name":"三甲","isUsed":0,"createTime":null,"updateTime":null}}]
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
