package com.example.healthmanage.ui.activity.temperature.response;

public class TransferBean {

    /**
     * id : 50
     * systemUserId : 25
     * transferTime : 2021-04-28 16:05:00
     */

    private int id;
    private int systemUserId;
    private String transferTime;
    private String token;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSystemUserId() {
        return systemUserId;
    }

    public void setSystemUserId(int systemUserId) {
        this.systemUserId = systemUserId;
    }

    public String getTransferTime() {
        return transferTime;
    }

    public void setTransferTime(String transferTime) {
        this.transferTime = transferTime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
