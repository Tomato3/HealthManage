package com.example.healthmanage.ui.activity.healthrecord.response;

import java.util.List;

public class MedicineBookResponse {

    /**
     * requestId : null
     * errorLog : null
     * status : 0
     * message : 查询成功
     * data : [{"id":4,"userId":19,"name":"病历本","createTime":1617017080000,"list":[{"id":7,"medicalRecordId":4,"url":"adsfsaggsfgsafsdfsfsaf.jpg"}]}]
     */

    private Object requestId;
    private Object errorLog;
    private int status;
    private String message;
    /**
     * id : 4
     * userId : 19
     * name : 病历本
     * createTime : 1617017080000
     * list : [{"id":7,"medicalRecordId":4,"url":"adsfsaggsfgsafsdfsfsaf.jpg"}]
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
        private int userId;
        private String name;
        private String createTime;
        /**
         * id : 7
         * medicalRecordId : 4
         * url : adsfsaggsfgsafsdfsfsaf.jpg
         */

        private List<ListBean> list;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            private int id;
            private int medicalRecordId;
            private String url;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getMedicalRecordId() {
                return medicalRecordId;
            }

            public void setMedicalRecordId(int medicalRecordId) {
                this.medicalRecordId = medicalRecordId;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
