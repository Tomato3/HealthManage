package com.example.healthmanage.ui.activity.integral.response;

import java.util.List;

/**
 * desc:
 * date:2021/7/22 16:15
 * author:bWang
 */
public class LogisticResponse {

    /**
     * requestId : null
     * errorLog : null
     * status : 200
     * message : 查询物流信息成功
     * data : {"Site":"www.zto.com","CourierPhone":"17712654784","ShipperCode":"ZTO","Traces":[{"AcceptStation":"【宁波市】 【宁波海曙】（0574-83895351、0574-83895358） 的 螃蟹巷（15990200353） 已揽收","AcceptTime":"2021-07-19 16:28:33"},{"AcceptStation":"【宁波市】 快件已经到达 【宁波海曙】","AcceptTime":"2021-07-19 19:04:07"},{"AcceptStation":"【宁波市】 快件离开 【宁波海曙】 已发往 【苏州中转部】","AcceptTime":"2021-07-19 19:04:12"},{"AcceptStation":"【宁波市】 快件已经到达 【宁波中转部】","AcceptTime":"2021-07-19 20:33:49"},{"AcceptStation":"【宁波市】 快件离开 【宁波中转部】 已发往 【苏州中转部】","AcceptTime":"2021-07-19 20:35:19"},{"AcceptStation":"【苏州市】 快件已经到达 【苏州中转部】","AcceptTime":"2021-07-20 04:02:02"},{"AcceptStation":"【苏州市】 快件离开 【苏州中转部】 已发往 【苏州吴中九部】","AcceptTime":"2021-07-20 05:18:22"},{"AcceptStation":"【苏州市】 快件已到达 【苏州吴中九部】（0512-68235125）,业务员 孙鹏（17712654784） 正在第1次派件, 请保持电话畅通,并耐心等待（95720为中通快递员外呼专属号码，请放心接听）","AcceptTime":"2021-07-20 08:43:27"},{"AcceptStation":"【苏州市】 快件已被【菜鸟的苏州建发独墅湾85栋物业店】代收，如有问题请电联（9519666），感谢使用中通快递，期待再次为您服务！","AcceptTime":"2021-07-20 12:13:16"},{"AcceptStation":"【苏州市】 您的快递已签收, 签收人在【菜鸟的苏州建发独墅湾85栋物业店】领取。如有疑问请电联:（17712654784）, 投诉电话:（9519666）, 您的快递已经妥投。风里来雨里去, 只为客官您满意。上有老下有小, 赏个好评好不好？【请在评价快递员处帮忙点亮五颗星星哦~】","AcceptTime":"2021-07-20 19:10:22"}],"takeTime":"1天2小时41分","updateTime":"2021-07-20 19:10:22","Success":true,"Reason":"查询成功","Name":"中通快递","Logo":"https://img3.fegine.com/express/zto.jpg","LogisticCode":"75488742571441","State":"3","Phone":"95311","Courier":""}
     */

