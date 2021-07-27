package com.example.healthmanage.ui.activity.integral.response;

/**
 * desc:
 * date:2021/7/21 14:37
 * author:bWang
 */
public class AddressResponse {

    /**
     * msg : 操作成功
     * code : 200
     * data : {"id":"22","name":"fz1","phone":"13712345678","address":"辽宁省-沈阳市-和平区","detailedAddress":"shsshsh","ifSelect":1,"userId":92,"createTime":"2021-05-31T11:52:18","updateTime":"2021-05-31T15:42:21"}
     */

    private String msg;
    private int code;
    /**
     * id : 22
     * name : fz1
     * phone : 13712345678
     * address : 辽宁省-沈阳市-和平区
     * detailedAddress : shsshsh
     * ifSelect : 1
     * userId : 92
     * createTime : 2021-05-31T11:52:18
     * updateTime : 2021-05-31T15:42:21
     */

    private DataBean data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String id;
        private String name;
        private String phone;
        private String address;
        private String detailedAddress;
        private int ifSelect;
        private int userId;
        private String createTime;
        private String updateTime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getDetailedAddress() {
            return detailedAddress;
        }

        public void setDetailedAddress(String detailedAddress) {
            this.detailedAddress = detailedAddress;
        }

        public int getIfSelect() {
            return ifSelect;
        }

        public void setIfSelect(int ifSelect) {
            this.ifSelect = ifSelect;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }
    }
}
