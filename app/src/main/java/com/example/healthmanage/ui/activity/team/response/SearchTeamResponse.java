package com.example.healthmanage.ui.activity.team.response;

public class SearchTeamResponse {


    /**
     * requestId : null
     * errorLog : null
     * status : 0
     * message : 成功
     * data : {"doctorName":null,"departmentName":"骨科","rank":"健康管理师","hospitalName":null,"phone":"13584869132","avatar":"http://192.168.199.235:8080/picture/e8b4b4bdb34705dc46fd0c5f70db8e1b.jpeg","sysId":19}
     */

    private Object requestId;
    private Object errorLog;
    private int status;
    private String message;
    /**
     * doctorName : null
     * departmentName : 骨科
     * rank : 健康管理师
     * hospitalName : null
     * phone : 13584869132
     * avatar : http://192.168.199.235:8080/picture/e8b4b4bdb34705dc46fd0c5f70db8e1b.jpeg
     * sysId : 19
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
        private String name;
        private String doctorName;
        private String departmentName;
        private String rank;
        private String hospitalName;
        private String phone;
        private String avatar;
        private int sysId;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDoctorName() {
            return doctorName;
        }

        public void setDoctorName(String doctorName) {
            this.doctorName = doctorName;
        }

        public String getDepartmentName() {
            return departmentName;
        }

        public void setDepartmentName(String departmentName) {
            this.departmentName = departmentName;
        }

        public String getRank() {
            return rank;
        }

        public void setRank(String rank) {
            this.rank = rank;
        }

        public String getHospitalName() {
            return hospitalName;
        }

        public void setHospitalName(String hospitalName) {
            this.hospitalName = hospitalName;
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

        public int getSysId() {
            return sysId;
        }

        public void setSysId(int sysId) {
            this.sysId = sysId;
        }
    }
}
