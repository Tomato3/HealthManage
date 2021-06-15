package com.example.healthmanage.ui.activity.consultation.response;

import java.util.List;

public class DoctordepartMentResponse {

    /**
     * requestId : null
     * errorLog : null
     * status : 0
     * message : 成功
     * data : [{"doctorName":"178556","departmentName":"外科","rank":"健康管理师","hospitalName":"苏州九龙医院","phone":"18852407703"},{"doctorName":"测试健康管理师19","departmentName":"骨科","rank":"健康管理师","hospitalName":"苏州独墅湖医院","phone":"13584869132"},{"doctorName":"发卡量","departmentName":"妇产科","rank":"健康营养师","hospitalName":"苏州市立医院","phone":"13953185295"},{"doctorName":"发了农","departmentName":"肿瘤科","rank":"健康护理师","hospitalName":"苏州大学附属第一医院(总院)","phone":"15738276815"},{"doctorName":"啊加快","departmentName":null,"rank":"医师","hospitalName":"苏州大学附属医院","phone":"18753172031"},{"doctorName":"测试333","departmentName":null,"rank":"医师","hospitalName":null,"phone":"13789567898"},{"doctorName":"测试1","departmentName":null,"rank":"医师","hospitalName":null,"phone":"13567894567"}]
     */

    private Object requestId;
    private Object errorLog;
    private int status;
    private String message;
    /**
     * doctorName : 178556
     * departmentName : 外科
     * rank : 健康管理师
     * hospitalName : 苏州九龙医院
     * phone : 18852407703
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
        private String doctorName;
        private String departmentName;
        private String rank;
        private String hospitalName;
        private String phone;
        private String avatar;
        private int sysId;
        private boolean isSelect;

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
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
