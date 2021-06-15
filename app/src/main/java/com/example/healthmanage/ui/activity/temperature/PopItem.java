package com.example.healthmanage.ui.activity.temperature;

public class PopItem {
    private String name;
    private boolean isSelect;

    public PopItem(String name, boolean isSelect) {
        this.name = name;
        this.isSelect = isSelect;
    }

    public PopItem(String name) {
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
