package com.example.healthmanage.bean.network.response;

import java.util.List;

public class MyDoctorResponse {


    /**
     * requestId : null
     * errorLog : null
     * status : 0
     * message : 查询成功
     * data : {"total":1,"rows":[{"id":25,"name":"马医生","type":null,"speciality":null,"experience":null,"grade":0,"professionalTitle":null,"sittingPlace":null,"avatar":null,"updateTime":null,"departmentId":null,"hospitalId":null,"departId":null}]}
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
         * total : 1
         * rows : [{"id":25,"name":"马医生","type":null,"speciality":null,"experience":null,"grade":0,"professionalTitle":null,"sittingPlace":null,"avatar":null,"updateTime":null,"departmentId":null,"hospitalId":null,"departId":null}]
         */

        private int total;
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
}
