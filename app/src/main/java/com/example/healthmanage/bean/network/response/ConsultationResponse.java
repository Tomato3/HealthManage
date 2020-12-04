package com.example.healthmanage.bean.network.response;

import java.util.List;

public class ConsultationResponse {

    /**
     * requestId : null
     * errorLog : null
     * status : 0
     * message : 查询成功
     * data : {"total":3,"rows":[{"id":15,"content":"起床了吗","fromUid":92,"toUid":1,"createTime":"2020-09-03 16:29:02","status":0},{"id":13,"content":"吃饭了吗","fromUid":92,"toUid":1,"createTime":"2020-09-03 15:18:18","status":0},{"id":14,"content":"睡觉了吗","fromUid":92,"toUid":1,"createTime":"2020-09-03 16:28:37","status":1}]}
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
         * total : 3
         * rows : [{"id":15,"content":"起床了吗","fromUid":92,"toUid":1,"createTime":"2020-09-03 16:29:02","status":0},{"id":13,"content":"吃饭了吗","fromUid":92,"toUid":1,"createTime":"2020-09-03 15:18:18","status":0},{"id":14,"content":"睡觉了吗","fromUid":92,"toUid":1,"createTime":"2020-09-03 16:28:37","status":1}]
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
             * id : 15
             * content : 起床了吗
             * fromUid : 92
             * toUid : 1
             * createTime : 2020-09-03 16:29:02
             * status : 0
             */

            private int id;
            private String content;
            private int fromUid;
            private int toUid;
            private String createTime;
            private int status;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getFromUid() {
                return fromUid;
            }

            public void setFromUid(int fromUid) {
                this.fromUid = fromUid;
            }

            public int getToUid() {
                return toUid;
            }

            public void setToUid(int toUid) {
                this.toUid = toUid;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }
        }
    }
}
