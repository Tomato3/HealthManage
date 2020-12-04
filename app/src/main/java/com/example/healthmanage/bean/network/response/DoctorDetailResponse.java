package com.example.healthmanage.bean.network.response;

public class DoctorDetailResponse {

    /**
     * requestId : null
     * errorLog : null
     * status : 0
     * message : 查询成功
     * data : {"id":25,"name":"马医生","type":null,"speciality":null,"experience":null,"grade":0,"professionalTitle":null,"sittingPlace":null,"avatar":null,"updateTime":null,"departmentId":null,"hospitalId":null,"departId":null}
     */

    private Object requestId;
    private Object errorLog;
    private int status;
    private String message;
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
        /**
         * id : 25
         * name : 马医生
         * type : null
         * speciality : null
         * experience : null
         * grade : 0
         * professionalTitle : null
         * sittingPlace : null
         * avatar : null
         * updateTime : null
         * departmentId : null
         * hospitalId : null
         * departId : null
         */

        private int id;
        private String name;
        private int type;
        private String speciality;
        private String experience;
        private int grade;
        private String professionalTitle;
        private String sittingPlace;
        private String avatar;
        private Object updateTime;
        private Object departmentId;
        private Object hospitalId;
        private Object departId;

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

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getSpeciality() {
            return speciality;
        }

        public void setSpeciality(String speciality) {
            this.speciality = speciality;
        }

        public String getExperience() {
            return experience;
        }

        public void setExperience(String experience) {
            this.experience = experience;
        }

        public int getGrade() {
            return grade;
        }

        public void setGrade(int grade) {
            this.grade = grade;
        }

        public String getProfessionalTitle() {
            return professionalTitle;
        }

        public void setProfessionalTitle(String professionalTitle) {
            this.professionalTitle = professionalTitle;
        }

        public String getSittingPlace() {
            return sittingPlace;
        }

        public void setSittingPlace(String sittingPlace) {
            this.sittingPlace = sittingPlace;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public Object getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Object updateTime) {
            this.updateTime = updateTime;
        }

        public Object getDepartmentId() {
            return departmentId;
        }

        public void setDepartmentId(Object departmentId) {
            this.departmentId = departmentId;
        }

        public Object getHospitalId() {
            return hospitalId;
        }

        public void setHospitalId(Object hospitalId) {
            this.hospitalId = hospitalId;
        }

        public Object getDepartId() {
            return departId;
        }

        public void setDepartId(Object departId) {
            this.departId = departId;
        }
    }
}
