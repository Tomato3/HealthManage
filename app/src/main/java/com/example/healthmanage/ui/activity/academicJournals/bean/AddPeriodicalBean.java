package com.example.healthmanage.ui.activity.academicJournals.bean;

public class AddPeriodicalBean {

    /**
     * deliveryColumn : 投稿栏目
     * content : 富文本内容
     * periodical : 投稿期刊
     * personalProfile : 个人简介
     * status : 0
     * systemUserId : 78
     * title : 标题
     */

    private String deliveryColumn;
    private String content;
    private String periodical;
    private String personalProfile;
    private int status;
    private int systemUserId;
    private String title;

    public String getDeliveryColumn() {
        return deliveryColumn;
    }

    public void setDeliveryColumn(String deliveryColumn) {
        this.deliveryColumn = deliveryColumn;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPeriodical() {
        return periodical;
    }

    public void setPeriodical(String periodical) {
        this.periodical = periodical;
    }

    public String getPersonalProfile() {
        return personalProfile;
    }

    public void setPersonalProfile(String personalProfile) {
        this.personalProfile = personalProfile;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getSystemUserId() {
        return systemUserId;
    }

    public void setSystemUserId(int systemUserId) {
        this.systemUserId = systemUserId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
