package com.example.healthmanage.bean.network.response;

import java.util.List;

public class TaskResponse {


    /**
     * requestId : null
     * errorLog : null
     * status : 0
     * message : 健康异常任务查询成功
     * data : {"total":7,"rows":[{"id":24,"userId":28,"title":"范总","content":"第一次测量","managerId":19,"doctorId":null,"doctorReply":null,"status":0,"createTime":"2020-08-27 14:28:59","doctorCreateTime":null,"mangerName":null,"doctorName":null},{"id":23,"userId":92,"title":"王斌","content":"发发发","managerId":19,"doctorId":null,"doctorReply":null,"status":0,"createTime":"2020-08-12 18:21:59","doctorCreateTime":null,"mangerName":null,"doctorName":null}]}
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
         * total : 7
         * rows : [{"id":24,"userId":28,"title":"范总","content":"第一次测量","managerId":19,"doctorId":null,"doctorReply":null,"status":0,"createTime":"2020-08-27 14:28:59","doctorCreateTime":null,"mangerName":null,"doctorName":null},{"id":23,"userId":92,"title":"王斌","content":"发发发","managerId":19,"doctorId":null,"doctorReply":null,"status":0,"createTime":"2020-08-12 18:21:59","doctorCreateTime":null,"mangerName":null,"doctorName":null}]
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
             * id : 24
             * userId : 28
             * title : 范总
             * content : 第一次测量
             * managerId : 19
             * doctorId : null
             * doctorReply : null
             * status : 0
             * createTime : 2020-08-27 14:28:59
             * doctorCreateTime : null
             * mangerName : null
             * doctorName : null
             */

            private int id;
            private int userId;
            private String title;
            private String content;
            private int managerId;
            private Object doctorId;
            private Object doctorReply;
            private int status;
            private String createTime;
            private Object doctorCreateTime;
            private Object mangerName;
            private Object doctorName;

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

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getManagerId() {
                return managerId;
            }

            public void setManagerId(int managerId) {
                this.managerId = managerId;
            }

            public Object getDoctorId() {
                return doctorId;
            }

            public void setDoctorId(Object doctorId) {
                this.doctorId = doctorId;
            }

            public Object getDoctorReply() {
                return doctorReply;
            }

            public void setDoctorReply(Object doctorReply) {
                this.doctorReply = doctorReply;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public Object getDoctorCreateTime() {
                return doctorCreateTime;
            }

            public void setDoctorCreateTime(Object doctorCreateTime) {
                this.doctorCreateTime = doctorCreateTime;
            }

            public Object getMangerName() {
                return mangerName;
            }

            public void setMangerName(Object mangerName) {
                this.mangerName = mangerName;
            }

            public Object getDoctorName() {
                return doctorName;
            }

            public void setDoctorName(Object doctorName) {
                this.doctorName = doctorName;
            }
        }
    }
}
