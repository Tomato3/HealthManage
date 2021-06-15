package com.example.healthmanage.ui.activity.signmember.response;

import java.util.List;

public class SignMemberResponse {

    /**
     * requestId : null
     * errorLog : null
     * status : 0
     * message : 查询成功
     * data : [{"phone":"13775452153","rank":0,"sex":0,"startTime":1617174505000,"endTime":1617174505000,"doctorSignStatus":0,"userSignStatus":0,"id":66,"avatar":"http://119.23.187.176:8083/picture/494901b6bb921601f928c9b9c11a3bf9.jpg","userName":"孙大贵"}]
     */

    private Object requestId;
    private Object errorLog;
    private int status;
    private String message;
    /**
     * phone : 13775452153
     * rank : 0
     * sex : 0
     * startTime : 1617174505000
     * endTime : 1617174505000
     * doctorSignStatus : 0
     * userSignStatus : 0
     * id : 66
     * avatar : http://119.23.187.176:8083/picture/494901b6bb921601f928c9b9c11a3bf9.jpg
     * userName : 孙大贵
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
        private String phone;
        private int rank;
        private int sex;
        private long startTime;
        private long endTime;
        //0签约 1不签约
        private int doctorSignStatus;
        private int userSignStatus;
        private int id;
        private String avatar;
        private String userName;
        private String nickName;
        //自己添加判断字段，0，未签约 1 已签约
//        private int signType;

//        public int getSignType() {
//            return signType;
//        }
//
//        public void setSignType(int signType) {
//            this.signType = signType;
//        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getRank() {
            return rank;
        }

        public void setRank(int rank) {
            this.rank = rank;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public long getStartTime() {
            return startTime;
        }

        public void setStartTime(long startTime) {
            this.startTime = startTime;
        }

        public long getEndTime() {
            return endTime;
        }

        public void setEndTime(long endTime) {
            this.endTime = endTime;
        }

        public int getDoctorSignStatus() {
            return doctorSignStatus;
        }

        public void setDoctorSignStatus(int doctorSignStatus) {
            this.doctorSignStatus = doctorSignStatus;
        }

        public int getUserSignStatus() {
            return userSignStatus;
        }

        public void setUserSignStatus(int userSignStatus) {
            this.userSignStatus = userSignStatus;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
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
    }
}
