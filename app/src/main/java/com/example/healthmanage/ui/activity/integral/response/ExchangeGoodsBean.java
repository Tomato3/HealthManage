package com.example.healthmanage.ui.activity.integral.response;

/**
 * desc:
 * date:2021/7/20 11:40
 * author:bWang
 */
public class ExchangeGoodsBean {

    /**
     * address : string
     * detailedAddress : string
     * exchangeQuantity : 1
     * goodsId : 1
     * phone : string
     * point : 400
     * userId : 78
     * name : zhang
     * locationId : 1
     */

    private String address;
    private String detailedAddress;
    private int exchangeQuantity;
    private int goodsId;
    private String phone;
    private int point;
    private int userId;
    private String name;
    private int locationId;

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

    public int getExchangeQuantity() {
        return exchangeQuantity;
    }

    public void setExchangeQuantity(int exchangeQuantity) {
        this.exchangeQuantity = exchangeQuantity;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
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

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }
}
