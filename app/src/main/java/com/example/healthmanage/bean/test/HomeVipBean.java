package com.example.healthmanage.bean.test;

/**
 * Created by sunguiyong on 2020/12/23
 */
public
class HomeVipBean {
    private String name;
    private String level;

    public HomeVipBean(String name, String level) {
        this.name = name;
        this.level = level;
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
}
