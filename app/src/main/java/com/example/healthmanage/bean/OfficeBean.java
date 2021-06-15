package com.example.healthmanage.bean;

public class OfficeBean {
    private String officeName;
    private String officeId;
    private boolean isSelect;

    public OfficeBean() {
    }

    public OfficeBean(String officeName, boolean isSelect) {
        this.officeName = officeName;
        this.isSelect = isSelect;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public String getOfficeId() {
        return officeId;
    }

    public void setOfficeId(String officeId) {
        this.officeId = officeId;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
