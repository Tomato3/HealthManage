package com.example.healthmanage.ui.activity.integral.response;

import java.io.Serializable;

/**
 * desc:
 * date:2021/7/19 15:01
 * author:bWang
 */
public class AddressBean implements Serializable {

    /**
     * locationId : 1
     * name : 邱琦雯
     * phone : 18888888887
     * address : 内蒙古自治区-呼和浩特市-新城区
     * detailedAddress : 宏智大厦5089
     */

    private String locationId;
    private String name;
    private String phone;
    private String address;
    private String detailedAddress;

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
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
}
