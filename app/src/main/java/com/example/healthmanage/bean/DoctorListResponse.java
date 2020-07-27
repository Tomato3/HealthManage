package com.example.healthmanage.bean;

import java.util.List;

public class DoctorListResponse {

    /**
     * requestId : null
     * errorLog : null
     * status : 0
     * message : 查询成功
     * data : [{"id":20,"name":"王医生","type":0,"speciality":null,"experience":null,"grade":null,"professionalTitle":null,"sittingPlace":null,"avatar":null,"updateTime":null,"departmentId":null,"hospitalId":null,"departId":null},{"id":22,"name":"方医生","type":1,"speciality":null,"experience":null,"grade":null,"professionalTitle":null,"sittingPlace":null,"avatar":null,"updateTime":null,"departmentId":null,"hospitalId":null,"departId":null},{"id":24,"name":"于医生","type":0,"speciality":null,"experience":null,"grade":null,"professionalTitle":null,"sittingPlace":null,"avatar":null,"updateTime":null,"departmentId":null,"hospitalId":null,"departId":null}]
     */

    private Object requestId;
    private Object errorLog;
    private int status;
    private String message;
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
        /**
         * id : 20
         * name : 王医生
         * type : 0
         * speciality : null
         * experience : null
         * grade : null
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
        private Object speciality;
        private Object experience;
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

        public Object getSpeciality() {
            return speciality;
        }

        public void setSpeciality(Object speciality) {
            this.speciality = speciality;
        }

        public Object getExperience() {
            return experience;
        }

        public void setExperience(Object experience) {
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
