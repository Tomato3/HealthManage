package com.example.healthmanage.ui.activity.memberdetail.bean;

public class HeartRateBean {
    private int heartRate;
    private String time;

    public HeartRateBean(int heartRate, String time) {
        this.heartRate = heartRate;
        this.time = time;
    }

    public int getHeartRate() {
        return heartRate;
    }

    public String getTime() {
        return time;
    }
}
