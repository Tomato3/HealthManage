package com.example.healthmanage.ui.activity.integral.response;

/**
 * desc:
 * date:2021/7/23 11:58
 * author:bWang
 */
public class OrderInfoResponse {

    /**
     * requestId : null
     * errorLog : null
     * status : 0
     * message : 成功
     * data : {"id":15,"userId":78,"orderNumber":"2021072116504069075304","locationId":1,"exchangeName":"按摩仪","stock":93,"point":400,"exchangeQuantity":1,"exchangeTime":"2021-07-21 16:50:40","orderStatus":1,"name":"zhang","phone":"135655462455","address":"江苏省苏州市工业园区","detailedAddress":"宏志大厦508","goodsId":1,"deliverTime":null,"logisticsType":null,"courierNumber":null,"courierCompany":null,"courierCompanyAbbr":null,"delFlag":0,"appGoods":{"id":1,"name":"按摩仪","url":"https://ss0.baidu.com/94o3dSag_xI4khGko9WTAnF6hhy/zhidao/pic/item/cb8065380cd791230f4870a5ac345982b3b780b3.jpg","detailsPoster":"http://119.23.187.176:8083/picture/330a2ae6707317b6ec1ced3b8a7c987a.jpg,http://119.23.187.176:8083/picture/080664505161ba9a7939fd088c343fd8.png,http://10.1.20.166:8080/picture/026dafc79f9be6cae234271af524c3ce.jpeg","marketPrice":999,"point":200,"stock":91,"logisticsType":0,"quantityLimit":0,"levelLimit":4,"quantity":null,"quantityPerDay":10,"details":"<p><img src=\"https://7478-tx-cloud-mix-mall-d6944c-1302673523.tcb.qcloud.la/1595658739458599036.jpg\" alt=\"\" /><\/p><p><img src=\"https://7478-tx-cloud-mix-mall-d6944c-1302673523.tcb.qcloud.la/1595658730372852130.jpg\" alt=\"\" width=\"790\" height=\"969\" /><\/p><p><img src=\"https://7478-tx-cloud-mix-mall-d6944c-1302673523.tcb.qcloud.la/1595658747670893241.jpg\" alt=\"\" width=\"790\" height=\"969\" /><\/p>","isShow":1,"exchangeQuantity":9,"delFlag":0}}
     */

    private Object requestId;
    private Object errorLog;
    private int status;
    private String message;
    /**
     * id : 15
     * userId : 78
     * orderNumber : 2021072116504069075304
     * locationId : 1
     * exchangeName : 按摩仪
     * stock : 93
     * point : 400
     * exchangeQuantity : 1
     * exchangeTime : 2021-07-21 16:50:40
     * orderStatus : 1
     * name : zhang
     * phone : 135655462455
     * address : 江苏省苏州市工业园区
     * detailedAddress : 宏志大厦508
     * goodsId : 1
     * deliverTime : null
     * logisticsType : null
     * courierNumber : null
     * courierCompany : null
     * courierCompanyAbbr : null
     * delFlag : 0
     * appGoods : {"id":1,"name":"按摩仪","url":"https://ss0.baidu.com/94o3dSag_xI4khGko9WTAnF6hhy/zhidao/pic/item/cb8065380cd791230f4870a5ac345982b3b780b3.jpg","detailsPoster":"http://119.23.187.176:8083/picture/330a2ae6707317b6ec1ced3b8a7c987a.jpg,http://119.23.187.176:8083/picture/080664505161ba9a7939fd088c343fd8.png,http://10.1.20.166:8080/picture/026dafc79f9be6cae234271af524c3ce.jpeg","marketPrice":999,"point":200,"stock":91,"logisticsType":0,"quantityLimit":0,"levelLimit":4,"quantity":null,"quantityPerDay":10,"details":"<p><img src=\"https://7478-tx-cloud-mix-mall-d6944c-1302673523.tcb.qcloud.la/1595658739458599036.jpg\" alt=\"\" /><\/p><p><img src=\"https://7478-tx-cloud-mix-mall-d6944c-1302673523.tcb.qcloud.la/1595658730372852130.jpg\" alt=\"\" width=\"790\" height=\"969\" /><\/p><p><img src=\"https://7478-tx-cloud-mix-mall-d6944c-1302673523.tcb.qcloud.la/1595658747670893241.jpg\" alt=\"\" width=\"790\" height=\"969\" /><\/p>","isShow":1,"exchangeQuantity":9,"delFlag":0}
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
        private int id;
        private int userId;
        private String orderNumber;
        private int locationId;
        private String exchangeName;
        private int stock;
        private int point;
        private int exchangeQuantity;
        private String exchangeTime;
        private int orderStatus;
        private String name;
        private String phone;
        private String address;
        private String detailedAddress;
        private int goodsId;
        private String deliverTime;
        private int logisticsType;
        private Object courierNumber;
        private Object courierCompany;
        private Object courierCompanyAbbr;
        private int delFlag;
        /**
         * id : 1
         * name : 按摩仪
         * url : https://ss0.baidu.com/94o3dSag_xI4khGko9WTAnF6hhy/zhidao/pic/item/cb8065380cd791230f4870a5ac345982b3b780b3.jpg
         * detailsPoster : http://119.23.187.176:8083/picture/330a2ae6707317b6ec1ced3b8a7c987a.jpg,http://119.23.187.176:8083/picture/080664505161ba9a7939fd088c343fd8.png,http://10.1.20.166:8080/picture/026dafc79f9be6cae234271af524c3ce.jpeg
         * marketPrice : 999
         * point : 200
         * stock : 91
         * logisticsType : 0
         * quantityLimit : 0
         * levelLimit : 4
         * quantity : null
         * quantityPerDay : 10
         * details : <p><img src="https://7478-tx-cloud-mix-mall-d6944c-1302673523.tcb.qcloud.la/1595658739458599036.jpg" alt="" /></p><p><img src="https://7478-tx-cloud-mix-mall-d6944c-1302673523.tcb.qcloud.la/1595658730372852130.jpg" alt="" width="790" height="969" /></p><p><img src="https://7478-tx-cloud-mix-mall-d6944c-1302673523.tcb.qcloud.la/1595658747670893241.jpg" alt="" width="790" height="969" /></p>
         * isShow : 1
         * exchangeQuantity : 9
         * delFlag : 0
         */

