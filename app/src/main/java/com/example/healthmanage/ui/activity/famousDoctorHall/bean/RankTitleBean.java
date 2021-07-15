package com.example.healthmanage.ui.activity.famousDoctorHall.bean;

/**
 * desc:
 * date:2021/7/8 14:31
 * author:bWang
 */
public class RankTitleBean {
    private String name;
    private boolean isSelect;

    public RankTitleBean(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
