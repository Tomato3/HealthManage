package com.example.healthmanage.bean.network.response;

import java.util.List;

public class ServicePlanResponse {


    /**
     * requestId : null
     * errorLog : null
     * status : 0
     * message : 服务计划查询成功
     * data : {"total":21,"rows":[{"id":1,"createUserId":7,"targetUserId":null,"serviceUserId":19,"serviceProjectIds":"1,2","serviceProjectNames":"服务项目一，服务项目二","type":2,"createTime":"2020-08-11 16:02:06","targetUserName":"服务目标对象1","planTime":null,"planPlace":null},{"id":2,"createUserId":7,"targetUserId":null,"serviceUserId":19,"serviceProjectIds":"1,3","serviceProjectNames":"服务项目一，服务项目三","type":2,"createTime":"2020-08-11 16:02:06","targetUserName":"服务目标对象1","planTime":null,"planPlace":null},{"id":3,"createUserId":7,"targetUserId":null,"serviceUserId":19,"serviceProjectIds":"1,3","serviceProjectNames":"服务项目一，服务项目三","type":2,"createTime":null,"targetUserName":"服务目标对象1","planTime":null,"planPlace":null}]}
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
         * total : 21
         * rows : [{"id":1,"createUserId":7,"targetUserId":null,"serviceUserId":19,"serviceProjectIds":"1,2","serviceProjectNames":"服务项目一，服务项目二","type":2,"createTime":"2020-08-11 16:02:06","targetUserName":"服务目标对象1","planTime":null,"planPlace":null},{"id":2,"createUserId":7,"targetUserId":null,"serviceUserId":19,"serviceProjectIds":"1,3","serviceProjectNames":"服务项目一，服务项目三","type":2,"createTime":"2020-08-11 16:02:06","targetUserName":"服务目标对象1","planTime":null,"planPlace":null},{"id":3,"createUserId":7,"targetUserId":null,"serviceUserId":19,"serviceProjectIds":"1,3","serviceProjectNames":"服务项目一，服务项目三","type":2,"createTime":null,"targetUserName":"服务目标对象1","planTime":null,"planPlace":null}]
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
             * id : 1
             * createUserId : 7
             * targetUserId : null
             * serviceUserId : 19
             * serviceProjectIds : 1,2
             * serviceProjectNames : 服务项目一，服务项目二
             * type : 2
             * createTime : 2020-08-11 16:02:06
             * targetUserName : 服务目标对象1
             * planTime : null
             * planPlace : null
             */

            private int id;
            private int createUserId;
            private Object targetUserId;
            private int serviceUserId;
            private String serviceProjectIds;
            private String serviceProjectNames;
            private int type;
            private String createTime;
            private String targetUserName;
            private String planTime;
            private String planPlace;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getCreateUserId() {
                return createUserId;
            }

            public void setCreateUserId(int createUserId) {
                this.createUserId = createUserId;
            }

            public Object getTargetUserId() {
                return targetUserId;
            }

            public void setTargetUserId(Object targetUserId) {
                this.targetUserId = targetUserId;
            }

            public int getServiceUserId() {
                return serviceUserId;
            }

            public void setServiceUserId(int serviceUserId) {
                this.serviceUserId = serviceUserId;
            }

            public String getServiceProjectIds() {
                return serviceProjectIds;
            }

            public void setServiceProjectIds(String serviceProjectIds) {
                this.serviceProjectIds = serviceProjectIds;
            }

            public String getServiceProjectNames() {
                return serviceProjectNames;
            }

            public void setServiceProjectNames(String serviceProjectNames) {
                this.serviceProjectNames = serviceProjectNames;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getTargetUserName() {
                return targetUserName;
            }

            public void setTargetUserName(String targetUserName) {
                this.targetUserName = targetUserName;
            }

            public String getPlanTime() {
                return planTime;
            }

            public void setPlanTime(String planTime) {
                this.planTime = planTime;
            }

            public String getPlanPlace() {
                return planPlace;
            }

            public void setPlanPlace(String planPlace) {
                this.planPlace = planPlace;
            }
        }
    }
}
