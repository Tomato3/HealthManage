package com.example.healthmanage.ui.activity.memberdetail.response;

public class HealthResponse {
    private String bushu;
    private String xiaohao;
    private String gaoya;
    private String diya;
    private String xueyang;
    private String maibo;
    private String xuetang;
    private String tiwen;
    private String heartrate;

    public HealthResponse() {
    }

    public HealthResponse(String bushu, String xiaohao, String gaoya, String diya, String xueyang, String maibo, String xuetang, String tiwen, String heartrate) {
        this.bushu = bushu;
        this.xiaohao = xiaohao;
        this.gaoya = gaoya;
        this.diya = diya;
        this.xueyang = xueyang;
        this.maibo = maibo;
        this.xuetang = xuetang;
        this.tiwen = tiwen;
        this.heartrate = heartrate;
    }

    public String getBushu() {
        return bushu;
    }

    public String getXiaohao() {
        return xiaohao;
    }

    public String getGaoya() {
        return gaoya;
    }

    public String getDiya() {
        return diya;
    }

    public String getXueyang() {
        return xueyang;
    }

    public String getMaibo() {
        return maibo;
    }

    public String getXuetang() {
        return xuetang;
    }

    public String getTiwen() {
        return tiwen;
    }

    public String getHeartrate() {
        return heartrate;
    }
}
