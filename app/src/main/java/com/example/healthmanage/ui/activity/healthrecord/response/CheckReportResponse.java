package com.example.healthmanage.ui.activity.healthrecord.response;

import java.util.List;

public class CheckReportResponse {

    /**
     * requestId : null
     * errorLog : null
     * status : 0
     * message : 查询成功
     * data : [{"id":2,"userId":19,"reportName":"第二次","checkTime":"2021-03-26","checkOrganization":"苏州","createTime":1616643695000,"updateTime":null,"urlList":null,"appUserReportList":[{"id":4,"checkReportId":2,"url":"http://119.23.187.176:8083/picture/1db41cb87a981bfc84e26af41d7386ba.png"},{"id":5,"checkReportId":2,"url":"http://119.23.187.176:8083/picture/1db41cb87a981bfc84e26af41d7386ba.png"}]},{"id":3,"userId":19,"reportName":"体检报告名称","checkTime":"2021-03-25","checkOrganization":"体检机构","createTime":null,"updateTime":null,"urlList":null,"appUserReportList":[{"id":13,"checkReportId":3,"url":"3"},{"id":14,"checkReportId":3,"url":"3"}]},{"id":4,"userId":19,"reportName":"体检报告名称","checkTime":"2021-03-25","checkOrganization":"体检机构","createTime":1616655123000,"updateTime":null,"urlList":null,"appUserReportList":[{"id":15,"checkReportId":4,"url":"4"},{"id":16,"checkReportId":4,"url":"4"}]},{"id":5,"userId":19,"reportName":"体检报告名称","checkTime":"2021-03-25","checkOrganization":"体检机构","createTime":1616655208000,"updateTime":null,"urlList":null,"appUserReportList":[{"id":17,"checkReportId":5,"url":"5"},{"id":18,"checkReportId":5,"url":"5"}]},{"id":6,"userId":19,"reportName":"体检报告名称","checkTime":"2021-03-25","checkOrganization":"体检机构","createTime":1616655283000,"updateTime":null,"urlList":null,"appUserReportList":[{"id":19,"checkReportId":6,"url":"体检报告图片url0001"},{"id":20,"checkReportId":6,"url":"体检报告图片url0002"}]}]
     */

    private Object requestId;
    private Object errorLog;
    private int status;
    private String message;
    /**
     * id : 2
     * userId : 19
     * reportName : 第二次
     * checkTime : 2021-03-26
     * checkOrganization : 苏州
     * createTime : 1616643695000
     * updateTime : null
     * urlList : null
     * appUserReportList : [{"id":4,"checkReportId":2,"url":"http://119.23.187.176:8083/picture/1db41cb87a981bfc84e26af41d7386ba.png"},{"id":5,"checkReportId":2,"url":"http://119.23.187.176:8083/picture/1db41cb87a981bfc84e26af41d7386ba.png"}]
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
        private String reportName;
        private String checkTime;
        private String checkOrganization;
        private long createTime;
        private Object updateTime;
        private Object urlList;
        /**
         * id : 4
         * checkReportId : 2
         * url : http://119.23.187.176:8083/picture/1db41cb87a981bfc84e26af41d7386ba.png
         */

        private List<AppUserReportListBean> appUserReportList;

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

        public String getReportName() {
            return reportName;
        }

        public void setReportName(String reportName) {
            this.reportName = reportName;
        }

        public String getCheckTime() {
            return checkTime;
        }

        public void setCheckTime(String checkTime) {
            this.checkTime = checkTime;
        }

        public String getCheckOrganization() {
            return checkOrganization;
        }

        public void setCheckOrganization(String checkOrganization) {
            this.checkOrganization = checkOrganization;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public Object getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Object updateTime) {
            this.updateTime = updateTime;
        }

        public Object getUrlList() {
            return urlList;
        }

        public void setUrlList(Object urlList) {
            this.urlList = urlList;
        }

        public List<AppUserReportListBean> getAppUserReportList() {
            return appUserReportList;
        }

        public void setAppUserReportList(List<AppUserReportListBean> appUserReportList) {
            this.appUserReportList = appUserReportList;
        }

        public static class AppUserReportListBean {
            private int id;
            private int checkReportId;
            private String url;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getCheckReportId() {
                return checkReportId;
            }

            public void setCheckReportId(int checkReportId) {
                this.checkReportId = checkReportId;
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