    private Object requestId;
    private Object errorLog;
    private int status;
    private String message;
    /**
     * Site : www.zto.com
     * CourierPhone : 17712654784
     * ShipperCode : ZTO
     * Traces : [{"AcceptStation":"【宁波市】 【宁波海曙】（0574-83895351、0574-83895358） 的 螃蟹巷（15990200353） 已揽收","AcceptTime":"2021-07-19 16:28:33"},{"AcceptStation":"【宁波市】 快件已经到达 【宁波海曙】","AcceptTime":"2021-07-19 19:04:07"},{"AcceptStation":"【宁波市】 快件离开 【宁波海曙】 已发往 【苏州中转部】","AcceptTime":"2021-07-19 19:04:12"},{"AcceptStation":"【宁波市】 快件已经到达 【宁波中转部】","AcceptTime":"2021-07-19 20:33:49"},{"AcceptStation":"【宁波市】 快件离开 【宁波中转部】 已发往 【苏州中转部】","AcceptTime":"2021-07-19 20:35:19"},{"AcceptStation":"【苏州市】 快件已经到达 【苏州中转部】","AcceptTime":"2021-07-20 04:02:02"},{"AcceptStation":"【苏州市】 快件离开 【苏州中转部】 已发往 【苏州吴中九部】","AcceptTime":"2021-07-20 05:18:22"},{"AcceptStation":"【苏州市】 快件已到达 【苏州吴中九部】（0512-68235125）,业务员 孙鹏（17712654784） 正在第1次派件, 请保持电话畅通,并耐心等待（95720为中通快递员外呼专属号码，请放心接听）","AcceptTime":"2021-07-20 08:43:27"},{"AcceptStation":"【苏州市】 快件已被【菜鸟的苏州建发独墅湾85栋物业店】代收，如有问题请电联（9519666），感谢使用中通快递，期待再次为您服务！","AcceptTime":"2021-07-20 12:13:16"},{"AcceptStation":"【苏州市】 您的快递已签收, 签收人在【菜鸟的苏州建发独墅湾85栋物业店】领取。如有疑问请电联:（17712654784）, 投诉电话:（9519666）, 您的快递已经妥投。风里来雨里去, 只为客官您满意。上有老下有小, 赏个好评好不好？【请在评价快递员处帮忙点亮五颗星星哦~】","AcceptTime":"2021-07-20 19:10:22"}]
     * takeTime : 1天2小时41分
     * updateTime : 2021-07-20 19:10:22
     * Success : true
     * Reason : 查询成功
     * Name : 中通快递
     * Logo : https://img3.fegine.com/express/zto.jpg
     * LogisticCode : 75488742571441
     * State : 3
     * Phone : 95311
     * Courier :
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
        private String Site;
        private String CourierPhone;
        private String ShipperCode;
        private String takeTime;
        private String updateTime;
        private boolean Success;
        private String Reason;
        private String Name;
        private String Logo;
        private String LogisticCode;
        private String State;
        private String Phone;
        private String Courier;
        /**
         * AcceptStation : 【宁波市】 【宁波海曙】（0574-83895351、0574-83895358） 的 螃蟹巷（15990200353） 已揽收
         * AcceptTime : 2021-07-19 16:28:33
         */

        private List<TracesBean> Traces;

        public String getSite() {
            return Site;
        }

        public void setSite(String Site) {
            this.Site = Site;
        }

        public String getCourierPhone() {
            return CourierPhone;
        }

        public void setCourierPhone(String CourierPhone) {
            this.CourierPhone = CourierPhone;
        }

        public String getShipperCode() {
            return ShipperCode;
        }

        public void setShipperCode(String ShipperCode) {
            this.ShipperCode = ShipperCode;
        }

        public String getTakeTime() {
            return takeTime;
        }

        public void setTakeTime(String takeTime) {
            this.takeTime = takeTime;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public boolean isSuccess() {
            return Success;
        }

        public void setSuccess(boolean Success) {
            this.Success = Success;
        }

        public String getReason() {
            return Reason;
        }

        public void setReason(String Reason) {
            this.Reason = Reason;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public String getLogo() {
            return Logo;
        }

        public void setLogo(String Logo) {
            this.Logo = Logo;
        }

        public String getLogisticCode() {
            return LogisticCode;
        }

        public void setLogisticCode(String LogisticCode) {
            this.LogisticCode = LogisticCode;
        }

        public String getState() {
            return State;
        }

        public void setState(String State) {
            this.State = State;
        }

        public String getPhone() {
            return Phone;
        }

        public void setPhone(String Phone) {
            this.Phone = Phone;
        }

        public String getCourier() {
            return Courier;
        }

        public void setCourier(String Courier) {
            this.Courier = Courier;
        }

        public List<TracesBean> getTraces() {
            return Traces;
        }

        public void setTraces(List<TracesBean> Traces) {
            this.Traces = Traces;
        }

        public static class TracesBean {
            private String AcceptStation;
            private String AcceptTime;

            public String getAcceptStation() {
                return AcceptStation;
            }

            public void setAcceptStation(String AcceptStation) {
                this.AcceptStation = AcceptStation;
            }

            public String getAcceptTime() {
                return AcceptTime;
            }

            public void setAcceptTime(String AcceptTime) {
                this.AcceptTime = AcceptTime;
            }
        }
    }
}
