package com.example.healthmanage.bean.test;

/**
 * Created by sunguiyong on 2020/12/23
 */
public
class HomeVipBean {
    private String name;
    private String level;
    private int vipImg;

    public HomeVipBean(String name, String level, int vipImg) {
        this.name = name;
        this.level = level;
        this.vipImg = vipImg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getVipImg() {
        return vipImg;
    }

    public void setVipImg(int vipImg) {
        this.vipImg = vipImg;
    }
}
