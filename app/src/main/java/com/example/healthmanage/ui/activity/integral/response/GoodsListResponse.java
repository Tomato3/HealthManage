package com.example.healthmanage.ui.activity.integral.response;

import java.util.List;

/**
 * desc:
 * date:2021/7/16 10:44
 * author:bWang
 */
public class GoodsListResponse {

    /**
     * requestId : null
     * errorLog : null
     * status : 0
     * message : 成功
     * data : [{"id":1,"name":"按摩仪","url":"https://ss0.baidu.com/94o3dSag_xI4khGko9WTAnF6hhy/zhidao/pic/item/cb8065380cd791230f4870a5ac345982b3b780b3.jpg","detailsPoster":"https://img2.baidu.com/it/u=1070003001,653753576&fm=26&fmt=auto&gp=0.jpg,https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fcdn.duitang.com%2Fuploads%2Fitem%2F201409%2F08%2F20140908130732_kVXzh.jpeg&refer=http%3A%2F%2Fcdn.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1628127534&t=983a5c2bcdf696972c62926ab4e3d74f","marketPrice":999,"point":200,"stock":94,"logisticsType":0,"quantityLimit":0,"levelLimit":4,"quantity":null,"quantityPerDay":1,"details":null,"isShow":1,"exchangeQuantity":6,"delFlag":0},{"id":2,"name":null,"url":null,"detailsPoster":null,"marketPrice":null,"point":20000,"stock":null,"logisticsType":null,"quantityLimit":0,"levelLimit":4,"quantity":null,"quantityPerDay":null,"details":null,"isShow":1,"exchangeQuantity":0,"delFlag":0},{"id":3,"name":null,"url":null,"detailsPoster":null,"marketPrice":null,"point":6000,"stock":null,"logisticsType":null,"quantityLimit":0,"levelLimit":4,"quantity":null,"quantityPerDay":null,"details":null,"isShow":1,"exchangeQuantity":0,"delFlag":0}]
     */

    private Object requestId;
    private Object errorLog;
    private int status;
    private String message;
    /**
     * id : 1
     * name : 按摩仪
     * url : https://ss0.baidu.com/94o3dSag_xI4khGko9WTAnF6hhy/zhidao/pic/item/cb8065380cd791230f4870a5ac345982b3b780b3.jpg
     * detailsPoster : https://img2.baidu.com/it/u=1070003001,653753576&fm=26&fmt=auto&gp=0.jpg,https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fcdn.duitang.com%2Fuploads%2Fitem%2F201409%2F08%2F20140908130732_kVXzh.jpeg&refer=http%3A%2F%2Fcdn.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1628127534&t=983a5c2bcdf696972c62926ab4e3d74f
     * marketPrice : 999
     * point : 200
     * stock : 94
     * logisticsType : 0
     * quantityLimit : 0
     * levelLimit : 4
     * quantity : null
     * quantityPerDay : 1
     * details : null
     * isShow : 1
     * exchangeQuantity : 6
     * delFlag : 0
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
        private Object details;
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

        public Object getDetails() {
            return details;
        }

        public void setDetails(Object details) {
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
