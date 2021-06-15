package com.example.healthmanage.ui.activity.memberdetail.bean;

public class BreathRateBean {
    private int breathRate;
    private String time;

    public BreathRateBean(int breathRate, String time) {
        this.breathRate = breathRate;
        this.time = time;
    }

    public int getBreathRate() {
        return breathRate;
    }

    public String getTime() {
        return time;
    }
}