        private AppGoodsBean appGoods;

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

        public String getOrderNumber() {
            return orderNumber;
        }

        public void setOrderNumber(String orderNumber) {
            this.orderNumber = orderNumber;
        }

        public int getLocationId() {
            return locationId;
        }

        public void setLocationId(int locationId) {
            this.locationId = locationId;
        }

        public String getExchangeName() {
            return exchangeName;
        }

        public void setExchangeName(String exchangeName) {
            this.exchangeName = exchangeName;
        }

        public int getStock() {
            return stock;
        }

        public void setStock(int stock) {
            this.stock = stock;
        }

        public int getPoint() {
            return point;
        }

        public void setPoint(int point) {
            this.point = point;
        }

        public int getExchangeQuantity() {
            return exchangeQuantity;
        }

        public void setExchangeQuantity(int exchangeQuantity) {
            this.exchangeQuantity = exchangeQuantity;
        }

        public String getExchangeTime() {
            return exchangeTime;
        }

        public void setExchangeTime(String exchangeTime) {
            this.exchangeTime = exchangeTime;
        }

        public int getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(int orderStatus) {
            this.orderStatus = orderStatus;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getDetailedAddress() {
            return detailedAddress;
        }

        public void setDetailedAddress(String detailedAddress) {
            this.detailedAddress = detailedAddress;
        }

        public int getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
        }

        public String getDeliverTime() {
            return deliverTime;
        }

        public void setDeliverTime(String deliverTime) {
            this.deliverTime = deliverTime;
        }

        public int getLogisticsType() {
            return logisticsType;
        }

        public void setLogisticsType(int logisticsType) {
            this.logisticsType = logisticsType;
        }

        public Object getCourierNumber() {
            return courierNumber;
        }

        public void setCourierNumber(Object courierNumber) {
            this.courierNumber = courierNumber;
        }

        public Object getCourierCompany() {
            return courierCompany;
        }

        public void setCourierCompany(Object courierCompany) {
            this.courierCompany = courierCompany;
        }

        public Object getCourierCompanyAbbr() {
            return courierCompanyAbbr;
        }

        public void setCourierCompanyAbbr(Object courierCompanyAbbr) {
            this.courierCompanyAbbr = courierCompanyAbbr;
        }

        public int getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(int delFlag) {
            this.delFlag = delFlag;
        }

        public AppGoodsBean getAppGoods() {
            return appGoods;
        }

        public void setAppGoods(AppGoodsBean appGoods) {
            this.appGoods = appGoods;
        }

        public static class AppGoodsBean {
            private int id;
            private String name;
            private String url;
            private String detailsPoster;
            private int marketPrice;
            private int point;
            private int stock;
            private int logisticsType;
            private int quantityLimit;
            private int levelLimit;
            private Object quantity;
            private int quantityPerDay;
            private String details;
            private int isShow;
            private int exchangeQuantity;
            private int delFlag;

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

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getDetailsPoster() {
                return detailsPoster;
            }

            public void setDetailsPoster(String detailsPoster) {
                this.detailsPoster = detailsPoster;
            }

            public int getMarketPrice() {
                return marketPrice;
            }

            public void setMarketPrice(int marketPrice) {
                this.marketPrice = marketPrice;
            }

            public int getPoint() {
                return point;
            }

            public void setPoint(int point) {
                this.point = point;
            }

            public int getStock() {
                return stock;
            }

            public void setStock(int stock) {
                this.stock = stock;
            }

            public int getLogisticsType() {
                return logisticsType;
            }

            public void setLogisticsType(int logisticsType) {
                this.logisticsType = logisticsType;
            }

            public int getQuantityLimit() {
                return quantityLimit;
            }

            public void setQuantityLimit(int quantityLimit) {
                this.quantityLimit = quantityLimit;
            }

            public int getLevelLimit() {
                return levelLimit;
            }

            public void setLevelLimit(int levelLimit) {
                this.levelLimit = levelLimit;
            }

            public Object getQuantity() {
                return quantity;
            }

            public void setQuantity(Object quantity) {
                this.quantity = quantity;
            }

            public int getQuantityPerDay() {
                return quantityPerDay;
            }

            public void setQuantityPerDay(int quantityPerDay) {
                this.quantityPerDay = quantityPerDay;
            }

            public String getDetails() {
                return details;
            }

            public void setDetails(String details) {
                this.details = details;
            }

            public int getIsShow() {
                return isShow;
            }

            public void setIsShow(int isShow) {
                this.isShow = isShow;
            }

            public int getExchangeQuantity() {
                return exchangeQuantity;
            }

            public void setExchangeQuantity(int exchangeQuantity) {
                this.exchangeQuantity = exchangeQuantity;
            }

            public int getDelFlag() {
                return delFlag;
            }

            public void setDelFlag(int delFlag) {
                this.delFlag = delFlag;
            }
        }
    }
}
