package com.example.healthmanage.ui.activity.qualification.response;

import java.util.List;

public class HospitalResponse {

    /**
     * requestId : null
     * errorLog : null
     * status : 0
     * message : 查询成功
     * data : [{"id":1,"name":"xx医院","typeId":1,"createTime":1615975651000,"updateTime":null,"avatar":"https://dss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2519824424,1132423651&fm=26&gp=0.jpg","addr":"苏州xxxx","phone":"18801234567","content":"富文本","provinceId":null,"cityId":null,"countyId":null},{"id":2,"name":"测试","typeId":1,"createTime":1616036179000,"updateTime":null,"avatar":"https://dss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2519824424,1132423651&fm=26&gp=0.jpg","addr":"测试地址","phone":"18812349874","content":"<p><img src=\"http://localhost:8088/YskJudgeSystem/testPicture/202103181056178yeqs.png\" style=\"max-width: 100%;\"/>测试<\/p>","provinceId":110000,"cityId":110100,"countyId":110101},{"id":3,"name":"测试1","typeId":1,"createTime":1616036371000,"updateTime":1616052341000,"avatar":"https://dss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2519824424,1132423651&fm=26&gp=0.jpg","addr":"测试地址11","phone":"123456789000","content":"<p>123qqw<\/p>","provinceId":110000,"cityId":110100,"countyId":110114}]
     */

    private Object requestId;
    private Object errorLog;
    private int status;
    private String message;
    /**
     * id : 1
     * name : xx医院
     * typeId : 1
     * createTime : 1615975651000
     * updateTime : null
     * avatar : https://dss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2519824424,1132423651&fm=26&gp=0.jpg
     * addr : 苏州xxxx
     * phone : 18801234567
     * content : 富文本
     * provinceId : null
     * cityId : null
     * countyId : null
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
        private Object provinceId;
        private Object cityId;
        private Object countyId;

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

        public Object getProvinceId() {
            return provinceId;
        }

        public void setProvinceId(Object provinceId) {
            this.provinceId = provinceId;
        }

        public Object getCityId() {
            return cityId;
        }

        public void setCityId(Object cityId) {
            this.cityId = cityId;
        }

        public Object getCountyId() {
            return countyId;
        }

        public void setCountyId(Object countyId) {
            this.countyId = countyId;
        }
    }
}
